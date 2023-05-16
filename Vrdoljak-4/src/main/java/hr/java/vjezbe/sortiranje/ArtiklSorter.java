package hr.java.vjezbe.sortiranje;

import hr.java.vjezbe.entitet.Artikl;

import java.util.Comparator;

public class ArtiklSorter implements Comparator<Artikl> {

    @Override
    public int compare(Artikl o1, Artikl o2) {
        int naslovCompare = o1.getNaziv().compareTo(o2.getNaziv());
        if (naslovCompare != 0) {
            return naslovCompare;
        } else {
            return o1.getOpis().compareTo(o2.getOpis());
        }
    }
}
