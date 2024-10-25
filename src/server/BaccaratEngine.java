package server;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class BaccaratEngine {
    
public BaccaratEngine(){
}

// creats an arraylist of x decks' worth of cards and shuffles it
public ArrayList<String> newDecks(int noOfDecks){
    ArrayList<String> decks = new ArrayList<>();
    for (int i=1; i <= noOfDecks; i++){
        for (int s=1; s < 5; s++){
            String sToString = String.valueOf(s);
            for (int v=1; v < 14; v++){
                String vToString = String.valueOf(v);
                decks.add(vToString + "." + sToString);
            }
        }
    }
    // shuffles it
    Collections.shuffle(decks);    
    return decks;
}

// runs 1 round of baccarat
public void run(File file) throws FileNotFoundException, IOException{
    ArrayList<String> cards = new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            cards.add(line);
        }

        if (cards.size() < 4){
            System.err.println("insufficient cards in deck!");
        }

        
    } 
}




}