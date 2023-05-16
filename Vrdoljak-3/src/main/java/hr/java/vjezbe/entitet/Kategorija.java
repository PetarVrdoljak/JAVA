package hr.java.vjezbe.entitet;

public class Kategorija {
    private String naziv;
    private Artikl[] artikl;

    public Kategorija(String naziv, Artikl[] artikl) {
        this.naziv = naziv;
        this.artikl = artikl;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Artikl[] getArtikl() {
        return artikl;
    }

    public void setArtikl(Artikl[] artikl) {
        this.artikl = artikl;
    }
}
