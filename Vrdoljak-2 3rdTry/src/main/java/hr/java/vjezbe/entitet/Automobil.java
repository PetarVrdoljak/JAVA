package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class Automobil extends Artikl implements Vozilo{
    private BigDecimal snagaKs;

    public Automobil(String naslov, String opis, BigDecimal cijena, BigDecimal snagaKs) {
        super(naslov, opis, cijena);
        this.snagaKs = snagaKs;
    }

    public BigDecimal getSnagaKs() {
        return snagaKs;
    }

    public void setSnagaKs(BigDecimal snagaKs) {
        this.snagaKs = snagaKs;
    }

    @Override
    public BigDecimal izracunajKw(BigDecimal snagaKs) {
        BigDecimal kw = snagaKs.multiply(new BigDecimal("0.735499"));
        return kw;
    }

    @Override
    public int izracunajGrupuOsiguranja() {
        BigDecimal snagaKw = izracunajKw(snagaKs);
        int group;
        if (snagaKw.compareTo(new BigDecimal("50")) <= 0) {
            group = 1;
        } else if (snagaKw.compareTo(new BigDecimal("100")) <= 0) {
            group = 2;
        } else if (snagaKw.compareTo(new BigDecimal("150")) <= 0) {
            group = 3;
        } else if (snagaKw.compareTo(new BigDecimal("200")) <= 0) {
            group = 4;
        } else {
            group = 5;
        }
        return group;
    }

    @Override
    public BigDecimal izracunajCijenuOsiguranja() {
        int group = izracunajGrupuOsiguranja();
        BigDecimal price;
        switch (group) {
            case 1:
                price = new BigDecimal("500");
                break;
            case 2:
                price = new BigDecimal("800");
                break;
            case 3:
                price = new BigDecimal("1200");
                break;
            case 4:
                price = new BigDecimal("2000");
                break;
            default:
                price = new BigDecimal("3000");
        }
        return price;
    }

    @Override
    public String tekstOglasa() {
        String ad = null;
        ad += "Snaga: " + snagaKs + "KS\n";
        ad += "Pretvorba: " +izracunajKw(snagaKs)+ "\n";
        ad += "Grupa osiguranja: " + izracunajGrupuOsiguranja() + "\n";
        ad += "Cijena osiguranja: " + izracunajCijenuOsiguranja() + "\n";
        return ad;
    }
   /* @Override
    public String tekstOglasa() {
        BigDecimal snagaKw = izracunajKw(snagaKs);
        BigDecimal cijenaOsiguranja = izracunajCijenuOsiguranja();
        return "Naslov: "+getNaslov()+ " \n" + "Opis: "+getOpis()+ " \n" + "Cijena: " + getCijena()
                + "\n" + "Konjske snage: "+getSnagaKs()+ "\n" + "Snaga u KW: "+snagaKw + " \n" + "Osiguranje: "
                + cijenaOsiguranja;
    }

    @Override
    public BigDecimal izracunajKw(BigDecimal konjskaSnaga) {
        BigDecimal pretvorbaKsuKw = konjskaSnaga.multiply(BigDecimal.valueOf(0.7457));
        return pretvorbaKsuKw;
    }

    @Override
    public BigDecimal izracunajGrupuOsiguranja() {
        int kw = izracunajKw(snagaKs).intValue();
        int group = switch (kw) {
            case 0, 1, 2, 3, 4, 5 -> 1;
            case 6, 7, 8, 9, 10, 11 -> 2;
            case 12, 13, 14, 15, 16, 17 -> 3;
            case 18, 19, 20, 21, 22, 23 -> 4;
            default -> 5;
        };
        return new BigDecimal(group);
    }
    @Override
    public BigDecimal izracunajCijenuOsiguranja() {
        BigDecimal cijenaOsiguranja;

        switch (izracunajGrupuOsiguranja().intValue()) {
            case 1:
                cijenaOsiguranja = getCijena().multiply(new BigDecimal("0.02"));
                break;
            case 2:
                cijenaOsiguranja = getCijena().multiply(new BigDecimal("0.03"));
                break;
            case 3:
                cijenaOsiguranja = getCijena().multiply(new BigDecimal("0.04"));
                break;
            case 4:
                cijenaOsiguranja = getCijena().multiply(new BigDecimal("0.05"));
                break;
            default:
                cijenaOsiguranja = BigDecimal.ZERO;
                break;
        }

        return cijenaOsiguranja.setScale(2, BigDecimal.ROUND_HALF_UP);
    }*/
}
