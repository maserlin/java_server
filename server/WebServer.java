
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
            server.createContext("/PIXI", new PixiHandler());
//            server.createContext("/PIXI2", new Pixi2Handler());
			server.setExecutor(null);
			server.start();
		}
		catch(IOException e){
			// --
		}
	}

    static class PixiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println("Pixi engine received");

            String vfr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
            "<PlaceBetResponse gameId=\"1\"><Jackpots/><Balances><Balance amount=\"398.70\" category=\"TOTAL\" currency=\"GBP\" name=\"Total\"/><Balance amount=\"398.70\" category=\"CASH\" currency=\"GBP\" name=\"Cash\"/></Balances><Outcome balance=\"398.70\"><Spin layout=\"0\" maxWin=\"false\" position=\"2,7,12,5,29\" spinWin=\"0.70\" stake=\"2.00\" symbols=\"6,2,0,2,1,11,9,6,0,1,2,7,11,2,3\"><Winlines><Winline count=\"3\" id=\"7\" symbol=\"2\" symbols=\"2,2,9,1,2\" win=\"7\"/></Winlines></Spin><DrawState drawId=\"0\" state=\"betting\"><Bet pick=\"\" seq=\"0\" stake=\"2.00\" type=\"L\" won=\"pending\"/></DrawState></Outcome></PlaceBetResponse>";
            String response = vfr;//"<PlaceBetResponse =\"win\" pos=\"0,0,0,0,0\" />";

            t.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
            t.getResponseHeaders().set("Access-Control-Allow-Headers", "cache-control, content-type, if-none-match, pragma");
            t.getResponseHeaders().set("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
            t.getResponseHeaders().set("Access-Control-Allow-Origin", "http://localhost:8080");
            t.sendResponseHeaders(200, response.length());

            BufferedReader reader = new BufferedReader(new InputStreamReader(t.getRequestBody()));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            System.out.println(out.toString());

            System.out.println("Responding with " + response);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

/*
	static class Pixi2Handler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
            System.out.println("Pixi2 engine received ");

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
*/








}