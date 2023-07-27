package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.baza.BazaPodataka;
import hr.java.vjezbe.vrdoljak7.entitet.Artikl;
import hr.java.vjezbe.vrdoljak7.entitet.Stan;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class StanController {

    private static List<Stan> stanovi;
    @FXML
    private TextField idStanTextField;
    @FXML
    private TextField imeStanTextField;
    @FXML
    private TextField opisStanTextField;
    @FXML
    private TextField kvadraturaStanTextField;
    @FXML
    private TextField cijenaStanTextField;
    @FXML
    private TextField stanjeStanTextField;
    @FXML
    private TableColumn<Artikl,String> idStanTableColumn;
    @FXML
    private TableColumn<Stan,String> imeStanTableColumn;
    @FXML
    private TableColumn<Stan,String> opisStanTableColumn;
    @FXML
    private TableColumn<Stan, Integer> kvadraturaStanTableColumn;
    @FXML
    private TableColumn<Stan,BigDecimal> cijenaStanTableColumn;
    @FXML
    private TableColumn<Stan,String> stanjeStanTableColumn;
    @FXML
    private TableView<Stan> stanTableView;

    public void initialize() {
        List<Artikl> artikli = BazaPodataka.dohvatStanova();
        stanovi = artikli.stream()
                .filter(artikl -> artikl instanceof Stan)
                .map(artikl -> (Stan) artikl)
                .toList();
        idStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        opisStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        kvadraturaStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("kvadratura"));
        cijenaStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        stanjeStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("stanje"));

        stanTableView.setItems(FXCollections.observableList(stanovi));
    }

    public void pretraziStanove() {
        String naziv = imeStanTextField.getText().trim();
        String opis = opisStanTextField.getText().trim();
        String kvadratura = kvadraturaStanTextField.getText().trim();
        String cijena = cijenaStanTextField.getText().trim();

        int stanje;
        try {
            stanje = Integer.parseInt(stanjeStanTextField.getText());
        } catch (NumberFormatException e) {
            stanje = -1;
        }

        int finalStanje = stanje;
        List<Stan> rezultatPretrage = stanovi.stream()
                .filter(artikl ->
                        (naziv.isEmpty() || artikl.getNaziv().equalsIgnoreCase(naziv))
                                && (opis.isEmpty() || artikl.getOpis().equalsIgnoreCase(opis))
                                && (kvadratura.isEmpty() || artikl.getKvadratura().equals(Integer.valueOf(kvadratura)))
                                && (cijena.isEmpty() || artikl.getCijena().toString().equals(cijena))
                                && finalStanje == -1)
                .collect(Collectors.toList());

        stanTableView.setItems(FXCollections.observableList(rezultatPretrage));
    }
}
