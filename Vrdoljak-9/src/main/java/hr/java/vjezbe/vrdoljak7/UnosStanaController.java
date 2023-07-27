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

public class UnosStanaController {
    private static List<Artikl> artikli;

    @FXML
    private TextField nazivTextField;
    @FXML
    private TextArea opisTextArea;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private TextField kvadaraturaTextField;
    @FXML
    private ComboBox<Stanje> stanjeComboBox;


    public void spremiNoviStan(){

        StringBuilder porukeOPogreskama = new StringBuilder();

        String naziv = nazivTextField.getText();
        if(naziv.isEmpty()) {
            porukeOPogreskama.append("naziv je obvezan!\n");
        }

        String opis = opisTextArea.getText();

        if(opis.isEmpty()) {
            porukeOPogreskama.append("opis je obvezan!\n");
        }
        String kvadratura = kvadaraturaTextField.getText();

        if(kvadratura.isEmpty()) {
            porukeOPogreskama.append("Iskustvo je obvezno!\n");
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
            Stan noviStan = new Stan(maxId.getAsLong() + 1, naziv, opis,Integer.parseInt(kvadratura)
                    , new BigDecimal(cijena), stanje);


            artikli.add(noviStan);
            BazaPodataka.pohranaStana(noviStan);


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
        artikli = BazaPodataka.dohvatStanova();
        ObservableList<Stanje> stanjeOpcije = FXCollections.observableArrayList(Stanje.values());
        stanjeComboBox.setItems(stanjeOpcije);


    }
}
