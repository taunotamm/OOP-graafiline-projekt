package peamine;

public class Kasutaja {

    private String nimi;
    private int punktid;

    public Kasutaja(String nimi, int punktid) {
        this.nimi = nimi;
        this.punktid = punktid;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getPunktid() {
        return punktid;
    }

    public void setPunktid(int punktid) {
        this.punktid = punktid;
    }
}