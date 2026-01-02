package handler;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Http10Handler implements HttpHandler {
    @Override
    public void handle(String requestLine, BufferedReader reader, OutputStream out) throws Exception {
        System.out.println("Handling as HTTP 1.0: " + requestLine);

        // 1. 요청 헤더 읽기 (HTTP 1.0은 빈 줄이 나올 때까지가 헤더)
        String headerLine;
        while ((headerLine = reader.readLine()) != null && !headerLine.isEmpty()) {
            System.out.println("Header: " + headerLine);
        }

        // 2. 응답 본문 준비
        String body = "<html><body><h1>Response from HTTP 1.0</h1><p>Request: " + requestLine + "</p></body></html>";
        byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);

        // 3. HTTP 1.0 응답 조립 (상태 라인 + 헤더 + 빈 줄 + 본문)
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.0 200 OK\r\n"); // 상태 라인
        response.append("Content-Type: text/html; charset=utf-8\r\n");
        response.append("Content-Length: ").append(bodyBytes.length).append("\r\n");
        response.append("Date: ").append(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)).append("\r\n");
        response.append("\r\n"); // 헤더와 본문을 구분하는 빈 줄 (필수!)

        // 4. 전송
        out.write(response.toString().getBytes(StandardCharsets.UTF_8));
        out.write(bodyBytes);
        out.flush();

        // HTTP 1.0은 요청 처리 후 연결을 닫는 것이 기본 원칙입니다.
    }
}
