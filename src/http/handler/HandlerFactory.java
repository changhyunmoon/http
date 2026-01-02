package http.handler;

public class HandlerFactory {
    public static HttpHandler getHandler(String requestLine){
        if(requestLine == null || requestLine.isEmpty()){
            return null;
        }

        String[] parts = requestLine.split(" ");
        if(parts.length<3){
            //버전 정보가 없으면 HTTP 0.9로 간주
            //System.out.println("Http 0.9 수신");
            return new Http09Handler();
        }

        String version = parts[2].toUpperCase();
        if(version.contains("HTTP/1.0")){
            //System.out.println("Http 1.0 수신");
            return new Http10Handler();
        }else if(version.contains("HTTP/2.0")){
            //return new Http20Handler();
            return new Http20Handler();
        }

        //기본값은 0.9
        return new Http09Handler();
    }
}
