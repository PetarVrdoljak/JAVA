package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GlavnaDatoteke {
    public static void main(String[] args) {

            System.out.println("Ucitavanje korisnika...");
            List<Korisnik> korisnici = dohvatiKorisnike();

            System.out.println("Ucitavanje artikala...");
            List<Artikl> artikli = dohvatiArtikle();

            System.out.println("Ucitavanje kategorija...");
            List<Kategorija<Artikl>> kategorije = dohvatiKategorije(artikli);

            System.out.println("Ucitavanje prodaje...");
            List<Prodaja> prodaje = dohvatiProdaju(artikli,korisnici);

            ispisProdaje(prodaje);
            printKategorije(kategorije);
            serijalizacijaProdaje(prodaje);

    }
    private static List<Korisnik> dohvatiKorisnike() {
        File korisnik=new File("dat/korisnici.txt");
        List<Korisnik> korisnici = new ArrayList<>();
        if(korisnik.exists()){
            try (FileReader fileReader=new FileReader(korisnik);
            BufferedReader LineReader=new BufferedReader(fileReader)) {
                String tipKorisnika;
                while ((tipKorisnika = LineReader.readLine()) != null) {
                    if(tipKorisnika.equals("2") ){
                        Long id = Long.parseLong(LineReader.readLine());
                        String ime = LineReader.readLine();
                        String prezime = LineReader.readLine();
                        String email = LineReader.readLine();
                        String telefon = LineReader.readLine();
                        PrivatniKorisnik privatniKorisnik=new PrivatniKorisnik(id,ime,prezime,
                                email,telefon);
                        korisnici.add(privatniKorisnik);
                    }
                    else if(tipKorisnika.equals("1")){
                        Long id = Long.parseLong(LineReader.readLine());
                        String ime = LineReader.readLine();
                        String web = LineReader.readLine();
                        String email = LineReader.readLine();
                        String telefon = LineReader.readLine();
                        PoslovniKorisnik poslovniKorisnik=new PoslovniKorisnik(id,ime,web,
                                email,telefon);
                        korisnici.add(poslovniKorisnik);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return korisnici;
    }
    private static List<Artikl> dohvatiArtikle() {
        File artikl = new File("dat/artikli.txt");
        List<Artikl> artikli = new ArrayList<>();
        if(artikl.exists())
        try (FileReader fileReader= new FileReader(artikl);
        BufferedReader lineReader = new BufferedReader(fileReader)) {
            String tipArtikla;
            while ((tipArtikla = lineReader.readLine()) != null) {
                if (tipArtikla.equals("1")){
                    Long id = Long.parseLong(lineReader.readLine());
                    String naziv = lineReader.readLine();
                    String opis = lineReader.readLine();
                    BigDecimal cijena = new BigDecimal(lineReader.readLine());
                    Stanje stanje = Stanje.fromBroj(Integer.parseInt(lineReader.readLine()));
                    Usluga usluga = new Usluga(id, naziv, opis, cijena, stanje);
                    artikli.add(usluga);
                }
                else if(tipArtikla.equals("2")){
                    Long id = Long.parseLong(lineReader.readLine());
                    String naziv = lineReader.readLine();
                    String opis = lineReader.readLine();
                    BigDecimal snagaKs= new BigDecimal(lineReader.readLine());
                    BigDecimal cijena = new BigDecimal(lineReader.readLine());
                    Stanje stanje = Stanje.fromBroj(Integer.parseInt(lineReader.readLine()));
                    Automobil automobil = new Automobil(id, naziv, opis,snagaKs, cijena, stanje);
                    artikli.add(automobil);
                }
                else if(tipArtikla.equals("3")){
                    Long id = Long.parseLong(lineReader.readLine());
                    String naziv = lineReader.readLine();
                    String opis = lineReader.readLine();
                    int snagaKs= Integer.parseInt(lineReader.readLine());
                    BigDecimal cijena = new BigDecimal(lineReader.readLine());
                    Stanje stanje = Stanje.fromBroj(Integer.parseInt(lineReader.readLine()));
                    Stan stan = new Stan(id, naziv, opis,snagaKs, cijena, stanje);
                    artikli.add(stan);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artikli;
    }
    private static List<Kategorija<Artikl>> dohvatiKategorije(List<Artikl> artikli) {
        File kategorija = new File("dat/kategorija.txt");
        List<Kategorija<Artikl>> kategorije = new ArrayList<>();
        if (kategorija.exists()){
            try (FileReader fileReader = new FileReader(kategorija);
            BufferedReader lineReader = new BufferedReader(fileReader)) {
                String line;
                while ((line = lineReader.readLine()) != null) {
                    Long id = Long.parseLong(line);
                    String naziv = lineReader.readLine();
                    Kategorija listaKategorije = new Kategorija(id, naziv);
                    String artikliIds = lineReader.readLine();
                    var listaIdArtikala = Arrays.asList(artikliIds.split(" "));
                    for (String idArtikla : listaIdArtikala) {
                        var trazenArtikl= artikli.stream().filter(x -> x.getId() == Long.parseLong(idArtikla))
                                .findFirst().orElse(null);

                        if (trazenArtikl != null)
                            listaKategorije.dodajArtikl(trazenArtikl);
                    }
                    kategorije.add(listaKategorije);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return kategorije;
    }
    private static List<Prodaja> dohvatiProdaju(List<Artikl> artikli, List<Korisnik> korisnici) {
        File fileProdaja = new File("dat/prodaja.txt");
        List<Prodaja> prodaje = new ArrayList<>();
        if(fileProdaja.exists()){
            try (FileReader fileReader = new FileReader(fileProdaja);
            BufferedReader lineReader = new BufferedReader(fileReader)) {
                String line;
                while ((line = lineReader.readLine()) != null) {
                    Long id= Long.parseLong(line);
                    String artiklId = lineReader.readLine();
                    String korisnikId = lineReader.readLine();
                    String datumObjave = lineReader.readLine();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    LocalDate datum = LocalDate.parse(datumObjave, dateTimeFormatter);
                    Artikl trazeniArtikl = artikli.stream().filter(x ->x.getId()==Long.parseLong(artiklId))
                            .findFirst().orElse(null);
                    Korisnik trazenKorisnik = korisnici.stream().filter(x ->x.getId()==Long.parseLong(korisnikId))
                            .findFirst().orElse(null);
                    Prodaja prodaja = new Prodaja(id,trazeniArtikl, trazenKorisnik, datum);
                    prodaje.add(prodaja);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prodaje;
    }
    private static void ispisProdaje(List<Prodaja> prodaje){
        System.out.println("Trenutno su oglasi na prodaju:");
        System.out.println("------------------------------------------------------------");

        prodaje.stream().forEach(prodaja -> {
            System.out.println("------------------------------------------------------------");
            System.out.println(prodaja.getArtikl().tekstOglasa());
            System.out.println("Datum objave: " + prodaja.getDatumObjaveUTekstualnomObliku());
            System.out.println(prodaja.getKorisnik().dohvatiKontakt());
        });
    }
    private static void printKategorije(List<Kategorija<Artikl>> kategorije){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Ispis po kategorijama:");
        System.out.println("--------------------------------------------------------------------------------");

        List<Kategorija<Artikl>> kategorijeSortirane =
                kategorije.stream().sorted((kat1,kat2)-> kat1.getNaziv().compareTo((kat2.getNaziv())))
                        .collect(Collectors.toList());

        for (Kategorija<Artikl> jednaKategorijaUNizu : kategorijeSortirane ) {
            System.out.println("Kategorija: " + jednaKategorijaUNizu.getNaziv());
            System.out.println("--------------------------------------------------------------------------------");

            jednaKategorijaUNizu.getArtikli()
                    .stream()
                    .sorted((art1, art2) -> art1.getNaziv().compareTo(art2.getNaziv()))
                    .forEach(art -> {
                        System.out.println(art.tekstOglasa());
                        System.out.println("--------------------------------------------------------------------------------");
                    });
        }
    }
    private static void serijalizacijaProdaje(List<Prodaja> prodaje){
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("dat.serijalizacija.dat"))) {
            out.writeObject(prodaje);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    private static List<Prodaja> deserijalizacijaProdaje() {
        List<Prodaja> procitanaProdaja = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("dat.serijalizacija.dat"))) {
            procitanaProdaja = (List<Prodaja>) in.readObject();

        } catch (IOException ex) {
            System.err.println(ex);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return  procitanaProdaja;
    }
}
