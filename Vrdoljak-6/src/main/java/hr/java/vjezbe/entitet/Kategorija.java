package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Kategorija<T extends Artikl> extends Entitet{

    private String naziv;
    private ArrayList<T> artikli;

    public Kategorija( Long id,String naziv) {
        super(id);
        this.naziv = naziv;
        this.artikli = new ArrayList<>(artikli);
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ArrayList<T> getArtikli() {
        return artikli;
    }

    public void setArtikli(ArrayList<T> artikli) {
        this.artikli = artikli;
    }

    public void dodajArtikl (T artikl){
        artikli.add(artikl);
    }
    public T dohvatiArtikle(int indeks){
        return artikli.get(indeks);
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

