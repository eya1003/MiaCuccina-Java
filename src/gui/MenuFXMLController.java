/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class MenuFXMLController implements Initializable {

    private TextField tfphone;
    private TextField  tfadresse;  
    private TableView<?> menutable;

    private DatePicker tdatedebut;
    private DatePicker tdatefin;
    @FXML
//    private TableView<?> menutable;
//    @FXML
//    private TableColumn<?, ?> nomcol;
//    @FXML
//    private TableColumn<?, ?> prixcol;
//    @FXML
//    private TableColumn<?, ?> desccol;
//    @FXML
//    private TableColumn<?, ?> imgccol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void save(MouseEvent event) {
          String addr = tfadresse.getText();
        try {
             
             if (  addr.isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Ecrivez votre adresse!");
                alert.show();
            }
             else {
                 System.out.println(Date.valueOf(tdatedebut.getValue()).toString());
         System.out.println(Date.valueOf(tdatefin.getValue()).toString());
        Reservation p = new Reservation(Integer.parseInt(tfphone.getText()), tfadresse.getText(),Date.valueOf(tdatedebut.getValue()),Date.valueOf(tdatefin.getValue()));
        ReservationService ps = new ReservationService();
                    ps.ajouterReservation(p);
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Succes");
                            alert.setContentText("Reservation ajouté");
                            alert.show();
           
             }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
                }
    

    private void clean(MouseEvent event) {
         tfphone.setText(null);
        tfadresse.setText(null);
        
    }

    @FXML
    private void btnmenu(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/front/MenuFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnReservationfront(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/front/FrontReservationFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btneventFront(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/front/"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnpanierFront(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/front/"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnLivraisonFront(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/front/"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
