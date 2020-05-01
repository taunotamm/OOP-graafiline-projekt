/**
 * Peaklass programmi käivitamiseks
 *
 * Autorid: Tauno Tamm ja Eero Ääremaa
 */

package peamine;

import java.io.*;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Peaklass {


    // Abimeetod listist juhusliku sõna saamiseks
    // Anname meetodile sisse loetud sõnade listi ja valime sealt suvalise
    public static String juhuslikSõna(ArrayList<String> sõnad) {
        int sõnadeArvListiIndeksina = sõnad.size() - 1;
        return sõnad.get((int) (Math.random() * sõnadeArvListiIndeksina));
    }


    // Abimeetod sõna lisamiseks andmebaasi
    public static void lisaSõna(String failinimi, String sõne) throws IOException {

        // Kui sõna on pikem kui 7 tähemärki siis kirjutame selle faili
        if (sõne.length() > 7) {
            try (
                    FileWriter fw = new FileWriter(failinimi, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)
            ) {
                out.println(sõne);
            } catch (IOException e) {
            }
        }
    }

    // Käivitame programmi
    public static void main(String[] args) {
        launch(args);
    }
}