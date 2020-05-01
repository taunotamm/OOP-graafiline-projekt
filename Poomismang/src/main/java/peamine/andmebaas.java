/**
 * Andmebaasi klass, loodud anmete siis lugemiseks
 *
 * Autorid: Tauno Tamm ja Eero Ääremaa
 */

package peamine;

import java.io.*;
import java.util.ArrayList;

public class andmebaas {

    // Meetod faili sisse lugemiseks
    public static ArrayList<String> loebSisse(String failinimi) throws IOException {
        ArrayList<String> sõnad = new ArrayList<>();
        try (BufferedReader faililugeja = new BufferedReader(new InputStreamReader(new FileInputStream(failinimi)))) {
            while (true) {
                String rida = faililugeja.readLine();
                if (rida == null)
                    break;

                // Kui sõna pole veel listis siis lisame selle
                // See on selleks, kui kasutaja on ühte sõna korduvalt lisanud siis selle suvaliset
                // valimise tõenäosus ei suureneks
                if (!sõnad.contains(rida))
                    sõnad.add(rida);
            }
        }

        // Tagastame sõnade listi
        return sõnad;
    }
}
