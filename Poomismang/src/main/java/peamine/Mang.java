package peamine;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class Mang extends Application{

    @Override
    public void start(Stage peaLava) throws IOException {

        // Vaja luua start ekraan, peale mida toimub kõik järgmine

        String sõna = Peaklass.juhuslikSõna(andmebaas.loebSisse("sonad.txt"));
        System.out.println(sõna);


        Group juur = new Group(); // luuakse juur
        Canvas lõuend = new Canvas(550, 400); // luuakse lõuend

        // Peamine verikaalne layout
        VBox vBox = new VBox();

        // Horisontallne layout
        HBox hBox = new HBox();


        TextField tekstiVäli = new TextField();
        tekstiVäli.setText("Sisesta täht");
        addTextLimiter(tekstiVäli, 1);

        Button nupp = new Button();
        nupp.setText("Paku");

        Label jubaPakutud = new Label();
        jubaPakutud.setText("Paku mõni täht");

        Label vastus = new Label();
        vastus.setText("_".repeat(sõna.length()));


        // Tegelema kasutaja sisendiga
        ArrayList<String> pakutud = new ArrayList<>();

        String[] kasutajaValik = new String[1];
        nupp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                kasutajaValik[0] = tekstiVäli.getText();
                if(!pakutud.contains(kasutajaValik[0])){
                    pakutud.add(kasutajaValik[0]);
                    jubaPakutud.setText("Paku mõni täht");

                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < sõna.length(); i++) {
                       if(pakutud.contains(String.valueOf(sõna.charAt(i)))){
                           stringBuilder.append(sõna.charAt(i));
                       }else {
                           stringBuilder.append('_');
                       }
                    }


                    if(stringBuilder.toString().equals(sõna)){
                        // Mine end screenile - sa võitsid vmidagi
                    }
                    System.out.println(stringBuilder.toString());
                    vastus.setText(stringBuilder.toString());

                    if(sõna.contains(kasutajaValik[0])){
                        System.out.println("arvasid tähe ära");
                    }
                }else{
                    jubaPakutud.setText("See täht on juba sisestatud," +
                            " palun sisesta uus täht!");
                    // Loome erindi mis ütleb kasutajale, et see täht on juba sisestatud
                }
                tekstiVäli.setText("");
            }
        });

        hBox.getChildren().addAll(tekstiVäli, nupp);
        vBox.getChildren().addAll(jubaPakutud, hBox, vastus);

        juur.getChildren().add(vBox);  // lõuend lisatakse juure alluvaks
        Scene stseen1 = new Scene(juur);  // luuakse stseen
        peaLava.setTitle("Poomismäng");  // lava tiitelribale pannakse tekst
        peaLava.setScene(stseen1);  // lavale lisatakse stseen
        peaLava.show();  // lava tehakse nähtavaks
    }


    // Kopeeritud  - vaja tõlkida/ viidata vmidagi
    //https://stackoverflow.com/questions/16538849/how-to-use-javafx-textfield-maxlength/33191834
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
}
