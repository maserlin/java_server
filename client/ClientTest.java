import java.net.*;
import java.io.*;
import java.io.Console;

public class ClientTest{

	public static void main(String [] args)throws IOException {
		Socket socket;
	PrintWriter out;
BufferedReader in;
		try{
			socket = new Socket("Think-PC",4444);
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
Console cin = System.console();
String line;
while(true){
line = cin.readLine();
		out.println( line );

	String recd="";
	if((line=in.readLine())!=null)System.out.println(line);
	else if(line.indexOf("Bye.")!= -1)System.exit(1);
}
		}
		catch(UnknownHostException e){
			System.out.println("host error");
			System.exit(1);
		}
		catch(IOException e){
			System.out.println("IO Error");
			System.exit(1);
		}



	}




}