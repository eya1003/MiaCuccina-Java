/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author aziza
 */


public class FXMLDocumentController {
    
    @FXML
    private Button ArtisteeBtn;     
    @FXML
    private Button EvenementBtn;     
    @FXML

    private void ArtisteeScene(ActionEvent event) throws IOException {
        Parent view3 = FXMLLoader.load(getClass().getResource("/event/ArtisteFXML.fxml"));
        Scene scene3 = new Scene(view3);
        Stage window = new Stage();
        window.setScene(scene3);
        window.show();
    }

    @FXML
    private void EventScene(ActionEvent event) throws IOException {
        Parent view3 = FXMLLoader.load(getClass().getResource("/event/EvenementFXML.fxml"));
        Scene scene3 = new Scene(view3);
        Stage window = new Stage();
        window.setScene(scene3);
        window.show();
    }
}
