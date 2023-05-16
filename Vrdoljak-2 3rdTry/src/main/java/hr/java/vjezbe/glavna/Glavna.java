package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;


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
            System.out.println("Unesite tip " + (i+1) + " korisnika: ");
            System.out.println("1. Privatni ");
            System.out.println("2. Poslovni ");
            System.out.print("Odabir >> ");
            Integer tipKorisnika = unosPodataka.nextInt();
            unosPodataka.nextLine();

            if(tipKorisnika == 1){
                PrivatniKorisnik privatniKorisnik = unosPrivatnogKorisnika(unosPodataka);
                poljeKorisnika[i]=privatniKorisnik;
            }
            else if(tipKorisnika == 2){
                PoslovniKorisnik poslovniKorisnik = unosPoslovnogKorisnika(unosPodataka);
                poljeKorisnika[i]=poslovniKorisnik;
            }
            else{
                System.out.println("Krivi unos.");
                i--;
            }

            /*Korisnik korisnik = unosKorisnika(unosPodataka);
            poljeKorisnika[i] = korisnik;*/

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
            System.out.println(" ");
            System.out.println(poljeProdaja[i].getArtikl().tekstOglasa());
            System.out.println("Kontakt: "+ poljeProdaja[i].getKorisnik().dohvatiKontakt());

        }
        System.out.println("Kraj programa!");
    }

    private static Prodaja unosProdaje(Scanner unosPodataka, Korisnik[] poljeKorisnika, Kategorija[] poljeKategorija) {
        System.out.println("Odaberite korisnika: ");
        for (int j = 0; j< poljeKorisnika.length; j++){
            System.out.println((j+1)+") "+ poljeKorisnika[j].dohvatiKontakt());
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

    /*private static Korisnik unosKorisnika(Scanner unosPodataka){

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
    }*/
    private static PrivatniKorisnik unosPrivatnogKorisnika(Scanner unosPodataka){
        System.out.println("Unesite ime korisnika: ");
        String imeKorisnika = unosPodataka.nextLine();

        System.out.println("Unesite prezime korisnika: ");
        String prezimeKorisnika = unosPodataka.nextLine();

        System.out.println("Unesite email korisnika: ");
        String emailKorisnika = unosPodataka.nextLine();

        System.out.println("Unesite telefon korisnika: ");
        String telefonKorisnika = unosPodataka.nextLine();

        PrivatniKorisnik noviPrivatniKorisnik = new PrivatniKorisnik(imeKorisnika,prezimeKorisnika,
                emailKorisnika,telefonKorisnika);
        return noviPrivatniKorisnik;
    }
    private static PoslovniKorisnik unosPoslovnogKorisnika(Scanner unosPodataka){
        System.out.println("Unesite naziv tvrtke: ");
        String nazivTvrtke = unosPodataka.nextLine();

        System.out.println("Unesite email tvrtke: ");
        String emailTvrtke = unosPodataka.nextLine();

        System.out.println("Unesite web tvrtke: ");
        String webTvrtke = unosPodataka.nextLine();

        System.out.println("Unesite telefon tvrtke: ");
        String telefonTvrtke = unosPodataka.nextLine();

        PoslovniKorisnik noviPoslovniKorisnik = new PoslovniKorisnik(nazivTvrtke,emailTvrtke,webTvrtke,telefonTvrtke);
        return noviPoslovniKorisnik;
    }
    /*private static Artikl unosArtikla(Scanner unosPodataka){

        System.out.println("Unesite naslov artikla: ");
        String naslovArtikla = unosPodataka.nextLine();

        System.out.println("Unesite opis artikla: ");
        String opisArtikla = unosPodataka.nextLine();

        System.out.println("Unesite cijenu artikla: ");
        BigDecimal cijenaArtikla = unosPodataka.nextBigDecimal();
        unosPodataka.nextLine();

        Artikl noviArtikl = new Artikl(naslovArtikla,opisArtikla,cijenaArtikla);
        return noviArtikl;
    }*/
    private static Usluga unosUsluge(Scanner unosPodataka){

        System.out.println("Unesite naslov oglasa usluge: ");
        String naslovOglasaUsluge = unosPodataka.nextLine();

        System.out.println("Unesite opis oglasa usluge: ");
        String opisOglasaUsluge = unosPodataka.nextLine();

        System.out.println("Unesite cijenu oglasa usluge: ");
        BigDecimal cijenaOglasaUsluge = unosPodataka.nextBigDecimal();
        unosPodataka.nextLine();

        Usluga novaUsluga = new Usluga(naslovOglasaUsluge,opisOglasaUsluge,cijenaOglasaUsluge);
        return novaUsluga;
    }
    private static Automobil unosAutomobil(Scanner unosPodataka){

        System.out.println("Unesite naslov oglasa automobila: ");
        String naslovOglasaAutomobila = unosPodataka.nextLine();

        System.out.println("Unesite opis oglasa automobila: ");
        String opisOglasaAutomobila = unosPodataka.nextLine();

        System.out.println("Unesite cijenu oglasa automobila: ");
        BigDecimal cijenaOglasaAutomobila = unosPodataka.nextBigDecimal();
        unosPodataka.nextLine();

        System.out.println("Unesite snagu (u ks) oglasa automobila: ");
        BigDecimal snagaOglasaAutomobila = unosPodataka.nextBigDecimal();
        unosPodataka.nextLine();

        Automobil noviAutomobil = new Automobil(naslovOglasaAutomobila,opisOglasaAutomobila,
                cijenaOglasaAutomobila ,snagaOglasaAutomobila);
        return noviAutomobil;
    }
    private static Kategorija unosKategorije(Scanner unosPodataka){

        System.out.println("Unesite naziv kategorije: ");
        String nazivKategorije = unosPodataka.nextLine();

        System.out.println("Unesite broj artikala koji želite unijeti za unesenu kategoriju: ");
        int brojArtikala = unosPodataka.nextInt();
        unosPodataka.nextLine();
        Artikl[] artikl = new Artikl[brojArtikala];
        for(int i = 0; i< artikl.length; i++){
            System.out.println("Unesite tip " + (i+1) + " artikla: ");
            System.out.println("1. Usluga ");
            System.out.println("2. Automobil ");
            System.out.print("Odabir >> ");
            Integer tipArtikla = unosPodataka.nextInt();
            unosPodataka.nextLine();
            if(tipArtikla == 1){
                Usluga usluga = unosUsluge(unosPodataka);
                artikl[i]=usluga;
            }
            else if(tipArtikla == 2){
                Automobil automobil = unosAutomobil(unosPodataka);
                artikl[i]=automobil;
            }
            else{
                System.out.println("Krivi unos.");
                i--;
            }
            /*Artikl noviArtikl = unosArtikla(unosPodataka);
            artikl[i] = noviArtikl;*/
        }
        Kategorija novaKategorija = new Kategorija(nazivKategorije,artikl);
        return novaKategorija;
    }
}
