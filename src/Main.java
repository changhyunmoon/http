import handler.HandlerFactory;
import handler.Http09Handler;
import handler.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 시작되었습니다");

            while (true) {
                try (Socket client = serverSocket.accept();
                     //클라이언트로부터 데이터를 읽기 위한 버퍼 생성
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     OutputStream out = client.getOutputStream()) {

                    //첫 줄을 미리 읽어 버전 확인
                    reader.mark(1024);
                    String requestLine = reader.readLine();

                    //팩토리를 통해 적절한 핸들러 가져오기
                    HttpHandler handler = HandlerFactory.getHandler(requestLine);

                    if (handler != null) {
                        handler.handle(requestLine, reader, out);
                    }
                } catch (Exception e) {
                    System.err.println("오류 발생 : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}