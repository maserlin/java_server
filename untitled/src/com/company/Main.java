package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Web server");
      		try{
      			HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
                server.createContext("/PIXI", new PixiHandler());
      			server.setExecutor(null);
      			server.start();
      		}
      		catch(IOException e){
      			// --
      		}
    }

    static class PixiHandler implements HttpHandler {
        private static final Integer WILD = 9;
        private static final Integer BONUS = 11;
        private WinObject winObject = new WinObject();
        private Random rng = new Random();

        List<List<Integer>> reels_0 =Arrays.asList(Arrays.asList(7, 5, 3, 2, 0, 1, 3, 0, 2, 2, 0, 1, 3, 0, 2, 4, 5, 6, 7, 0, 4, 1, 0, 2, 3, 1, 8, 2, 4, 1, 0, 3, 2, 1, 0, 4, 6, 5, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 1),
        Arrays.asList(1, 4, 5, 1, 6, 5, 0, 2, 1, 2, 0, 1, 3, 0, 2, 0, 3, 4, 0, 2, 3, 7, 6, 1, 4, 0, 3, 1, 2, 6, 7, 2, 1, 0, 4, 1, 0, 0, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 3),
        Arrays.asList(3, 1, 7, 5, 3, 0, 4, 1, 2, 0, 1, 3, 0, 2, 6, 5, 0, 1, 2, 0, 3, 2, 1, 3, 8, 2, 9, 8, 4, 0, 1, 3, 0, 2, 1, 4, 2, 5, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 7),
        Arrays.asList(0, 3, 2, 4, 1, 0, 3, 2, 0, 4, 3, 0, 1, 0, 2, 2, 0, 1, 3, 0, 2, 3, 0, 7, 6, 5, 1, 6, 5, 7, 2, 1, 0, 4, 1, 0, 0, 2, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 1),
        Arrays.asList(0, 8, 4, 0, 1, 8, 0, 2, 0, 1, 3, 0, 4, 2, 2, 0, 1, 3, 0, 2, 0, 1, 3, 2, 6, 7, 5, 1, 7, 5, 6, 1, 0, 3, 1, 0, 4, 2, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 0));

        List<List<Integer>> reels_1 = Arrays.asList(Arrays.asList(7, 5, 3, 2, 0, 1, 3, 0, 2, 2, 0, 1, 3, 0, 2, 4, 5, 6, 7, 0, 4, 1, 0, 2, 3, 1, 8, 2, 4, 1, 0, 3, 2, 1, 0, 4, 6, 5, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 1),
            Arrays.asList(1, 4, 5, 1, 6, 5, 0, 2, 1, 2, 0, 1, 3, 0, 2, 0, 3, 4, 0, 2, 3, 7, 6, 1, 4, 0, 3, 1, 2, 6, 7, 2, 1, 0, 4, 1, 0, 0, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 3),
            Arrays.asList(11, 1, 7, 5, 3, 0, 4, 1, 2, 0, 1, 3, 0, 2, 6, 5, 0, 1, 2, 0, 3, 2, 1, 3, 8, 2, 9, 8, 4, 0, 1, 3, 0, 2, 1, 4, 2, 5, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 7),
            Arrays.asList(0, 3, 2, 4, 1, 0, 3, 2, 0, 4, 3, 0, 1, 0, 2, 2, 0, 1, 3, 0, 2, 3, 0, 7, 6, 5, 1, 6, 5, 7, 2, 1, 0, 4, 1, 0, 0, 2, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 1),
            Arrays.asList(0, 8, 4, 0, 1, 8, 0, 2, 0, 1, 3, 0, 4, 2, 2, 0, 1, 3, 0, 2, 0, 1, 3, 2, 6, 7, 5, 1, 7, 5, 6, 1, 0, 3, 1, 0, 4, 2, 7, 5, 3, 2, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 0));


        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println("Pixi engine received request " + t.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(t.getRequestBody()));
            StringBuilder requestXML = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestXML.append(line);
            }

            if(requestXML.length() == 0){
                t.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
                t.getResponseHeaders().set("Access-Control-Allow-Headers", "cache-control, content-type, if-none-match, pragma");
                t.getResponseHeaders().set("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
                t.getResponseHeaders().set("Access-Control-Allow-Origin", "http://localhost:8080");
                String response = "";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
            else {
                System.out.println("Received Request: " + requestXML.toString());
                String response = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                    "<PlaceBetResponse gameId=\"1\"><Jackpots/><Balances><Balance amount=\"398.70\" category=\"TOTAL\" currency=\"GBP\" name=\"Total\"/><Balance amount=\"398.70\" category=\"CASH\" currency=\"GBP\" name=\"Cash\"/></Balances><Outcome balance=\"398.70\">" +
                    getSpinResponse(requestXML) + //"<Spin layout=\"0\" maxWin=\"false\" position=\"2,7,12,5,29\" spinWin=\"0.70\" stake=\"2.00\" symbols=\"6,2,0,2,1,11,9,6,0,1,2,7,11,2,3\"><Winlines><Winline count=\"3\" id=\"7\" symbol=\"2\" symbols=\"2,2,9,1,2\" win=\"7\"/></Winlines></Spin>" +
                    "<DrawState drawId=\"0\" state=\"betting\"><Bet pick=\"\" seq=\"0\" stake=\"2.00\" type=\"L\" won=\"pending\"/></DrawState></Outcome></PlaceBetResponse>";
                System.out.println("Responding with " + response);

                t.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
                t.getResponseHeaders().set("Access-Control-Allow-Headers", "cache-control, content-type, if-none-match, pragma");
                t.getResponseHeaders().set("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
                t.getResponseHeaders().set("Access-Control-Allow-Origin", "http://localhost:8080");
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }

        /**
         *
         * @return valid string for response
         */
        private String getSpinResponse(StringBuilder requestXML) {
            winObject = new WinObject();
            List<Integer> reelStops = new ArrayList<Integer>();
            int reelset = 0;
            List<List<Integer>> reels;

            StringBuilder response = new StringBuilder();
            response.append("<Spin ");
            String [] req = requestXML.toString().split(" ");
            float stake = 0.0f;
            int lines;
            List<Integer> foItems = new ArrayList();

            for(int item=0; item<req.length; ++item){
                String [] pair = req[item].split("=");
                //System.out.println(pair[0]);
                if(pair[0].equals("stake")){
                    pair[1] = pair[1].replaceAll("\"","");
                    stake = Float.parseFloat(pair[1]);
                    System.out.println("Stake is " + stake);
                }
                else if(pair[0].equals("winlines")){
                    pair[1] = pair[1].replaceAll("\"","");
                    lines = Integer.parseInt(pair[1]);
                    System.out.println("Lines are " + lines);
                }
                else if(pair[0].equals("foitems")){
                    pair[1] = pair[1].replaceAll("\"","");
                    if(!pair[1].equals("null")) {
                        String [] items = pair[1].split(",");
                        for(int i=0; i<items.length; ++i){
                            foItems.add(Integer.parseInt(items[i]));
                        }
                        System.out.println("foItems are " + foItems);
                    }
                }
            }

            // IF foitems, set reels and stops
            if(foItems.size() > 0){
                reelset = foItems.remove(0);
                reelStops = foItems.subList(0, foItems.size());
            }
            // else decide on bonus trigger
            else{
                int r = rng.nextInt(1);
                if( r == 0){
                    reelset = 1;
                }
            }

            // Set reels
            reels = getReels(reelset);

            //
            response.append("layout=\"" + reelset + "\" maxWin=\"false\" ");

            // If reelStops were not set by foitems
            if(reelStops.size() == 0){
                for(int r=0; r<reels.size(); ++r){
                    int rand = rng.nextInt(reels.get(r).size());
                    reelStops.add(rand);
                }

                // Trigger bonus
                if(reelset == 1){
                    List<Integer> reel = reels.get(2);
                    int index = reel.indexOf(BONUS);
                    int rand = rng.nextInt(3);
                    index = getWrappedIndex(index -1 + rand, reel);
                    reelStops.set(2, index);
                }
            }
            String stops = reelStops.toString();
            stops = stops.replace("]","");
            stops = stops.replace("[", "");
            stops = stops.replace(" ", "");
            response.append("position=\"" + stops + "\" ");
            response.append("stake=\"" + stake + "\" ");
            List<Integer> reelMap = getSymbolsInView(reels, reelStops);
            String symbolsInView = reelMap.toString();
            symbolsInView = symbolsInView.replace("]","");
            symbolsInView = symbolsInView.replace("[","");
            symbolsInView = symbolsInView.replace(" ","");
            response.append("symbols=\"" + symbolsInView + "\" ");

            calculateWins(reelMap);

            int totalWin = 0;
            if(winObject.lines.size() > 0) {
                for (int win = 0; win < winObject.lines.size(); ++win) {
                    totalWin += winObject.winAmount.get(win);
                }
            }
            response.append("spinWin=\"" + totalWin + "\">");

            if(winObject.lines.size() > 0) {
                response.append("<Winlines>");

                //<Winline count="3" id="7" symbol="2" symbols="2,2,9,1,2" win="7"/>
                for (int win = 0; win < winObject.lines.size(); ++win) {
                    System.out.println("Winning line " + winObject.lines.get(win) + " " + winObject.winline.get(win).toString());



                    response.append("<Winline count=\"" + winObject.winline.get(win).size() + "\" ");
                    response.append("id=\"" + winObject.lines.get(win) + "\" ");
                    response.append("symbol=\"" + getSymbol(winObject.winline.get(win)) + "\" ");
                    symbolsInView = winObject.winline.get(win).toString();
                    symbolsInView = symbolsInView.replace("]","");
                    symbolsInView = symbolsInView.replace("[","");
                    symbolsInView = symbolsInView.replace(" ","");
                    response.append("symbols=\"" + symbolsInView + "\" ");
                    response.append("win=\"" + winObject.winAmount.get(win) + "\">");
                    response.append("</Winline>");
                }


                response.append("</Winlines>");
            }
            response.append("</Spin>");






            return response.toString();//"<Spin layout=\"0\" maxWin=\"false\" position=\"2,7,12,5,29\" spinWin=\"0.70\" stake=\"2.00\" symbols=\"6,2,0,2,1,11,9,6,0,1,2,7,11,2,3\"><Winlines><Winline count=\"3\" id=\"7\" symbol=\"2\" symbols=\"2,2,9,1,2\" win=\"7\"/></Winlines></Spin>";
        }

        private String getSymbol(List<Integer> integers) {

            for(int s=0; s<integers.size(); ++s){
                if(integers.get(s) != WILD)return integers.get(s).toString();
            }
            return WILD.toString();
        }

        private void calculateWins(List<Integer> symbolsInView) {

            List<List<Integer>> reelMap = new ArrayList<List<Integer>>();
            reelMap.add(symbolsInView.subList(0,3));
            reelMap.add(symbolsInView.subList(3,6));
            reelMap.add(symbolsInView.subList(6,9));
            reelMap.add(symbolsInView.subList(9,12));
            reelMap.add(symbolsInView.subList(12, 15));


            List<List<Integer>> winlines = Arrays.asList(Arrays.asList(1,1,1,1,1),// 1
            Arrays.asList(0,0,0,0,0),// 2
            Arrays.asList(2,2,2,2,2),// 3
            Arrays.asList(0,1,2,1,0),// 4
            Arrays.asList(2,1,0,1,2),// 5
            Arrays.asList(0,0,1,2,2),// 6
            Arrays.asList(2,2,1,0,0),// 7
            Arrays.asList(1,0,0,0,1),// 8
            Arrays.asList(1,2,2,2,1),// 9
            Arrays.asList(1,0,1,0,1),// 10
            Arrays.asList(1,2,1,2,1),// 11
            Arrays.asList(0,1,0,1,0),// 12
            Arrays.asList(2,1,2,1,2),// 13
            Arrays.asList(1,1,0,1,1),// 14
            Arrays.asList(1,1,2,1,1),// 15
            Arrays.asList(0,1,1,1,0),// 16
            Arrays.asList(2,1,1,1,2),// 17
            Arrays.asList(0,2,0,2,0),// 18
            Arrays.asList(2,0,2,0,2),// 19
            Arrays.asList(2,2,0,2,2));// 20

            for(int line=0; line<winlines.size(); ++line)
            {
                List<Integer> winline = winlines.get(line);
                List<Integer> symbolsOnWinline = new ArrayList<Integer>();
                for(int pos=0; pos<winline.size(); ++pos){
                    symbolsOnWinline.add(reelMap.get(pos).get(winline.get(pos)));
                }
                System.out.println(symbolsOnWinline);

                analyseSymbols(line, symbolsOnWinline);
            }
        }

        /**
         * 
         * @param line
         * @param symbolsOnWinline
         */
        private void analyseSymbols(int line, List<Integer> symbolsOnWinline) {
            int count = 1;
            
            List<Integer> wilds = new ArrayList<Integer>();
            
            // Track which symbols are NOT wild!
            for(int s=0; s<symbolsOnWinline.size(); ++s){
                if(symbolsOnWinline.get(s) != WILD)wilds.add(s);
            }
            
            // Wilds win: winAmount is wilds value
            if(wilds.size() == 0){
                winObject.winline.add(symbolsOnWinline.subList(0, symbolsOnWinline.size()));
                winObject.lines.add(line);
                winObject.winAmount.add(WILD * 100);
            }
            
            // Symbol win with 4 wilds: winAmount is symbol value
            else if(wilds.size() == 1){
                winObject.winline.add(symbolsOnWinline.subList(0, symbolsOnWinline.size()));
                winObject.lines.add(line);
                winObject.winAmount.add((1+symbolsOnWinline.get(wilds.get(0))) * 50);
            }
            
            // 
            else{
                // First symbol that's not a wild
                int matchSymbol = -1;
                for(int s=0; s<symbolsOnWinline.size(); ++s){
                    if(symbolsOnWinline.get(s) != WILD)
                    {
                        matchSymbol = symbolsOnWinline.get(s);
                        break;
                    }
                }
                
                // Check all symbols against match symbol
                for(int s=1; s<symbolsOnWinline.size(); ++s)
                {
                    if(this.match(matchSymbol, symbolsOnWinline.get(s)))++count;
                    else break;
                }
                
                // 3 or more symbols match
                if(count > 2)
                {
                    winObject.winline.add(symbolsOnWinline.subList(0, count));
                    winObject.lines.add(line);
                    winObject.winAmount.add((1 + matchSymbol) * count * 10);
                }
            }
        }

        private boolean match(int matchSymbol, int s2){
            if( s2 == WILD )return true;
            if(s2 == matchSymbol)return true;
            return false;
        }



        /**
         *
         * @param reels
         * @param reelStops
         * @return
         */
        private List<Integer> getSymbolsInView(List<List<Integer>> reels, List<Integer> reelStops) {
            List<Integer> allSymbols = new ArrayList<Integer>();
            for(int reel=0; reel<reels.size(); ++reel){
                allSymbols.addAll(symbolsInView(reelStops.get(reel), reels.get(reel)));
            }
            return allSymbols;
        }


        /**
         *
         * @param index
         * @param reelband
         * @return
         */
        private List<Integer> symbolsInView(int index, List<Integer> reelband){
            List<Integer> symbols = new ArrayList<Integer>();

            symbols.add(reelband.get(getWrappedIndex(index + 1, reelband)));
            symbols.add(reelband.get(getWrappedIndex(index, reelband)));
            symbols.add(reelband.get(getWrappedIndex(index - 1, reelband)));

            return symbols;
        }


        /**
         *
         * @param newIndex
         * @param reelband
         * @return
         */
         private int getWrappedIndex(int newIndex, List<Integer> reelband){
            return (newIndex + reelband.size()) % reelband.size();
        }
        /**
         *
         * @param which
         * @return valid reelset
         */
        private List<List<Integer>> getReels(int which) {
            switch(which){
                case 0:
                    return reels_0;
                case 1:
                    return reels_1;
            }
            return null;
        }

        private class WinObject {
            public List<List<Integer>> winline;
            public List<Integer> lines;
            public List<Integer> winAmount;
            public Bonus bonus;
            public WinObject(){
                winline = new ArrayList<List<Integer>>();
                lines = new ArrayList<Integer>();
                winAmount = new ArrayList<Integer>();
                bonus = null;
            }
            public class Bonus {
                public List<Integer> wins = new ArrayList<Integer>();
            }
        }
    }
}
