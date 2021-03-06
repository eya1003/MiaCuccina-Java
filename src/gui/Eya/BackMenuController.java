/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eya;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class BackMenuController implements Initializable {
    
    @FXML
    private Label label;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UserScene(MouseEvent event) throws IOException {
         Parent view7=FXMLLoader.load(getClass().getResource("/gui/UserTable.fxml"));
                Scene scene7=new Scene(view7);
                //Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                Stage window =new Stage();
                window.setScene(scene7);
                window.show();
    }

    @FXML
    private void AvisScene(MouseEvent event) throws IOException {
         Parent view7=FXMLLoader.load(getClass().getResource("/gui/tableView.fxml"));
                Scene scene7=new Scene(view7);
                //Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                Stage window =new Stage();
                window.setScene(scene7);
                window.show();
    }
     @FXML
    void logout(ActionEvent event) throws IOException {
        
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm logout");
        alert.setHeaderText(null);
        alert.setContentText("Continue logging out?");
        Optional <ButtonType> action =alert.showAndWait();
        
        if (action.get()==ButtonType.OK){
        Parent view5=FXMLLoader.load(getClass().getResource("signIn.fxml"));
                Scene scene5=new Scene(view5);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene5);
                window.show();
        }
      
    }
    
}
