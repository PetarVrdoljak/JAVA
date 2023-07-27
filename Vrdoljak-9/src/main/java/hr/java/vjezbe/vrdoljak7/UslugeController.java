package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class UslugeController {

    private static List<Usluga> usluge;

    @FXML
    private TextField idUslugeTextField;
    @FXML
    private TextField imeTextField;
    @FXML
    private TextField opisTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private TextField stanjeTextField;
    @FXML
    private TableColumn<Usluga,String> idUslugeTableColumn;
    @FXML
    private TableColumn<Usluga,String> imeUslugeTableColumn;
    @FXML
    private TableColumn<Usluga,String> opisUslugeTableColumn;
    @FXML
    private TableColumn<Usluga,String> cijenaUslugeTableColumn;
    @FXML
    private TableColumn<Usluga,String> stanjeUslugeTableColumn;
    @FXML
    private TableView<Usluga> uslugeTableView;


    public void initialize() {
        List<Artikl> artikli = BazaPodataka.dohvatUsluga();
        usluge = artikli.stream()
                .filter(artikl -> artikl instanceof Usluga)
                .map(artikl -> (Usluga) artikl)
                .toList();

        idUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        opisUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        cijenaUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        stanjeUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("stanje"));

        uslugeTableView.setItems(FXCollections.observableList(usluge));
    }

    public void pretraziUsluge() {
        String naziv = imeTextField.getText().trim();
        String opis = opisTextField.getText().trim();
        String cijena = cijenaTextField.getText().trim();

        int stanje;
        try {
            stanje = Integer.parseInt(stanjeTextField.getText());
        } catch (NumberFormatException e) {
            stanje = -1;
        }

        int finalStanje = stanje;
        List<Usluga> rezultatPretrage = usluge.stream()
                .filter(artikl ->
                        (naziv.isEmpty() || artikl.getNaziv().equalsIgnoreCase(naziv))
                                && (opis.isEmpty() || artikl.getOpis().equalsIgnoreCase(opis))
                                && (cijena.isEmpty() || artikl.getCijena().toString().equals(cijena))
                                && finalStanje == -1)
                .collect(Collectors.toList());

        uslugeTableView.setItems(FXCollections.observableList(rezultatPretrage));
    }

}
