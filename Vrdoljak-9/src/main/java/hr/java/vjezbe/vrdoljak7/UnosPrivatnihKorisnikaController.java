package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.Entitet;
import hr.java.vjezbe.vrdoljak7.entitet.Korisnik;
import hr.java.vjezbe.vrdoljak7.entitet.PrivatniKorisnik;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.OptionalLong;

public class UnosPrivatnihKorisnikaController {

    private static List<Korisnik> korisnikList;

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

    public void spremiNovogPrivatnogKorisnika() {

        StringBuilder porukeOPogreskama = new StringBuilder();


        String imePrivatnogKorisnika = imeTextField.getText();

        if(imePrivatnogKorisnika.isEmpty()) {
            porukeOPogreskama.append("Ime korisnika je obvezno!\n");
        }

        String prezimePrivatnogKorisnika = prezimeTextField.getText();

        if(prezimePrivatnogKorisnika.isEmpty()) {
            porukeOPogreskama.append("Prezime korisnika je obvezno!\n");
        }

        String emailPrivatnogKorisnika = emailTextField.getText();

        if(emailPrivatnogKorisnika.isEmpty()) {
            porukeOPogreskama.append("Email korisnika je obvezan!\n");
        }

        String telefonPrivatnogKorisnika = telefonTextField.getText();

        if(telefonPrivatnogKorisnika.isEmpty()) {
            porukeOPogreskama.append("Telefon korisnika je obvezan!\n");
        }

        if(porukeOPogreskama.isEmpty()) {
            OptionalLong maxId = korisnikList.stream().mapToLong(Entitet::getId).max();
            PrivatniKorisnik korisnik = new PrivatniKorisnik(maxId.getAsLong() , imePrivatnogKorisnika,
                    prezimePrivatnogKorisnika,emailPrivatnogKorisnika, telefonPrivatnogKorisnika);
            korisnikList.add(korisnik);
            BazaPodataka.pohranaPrivatnogKorisnika(korisnik);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje korisnika");
            alert.setHeaderText("Novi korisnik je spremljen");

            alert.showAndWait();
            }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neuspješno spremanje studenta");
            alert.setHeaderText("Novi student nije spremljen u aplikaciju");
            alert.setContentText(porukeOPogreskama.toString());

            alert.showAndWait();
        }
    }


    @FXML
    public void initialize(){
        korisnikList = BazaPodataka.dohvatPrivatnihKorisnika();
    }
}
