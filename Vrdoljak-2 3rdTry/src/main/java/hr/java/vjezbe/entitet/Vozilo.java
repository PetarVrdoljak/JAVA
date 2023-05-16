package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public interface Vozilo {
    default BigDecimal izracunajKw(BigDecimal snaga){
        BigDecimal faktorKonverzije = new BigDecimal("0.7457");
        return snaga.multiply(faktorKonverzije);
    }
    default BigDecimal izracunajCijenuOsiguranja(){
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
    int izracunajGrupuOsiguranja();
    }
    /*default BigDecimal izracunajKw(BigDecimal snaga){
        BigDecimal faktorKonverzije = new BigDecimal("0.7457");
        return snaga.multiply(faktorKonverzije);
    }

    default BigDecimal izracunajGrupuOsiguranja(){
        throw new UnsupportedOperationException("Nije implementirana metoda!");
    }

    default BigDecimal izracunajCijenuOsiguranja(){
        BigDecimal grupa = izracunajGrupuOsiguranja();
        BigDecimal cijena;

        cijena = switch (Integer.valueOf(String.valueOf(grupa))){
            case 1
                 ->  new BigDecimal("2000");
            case 2
                 ->  new BigDecimal("1500");
            case 3
                 ->  new BigDecimal("1000");
            case 4
                 ->  new BigDecimal("750");
            case 5
                 ->  new BigDecimal("500");
            default
                -> throw new IllegalArgumentException("Kriva grupa osiguranja" + grupa);
        };

        return cijena;
    }
}*/
