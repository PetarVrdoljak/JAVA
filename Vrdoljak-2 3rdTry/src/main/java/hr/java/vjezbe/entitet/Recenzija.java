package hr.java.vjezbe.entitet;

public record Recenzija(String tekst, int ocjena) {

    public Recenzija {
        if (ocjena < 1 || ocjena > 5) {
            throw new IllegalArgumentException("Ocjena recenzije mora biti između 1 i 5.");
        }
    }

    public String getTekst() {
        return tekst;
    }

    public int getOcjena() {
        return ocjena;
    }
}
