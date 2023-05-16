package hr.java.vjezbe.entitet;


import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class Automobil extends Artikl implements Vozilo {

    private static final Logger logger = LoggerFactory.getLogger(Automobil.class);

    private BigDecimal snagaKs;

    public Automobil(Long id,String naziv, String opis, BigDecimal snagaKs, BigDecimal cijena,Stanje stanje) {
        super(id, naziv, opis, cijena,new ArrayList<Recenzija>(),stanje);
        this.snagaKs = snagaKs;
    }

    public BigDecimal getSnagaKs() {
        return snagaKs;
    }

    public void setSnagaKs(BigDecimal snagaKs) {
        this.snagaKs = snagaKs;
    }

    @Override
    public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
        BigDecimal snagaKw = this.izracunajKw(getSnagaKs());

        if (snagaKw.compareTo(BigDecimal.valueOf(200)) >= 0) {
            throw new NemoguceOdreditiGrupuOsiguranjaException();
        }
        if (snagaKw.compareTo(BigDecimal.valueOf(50)) <= 0) {
            return BigDecimal.valueOf(1);
        } else if (snagaKw.compareTo(BigDecimal.valueOf(100)) <= 0) {
            return BigDecimal.valueOf(2);
        } else if (snagaKw.compareTo(BigDecimal.valueOf(150)) <= 0) {
            return BigDecimal.valueOf(3);
        } else if (snagaKw.compareTo(BigDecimal.valueOf(200)) < 0) {
            return BigDecimal.valueOf(4);
        } else {
            return BigDecimal.valueOf(5);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Automobil automobil)) return false;
        return Objects.equals(snagaKs, automobil.snagaKs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snagaKs);
    }

    @Override
    public String tekstOglasa() {
        String cijenaOsiguranja = "";
        try {
            BigDecimal grupaOsiguranja = izracunajGrupuOsiguranja();
            cijenaOsiguranja = izracunajCijenuOsiguranja().toString();
        } catch (NemoguceOdreditiGrupuOsiguranjaException e) {
            logger.error("Greška prilikom određivanja grupe osiguranja", e);
            cijenaOsiguranja = "Previše kw, ne mogu odrediti grupu osiguranja.";
        }


        return "\nNaslov automobila: " + getNaziv() + "\nOpis automobila: " + getOpis() + " \nSnaga automobila u KS: " + snagaKs +
                "\nSnaga automobila u kW: " + izracunajKw(snagaKs) +
                "\nStanje automobila: "+getStanje()+
                "\nIzračun osiguranja automobila: " + cijenaOsiguranja +
                "\nCijena automobila: " + getCijena();
    }
}