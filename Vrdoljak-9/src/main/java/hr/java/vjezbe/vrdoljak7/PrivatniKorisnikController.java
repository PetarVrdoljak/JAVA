package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.Korisnik;
import hr.java.vjezbe.vrdoljak7.entitet.PrivatniKorisnik;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PrivatniKorisnikController {

    private static List<PrivatniKorisnik> privatniKorisnici;
    @FXML
    private TextField idKorisnikaTextField;
    @FXML
    private TextField imeTextField;
    @FXML
    private TextField prezimeTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField telefonTextField;
    @FXML
    private TableColumn<PrivatniKorisnik,String> idKorisnikaTableColumn;
    @FXML
    private TableColumn<PrivatniKorisnik,String> imeKorisnikaTableColumn;
    @FXML
    private TableColumn<PrivatniKorisnik,String> prezimeKorisnikaTableColumn;
    @FXML
    private TableColumn<PrivatniKorisnik,String> emailKorisnikaTableColumn;
    @FXML
    private TableColumn<PrivatniKorisnik,String> telefonKorisnikaTableColumn;
    @FXML
    private TableView<PrivatniKorisnik> privatniKorisnikTableView;


    public void initialize() {
        List<Korisnik> korisnikList= BazaPodataka.dohvatPrivatnihKorisnika();
        privatniKorisnici = korisnikList.stream()
                .filter(korisnik -> korisnik instanceof PrivatniKorisnik)
                .map(korisnik -> (PrivatniKorisnik)korisnik).toList();


        idKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezimeKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        emailKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefonKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        privatniKorisnikTableView.setItems(FXCollections.observableList(privatniKorisnici));

    }
    public void pretraziPrivatneKorisnike() {
        String ime = imeTextField.getText().trim();
        String prezime = prezimeTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String telefon = telefonTextField.getText().trim();

        List<PrivatniKorisnik> rezultatPretrage = privatniKorisnici.stream()
                .filter(korisnik -> korisnik instanceof PrivatniKorisnik)
                .map(korisnik -> (PrivatniKorisnik) korisnik)
                .filter(privatniKorisnik ->
                        (ime.isEmpty() || privatniKorisnik.getIme().equalsIgnoreCase(ime))
                                && (prezime.isEmpty() || privatniKorisnik.getPrezime().equalsIgnoreCase(prezime))
                                && (email.isEmpty() || privatniKorisnik.getEmail().equalsIgnoreCase(email))
                                && (telefon.isEmpty() || privatniKorisnik.getTelefon().equalsIgnoreCase(telefon))
                )
                .collect(Collectors.toList());

        privatniKorisnikTableView.setItems(FXCollections.observableList(rezultatPretrage));
    }
}
