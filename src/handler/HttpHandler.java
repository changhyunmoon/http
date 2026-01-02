package handler;

import java.io.BufferedReader;
import java.io.OutputStream;

public interface HttpHandler {
    void handle(String requestLine, BufferedReader reader, OutputStream out) throws Exception;
}
