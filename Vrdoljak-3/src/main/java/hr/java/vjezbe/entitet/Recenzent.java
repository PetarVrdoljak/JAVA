package hr.java.vjezbe.entitet;

public sealed interface Recenzent permits PoslovniKorisnik, PrivatniKorisnik {
    default void ostaviRecenziju(Artikl artikl, Recenzija recenzija){
        //artikl.dodajRecenziju(recenzija);
        artikl.getRecenzije().add(recenzija);
    };
}
