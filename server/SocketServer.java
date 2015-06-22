
import java.net.*;
import java.io.*;

public class SocketServer{

    public static void main(String[] args) throws IOException {
        System.out.println("SocketServer listening on 4444");
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;
        
	try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                clientSocket.getInputStream()));
        String inputLine, outputLine;

        //out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
             outputLine = "Server received " + inputLine + " request, response is " + inputLine + " response";;
             out.println(outputLine);
		//Console cout = System.Console();
		System.out.println("Server received \"" + inputLine + "\", responding...");
             if (inputLine .equals("Bye."))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
