package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.entitet.Artikl;
import hr.java.vjezbe.vrdoljak7.entitet.Stan;
import hr.java.vjezbe.vrdoljak7.entitet.Stanje;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StanController {
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
    private TableColumn<Artikl,String> imeStanTableColumn;
    @FXML
    private TableColumn<Artikl,String> opisStanTableColumn;
    @FXML
    private TableColumn<Artikl, Integer> kvadraturaStanTableColumn;
    @FXML
    private TableColumn<Artikl,BigDecimal> cijenaStanTableColumn;
    @FXML
    private TableColumn<Artikl,String> stanjeStanTableColumn;
    @FXML
    private TableView<Artikl> stanTableView;
    private static List<Artikl> dohvatiStanArtikala() {
        File artikl = new File("dat/artikli.txt");
        List<Artikl> artikli = new ArrayList<>();
        if(artikl.exists())
            try (FileReader fileReader= new FileReader(artikl);
                 BufferedReader lineReader = new BufferedReader(fileReader)) {
                String tipArtikla;
                while ((tipArtikla = lineReader.readLine()) != null) {
                    /*if (tipArtikla.equals("1")){
                        Long id = Long.parseLong(lineReader.readLine());
                        String naziv = lineReader.readLine();
                        String opis = lineReader.readLine();
                        BigDecimal cijena = new BigDecimal(lineReader.readLine());
                        Stanje stanje = Stanje.fromBroj(Integer.parseInt(lineReader.readLine()));
                        Usluga usluga = new Usluga(id, naziv, opis, cijena, stanje);
                        artikli.add(usluga);
                    }
                    else if(tipArtikla.equals("2")){
                        Long id = Long.parseLong(lineReader.readLine());
                        String naziv = lineReader.readLine();
                        String opis = lineReader.readLine();
                        BigDecimal snagaKs= new BigDecimal(lineReader.readLine());
                        BigDecimal cijena = new BigDecimal(lineReader.readLine());
                        Stanje stanje = Stanje.fromBroj(Integer.parseInt(lineReader.readLine()));
                        Automobil automobil = new Automobil(id, naziv, opis,snagaKs, cijena, stanje);
                        artikli.add(automobil);
                    }
                    else*/ if(tipArtikla.equals("3")){
                        Long id = Long.parseLong(lineReader.readLine());
                        String naziv = lineReader.readLine();
                        String opis = lineReader.readLine();
                        int snagaKs= Integer.parseInt(lineReader.readLine());
                        BigDecimal cijena = new BigDecimal(lineReader.readLine());
                        Stanje stanje = Stanje.fromBroj(Integer.parseInt(lineReader.readLine()));
                        Stan stan = new Stan(id, naziv, opis,snagaKs, cijena, stanje);
                        artikli.add(stan);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return artikli;
    }
    List<Artikl> stanovi = new ArrayList<>();

    public void postaviTablicuAutomobila() {
        idStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        opisStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        kvadraturaStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("kvadratura"));
        cijenaStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        stanjeStanTableColumn.setCellValueFactory(new PropertyValueFactory<>("stanje"));

        stanTableView.setItems(FXCollections.observableList(stanovi));
    }

    public void initialize() {
        stanovi = dohvatiStanArtikala();
        postaviTablicuAutomobila();
    }
}
