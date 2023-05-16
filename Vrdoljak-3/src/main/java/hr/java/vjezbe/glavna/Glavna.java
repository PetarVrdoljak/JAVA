package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;
import hr.java.vjezbe.iznimke.OzbiljnaGreska;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Glavna {

    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static void main(String[] args) throws NemoguceOdreditiGrupuOsiguranjaException {

        logger.info("Pokrenut je program");
        Scanner unosPodataka = new Scanner(System.in);
        Korisnik[]poljeKorisnika=unosKorisnika(unosPodataka);


                    System.out.println("Unesite broj kategorija koji zelite unijeti: ");
                    Integer BROJ_KATEGORIJA = 0;

                    try {
                        BROJ_KATEGORIJA=unosPodataka.nextInt();
                        unosPodataka.nextLine();
                        provjeraUnosaBrojaKategorija(BROJ_KATEGORIJA);
                    } catch (OzbiljnaGreska | InputMismatchException e) {
                        logger.error("Pogreška u unosu kod kategorija");
                    }
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
                    logger.info("Program je završio.");
                }
   public static Korisnik [] unosKorisnika (Scanner unosPodataka){
       System.out.println("Unesite broj korisnika koji zelite unijeti: ");
       logger.info("Unos broj korisnika.");

       try {
           Integer BROJ_KORISNIKA = unosPodataka.nextInt();
           unosPodataka.nextLine();
           provjeraUnosaBrojaKorisnika(BROJ_KORISNIKA);
           Korisnik[] poljeKorisnika = new Korisnik[BROJ_KORISNIKA];
           for(int j = 0; j < BROJ_KORISNIKA; j++){
               Integer tipKorisnika = odabirTipKorisnika(unosPodataka);
               if(tipKorisnika==1){
                   poljeKorisnika[j] = unosPrivatnogKorisnika(unosPodataka);
               }
               else {
                   poljeKorisnika[j]=unosPoslovnogKorisnika(unosPodataka);
               }
           }
           return poljeKorisnika;
       } catch (OzbiljnaGreska | InputMismatchException  e) {
           logger.error("Pogreška u unosu kod korisnika");
       }
       return null;
   }
    public static Integer odabirTipKorisnika (Scanner unosPodataka) {
        Boolean provjeraTipa = false;
        Integer odabraniTip = -1;
        logger.info("Unos tipa korisnika");
        do {
            System.out.println("Unesite tip korisnika: ");
            System.out.println("1. Privatni ");
            System.out.println("2. Poslovni ");
            System.out.print("Odabir >> ");
            odabraniTip = unosPodataka.nextInt();
            unosPodataka.nextLine();

            if (odabraniTip < 1 || odabraniTip > 2) {
                System.out.println("Krivi unos.");
                provjeraTipa = true;
            } else {
                return odabraniTip;
            }
        } while (provjeraTipa);
        return odabirTipKorisnika(unosPodataka);
    }
    private static void provjeraUnosaBrojaKategorija(Integer BROJ_KATEGORIJA) throws OzbiljnaGreska {
        if(BROJ_KATEGORIJA < 0) {
            throw new OzbiljnaGreska("Unijeli ste negativan broj! ");
        }
        else if(BROJ_KATEGORIJA ==0){
            throw new OzbiljnaGreska("Unijeli ste nulu! ");
        }

    }
    private static void provjeraUnosaBrojaKorisnika(Integer BROJ_KORISNIKA) throws OzbiljnaGreska {
        if(BROJ_KORISNIKA < 0) {
            throw new OzbiljnaGreska("Unijeli ste negativan broj! ");
        }
        else if(BROJ_KORISNIKA ==0){
            throw new OzbiljnaGreska("Unijeli ste nulu! ");
        }

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

    private static PrivatniKorisnik unosPrivatnogKorisnika(Scanner unosPodataka){
        logger.info("Unos privatnog korisnika.");
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
        logger.info("Unos poslovnog korisnika.");
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
    private static Usluga unosUsluge(Scanner unosPodataka) {
        logger.info("Unos usluge.");

        Boolean ispravanUnos = false;

        System.out.println("Unesite naslov oglasa usluge: ");
        String naslovOglasaUsluge = unosPodataka.nextLine();

        System.out.println("Unesite opis oglasa usluge: ");
        String opisOglasaUsluge = unosPodataka.nextLine();

        System.out.println("Unesite cijenu oglasa usluge: ");
        BigDecimal cijenaOglasaUsluge = null;
        do {
            try {
                cijenaOglasaUsluge = unosPodataka.nextBigDecimal();
                unosPodataka.nextLine();
                provjeraUsluge(cijenaOglasaUsluge);
                ispravanUnos = true;


            } catch (OzbiljnaGreska | InputMismatchException e) {
                System.out.println("Neispravan unos cijene usluge. Molimo unesite ponovo.");
                logger.error("Pogreška kod unosa usluge.");
            }

        } while (!ispravanUnos );
        Usluga novaUsluga = new Usluga(naslovOglasaUsluge, opisOglasaUsluge, cijenaOglasaUsluge);
        return novaUsluga;
    }
    private static void provjeraUsluge(BigDecimal cijenaOglasaUsluge) throws OzbiljnaGreska {
        if(cijenaOglasaUsluge.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new OzbiljnaGreska("Unijeli ste negativan broj! ");
        }
        else if(cijenaOglasaUsluge.compareTo(BigDecimal.ZERO)==0){
            throw new OzbiljnaGreska("Unijeli ste nulu! ");
        }
    }
    private static Automobil unosAutomobil(Scanner unosPodataka) {

        logger.info("Unos automobila.");

        Boolean ispravanUnos = false;

        System.out.println("Unesite naslov oglasa automobila: ");
        String naslovOglasaAutomobila = unosPodataka.nextLine();

        System.out.println("Unesite opis oglasa automobila: ");
        String opisOglasaAutomobila = unosPodataka.nextLine();

        System.out.println("Unesite cijenu oglasa automobila: ");
        BigDecimal cijenaOglasaAutomobila = null;
        do{
            try{
                cijenaOglasaAutomobila = unosPodataka.nextBigDecimal();
                unosPodataka.nextLine();
                provjeraCijeneAutomobila(cijenaOglasaAutomobila);
                ispravanUnos=true;
            }catch (OzbiljnaGreska | InputMismatchException e){
                System.out.println("Neispravan unos. Molimo unesite ponovo.");
                logger.error("Pogrška u unosu cijene automobila.");
            }

        }while(!ispravanUnos);


        System.out.println("Unesite snagu (u ks) oglasa automobila: ");
        BigDecimal snagaOglasaAutomobila = null;
        do{
            try{
                snagaOglasaAutomobila = unosPodataka.nextBigDecimal();
                unosPodataka.nextLine();
                provjeraSnaguAutomobila(cijenaOglasaAutomobila);
                ispravanUnos=true;
            }catch (OzbiljnaGreska | InputMismatchException e){
                System.out.println("Neispravan unos. Molimo unesite ponovo.");
                logger.error("Pogrška u unos konjskih snaga.");
            }

        }while(!ispravanUnos);


        Automobil noviAutomobil = new Automobil(naslovOglasaAutomobila,opisOglasaAutomobila,
                cijenaOglasaAutomobila ,snagaOglasaAutomobila);
        return noviAutomobil;
    }
    private static void provjeraCijeneAutomobila(BigDecimal cijenaOglasaAutomobila)
            throws OzbiljnaGreska {
        if(cijenaOglasaAutomobila.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new OzbiljnaGreska("Unijeli ste nedozvoljen broj! ");
        }
    }
    private static void provjeraSnaguAutomobila(BigDecimal snagaOglasaAutomobila)
            throws OzbiljnaGreska {
        if(snagaOglasaAutomobila.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new OzbiljnaGreska("Unijeli ste nedozvoljen broj! ");
        }
    }
    private static Stan unosStana(Scanner unosPodataka) {
        logger.info("Unos stana");
        Boolean ispravanUnos = false;

        System.out.println("Unesite naslov oglasa stana: ");
        String naslovStana = unosPodataka.nextLine();


        System.out.println("Unesite opis oglasa stana: ");
        String opisStana = unosPodataka.nextLine();

        System.out.println("Unesite cijenu oglasa stana: ");
        BigDecimal cijenaStana = null;
        do {
            try {
                cijenaStana = unosPodataka.nextBigDecimal();
                unosPodataka.nextLine();
                provjeraUsluge(cijenaStana);
                ispravanUnos = true;


            } catch (OzbiljnaGreska | InputMismatchException e) {
                System.out.println("Neispravan unos cijene usluge. Molimo unesite ponovo.");
                logger.error("Pogrška kod unosa cijene stana.");
            }

        } while (!ispravanUnos);

        System.out.println("Unesite kvadraturu stana: ");
        int kvadraturaStana = 0;
        do {
            try {
                kvadraturaStana = unosPodataka.nextInt();
                unosPodataka.nextLine();
                provjerakvadraturestana(kvadraturaStana);
                ispravanUnos = true;


            } catch (OzbiljnaGreska | InputMismatchException e) {
                System.out.println("Neispravan unos cijene usluge. Molimo unesite ponovo.");
                logger.error("Pogreška kod unosa cijene.");
            }

        } while (!ispravanUnos);

        Stan novaUsluga = new Stan(naslovStana, opisStana, cijenaStana, kvadraturaStana);
        return novaUsluga;

    }
    private static void provjerakvadraturestana(int kvadratura) throws OzbiljnaGreska {

        if(kvadratura <= 0) {
            throw new OzbiljnaGreska("Unijeli ste neispravan broj! ");
        }
    }
    private static Kategorija unosKategorije(Scanner unosPodataka) {
        logger.info("Unos kategorije");

        Boolean ispravanUnos = false;

        System.out.println("Unesite naziv kategorije: ");
        String nazivKategorije = unosPodataka.nextLine();

        System.out.println("Unesite broj artikala koji želite unijeti za unesenu kategoriju: ");
        int brojArtikala = 0;
        do {
            try {
                brojArtikala = unosPodataka.nextInt();
                unosPodataka.nextLine();
                provjeraBrojaArtikla(brojArtikala);
                ispravanUnos = true;
            } catch (OzbiljnaGreska | InputMismatchException e) {
                System.out.println("Neispravan unos. Molimo unesite ponovo.");
                logger.error("Pogreška kod unosa broj artikala");
            }

        } while (!ispravanUnos);

        Artikl[] artikl = new Artikl[brojArtikala];
        for (int i = 0; i < artikl.length; i++) {
            System.out.println("Unesite tip " + (i + 1) + " artikla: ");
            System.out.println("1. Usluga ");
            System.out.println("2. Automobil ");
            System.out.println("3. Stan ");
            System.out.print("Odabir >> ");
            Integer tipArtikla = unosPodataka.nextInt();
            unosPodataka.nextLine();
            if (tipArtikla == 1) {
                Usluga usluga = unosUsluge(unosPodataka);
                artikl[i] = usluga;
            } else if (tipArtikla == 2) {
                Automobil automobil = unosAutomobil(unosPodataka);
                artikl[i] = automobil;
            } else if (tipArtikla == 3) {
                Stan stan= unosStana(unosPodataka);
                artikl[i] = stan;
            }
        }
        Kategorija novaKategorija = new Kategorija(nazivKategorije, artikl);
        return novaKategorija;
    }
    private static void provjeraBrojaArtikla(int brojArtikla) throws OzbiljnaGreska {
        if(brojArtikla <= 0) {
            throw new OzbiljnaGreska("Unijeli ste neispravan broj! ");
        }

    }
}
