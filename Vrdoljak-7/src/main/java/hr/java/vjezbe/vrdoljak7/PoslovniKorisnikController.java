package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.entitet.Korisnik;
import hr.java.vjezbe.vrdoljak7.entitet.PoslovniKorisnik;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoslovniKorisnikController {
    @FXML
    private TextField idKorisnikaTextField;
    @FXML
    private TextField imeTextField;
    @FXML
    private TextField webTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField telefonTextField;
    @FXML
    private TableColumn<Korisnik,String> idKorisnikaTableColumn;
    @FXML
    private TableColumn<Korisnik,String> imeKorisnikaTableColumn;
    @FXML
    private TableColumn<Korisnik,String> webKorisnikaTableColumn;
    @FXML
    private TableColumn<Korisnik,String> emailKorisnikaTableColumn;
    @FXML
    private TableColumn<Korisnik,String> telefonKorisnikaTableColumn;
    @FXML
    private TableView<Korisnik> poslovniKorisnikTableView;


    private static List<Korisnik> dohvatiPoslovneKorisnike() {
        File korisnik=new File("dat/korisnici.txt");
        List<Korisnik> korisnici = new ArrayList<>();
        if(korisnik.exists()){
            try (FileReader fileReader=new FileReader(korisnik);
                 BufferedReader LineReader=new BufferedReader(fileReader)) {
                String tipKorisnika;
                while ((tipKorisnika = LineReader.readLine()) != null) {
                    /*if(tipKorisnika.equals("1") ){
                        Long id = Long.parseLong(LineReader.readLine());
                        String imeKorisnika = LineReader.readLine();
                        String prezimeKorisnika = LineReader.readLine();
                        String emailKorisnika = LineReader.readLine();
                        String telefonKorisnika = LineReader.readLine();
                        PrivatniKorisnik privatniKorisnik=new PrivatniKorisnik(id,imeKorisnika,prezimeKorisnika,
                                emailKorisnika,telefonKorisnika);
                        korisnici.add(privatniKorisnik);
                    }
                    else*/ if(tipKorisnika.equals("2")){
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

    List<Korisnik> poslovniKorisnici = new ArrayList<>();

    public void postaviTablicuKorisnika() {
        idKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        webKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("web"));
        emailKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefonKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        poslovniKorisnikTableView.setItems(FXCollections.observableList(poslovniKorisnici));
    }

    public void initialize() {
        poslovniKorisnici = dohvatiPoslovneKorisnike();
        postaviTablicuKorisnika();
    }

    public void pretraziPrivatneKorisnike(){

    }
}
