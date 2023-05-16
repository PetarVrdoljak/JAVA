package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Artikl {
    private String naslov;
    private String opis;
    private BigDecimal cijena;
    private List<Recenzija> recenzije;

    public Artikl(String naslov, String opis, BigDecimal cijena) {
        this.naslov = naslov;
        this.opis = opis;
        this.cijena = cijena;
        this.recenzije = new ArrayList<>();
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
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
    public void dodajRecenziju(Recenzija recenzija){this.recenzije.add(recenzija);}

    public abstract String tekstOglasa() throws NemoguceOdreditiGrupuOsiguranjaException;
}
