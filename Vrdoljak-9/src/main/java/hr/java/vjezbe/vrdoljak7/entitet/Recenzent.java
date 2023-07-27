package hr.java.vjezbe.vrdoljak7.entitet;

import java.util.List;

public sealed interface Recenzent permits PrivatniKorisnik, PoslovniKorisnik {

    default void ostaviRecenziju(Artikl artikl, Recenzija recenzija){
        List<Recenzija> listaRecenzijaNaOvomArtiklu = artikl.getRecenzije();
        listaRecenzijaNaOvomArtiklu.add(recenzija);
        artikl.setRecenzije(listaRecenzijaNaOvomArtiklu);
    }

}
