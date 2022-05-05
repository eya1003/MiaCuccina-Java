/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizaa
 */
public class Controller implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnevenement;
    @FXML
    private Button btnartiste;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
@FXML
    private void EventScene(ActionEvent event) throws IOException {
        Parent view3=FXMLLoader.load(getClass().getResource("/event/EvenementFXML.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =new Stage();
                window.setScene(scene3);
                window.show();
    }
    
    @FXML
    private void ArtisteScene(ActionEvent event) throws IOException {
        Parent view3=FXMLLoader.load(getClass().getResource("/event/ArtisteFXML.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =new Stage();
                window.setScene(scene3);
                window.show();
    }
    
    @FXML
    private void handleClicks(ActionEvent event) {
    }
}
    