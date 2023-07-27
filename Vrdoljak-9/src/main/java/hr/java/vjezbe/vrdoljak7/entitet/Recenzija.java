package hr.java.vjezbe.vrdoljak7.entitet;

public record Recenzija(String tekstRecenzije, int ocjenaRecenzije) {

    public Recenzija{
        if (ocjenaRecenzije < 1 || ocjenaRecenzije > 5) {
            System.out.println("Ocjena recenzije mora biti između 1-5 !");
        }
    }
}
