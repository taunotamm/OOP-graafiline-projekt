package peamine;

import java.io.*;
import java.util.ArrayList;

public class andmebaas {
    public static ArrayList<String> loebSisse(String failinimi) throws IOException {
        ArrayList<String> sõnad = new ArrayList<>();
        try(BufferedReader faililugeja = new BufferedReader(new InputStreamReader(new FileInputStream(failinimi)))){
            while (true){
                String rida = faililugeja.readLine();
                if (rida == null)
                    break;
                if(!sõnad.contains(rida))
                    sõnad.add(rida);
            }
        }
        return sõnad;
    }
}
