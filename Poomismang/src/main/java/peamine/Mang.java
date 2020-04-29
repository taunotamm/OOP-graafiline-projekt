package peamine;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.shape.*;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.paint.Color.BLACK;

public class Mang extends Application{
    public Stage peaLava;
    public Kasutaja kasutaja;

    @Override
    public void start(Stage peaLava) throws IOException {

        this.kasutaja = new Kasutaja("", 0);
        this.peaLava = peaLava;

        Scene algus = algus();
        peaLava.setTitle("Poomismäng");  // lava tiitelribale pannakse tekst

        peaLava.setScene(algus);  // lavale lisatakse stseen
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

    public Scene mang() throws IOException {

        // Vaja luua start ekraan, peale mida toimub kõik järgmine

        String sõna = Peaklass.juhuslikSõna(andmebaas.loebSisse("sonad.txt"));
        System.out.println(sõna);


        Group juur = new Group(); // luuakse juur
        Canvas lõuend = new Canvas(600, 600); // luuakse lõuend

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

        // Kuvame ekraani alumises osas kasutaja andmed
        Label kasutajaNimi = new Label();
        kasutajaNimi.setText("Kasutajanimi: " + kasutaja.getNimi() + "\t");
        Label punktid = new Label();
        punktid.setText("Punktid: " + String.valueOf(kasutaja.getPunktid()));

        HBox kasutajaAndmed = new HBox();
        kasutajaAndmed.getChildren().addAll(kasutajaNimi, punktid);


        // Tegelema kasutaja sisendiga
        ArrayList<String> pakutud = new ArrayList<>();

        String[] kasutajaValik = new String[1];
        nupp.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
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
                        Scene loppStseen = lopp();
                        this.peaLava.setScene(loppStseen);

                    }
                    System.out.println(stringBuilder.toString());
                    vastus.setText(stringBuilder.toString());

                    if(sõna.contains(kasutajaValik[0])){
                        System.out.println("arvasid tähe ära");
                        kasutaja.setPunktid(kasutaja.getPunktid()+5);
                    }else{
                        kasutaja.setPunktid(kasutaja.getPunktid()-1);
                    }

                    // Uuendame kasutaja punktide andmeid
                    kasutajaNimi.setText("Kasutajanimi: " + kasutaja.getNimi() + "\t");
                    punktid.setText("Punktid: " + String.valueOf(kasutaja.getPunktid()));

                }else{
                    jubaPakutud.setText("See täht on juba sisestatud," +
                            " palun sisesta uus täht!");
                    // Loome erindi mis ütleb kasutajale, et see täht on juba sisestatud
                }
                tekstiVäli.setText("");
            });

        //Drawing a Rectangle
        Rectangle rectangle = new Rectangle();

        //Setting the properties of the rectangle
        rectangle.setWidth(300.0);
        rectangle.setHeight(150.0);
        rectangle.setFill(BLACK);

        Line joon = new Line();
        joon.setStartX(80);
        joon.setStartY(0);
        joon.setEndX(80);
        joon.setEndY(150);


        hBox.getChildren().addAll(tekstiVäli, nupp);
        vBox.getChildren().addAll(jubaPakutud, hBox, joon, vastus, kasutajaAndmed);

        juur.getChildren().add(vBox);  // lõuend lisatakse juure alluvaks
        Scene mangStseen = new Scene(juur);  // luuakse stseen

        return mangStseen;

    }

    public Scene algus(){
        Stage algusLava = new Stage();
        Label nimeLabel = new Label("Sisesta oma nimi");
        TextField nimi = new TextField("");
        Button alustaButton = new Button("Alusta mängu");

        alustaButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    try {
                        kasutaja.setNimi(nimi.getText());
                        this.peaLava.setScene(mang());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(nimeLabel, nimi, alustaButton);
        vBox.setAlignment(Pos.CENTER);

        Scene algusStseen = new Scene(vBox);
        this.peaLava.setScene(algusStseen);
        return algusStseen;
    }


    //lopp() on loodud viimase ekraani jaoks, kui kõik küsimused on läbitud
    public Scene lopp(){

        //Loome uue lava
        Stage lopp_lava = new Stage();

        Button sulgeButton = new Button("Välju");
        Button uuestiButton = new Button("Mängi uuesti");

        //Asetame teksti ja nupu üksteiste peale
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(uuestiButton, sulgeButton);

        //Kui vajutatakse nuppu välju siis pannakse rakendus kinni
        sulgeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> System.exit(0));

        uuestiButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    try {
                        this.peaLava.setScene(mang());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        //Tagastame stseeni
        Scene stseen2 = new Scene(vBox);
        return stseen2;
    }
}