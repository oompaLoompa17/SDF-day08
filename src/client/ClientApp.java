package client;

import java.io.*;
import java.net.*;

public class ClientApp {
    
    public static void main(String[] args) throws IOException {
        
        String[] text = args[0].split(":");
        String host = text[0];
        int port = Integer.parseInt(text[1]);

        Socket socket = new Socket(host, port);

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        Console console = System.console();
        
        while (true){
            System.out.println("I await your next command, my liege");
            String request = console.readLine().replace(" ", "|");
            System.out.printf(request);
            bw.write(request);
            bw.flush();
        }

    }
}
