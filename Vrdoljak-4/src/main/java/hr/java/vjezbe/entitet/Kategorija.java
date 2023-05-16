package hr.java.vjezbe.entitet;

import java.util.Objects;
import java.util.Set;

public class Kategorija {

    private String naziv;
    private Set<Artikl> artikli;

    public Kategorija(String naziv, Set<Artikl> artikli) {
        this.naziv = naziv;
        this.artikli = artikli;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Artikl> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikl> artikli) {
        this.artikli = artikli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kategorija that)) return false;
        return naziv.equals(that.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }
}



