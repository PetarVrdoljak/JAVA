package hr.java.vjezbe.entitet;

import java.util.Arrays;
import java.util.List;

public sealed interface Recenzent permits PrivatniKorisnik, PoslovniKorisnik {

    default void ostaviRecenziju(Artikl artikl, Recenzija recenzija){
        List<Recenzija> listaRecenzijaNaOvomArtiklu = artikl.getRecenzije();
        listaRecenzijaNaOvomArtiklu.add(recenzija);
        artikl.setRecenzije(listaRecenzijaNaOvomArtiklu);
    }

}
