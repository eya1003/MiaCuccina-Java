/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import entities.Evenement;
import services.ServiceEvenement;
import java.io.IOException;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aziza
 */
public class Event_frontController implements Initializable {

    @FXML
    private TableView<Evenement> tablec;
    @FXML
    private TableColumn<Evenement, Integer> idt;
    @FXML
    private TableColumn<Evenement, Date> datet;
    @FXML
    private TableColumn<Evenement, String> nomt;
    @FXML
    private TableColumn<Evenement, String> typet;
    @FXML
    private TableColumn<Evenement, String> descriptiont;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    
    @FXML
    private void AfficherEvenement(ActionEvent event) {
        ServiceEvenement sc = new ServiceEvenement();
        ObservableList<Evenement> evenements = sc.AfficherEvenement();

        idt.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_event"));
        nomt.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_event"));
        typet.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_event"));
        descriptiont.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description_event"));
        tablec.setItems(evenements);
    }

    @FXML
    private void btnmenu(MouseEvent event) {
    }

    @FXML
    private void btnReservationfront(MouseEvent event) {
    }

    @FXML
    private void btneventFront(MouseEvent event) {
    }

    @FXML
    private void btnpanierFront(MouseEvent event) {
    }

    @FXML
    private void btnLivraisonFront(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
    }

    
}
