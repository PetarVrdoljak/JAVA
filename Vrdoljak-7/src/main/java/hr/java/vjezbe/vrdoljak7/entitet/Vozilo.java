package hr.java.vjezbe.vrdoljak7.entitet;

import hr.java.vjezbe.vrdoljak7.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Vozilo {

    BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException;

    default BigDecimal izracunajKw(BigDecimal konjskeSnage){
        BigDecimal koeficijent = new BigDecimal(0.745);
        return konjskeSnage.multiply(koeficijent).setScale(0, RoundingMode.HALF_UP);
    }

    default BigDecimal izracunajCijenuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
        BigDecimal grupa = izracunajGrupuOsiguranja();
        BigDecimal cijenaOsiguranja;

        switch (grupa.intValue()){
            case 1:
                cijenaOsiguranja = new BigDecimal(1000);
                break;
            case 2:
                cijenaOsiguranja = new BigDecimal(2000);
                break;
            case 3:
                cijenaOsiguranja= new BigDecimal(3000);
                break;
            case 4:
                cijenaOsiguranja= new BigDecimal(4000);
                break;
            case 5:
                cijenaOsiguranja= new BigDecimal(5000);
                break;
            default:
                cijenaOsiguranja=new BigDecimal(0);
        }
        return cijenaOsiguranja;
    }
}
