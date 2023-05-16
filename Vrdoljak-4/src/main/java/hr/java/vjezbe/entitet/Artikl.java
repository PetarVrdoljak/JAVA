package hr.java.vjezbe.entitet;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public abstract class Artikl {

    private String naziv;
    private String opis;
    private BigDecimal cijena;
    private List<Recenzija> recenzije;
    private Stanje stanje;

    public Artikl(String naziv, String opis, BigDecimal cijena, List<Recenzija> recenzije, Stanje stanje) {
        this.naziv = naziv;
        this.opis = opis;
        this.cijena = cijena;
        this.recenzije = recenzije;
        this.stanje = stanje;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public List<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(List<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Stanje getStanje() {
        return stanje;
    }

    public void setStanje(Stanje stanje) {
        this.stanje = stanje;
    }

    public abstract String tekstOglasa();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikl artikl = (Artikl) o;
        return Objects.equals(naziv, artikl.naziv) && Objects.equals(opis, artikl.opis) && Objects.equals(cijena, artikl.cijena) && Objects.equals(recenzije, artikl.recenzije) && stanje == artikl.stanje;
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, opis, cijena, recenzije, stanje);
    }
}
