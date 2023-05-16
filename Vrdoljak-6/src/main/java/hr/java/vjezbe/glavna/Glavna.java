package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Glavna {

    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static void main(String[] args) {
        /*Scanner unosPodataka = new Scanner(System.in);

        List<Korisnik> listaKorisnika = unosKorisnika(unosPodataka);
        //List<Kategorija> listaKategorija = unosKategorijeIArtikla(unosPodataka);
        HashMap<Kategorija<Artikl>,List<Artikl>> hashMap = new HashMap<>();
        List<Kategorija<Artikl>> listaKategorija = unosKategorijeIArtikla(unosPodataka,hashMap);
        List<Prodaja> listaProdaje = aktivnoNaProdaju(unosPodataka,listaKategorija,listaKorisnika);


        ispisOglasaNaProdaju(listaProdaje);
        printKategorije(listaKategorija);
        printKategorijaArtikliMapa(hashMap);



        System.out.println("Kraj programa");

    }

    private static BigDecimal provjeraBigDecimala (Scanner unosPodataka){
        BigDecimal broj;
        while (true) {
            try {
                broj = unosPodataka.nextBigDecimal();
                break;
            } catch (InputMismatchException e) {
                logger.error("Nije unesen big decimal broj", e);
                System.out.print("Neispravan unos! Molim unesite cijeli broj: ");
                unosPodataka.nextLine();
            }
        }
        return broj;
    }
    private static Integer provjeraIntegera (Scanner unosPodataka){
        Integer broj;
        while (true) {
            try {
                broj = unosPodataka.nextInt();
                break;
            } catch (InputMismatchException e) {
                logger.error("Nije unesen cijeli broj", e);
                System.out.print("Neispravan unos! Molim unesite cijeli broj: ");
                unosPodataka.nextLine();
            }
        }
        return broj;
    }
    private static Stanje unosStanja(Scanner unosPodataka) {
        for (int i = 0; i < Stanje.values().length; i++) {
            System.out.println((i + 1) + ". " + Stanje.values()[i]);
        }
        Integer stanjeRedniBroj = null;
        while (true) {
            System.out.print("Odabir stanja artikla >> ");
            stanjeRedniBroj = provjeraIntegera(unosPodataka);
            unosPodataka.nextLine();
            if (stanjeRedniBroj >= 1
                    && stanjeRedniBroj <= Stanje.values().length)
            {
                return Stanje.values()[stanjeRedniBroj - 1];
            } else {
                System.out.println("Neispravan unos!");
            }
        }
    }
    private static List<Korisnik> unosKorisnika(Scanner unosPodataka) {

        System.out.print("Unesite broj korisnika koji želite unijeti: ");
        Integer brojKorisnika = provjeraIntegera(unosPodataka);
        unosPodataka.nextLine();

        List<Korisnik> listaKorisnika = new ArrayList<>();
        for (int i = 0; i < brojKorisnika; i++) {
            System.out.println("Unesite tip "+(i+1)+ ".korisnika \n1.Privatni " +
                    "\n2.Poslovni");
            System.out.println("Odabir >>");
            Integer izborTipaKorisnika = provjeraIntegera(unosPodataka);
            unosPodataka.nextLine();

            if(izborTipaKorisnika==1){
                System.out.print("Unesite ime " + (i + 1) + ". osobe: ");
                String ime = unosPodataka.nextLine();
                System.out.print("Unesite prezime " + (i + 1) + ". osobe: ");
                String prezime = unosPodataka.nextLine();
                System.out.print("Unesite e-Mail " + (i + 1) + ". osobe: ");
                String email = unosPodataka.nextLine();
                System.out.print("Unesite telefon " + (i + 1) + ". osobe: ");
                String telefon = unosPodataka.nextLine();

                PrivatniKorisnik privatniKorisnik=new PrivatniKorisnik(ime,prezime,
                        email,telefon);
                listaKorisnika.add(privatniKorisnik);

            } else if (izborTipaKorisnika==2) {

                System.out.print("Unesite naziv " + (i + 1) + ". tvrtke: ");
                String naziv = unosPodataka.nextLine();
                System.out.print("Unesite e-mail " + (i + 1) + ". tvrtke: ");
                String email = unosPodataka.nextLine();
                System.out.print("Unesite web " + (i + 1) + ". tvrtke: ");
                String web = unosPodataka.nextLine();
                System.out.print("Unesite telefon " + (i + 1) + ". tvrtke: ");
                String telefon = unosPodataka.nextLine();

                PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(naziv,web,email,telefon);
                listaKorisnika.add(poslovniKorisnik);
            }
        }
        return listaKorisnika;
    }
    private static List<Kategorija<Artikl>> unosKategorijeIArtikla(Scanner unosPodataka,HashMap<Kategorija<Artikl>,List<Artikl>>hashMap) {
        System.out.print("Unesite broj kategorija koji želite unijeti: ");
        Integer brojKategorija = provjeraIntegera(unosPodataka);
        unosPodataka.nextLine();
        List<Kategorija<Artikl>> listaKategorije = new ArrayList<>();


        for (int i = 0; i <brojKategorija; i++) {
            System.out.print("Unesite naziv " + (i + 1) + " kategorije: ");
            String naziv = unosPodataka.nextLine();
            System.out.print("Unesite broj artikala koji želite unijeti za unesenu" +(i+1)+" kategoriju: ");
            Integer brojArtikala = provjeraIntegera(unosPodataka);
            unosPodataka.nextLine();

            Kategorija<Artikl> artikli = new Kategorija<>(naziv);
            for (int j = 0; j < brojArtikala; j++) {
                System.out.println("Unesite tip "+(j+1)+". artikala \n1.Usluga \n2.Automobil \n3.Stan");
                System.out.println("Odabir >>");
                Integer tipArtikla = provjeraIntegera(unosPodataka);
                unosPodataka.nextLine();

                if(tipArtikla==1){
                    System.out.print("Unesite naslov " + (j + 1) + ". oglasa usluge: ");
                    String naslov = unosPodataka.nextLine();
                    System.out.print("Unesite opis " + (j + 1) + ". oglasa usluge: ");
                    String opis = unosPodataka.nextLine();
                    System.out.print("Unesite cijenu " + (j + 1) + ". oglasa usluge: ");
                    BigDecimal cijena = provjeraBigDecimala(unosPodataka);
                    unosPodataka.nextLine();

                    Usluga usluga = new Usluga(naslov,opis,cijena,unosStanja(unosPodataka),id);
                    artikli.dodajArtikl(usluga);

                } else if (tipArtikla==2) {
                    System.out.print("Unesite naslov " + (j + 1) + ". oglasa automobila: ");
                    String naslov = unosPodataka.nextLine();
                    System.out.print("Unesite opis " + (j + 1) + ". oglasa automobila: ");
                    String opis = unosPodataka.nextLine();
                    System.out.print("Unesite snagu " + (j + 1) + ". (u ks) oglasa automobila: ");
                    BigDecimal snaga = provjeraBigDecimala(unosPodataka);
                    System.out.print("Unesite cijenu " + (j + 1) + ". oglasa automobila: ");
                    BigDecimal cijena = provjeraBigDecimala(unosPodataka);
                    unosPodataka.nextLine();

                    Automobil automobil = new Automobil(naslov,opis,snaga,cijena,unosStanja(unosPodataka));
                    artikli.dodajArtikl(automobil);
                }
                else if (tipArtikla==3) {
                    System.out.print("Unesite naslov " + (j + 1) + ". oglasa stana: ");
                    String naslov = unosPodataka.nextLine();
                    System.out.print("Unesite opis " + (j + 1) + ". oglasa stana: ");
                    String opis = unosPodataka.nextLine();
                    System.out.print("Unesite kvadraturu " + (j + 1) + ". stana: ");
                    int kvadratura = provjeraIntegera(unosPodataka);
                    System.out.print("Unesite cijenu " + (j + 1) + ". oglasa stana: ");
                    BigDecimal cijena = provjeraBigDecimala(unosPodataka);
                    unosPodataka.nextLine();

                    Stan stan = new Stan(naslov,opis,kvadratura,cijena,unosStanja(unosPodataka));
                    artikli.dodajArtikl(stan);
                }


            }

            listaKategorije.add(artikli);
            hashMap.put(artikli,artikli.getArtikli());
        }
        return listaKategorije;
    }
    private static List<Prodaja> aktivnoNaProdaju(Scanner unosPodataka, List<Kategorija<Artikl>> kategorije, List<Korisnik> korisnici) {
        System.out.print("Unesite broj artikala koji su aktivno na prodaju: ");
        Integer brojArtikala = provjeraIntegera(unosPodataka);
        unosPodataka.nextLine();
        List<Prodaja>prodaje = new ArrayList<>();

        for(int j =0; j<brojArtikala;j++) {
            System.out.println("Odaberite korisnika: ");
            int brojac = 1;

            for (Korisnik korisnik : korisnici) {
                System.out.println((brojac++) + "." + korisnik.dohvatiKontakt());
            }
            System.out.println("Odabir >>");
            Integer brojOdabiraKorisnika = provjeraIntegera(unosPodataka);
            unosPodataka.nextLine();

            System.out.println("Odaberite kategoriju:");
            int brojac2 = 1;
            for (Kategorija kategorija : kategorije) {
                System.out.println((brojac2++) + ". " + kategorija.getNaziv());
            }
            System.out.println("Odabir >>");
            Integer brojOdabraneKategorije = provjeraIntegera(unosPodataka);
            unosPodataka.nextLine();

            Kategorija odabranaKategorija = kategorije.get(brojOdabraneKategorije-1);
            List<Artikl> listaArtikli = odabranaKategorija.getArtikli();

            System.out.println("Odaberite artikl:");
            int brojac3 = 1;
            for (Artikl artikl : listaArtikli) {
                System.out.println((brojac3++) + ". " + artikl.getNaziv());
            }
            System.out.println("Odabir >>");
            Integer brojOdabranogArtikla = provjeraIntegera(unosPodataka);
            unosPodataka.nextLine();

            Artikl odabraniArtikl = listaArtikli.stream()
                    .skip(brojOdabranogArtikla-1)
                    .findFirst()
                    .orElse(null);

            Prodaja novaProdaja = new Prodaja(
                    odabraniArtikl,
                    korisnici.get(brojOdabiraKorisnika-1),
                    LocalDate.now());

            prodaje.add(novaProdaja);
        }
        return prodaje;
    }
    private static void ispisOglasaNaProdaju(List<Prodaja> listaProdaje){

        System.out.println("Trenutno su oglasi na prodaju:");
        listaProdaje.stream().forEach(listaProdaja-> {
            System.out.println(listaProdaja.getArtikl().tekstOglasa());
            System.out.println("Datum objave: " + listaProdaja.getDatumObjaveUTekstualnomObliku());
            System.out.println(listaProdaja.getKorisnik().dohvatiKontakt());
        });
    }
    private static Map<Kategorija, List<Artikl>> ispisKategorijaArtikleMape(List<Kategorija<Artikl>> listaKategorije) {
        Map<Kategorija, List<Artikl>> kategorijaArtikliMapa = new HashMap<>();

        for (Kategorija kategorija : listaKategorije) {
            kategorijaArtikliMapa.put(kategorija, new ArrayList<>(kategorija.getArtikli()));
        }

        return kategorijaArtikliMapa;
    }
    private static void printKategorije(List<Kategorija<Artikl>> listakategorije) {
        System.out.println("Ispis kategorija:");;

        List<Kategorija<Artikl>> sortiranaListaKategorija =
                listakategorije.stream().sorted((k1,k2)->k1.getNaziv().compareTo((k2.getNaziv())))
                        .collect(Collectors.toList());
        for (Kategorija<Artikl> kategorijUNizu : sortiranaListaKategorija) {
            System.out.println(" ");
            System.out.println("Kategorija: ˇ"+ kategorijUNizu.getNaziv());

            kategorijUNizu.getArtikli()
                    .stream()
                    .sorted((a1,a2)-> a1.getNaziv().compareTo(a2.getNaziv()))
                    .forEach(a -> {
                        System.out.println(a.tekstOglasa());
                    });
        }
    }

    private static void printKategorijaArtikliMapa(HashMap<Kategorija<Artikl>, List<Artikl>> listaKategorijeArtikliMapa) {
        System.out.println("Ispis mape:");

        listaKategorijeArtikliMapa.entrySet().stream()
                .forEach(entry -> {
                    Kategorija kategorija = entry.getKey();
                    List<Artikl> artikli = entry.getValue();

                    System.out.println("Kategorija: "+ kategorija.getNaziv());

                    artikli.stream().sorted((a1,a2) -> a1.getNaziv().compareTo(a2.getNaziv()))
                            .forEach(artikl -> {
                                System.out.println(artikl.tekstOglasa());
                            });
                });*/

    }

}
