import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Peaklass {


    public static String juhuslikSõna (ArrayList<String> sõnad){
        int sõnadeArvListiIndeksina = sõnad.size() - 1;
        return sõnad.get((int) (Math.random()*sõnadeArvListiIndeksina));
    }


    public static void main(String[] args) throws IOException {
        ArrayList<String> sõnad = andmebaas.loebSisse("sonad.txt");
        System.out.println(sõnad);
        System.out.println(juhuslikSõna(sõnad));

    }
}
