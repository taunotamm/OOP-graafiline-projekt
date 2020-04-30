package peamine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import static javafx.application.Application.launch;

public class Peaklass {

    public static String juhuslikSõna (ArrayList<String> sõnad){
        int sõnadeArvListiIndeksina = sõnad.size() - 1;
        return sõnad.get((int) (Math.random()*sõnadeArvListiIndeksina));
    }

    public static void lisaSõna(String failinimi, String sõne) throws IOException {
        if(sõne.length() > 7){
            try(FileWriter fw = new FileWriter(failinimi, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(sõne);
            } catch (IOException e) {
            }
        }

    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
