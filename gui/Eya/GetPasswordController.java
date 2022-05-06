/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eya;
//import Eya.FXMLDocumentController_1;

///import com.sun.java.swing.plaf.windows.resources.windows;
/*
import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hoxha
 */
/*
public class GetPasswordController implements Initializable {
   
    PreparedStatement ps,ps1; 
     String ans;
       String pass;
    

    @FXML
    private TextField usernametxt;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField questiontxt;

    @FXML
    private TextField answertxt;

    @FXML
    private TextField passtxt;

    @FXML
    private Button searchBtn;

    @FXML
    private Button getpswBtn;

    @FXML
    private Button backBtn;
    
    @FXML
    private Label errorLb;
    
    @FXML
    private Label errorAnswer;
    @FXML
    private TextField type_up;
    @FXML
    private Label errorlb2;

    
     @FXML
    void searchPsw(ActionEvent event) throws IOException {
        DbConnect connect = new DbConnect();
       try {
            Connection con = connect.getConnect();
            
            passtxt.setText("");
            answertxt.setText("");
              // String type = type_up.getText().trim();
               String u_name = usernametxt.getText().trim();
            if(u_name.isEmpty()){
                errorLb.setText("Please insert username");
            } 
            
            else {
                String sql = "select name, emailadresse,cin, password, role from user where username=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, u_name);
                //ps.setString(2, type);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    nametxt.setText(rs.getString(12));
                    type_up.setText(rs.getString(11));
                    questiontxt.setText(rs.getString(2));
                    
                    ans = rs.getString(3);
                    pass = rs.getString(4);
                    errorLb.setText("");
                    
                    ps.close();
                    rs.close();
                }
                else {
                    errorLb.setText("Error: Username is incorrect");
                }
                
                
            }
            
        } catch (Exception ex) {
            System.out.println("something wrong" + ex);
        } 
            
    }
    
     @FXML
    void retrivePsw(ActionEvent event) throws IOException {
        
          if(ans.equals(answertxt.getText().trim())){
            passtxt.setText(pass);
        }
        else {
         errorAnswer.setText("Your answer is wrong. Please try again");
        }
            
    }
    
    @FXML
    void backLogin(ActionEvent event) throws IOException {
       Parent view4=FXMLLoader.load(getClass().getResource("/loggingfx/FXMLDocument.fxml"));
                Scene scene4=new Scene(view4);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene4);
                window.show();
     
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
}
*/