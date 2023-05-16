package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prodaja extends Entitet implements Serializable {

    private Artikl artikl;
    private Korisnik korisnik;
    private LocalDate datumObjave;

    public Prodaja(Long id, Artikl artikl, Korisnik korisnik, LocalDate datumObjave) {
        super(id);
        this.artikl = artikl;
        this.korisnik = korisnik;
        this.datumObjave = datumObjave;
    }

    public Artikl getArtikl() {
        return artikl;
    }

    public void setArtikl(Artikl artikl) {
        this.artikl = artikl;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public LocalDate getDatumObjave() {
        return this.datumObjave;
    }

    public String getDatumObjaveUTekstualnomObliku(){
        LocalDate datumObjaveOglasa = getDatumObjave();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = datumObjaveOglasa.format(dateTimeFormatter);

        return formattedDate;
    }

    public void setDatumObjave(LocalDate datumObjave) {
        this.datumObjave = datumObjave;
    }
}
