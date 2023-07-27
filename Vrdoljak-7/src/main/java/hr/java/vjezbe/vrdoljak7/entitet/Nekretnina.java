package hr.java.vjezbe.vrdoljak7.entitet;

import hr.java.vjezbe.vrdoljak7.iznimke.CijenaJePreniskaException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Nekretnina {
    default BigDecimal izracunajPorez(BigDecimal cijena) {
        try {
            if (cijena.compareTo(BigDecimal.valueOf(10000)) < 0) {
                throw new CijenaJePreniskaException("Cijena nekretnine je preniska.");
            }

            BigDecimal porez = cijena.multiply(BigDecimal.valueOf(0.03)).setScale(0, RoundingMode.HALF_UP);
            return porez;
        } catch (CijenaJePreniskaException e) {
            obradiIznimku(e);
            return null;
        }
    }

    void obradiIznimku(CijenaJePreniskaException e);
}