package http.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

public class Http20Handler implements HttpHandler{
    @Override
    public void handle(String requestLine, BufferedReader reader, OutputStream out) throws Exception{
        // 실제 HTTP 2.0은 첫 줄 파싱 방식이 아니라
        // 연결 직후 "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n" 이라는 문자열을 확인해야 합니다.
        System.out.println("HTTP/2.0 연결 시도 감지");

        // 1. SETTINGS 프레임 전송 (서버의 설정값 전달)
        sendSettingsFrame(out);

        // 2. 바이너리 스트림 읽기 루프
        // (주의: HTTP 2.0은 BufferedReader 대신 DataInputStream 같은 바이너리 스트림이 필요함)
        while (true) {
            // 프레임 헤더 읽기 -> 타입 확인 -> 헤더 압축 해제(HPACK) -> 스트림별 처리
            // 이 과정은 매우 복잡하여 Netty나 Jetty 같은 라이브러리 사용이 권장됩니다.
        }
    }

    private void sendSettingsFrame(OutputStream out) throws IOException {
        // 빈 설정 프레임 혹은 기본값 전송 로직
    }
}
