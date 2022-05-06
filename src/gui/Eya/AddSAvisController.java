/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.eya;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import entities.Avis;
import org.controlsfx.control.Rating;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class AddSAvisController implements Initializable {

    
    @FXML
    private JFXTextField emailFld;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Avis student = null;
    private boolean update;
    int studentId;
    @FXML
    private Label erromessage;
    @FXML
    private Rating rating;
    @FXML
    private JFXTextField contenuFld;
    @FXML
    private JFXDatePicker dateFld;
    @FXML
    private JFXTextField objetFld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(MouseEvent event) {

        connection = MyDB.getInstance().getConnexion();
        String email = emailFld.getText();
        String date = String.valueOf(dateFld.getValue());
        String objet = objetFld.getText();
        String contenu = contenuFld.getText();

        if (email.isEmpty() || date.isEmpty() || objet.isEmpty() || contenu.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }  
        else  if(validateObjet()  & validateEmaill() ){
            getQuery();
            insert();
            clean();
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User details has been updated.");
                alert.showAndWait();
               }
        

    }

    @FXML
    private void clean() {
        emailFld.setText(null);
        dateFld.setValue(null);
        objetFld.setText(null);
        contenuFld.setText(null);
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `avis`( `email`, `date`, `objet`, `contenu`) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `avis` SET "
                    + "`email`=?,"
                    + "`date`=?,"
                    + "`objet`=?,"
                    + "`contenu`= ? WHERE id = '"+avisId+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, emailFld.getText());
            preparedStatement.setString(2, String.valueOf(dateFld.getValue()));
            preparedStatement.setString(3, objetFld.getText());
            preparedStatement.setString(4, contenuFld.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddSAvisController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
int   avisId=0 ;
    void setTextField(int id, String name, LocalDate toLocalDate, String adress, String email) {

        avisId = id;
        emailFld.setText(name);
        dateFld.setValue(toLocalDate);
        objetFld.setText(adress);
        contenuFld.setText(name);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    private boolean validateEmaill(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailFld.getText());
        if(m.find() && m.group().equals(emailFld.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Email");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Email");
                alert.showAndWait();
            
            return false;            
        }

}
     private boolean validateObjet(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(objetFld.getText());
        if(m.find() && m.group().equals(objetFld.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Objet");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Objet");
                alert.showAndWait();
            
            return false;            
        }
    }
      private boolean validateContenu(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(contenuFld.getText());
        if(m.find() && m.group().equals(contenuFld.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Contenu");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Contenu");
                alert.showAndWait();
            
            return false;            
        }
    }
      
}
