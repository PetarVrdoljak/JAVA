package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.Entitet;
import hr.java.vjezbe.vrdoljak7.entitet.Korisnik;
import hr.java.vjezbe.vrdoljak7.entitet.PoslovniKorisnik;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.OptionalLong;

public class UnosPoslovnihKorisnikaController {
    private static List<Korisnik> korisnikList;

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

    public void spremiNovogPoslovnogKorisnika() {

        StringBuilder porukeOPogreskama = new StringBuilder();


        String nazivFirme = imeTextField.getText();

        if(nazivFirme.isEmpty()) {
            porukeOPogreskama.append("Ime korisnika je obvezno!\n");
        }

        String webFirme = webTextField.getText();

        if(webFirme.isEmpty()) {
            porukeOPogreskama.append("Prezime korisnika je obvezno!\n");
        }

        String emailFirme = emailTextField.getText();

        if(emailFirme.isEmpty()) {
            porukeOPogreskama.append("Email korisnika je obvezan!\n");
        }

        String telefonFirme = telefonTextField.getText();

        if(telefonFirme.isEmpty()) {
            porukeOPogreskama.append("Telefon korisnika je obvezan!\n");
        }

        if(porukeOPogreskama.isEmpty()) {
            OptionalLong maxId = korisnikList.stream().mapToLong(Entitet::getId).max();
            PoslovniKorisnik korisnik = new PoslovniKorisnik(maxId.getAsLong() , nazivFirme,
                    webFirme,emailFirme, telefonFirme);
            korisnikList.add(korisnik);
            BazaPodataka.pohranaPoslovnogKorisnika(korisnik);

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
        korisnikList = BazaPodataka.dohvatPoslovnihKorisnika();
    }
}

