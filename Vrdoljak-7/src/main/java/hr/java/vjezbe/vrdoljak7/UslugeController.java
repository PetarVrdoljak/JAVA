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

public class UslugeController {

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
    private TableColumn<Artikl,String> idUslugeTableColumn;
    @FXML
    private TableColumn<Artikl,String> imeUslugeTableColumn;
    @FXML
    private TableColumn<Artikl,String> opisUslugeTableColumn;
    @FXML
    private TableColumn<Artikl,String> cijenaUslugeTableColumn;
    @FXML
    private TableColumn<Artikl,String> stanjeUslugeTableColumn;
    @FXML
    private TableView<Artikl> uslugeTableView;
    private static List<Artikl> dohvatiUslugeArtikala() {
        File artikl = new File("dat/artikli.txt");
        List<Artikl> artikli = new ArrayList<>();
        if(artikl.exists())
            try (FileReader fileReader= new FileReader(artikl);
                 BufferedReader lineReader = new BufferedReader(fileReader)) {
                String tipArtikla;
                while ((tipArtikla = lineReader.readLine()) != null) {
                    if (tipArtikla.equals("1")){
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
                    else if(tipArtikla.equals("3")){
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
    List<Artikl> usluge = new ArrayList<>();

    public void postaviTablicuUsluga() {
        idUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imeUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        opisUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        cijenaUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        stanjeUslugeTableColumn.setCellValueFactory(new PropertyValueFactory<>("stanje"));

        uslugeTableView.setItems(FXCollections.observableList(usluge));
    }

    public void initialize() {
        usluge = dohvatiUslugeArtikala();
        postaviTablicuUsluga();
    }

}
