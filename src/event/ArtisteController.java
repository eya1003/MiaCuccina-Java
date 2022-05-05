/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import Entities.Artiste;
import Service.ServiceArtiste;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author aziza
 */
public class ArtisteController implements Initializable {

    private Label label;
    @FXML
    private TextField tfnoma;
    @FXML
    private TextField tftypea;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfemail;
    @FXML
    private TableView<Artiste> tablea;
    @FXML
    private TableColumn<Artiste, Integer> idat;
    @FXML
    private TableColumn<Artiste, String> emailt;
    @FXML
    private TableColumn<Artiste, String> nomat;
    @FXML
    private TableColumn<Artiste, String> typeat;
    @FXML
    private TableColumn<Artiste, String> numerot;
    @FXML
    private TextField idsup;
    @FXML
    private TextField tfsearch;
    @FXML
    private TextField tfdescription;

    private int vartri = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterArtiste(ActionEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        Artiste c = new Artiste();
        c.setNom_artiste(tfnoma.getText());
        c.setNom_artiste(tfnoma.getText());
        c.setEmail_artiste(tfemail.getText());
        c.setNum_artiste(Integer.parseInt(tfnumero.getText()));


        sc.AjouterArtiste(c);
        this.AfficherArtiste(event);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(0);
                return null;
            }
        };
    }
    
    
    @FXML
    private void AfficherArtiste(ActionEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        ObservableList<Artiste> artistes = sc.AfficherArtiste();

        idat.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("id_artiste"));
        nomat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("nom_artiste"));
        emailt.setCellValueFactory(new PropertyValueFactory<Artiste, String>("email_artiste"));
        numerot.setCellValueFactory(new PropertyValueFactory<Artiste, String>("num_artiste"));
        typeat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("type_artiste"));
        tablea.setItems(artistes);
    }

    @FXML
    private void selectionner(MouseEvent event) {
        Artiste c = tablea.getSelectionModel().getSelectedItem();
        idsup.setText(String.valueOf(c.getId_artiste()));
        tfnoma.setText(c.getNom_artiste());
        tfemail.setText(c.getEmail_artiste());
        tfnumero.setText(String.valueOf(c.getNum_artiste()));
        tftypea.setText(c.getType_artiste());
        
    }


    

    @FXML
    private void Supprimerartiste(ActionEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        sc.supprimerartiste(Integer.parseInt(idsup.getText()));
        this.AfficherArtiste(event);
    }

    @FXML
    private void ModifierArtiste(ActionEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        Artiste c = new Artiste();
        c.setNom_artiste(tfnoma.getText());
        c.setNom_artiste(tfnoma.getText());
        c.setEmail_artiste(tfemail.getText());
       // c.setNum_artiste(tfnumero.getText());
        sc.ModifierArtiste(c);
    }

    @FXML
    private void searchkey(KeyEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        ObservableList<Artiste> artistes = sc.search(tfsearch.getText());
        idat.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("id_artiste"));
        nomat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("nom_artiste"));
        emailt.setCellValueFactory(new PropertyValueFactory<Artiste, String>("email_artiste"));
        numerot.setCellValueFactory(new PropertyValueFactory<Artiste, String>("num_artiste"));
        typeat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("type_artiste"));
        tablea.setItems(artistes);
    }

    @FXML
    private void tributton(MouseEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        ObservableList<Artiste> artistes;
        if (vartri == 1) {
            vartri = 0;
            artistes = sc.triasc();
            idat.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("id_artiste"));
        nomat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("nom_artiste"));
        emailt.setCellValueFactory(new PropertyValueFactory<Artiste, String>("email_artiste"));
        numerot.setCellValueFactory(new PropertyValueFactory<Artiste, String>("num_artiste"));
        typeat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("type_artiste"));
        tablea.setItems(artistes);

        } else {
            vartri = 1;
            artistes = sc.triadsc();
            idat.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("id_artiste"));
        nomat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("nom_artiste"));
        emailt.setCellValueFactory(new PropertyValueFactory<Artiste, String>("email_artiste"));
        numerot.setCellValueFactory(new PropertyValueFactory<Artiste, String>("num_artiste"));
        typeat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("type_artiste"));
        tablea.setItems(artistes);

        }
    }

}
