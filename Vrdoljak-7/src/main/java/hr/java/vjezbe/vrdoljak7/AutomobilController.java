package hr.java.vjezbe.vrdoljak7;

import hr.java.vjezbe.vrdoljak7.entitet.*;
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

public class AutomobilController {

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
    private TableColumn<Artikl,String> idAutomobilTableColumn;
    @FXML
    private TableColumn<Artikl,String> imeAutomobileTableColumn;
    @FXML
    private TableColumn<Artikl,String> opisAutomobilTableColumn;
    @FXML
    private TableColumn<Artikl,BigDecimal> snagaKSAutomobilTableColumn;
    @FXML
    private TableColumn<Artikl,BigDecimal> cijenaAutomobilTableColumn;
    @FXML
    private TableColumn<Artikl,String> stanjeAutomobilTableColumn;
    @FXML
    private TableView<Artikl> automobilTableView;
    private static List<Artikl> dohvatiAutomobilArtikala() {
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
                    else*/ if(tipArtikla.equals("2")){
                        Long id = Long.parseLong(lineReader.readLine());
                        String naziv = lineReader.readLine();
                        String opis = lineReader.readLine();
                        BigDecimal snagaKs= new BigDecimal(lineReader.readLine());
                        BigDecimal cijena = new BigDecimal(lineReader.readLine());
                        Stanje stanje = Stanje.fromBroj(Integer.parseInt(lineReader.readLine()));
                        Automobil automobil = new Automobil(id, naziv, opis,snagaKs, cijena, stanje);
                        artikli.add(automobil);
                    }
                    /*else if(tipArtikla.equals("3")){
                        Long id = Long.parseLong(lineReader.readLine());
                        String naziv = lineReader.readLine();
                        String opis = lineReader.readLine();
                        int snagaKs= Integer.parseInt(lineReader.readLine());
                        BigDecimal cijena = new BigDecimal(lineReader.readLine());
                        Stanje stanje = Stanje.fromBroj(Integer.parseInt(lineReader.readLine()));
                        Stan stan = new Stan(id, naziv, opis,snagaKs, cijena, stanje);
                        artikli.add(stan);
                    }*/

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return artikli;
    }
    List<Artikl> automobili = new ArrayList<>();

    public void postaviTablicuAutomobila() {
        idAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeAutomobileTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        opisAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        snagaKSAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("snagaKs"));
        cijenaAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        stanjeAutomobilTableColumn.setCellValueFactory(new PropertyValueFactory<>("stanje"));

        automobilTableView.setItems(FXCollections.observableList(automobili));
    }

    public void initialize() {
        automobili = dohvatiAutomobilArtikala();
        postaviTablicuAutomobila();
    }
}
