package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;


public class Stan extends Artikl implements Nekretnina {

    private static final Logger logger = LoggerFactory.getLogger(Nekretnina.class);
    private int Kvadratura;

    public Stan(Long id, String naziv, String opis, int kvadratura, BigDecimal cijena,Stanje stanje) {
        super(id, naziv, opis, cijena,new ArrayList<Recenzija>(),stanje);
        Kvadratura = kvadratura;
    }

    public int getKvadratura() {
        return Kvadratura;
    }

    public void setKvadratura(int kvadratura) {
        Kvadratura = kvadratura;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stan stan)) return false;
        return Kvadratura == stan.Kvadratura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Kvadratura);
    }

    @Override
    public String tekstOglasa() {
        BigDecimal porez = izracunajPorez(getCijena());
        String porezInfo;
        if (porez == null) {
            porezInfo = "Cijena nekretnine ne smije biti niža od 10000kn.";
        } else {
            porezInfo = "Porez na nekretninu iznosi: " + porez;
        }


        return "\nNaslov nekretnine: " + getNaziv() +
                "\nOpis nekretnine: " + getOpis() +
                "\nKvadratura nekretnine: " + getKvadratura() +
                "\nStanje nekretnine: "+getStanje()+
                "\nPorez na nekretnine: " + porezInfo +
                "\nCijena nekretnine: " + getCijena();
    }

    @Override
    public void obradiIznimku(CijenaJePreniskaException e) {
        logger.error("Greška prilikom izračunavanja poreza na nekretninu", e);
    }
}


