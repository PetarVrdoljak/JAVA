package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Usluga extends Artikl {

    public Usluga(Long id, String naziv, String opis, BigDecimal cijena,Stanje stanje) {
        super(id, naziv, opis, cijena,new ArrayList<Recenzija>(),stanje);
    }


    @Override
    public String tekstOglasa() {
        return "\nNaslov usluge: "+getNaziv()+"\nOpis usluge: "+getOpis()+
                "\nStanje usluge: "+getStanje()+
                "\nCijena usluge: "+getCijena();
    }

}
