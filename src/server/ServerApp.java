package server;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class ServerApp {
    
    public static void main(String[] args) throws IOException, FileNotFoundException{
        
        int port = Integer.parseInt(args[0]);
        int nuDecks = Integer.parseInt(args[1]);

        // write new game deck to file
        Writer w = new FileWriter("cards.db");
        BufferedWriter bw = new BufferedWriter(w);
        BaccaratEngine newGame = new BaccaratEngine();
        for (String s: newGame.newDecks(nuDecks)){
            w.write(s);
            w.write("\n");
        }
        w.close();

        ServerSocket ssocket = new ServerSocket(port);
        Socket socket = ssocket.accept();

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        bw = new BufferedWriter(osw);

        while(true){
            String[] request = br.readLine().toLowerCase().split("|");
            
            String name = null;
            File f = null;
            FileWriter fw = null;
            FileReader fr = null;

            switch (request[0]){
                
                case "login":
                name = request[1];
                f = new File(name + ".db");
                f.createNewFile();
                fw = new FileWriter(f);
                fw.write(request[2]);
                fw.close();
                break;

                case "bet":
                int betAmt = Integer.parseInt(request[1]);
                fr = new FileReader(f);
                char[] charBuffer = null;
                if (betAmt > fr.read(charBuffer)){ //check if wallet can take betAmt
                    System.err.printf("You're too poor! Current wallet value: $%s", Arrays.toString(charBuffer));
                } else {
                    int balance = Integer.parseInt(new String(charBuffer));
                    balance -= betAmt;
                    fw.write(balance);
                    System.out.printf("New wallet balance: $%d", balance);
                }
                break;

                case "deal":
                break;
            }
        }
    }
}
