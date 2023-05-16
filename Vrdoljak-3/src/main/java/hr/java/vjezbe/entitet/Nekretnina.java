package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

import java.math.BigDecimal;

public interface Nekretnina {
    default BigDecimal izracunajPorez(BigDecimal cijena) throws CijenaJePreniskaException{
        if (cijena.compareTo(BigDecimal.valueOf(10000))<0){
            throw new CijenaJePreniskaException("Cijena je preniska: " + cijena );
        }
        return cijena.multiply(BigDecimal.valueOf(0.03));
    }
}
