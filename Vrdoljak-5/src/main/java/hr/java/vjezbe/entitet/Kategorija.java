package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

public class Kategorija<T extends Artikl> {

    private String naziv;
    private ArrayList<T> artikli;

    public Kategorija(String naziv) {
        this.naziv = naziv;
        this.artikli = new ArrayList<>();
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

    public T dohvatiArtikl(int index) {
        return this.artikli.get(index);
    }

    public void dodajArtikl(T artikl) {
        this.artikli.add(artikl);
    }

    public List<T> dohvatiListuArtikala() {
        return this.artikli;
    }
}
