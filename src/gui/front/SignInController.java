/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;


import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class SignInController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtusername;
    @FXML
    private Button loginBtn;
    @FXML
    private PasswordField txtpass;
    @FXML
    private Button signupBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorMsg;
    @FXML
    private Button forgotBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private ComboBox<String> roletf;
     Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        roletf.getItems().addAll("Visiteur" ,"Admin");
    }    

  @FXML
    void login(ActionEvent event) throws IOException {
          DbConnect connect = new DbConnect();
        try {
            Connection con = connect.getConnect();

            String username = txtusername.getText().trim();
            String password = txtpass.getText().trim();
             String type = roletf.getValue().toString();
            if(username.isEmpty() || password.isEmpty() || type.isEmpty()){
               errorMsg.setText("Please insert username and password and type");
            }
            else
            {

                PreparedStatement ps = con.prepareStatement("select * from user where username=?"
                    + " and password=?" + "and role=?");

                ps.setString(1,txtusername.getText().trim() );
                ps.setString(2, txtpass.getText().trim());
                 ps.setString(3, roletf.getValue().trim());
                 
                ResultSet rs = ps.executeQuery();
                 System.out.println(type);
           
                if(rs.next()){
                   if( type.equals("Admin"))
                   {
                    Parent view3=FXMLLoader.load(getClass().getResource("MenuBack.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
               window.show();
                   }
                   else {
                       Parent view3=FXMLLoader.load(getClass().getResource("MenuFront.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
               window.show();}
                   }
                

                else {
                    errorMsg.setText("Invalid credentials. Please try again");
                }
            }
        }
        catch(Exception ex){
            System.out.println("error" + ex.toString());
        }

    }

    @FXML
    private void signupScene(ActionEvent event) throws IOException {
        Parent view3=FXMLLoader.load(getClass().getResource("/gui/front/SignupPage.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =new Stage();
                window.setScene(scene3);
                window.show();
    }

    @FXML
    private void forgotPsw(ActionEvent event) throws IOException {
        Parent view4=FXMLLoader.load(getClass().getResource("/gui/SendCode.fxml"));
                Scene scene4=new Scene(view4);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene4);
                window.show();
    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    

}
