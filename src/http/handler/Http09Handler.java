package http.handler;

import java.io.BufferedReader;
import java.io.OutputStream;

//HTTP 0.9
public class Http09Handler implements HttpHandler{
    @Override
    public void handle(String requestLine, BufferedReader reader, OutputStream out) throws Exception {
        System.out.println("Handling as HTTP 0.9: " + requestLine);
        String response = "<html><body><h1>Response from HTTP 0.9</h1></body></html>";
        out.write(response.getBytes());
        out.flush();
    }
}
