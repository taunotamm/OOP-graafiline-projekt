/**
 * Kasutaja klass kasutaja loomiseks
 *
 * Autorid: Tauno Tamm ja Eero Ääremaa
 */

package peamine;

public class Kasutaja {

    // Isendiväljad kasutajanime ja punktide salvestamiseks
    private String nimi;
    private int punktid;

    // Loome konstruktori ning get ja set meetodid

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