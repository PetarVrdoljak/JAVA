package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Kategorija;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.Prodaja;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Glavna {
    public static void main(String[] args) {

        Scanner unosPodataka = new Scanner(System.in);
        System.out.println("Unesite broj korisnika koji zelite unijeti: ");
        Integer BROJ_KORISNIKA=unosPodataka.nextInt();
        unosPodataka.nextLine();
        Korisnik[] poljeKorisnika = new Korisnik[BROJ_KORISNIKA];
        for(int i = 0; i < BROJ_KORISNIKA; i++){
            Korisnik korisnik = unosKorisnika(unosPodataka);
            poljeKorisnika[i] = korisnik;

        }

        System.out.println("Unesite broj kategorija koji zelite unijeti: ");
        Integer BROJ_KATEGORIJA=unosPodataka.nextInt();
        unosPodataka.nextLine();
        Kategorija[] poljeKategorija = new Kategorija[BROJ_KATEGORIJA];
        for(int i = 0; i < BROJ_KATEGORIJA; i++){
            Kategorija kategorija = unosKategorije(unosPodataka);
            poljeKategorija[i] = kategorija;
        }

        System.out.println("Unesite broj artikala koji su aktivno na prodaju: ");
        Integer BROJ_PRODAJA= unosPodataka.nextInt();
        Prodaja[] poljeProdaja=new Prodaja[BROJ_PRODAJA];
        for(int i=0; i<BROJ_PRODAJA; i++){
            Prodaja prodaja= unosProdaje(unosPodataka, poljeKorisnika, poljeKategorija);
            poljeProdaja[i] = prodaja;
        }

        System.out.println("Trenutni oglasi na prodaju: ");
        for(int i=0; i<poljeProdaja.length;i++){
            System.out.println((i+1) +". artikl:");
            System.out.println("Naslov: "+poljeProdaja[i].getArtikl().getNaslov());
            System.out.println("Opis: "+poljeProdaja[i].getArtikl().getOpis());
            System.out.println("Cijena: "+poljeProdaja[i].getArtikl().getCijena());
            System.out.println("Datum objave: "+poljeProdaja[i].getDatumObjave());
            System.out.println("Kontakt: "+ poljeProdaja[i].getKorisnik().getIme()
                    + poljeProdaja[i].getKorisnik().getPrezime() + poljeProdaja[i].getKorisnik().getEmail()
                    + poljeProdaja[i].getKorisnik().getTelefon());

        }
        System.out.println("Kraj programa!");
    }

    private static Prodaja unosProdaje(Scanner unosPodataka, Korisnik[] poljeKorisnika, Kategorija[] poljeKategorija) {
        System.out.println("Odaberite korisnika: ");
        for (int j = 0; j< poljeKorisnika.length; j++){
            System.out.println((j+1)+") "+ poljeKorisnika[j].getIme());
        }
        Integer odabraniKorisnik = unosPodataka.nextInt();

        System.out.println("Odaberite kategoriju: ");
        for (int j = 0; j< poljeKategorija.length; j++){
            System.out.println((j+1)+") "+ poljeKategorija[j].getNaziv());
        }
        Integer odabranaKategorija = unosPodataka.nextInt();
        Artikl[] artiklUKategoriji = poljeKategorija[odabranaKategorija-1].getArtikl();

        System.out.println("Odaberite artikal: ");
        for (int i=0; i<artiklUKategoriji.length;i++){
            System.out.println((i+1)+") "+ artiklUKategoriji[i].getNaslov());
        }
        Integer odabraniArtikl = unosPodataka.nextInt();

        Prodaja novaProdaja = new Prodaja(artiklUKategoriji[odabraniArtikl-1],poljeKorisnika[odabraniKorisnik-1], LocalDate.now());
        return novaProdaja;
    }

    private static Korisnik unosKorisnika(Scanner unosPodataka){

        System.out.println("Unesite ime korisnika: ");
        String imeKorisnika = unosPodataka.nextLine();

        System.out.println("Unesite prezime korisnika: ");
        String prezimeKorisnika = unosPodataka.nextLine();

        System.out.println("Unesite email korisnika: ");
        String emailKorisnika = unosPodataka.nextLine();

        System.out.println("Unesite telefon korisnika: ");
        String telefonKorisnika = unosPodataka.nextLine();

        Korisnik noviKorisnik = new Korisnik(imeKorisnika,
                prezimeKorisnika, emailKorisnika, telefonKorisnika);

        return noviKorisnik;
    }
    private static Artikl unosArtikla(Scanner unosPodataka){

        System.out.println("Unesite naslov artikla: ");
        String naslovArtikla = unosPodataka.nextLine();

        System.out.println("Unesite opis artikla: ");
        String opisArtikla = unosPodataka.nextLine();

        System.out.println("Unesite cijenu artikla: ");
        BigDecimal cijenaArtikla = unosPodataka.nextBigDecimal();
        unosPodataka.nextLine();

        Artikl noviArtikl = new Artikl(naslovArtikla,opisArtikla,cijenaArtikla);
        return noviArtikl;
    }
    private static Kategorija unosKategorije(Scanner unosPodataka){

        System.out.println("Unesite naziv kategorije: ");
        String nazivKategorije = unosPodataka.nextLine();

        System.out.println("Unesite broj artikla: ");
        int brojArtikala = unosPodataka.nextInt();
        unosPodataka.nextLine();
        Artikl[] artikl = new Artikl[brojArtikala];
        for(int i = 0; i< artikl.length; i++){
            Artikl noviArtikl = unosArtikla(unosPodataka);
            artikl[i] = noviArtikl;
        }
        Kategorija novaKategorija = new Kategorija(nazivKategorije,artikl);
        return novaKategorija;
    }
}
