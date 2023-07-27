package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class AutomobilController {

    private static List<Automobil> automobili;

    @FXML
    private TextField idAutomobilTextField;
    @FXML
    private TextField imeAutomobilTextField;
    @FXML
    private TextField opisAutomobilTextField;
    @FXML
    private TextField snagaKSAutomobilTextField;
    @FXML
    private TextField cijenaAutomobilTextField;
    @FXML
    private TextField stanjeAutomobilTextField;
    @FXML
    private TableColumn<Automobil,String> idAutomobilTableColumn;
    @FXML
    private TableColumn<Automobil,String> imeAutomobileTableColumn;
    @FXML
    private TableColumn<Automobil,String> opisAutomobilTableColumn;
    @FXML
    private TableColumn<Automobil,BigDecimal> snagaKSAutomobilTableColumn;
    @FXML
    private TableColumn<Automobil,BigDecimal> cijenaAutomobilTableColumn;
    @FXML
    private TableColumn<Automobil,String> stanjeAutomobilTableColumn;
    @FXML
    private TableView<Automobil> automobilTableView;


    public void initialize() {
        List<Artikl> artikli = BazaPodataka.dohvatAutomobila();
        automobili = artikli.stream()
                .filter(artikl -> artikl instanceof Automobil)
                .map(artikl -> (Automobil) artikl)
                .toList();
        idAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeAutomobileTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        opisAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        snagaKSAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("snagaKs"));
        cijenaAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        stanjeAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("stanje"));

        automobilTableView.setItems(FXCollections.observableList(automobili));
    }

    public void pretraziAutomobile() {
        String naziv = imeAutomobilTextField.getText().trim();
        String opis = opisAutomobilTextField.getText().trim();
        String snagaKs = snagaKSAutomobilTextField.getText().trim();
        String cijena = cijenaAutomobilTextField.getText().trim();

        int stanje;
        try {
            stanje = Integer.parseInt(stanjeAutomobilTableColumn.getText());
        } catch (NumberFormatException e) {
            stanje = -1;
        }

        int finalStanje = stanje;
        List<Automobil> rezultatPretrage = automobili.stream()
                .filter(artikl ->
                        (naziv.isEmpty() || artikl.getNaziv().equalsIgnoreCase(naziv))
                                && (opis.isEmpty() || artikl.getOpis().equalsIgnoreCase(opis))
                                && (snagaKs.isEmpty() || artikl.getSnagaKs().toString().equals(snagaKs))
                                && (cijena.isEmpty() || artikl.getCijena().toString().equals(cijena))
                                && finalStanje == -1)
                .collect(Collectors.toList());

        automobilTableView.setItems(FXCollections.observableList(rezultatPretrage));
    }

}
