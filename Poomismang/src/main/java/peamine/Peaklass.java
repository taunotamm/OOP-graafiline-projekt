package peamine;

import java.util.ArrayList;
import static javafx.application.Application.launch;

public class Peaklass {

    public static String juhuslikSõna (ArrayList<String> sõnad){
        int sõnadeArvListiIndeksina = sõnad.size() - 1;
        return sõnad.get((int) (Math.random()*sõnadeArvListiIndeksina));
    }

    public static void main(String[] args) { launch(args); }
}
