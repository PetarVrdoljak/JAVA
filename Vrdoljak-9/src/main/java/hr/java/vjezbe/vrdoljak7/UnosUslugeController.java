package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.Artikl;
import hr.java.vjezbe.vrdoljak7.entitet.Entitet;
import hr.java.vjezbe.vrdoljak7.entitet.Stanje;
import hr.java.vjezbe.vrdoljak7.entitet.Usluga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalLong;

public class UnosUslugeController {

    private static List<Artikl> artikli;

    @FXML
    private TextField nazivTextField;
    @FXML
    private TextArea opisTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private ComboBox<Stanje> stanjeComboBox;


    public void spremiNovuUslugu(){

        StringBuilder porukeOPogreskama = new StringBuilder();

        String naziv = nazivTextField.getText();
        if(naziv.isEmpty()) {
            porukeOPogreskama.append("naziv je obvezan!\n");
        }

        String opis = opisTextField.getText();

        if(opis.isEmpty()) {
            porukeOPogreskama.append("opis je obvezan!\n");
        }
        String cijena = cijenaTextField.getText();
        if(cijena.isEmpty()) {
            porukeOPogreskama.append("cijena je obvezna\n");
        }

        Stanje stanje = stanjeComboBox.getValue();

        if(stanje == null) {
            porukeOPogreskama.append("Stanje je obvezno\n");
        }

        if (porukeOPogreskama.isEmpty()) {

            OptionalLong maxId = artikli.stream()
                    .mapToLong(Entitet::getId).max();
            Usluga novaUsluga = new Usluga(maxId.getAsLong() + 1, naziv, opis,new BigDecimal(cijena), stanje);


            artikli.add(novaUsluga);
            BazaPodataka.pohranaUsluga(novaUsluga);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje usluge");
            alert.setHeaderText("Nova usluga je spremljena");


            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neuspješno spremanje usluge");
            alert.setHeaderText("Nova usluga nije spremljena u aplikaciju");
            alert.setContentText(porukeOPogreskama.toString());

            alert.showAndWait();
        }
    }


    @FXML
    public void initialize() {
        artikli = BazaPodataka.dohvatUsluga();
        ObservableList<Stanje> stanjeOpcije = FXCollections.observableArrayList(Stanje.values());
        stanjeComboBox.setItems(stanjeOpcije);


    }
}
