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

    public static void lisaSõna(String sõne) throws IOException {
        try(FileWriter fw = new FileWriter(sõne, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println("the text");
            //more code
            out.println("more text");
            //more code
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
