
import java.net.*;
import java.io.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer{

	public static void main(String[] args){
		System.out.println("Web server");
		try{
			HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
			server.createContext("/test", new MyHandler());
			server.setExecutor(null);
			server.start();
		}
		catch(IOException e){
			// --
		}
	}

	static class MyHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
            System.out.println("rec");
			String response = "<Response><Status=\"win\" pos=\"0,0,0,0,0\"/></Response>";

            t.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
            t.getResponseHeaders().set("Access-Control-Allow-Headers","cache-control, content-type, if-none-match, pragma");
            t.getResponseHeaders().set("Allow","GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
            t.getResponseHeaders().set("Access-Control-Allow-Origin","http://localhost:8080");
			t.sendResponseHeaders(200, response.length());

			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	
	}








}