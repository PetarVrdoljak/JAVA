package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.Korisnik;
import hr.java.vjezbe.vrdoljak7.entitet.PoslovniKorisnik;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PoslovniKorisnikController {

    private static List<PoslovniKorisnik> poslovniKorisnici;
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
    private TableColumn<PoslovniKorisnik,String> idKorisnikaTableColumn;
    @FXML
    private TableColumn<PoslovniKorisnik,String> imeKorisnikaTableColumn;
    @FXML
    private TableColumn<PoslovniKorisnik,String> webKorisnikaTableColumn;
    @FXML
    private TableColumn<PoslovniKorisnik,String> emailKorisnikaTableColumn;
    @FXML
    private TableColumn<PoslovniKorisnik,String> telefonKorisnikaTableColumn;
    @FXML
    private TableView<PoslovniKorisnik> poslovniKorisnikTableView;

    public void initialize() {
        List<Korisnik> korisnikList= BazaPodataka.dohvatPoslovnihKorisnika();
        poslovniKorisnici = korisnikList.stream()
                .filter(korisnik -> korisnik instanceof PoslovniKorisnik)
                .map(korisnik -> (PoslovniKorisnik)korisnik).toList();


        idKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        webKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("web"));
        emailKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefonKorisnikaTableColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        poslovniKorisnikTableView.setItems(FXCollections.observableList(poslovniKorisnici));
    }

    public void pretraziPoslovneKorisnike() {
        String ime = imeTextField.getText().trim();
        String web = webTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String telefon = telefonTextField.getText().trim();

        List<PoslovniKorisnik> rezultatPretrage = poslovniKorisnici.stream()
                .filter(korisnik -> korisnik instanceof PoslovniKorisnik)
                .map(korisnik -> (PoslovniKorisnik) korisnik)
                .filter(poslovniKorisnik ->
                        (ime.isEmpty() || poslovniKorisnik.getNaziv().equalsIgnoreCase(ime))
                                && (web.isEmpty() || poslovniKorisnik.getWeb().equalsIgnoreCase(web))
                                && (email.isEmpty() || poslovniKorisnik.getEmail().equalsIgnoreCase(email))
                                && (telefon.isEmpty() || poslovniKorisnik.getTelefon().equalsIgnoreCase(telefon))
                )
                .collect(Collectors.toList());

        poslovniKorisnikTableView.setItems(FXCollections.observableList(rezultatPretrage));
    }
}
