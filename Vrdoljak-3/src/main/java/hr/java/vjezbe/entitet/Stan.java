package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

import java.math.BigDecimal;

public class Stan extends Artikl implements Nekretnina {

    private int kvadratura ;

    public Stan(String naslov, String opis, BigDecimal cijena, int kvadratura) {
        super(naslov, opis, cijena);
        this.kvadratura = kvadratura;
    }

    public int getKvadratura() {
        return kvadratura;
    }

    public void setKvadratura(int kvadratura) {
        this.kvadratura = kvadratura;
    }
    @Override
    public BigDecimal izracunajPorez(BigDecimal cijena) {
        BigDecimal porez = cijena.multiply(new BigDecimal("0.03"));
        if(cijena.compareTo(new BigDecimal("10000"))<0){
            throw new CijenaJePreniskaException("Cijena nekretnine je preniska! ");
        }
        return porez;
    }

    @Override
    public String tekstOglasa(){
        return String.format("%s, %d m2, %s, %s - Porez: %s", getNaslov(),kvadratura, getOpis(),getCijena()
                ,izracunajPorez(getCijena()));
    }


}
