package hr.java.vjezbe.vrdoljak7.baza;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;

import hr.java.vjezbe.vrdoljak7.entitet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BazaPodataka {
    private static final String PROPERTIES_FILE = "properties/bazaPodataka.properties";

    private static Connection spajanjeNaBazu() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(PROPERTIES_FILE));

        String bazaPodatakaUrl = properties.getProperty("bazaPodatakaUrl");
        String korisnickoIme = properties.getProperty("korisnickoIme");
        String lozinka = properties.getProperty("lozinka");

        Connection connection = DriverManager.getConnection(bazaPodatakaUrl, korisnickoIme, lozinka);


        return connection;
    }

    public static List<Korisnik> dohvatPrivatnihKorisnika() {
        List<Korisnik> privatniKorisnici = new ArrayList<>();

        try (Connection connection = spajanjeNaBazu();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM KORISNIK WHERE IDTIPKORISNIKA = '1'")) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String email = resultSet.getString("email");
                String telefon = resultSet.getString("telefon");

                PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(id, ime, prezime, email, telefon);
                privatniKorisnici.add(privatniKorisnik);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return privatniKorisnici;
    }

    public static void pohranaPrivatnogKorisnika(PrivatniKorisnik korisnik) {
        try (Connection connection = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "insert into korisnik (Ime, Prezime, Telefon, Email, idTipKorisnika) " +
                                    "values (?, ?, ?, ?, 1);");


            preparedStatement.setString(1, korisnik.getIme());
            preparedStatement.setString(2, korisnik.getPrezime());
            preparedStatement.setString(3, korisnik.getTelefon());
            preparedStatement.setString(4, korisnik.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }




    public static List<Korisnik> dohvatPoslovnihKorisnika() {
        List<Korisnik> poslovniKorisnici = new ArrayList<>();

        try (Connection connection = spajanjeNaBazu();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM KORISNIK WHERE IDTIPKORISNIKA = '2'")) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String naziv = resultSet.getString("naziv");
                String web = resultSet.getString("web");
                String email = resultSet.getString("email");
                String telefon = resultSet.getString("telefon");

                PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(id, naziv, web, email, telefon);
                poslovniKorisnici.add(poslovniKorisnik);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return poslovniKorisnici;
    }

    public static void pohranaPoslovnogKorisnika(PoslovniKorisnik korisnik) {
        try (Connection connection = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "insert into korisnik (Naziv, Web, Telefon, Email, idTipKorisnika) " +
                                    "values (?, ?, ?, ?, 2);");


            preparedStatement.setString(1, korisnik.getNaziv());
            preparedStatement.setString(2, korisnik.getWeb());
            preparedStatement.setString(3, korisnik.getTelefon());
            preparedStatement.setString(4, korisnik.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Artikl> dohvatUsluga() {
        List<Artikl> usluge = new ArrayList<>();

        try (Connection connection = spajanjeNaBazu();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM ARTIKL WHERE IDTIPARTIKLA = '2'")) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String naziv = resultSet.getString("naslov");
                String opis = resultSet.getString("opis");
                BigDecimal cijena = resultSet.getBigDecimal("cijena");
                int stanjeBroj = resultSet.getInt("idstanje");

                Stanje stanje = Stanje.fromBroj(stanjeBroj);

                Usluga usluga = new Usluga(id, naziv, opis, cijena, stanje);
                usluge.add(usluga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return usluge;
    }

    public static void pohranaUsluga(Usluga artikl) {
        try (Connection connection = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "insert into artikl (Naslov, Opis, Cijena, idStanje, idTipArtikla) " +
                                    "values (?, ?, ?, ?, 2);");


            preparedStatement.setString(1, artikl.getNaziv());
            preparedStatement.setString(2, artikl.getOpis());
            preparedStatement.setBigDecimal(3, artikl.getCijena());
            preparedStatement.setLong(4, (artikl.getStanje().ordinal()+1));
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Artikl> dohvatAutomobila() {
        List<Artikl> automobili = new ArrayList<>();

        try (Connection connection = spajanjeNaBazu();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM ARTIKL WHERE IDTIPARTIKLA = '1'")) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String naziv= resultSet.getString("naslov");
                String opis = resultSet.getString("opis");
                BigDecimal cijena = resultSet.getBigDecimal("cijena");
                BigDecimal snagaKs = resultSet.getBigDecimal("snaga");


                int stanjeBroj = resultSet.getInt("idstanje");

                Stanje stanje = Stanje.fromBroj(stanjeBroj);

                Automobil automobil = new Automobil(id,naziv, opis, snagaKs, cijena,stanje);
                automobili.add(automobil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return automobili;
    }

    public static void pohranaAutomobila(Automobil artikl) {
        try (Connection connection = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "insert into artikl (Naslov, Opis, Cijena, Snaga, idStanje, idTipArtikla) " +
                                    "values (?, ?, ?, ?, ?, 1);");


            preparedStatement.setString(1, artikl.getNaziv());
            preparedStatement.setString(2, artikl.getOpis());
            preparedStatement.setBigDecimal(3, artikl.getCijena());
            preparedStatement.setBigDecimal(4, artikl.getSnagaKs());
            preparedStatement.setLong(5, (artikl.getStanje().ordinal()+1));
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Artikl> dohvatStanova() {
        List<Artikl> stanovi = new ArrayList<>();

        try (Connection connection = spajanjeNaBazu();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM ARTIKL WHERE IDTIPARTIKLA = '3'")) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String naziv= resultSet.getString("naslov");
                String opis = resultSet.getString("opis");
                BigDecimal cijena = resultSet.getBigDecimal("cijena");
                int kvadratura = resultSet.getInt("kvadratura");


                int stanjeBroj = resultSet.getInt("idstanje");

                Stanje stanje = Stanje.fromBroj(stanjeBroj);

                Stan stan = new Stan(id,naziv, opis, kvadratura, cijena,stanje);
                stanovi.add(stan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stanovi;
    }
    public static void pohranaStana(Stan artikl) {
        try (Connection connection = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "insert into artikl (Naslov, Opis, Cijena, Kvadratura, idStanje, idTipArtikla) " +
                                    "values (?, ?, ?, ?, ?, 3);");


            preparedStatement.setString(1, artikl.getNaziv());
            preparedStatement.setString(2, artikl.getOpis());
            preparedStatement.setBigDecimal(3, artikl.getCijena());
            preparedStatement.setInt(4, artikl.getKvadratura());
            preparedStatement.setLong(5, (artikl.getStanje().ordinal()+1));
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}
