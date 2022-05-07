/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import entities.Livraison;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.LivraisonService;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class FrontLivraisonFXMLController implements Initializable {

    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfetat;
    @FXML
    private TextField tfadresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnmenu(MouseEvent event) {
    }

    @FXML
    private void btnReservationfront(MouseEvent event) {
    }

    @FXML
    private void btneventFront(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("event/EventFront.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Event_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnpanierFront(MouseEvent event) {
    }

    @FXML
    private void btnLivraisonFront(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AjoutLivraison(ActionEvent event) {
        Livraison liv = new Livraison(tfdescription.getText(),
                (Integer.parseInt(tfetat.getText())),
                (tfadresse.getText())
//                (Integer.parseInt(tfidliv.getText()))
                
                        
                 
        );
        LivraisonService ls = new LivraisonService();
        try {
            ls.ajouterLivrai(liv);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Livraison  ajoutée");
            alert.show();
            tfdescription.setText(""); 
            tfetat.setText("");
//            tfidliv.setText("");
            tfadresse.setText("");
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
