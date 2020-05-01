/**
 * Mängu klass. Selles klassis toimub mängu stseenide loomine ja mängu interaktiivse osa loomine
 *
 * Autorid: Tauno Tamm ja Eero Ääremaa
 */

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

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static peamine.Peaklass.lisaSõna;

public class Mang extends Application {

    // Järgmised avalikud isendiväljad on loodud:
    public Stage peaLava;       // lava muutmiseks
    public Kasutaja kasutaja;   // kasutaja andmete jälgimiseks.

    @Override
    public void start(Stage peaLava) throws IOException {

        // Loome uue kasutaja ja pealava
        this.kasutaja = new Kasutaja("", 0);
        this.peaLava = peaLava;

        // Loome uue stseeni "algus" ja kuvame selle
        Scene algus = algus();
        peaLava.setTitle("Guess the word");  // lava tiitelribale pannakse tekst

        peaLava.setScene(algus);  // lavale lisatakse stseen
        peaLava.show();  // lava tehakse nähtavaks
    }


    // Abimeetod, mis piirab kasutaja sisendi maksimaalset pikkust
    // Inspiratsioon: https://stackoverflow.com/questions/16538849/how-to-use-javafx-textfield-maxlength/33191834
    public static void sisendiPikkusePiiraja(final TextField tf, final int maxPikkus) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String vanaSõne, final String uusSõne) {
                if (tf.getText().length() > maxPikkus) {
                    String s = tf.getText().substring(0, maxPikkus);
                    tf.setText(s);
                }
            }
        });
    }

    // Mängu stseeni loomine
    public Scene mang() throws IOException {

        // Määrame pealava ehk ekraani suuruse, 250px x 250px
        peaLava.setWidth(250);
        peaLava.setHeight(250);

        // Valime juhuslikult uue sõne
        String sõna = Peaklass.juhuslikSõna(andmebaas.loebSisse("sonad.txt"));
        // Testimiseks saab järgmise rea maha kommenteerida ja näha käsurealt mis sõna valiti
        //System.out.println(sõna);


        Group juur = new Group(); // luuakse juur
        Canvas lõuend = new Canvas(250, 250); // luuakse lõuend

        // Peamine verikaalne layout
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        // Horisontallne layout
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);


        // Loome tekstivälja, kuhu kasutaja saab pakkumisi edastada
        TextField tekstiVäli = new TextField();
        tekstiVäli.setText("Enter a character");

        // Piirame kasutaja sisendin pikkuse ühe tähe peale
        sisendiPikkusePiiraja(tekstiVäli, 1);

        // Nupp tähe pakkumiseks
        Button nupp = new Button();
        nupp.setText("Enter");

        // Tagasiside kasutajale
        Label jubaPakutud = new Label();
        jubaPakutud.setText("Guess");

        // Kuvame kasutajale arvatava sõna pikkuse
        Label äraArvatud = new Label();
        Label vastus = new Label();
        vastus.setText("_".repeat(sõna.length()));

        // Kuvame ekraani alumises osas kasutaja andmed
        Label kasutajaNimi = new Label();
        kasutajaNimi.setText("Name: " + kasutaja.getNimi() + "\t");
        Label punktid = new Label();
        punktid.setText("Points: " + String.valueOf(kasutaja.getPunktid()));

        // Kuvame kasutaja andmed horisontaalselt
        HBox kasutajaAndmed = new HBox();
        kasutajaAndmed.getChildren().addAll(kasutajaNimi, punktid);
        kasutajaAndmed.setAlignment(Pos.BOTTOM_CENTER);


        // Tegelema kasutaja sisendiga
        ArrayList<String> pakutud = new ArrayList<>();

        // Loome kasutaja sisendiks massiivi ühe elemendiga
        String[] kasutajaValik = new String[1];

        // Kui kasutaja vajutab pakkumise nuppu siis toimub järgmine
        nupp.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {

                    // Võtame kasutaja sisendi tekstiväljast
                    kasutajaValik[0] = tekstiVäli.getText();

                    // Kui sellist tähte pole veel pakutud siis teeme järgmist
                    if (!pakutud.contains(kasutajaValik[0])) {

                        // Lisame pakutud tähtede listi hetkel pakutud tähe
                        pakutud.add(kasutajaValik[0]);
                        jubaPakutud.setText("Guess");

                        // Loome kasutajale tagasisideks ära arvatud tähtedest sõne, kus arvamata tähtede asemel on "_"
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < sõna.length(); i++) {
                            if (pakutud.contains(String.valueOf(sõna.charAt(i)))) {
                                stringBuilder.append(sõna.charAt(i));
                            } else {
                                stringBuilder.append('_');
                            }
                        }

                        // Kui kasutaja on arvanud ära terve sõna siis anname kasutajale lõppekraani
                        if (stringBuilder.toString().equals(sõna)) {
                            Scene loppStseen = lopp();
                            this.peaLava.setScene(loppStseen);
                        }

                        //System.out.println(stringBuilder.toString());

                        // Määrame vastusteks uuendatud vastuse
                        vastus.setText(stringBuilder.toString());

                        // Kui sõnast arvati üks täht ära siis anname kasutajale tagasiside ja lisame 5 punkti
                        // Kui arvati valesti siis lahutame punkti ja anname tagasisidet
                        if (sõna.contains(kasutajaValik[0])) {
                            äraArvatud.setText("You guessed right! You received 5 points.");
                            kasutaja.setPunktid(kasutaja.getPunktid() + 5);
                        } else {
                            äraArvatud.setText("Wrong guess! You lost 1 point.");
                            kasutaja.setPunktid(kasutaja.getPunktid() - 1);
                        }

                        // Uuendame kasutaja punktide andmeid
                        kasutajaNimi.setText("Name: " + kasutaja.getNimi() + "\t");
                        punktid.setText("Points: " + String.valueOf(kasutaja.getPunktid()));

                    // Kui kasutaja on juba selle tähe pakkunud siis anname kasutajale selle kohta tagasisidet
                    } else {
                        jubaPakutud.setText("This character has been entered already," +
                                " take another guess!");
                    }

                    // Lõpuks tühejendame tekstivälja
                    tekstiVäli.setText("");
                });


        // Lisame soovitud elemendid ja kuvame need ekraanile
        hBox.getChildren().addAll(tekstiVäli, nupp);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(jubaPakutud, hBox, äraArvatud, vastus, kasutajaAndmed);

        vBox.setAlignment(Pos.CENTER);
        juur.getChildren().add(vBox);  // lõuend lisatakse juure alluvaks
        Scene mangStseen = new Scene(juur);  // luuakse stseen

        // Tagastame mängu stseeni
        return mangStseen;

    }


    // Avakuva loomine
    public Scene algus() {

        // Loome uue lava ja lisame sinna elemndid
        Stage algusLava = new Stage();
        Label nimeLabel = new Label("Enter your name");
        TextField nimi = new TextField("");
        Button alustaButton = new Button("Play");

        // Kui kasutaja vajutab alusta siis toiumub järgmine
        alustaButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    try {
                        // Määrame kasutajanimeks kasutaja poolt antud nime
                        kasutaja.setNimi(nimi.getText());
                        // Anname kasutajale mängu steeni, kus mäng toimub
                        peaLava.setScene(mang());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        // Loome elemendid, millega kasutaja saab uue sõna lisada
        Label sisestaInfo = new Label("Add a word longer than 7 letters");
        Button lisaSõna = new Button();
        lisaSõna.setText("Submit");
        TextField uusSõna = new TextField("Enter a word");
        Label kinnitus = new Label("");
        HBox lisamine = new HBox();
        lisamine.setAlignment(Pos.BOTTOM_CENTER);
        lisamine.getChildren().addAll(lisaSõna, uusSõna);

        // Kui kasutaja on vajutanud nuppu uue sõna lisamiseks
        lisaSõna.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {

                    // Võtame kasutaja sisendi ja salvestame selle muutujasse
                    String sisend = uusSõna.getText();

                    // Lisame sõna ja anname kasutajale tagasisidet
                    try {
                        lisaSõna("sonad.txt", sisend);
                        uusSõna.setText("Enter a new word");
                        kinnitus.setText("Success!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


        // Lisame elemendid stseeni
        VBox vBox = new VBox();
        vBox.getChildren().addAll(nimeLabel, nimi, alustaButton, sisestaInfo, lisamine, kinnitus);
        vBox.setAlignment(Pos.CENTER);

        Scene algusStseen = new Scene(vBox);
        peaLava.setHeight(250);
        peaLava.setWidth(250);
        // Määrame stseeniks algussteeni
        peaLava.setScene(algusStseen);

        // Tagastame stseeni
        return algusStseen;
    }


    //lopp() on loodud viimase ekraani jaoks, kui kõik küsimused on läbitud
    public Scene lopp() {

        //Loome uue lava
        Stage lopp_lava = new Stage();

        Label skoor = new Label();
        skoor.setText(String.valueOf("Total points: " + kasutaja.getPunktid() + " points"));

        Button sulgeButton = new Button("Exit");
        Button uuestiButton = new Button("Next word");

        //Asetame teksti ja nupu üksteiste peale
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(skoor, uuestiButton, sulgeButton);

        //Kui vajutatakse nuppu välju siis pannakse rakendus kinni ja kirutatakse tulemused faili.
        sulgeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    try (DataOutputStream failiVäljnd = new DataOutputStream(new FileOutputStream("Viimane_tulemus.txt"))) {
                        failiVäljnd.writeUTF("Viimane tulemus:" + "\n");
                        failiVäljnd.writeUTF("Nimi: " + kasutaja.getNimi() + "\n");
                        failiVäljnd.writeUTF("Punktid: " + String.valueOf(kasutaja.getPunktid()) + "\n");
                        failiVäljnd.writeUTF("Kuupäev: " + String.valueOf(LocalDateTime.now()));
                    } catch (IOException e) {
                    }


                    System.exit(0);
                });

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