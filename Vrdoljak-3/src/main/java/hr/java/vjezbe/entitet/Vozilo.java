package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

import java.math.BigDecimal;

public interface Vozilo {
    default BigDecimal izracunajKw(BigDecimal snaga){
        BigDecimal faktorKonverzije = new BigDecimal("0.7457");
        return snaga.multiply(faktorKonverzije);
    }
    default BigDecimal izracunajCijenuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
        BigDecimal cijenaOsiguranja;
        BigDecimal grupaOsiguranja = BigDecimal.valueOf(izracunajGrupuOsiguranja());

        switch (grupaOsiguranja.intValue()) {
            case 1:
                cijenaOsiguranja = new BigDecimal("500");
                break;
            case 2:
                cijenaOsiguranja = new BigDecimal("700");
                break;
            case 3:
                cijenaOsiguranja = new BigDecimal("900");
                break;
            case 4:
                cijenaOsiguranja = new BigDecimal("1100");
                break;
            case 5:
                cijenaOsiguranja = new BigDecimal("1300");
                break;
            default:
                cijenaOsiguranja = new BigDecimal("0");
                break;
        }

        return cijenaOsiguranja;
    }
    int izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException;
    }

