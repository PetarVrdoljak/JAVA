package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.*;
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

public class UnosAutomobilaController {
    private static List<Artikl> artikli;

    @FXML
    private TextField nazivTextField;
    @FXML
    private TextArea opisTextArea;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private TextField snagaTextField;
    @FXML
    private ComboBox<Stanje> stanjeComboBox;


    public void spremiNoviAutomobil(){

        StringBuilder porukeOPogreskama = new StringBuilder();

        String naziv = nazivTextField.getText();
        if(naziv.isEmpty()) {
            porukeOPogreskama.append("naziv je obvezan!\n");
        }

        String opis = opisTextArea.getText();

        if(opis.isEmpty()) {
            porukeOPogreskama.append("opis je obvezan!\n");
        }
        String snagaKs = snagaTextField.getText();

        if(snagaKs.isEmpty()) {
            porukeOPogreskama.append("snaga je obvezna!\n");
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
            Automobil noviAutomobil = new Automobil(maxId.getAsLong() + 1, naziv, opis,new BigDecimal(snagaKs), new BigDecimal(cijena), stanje);


            artikli.add(noviAutomobil);
            BazaPodataka.pohranaAutomobila(noviAutomobil);


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
        artikli = BazaPodataka.dohvatAutomobila();
        ObservableList<Stanje> stanjeOpcije = FXCollections.observableArrayList(Stanje.values());
        stanjeComboBox.setItems(stanjeOpcije);


    }
}
