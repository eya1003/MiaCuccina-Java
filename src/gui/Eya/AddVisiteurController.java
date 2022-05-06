/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.eya;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import entities.User;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddVisiteurController implements Initializable {

    @FXML
    private JFXTextField usernametf;
    @FXML
    private ComboBox<String> roletf;
   
   
    
    @FXML
    private JFXTextField localisationtf;
    @FXML
    private JFXTextField imagetf;
    @FXML
    private JFXTextField numerotf;
    @FXML
    private JFXTextField emailtf;
   
    @FXML
    private JFXTextField passwordtf;
     String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
   // PreparedStatement ps,ps1; 
    User user = null;
    private boolean update;
    @FXML
    private ImageView imageview;
    @FXML
    private JFXTextField passwordtf1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          roletf.getItems().addAll("Visiteur");
    }    

    @FXML
    void save(MouseEvent event) {
      connection = MyDB.getInstance().getConnexion();
      
       
       
  String  username =usernametf.getText() ;
//  String  firstname =firstnametf.getText() ;
  
//  String  cin =cintf.getText() ;
  
  String   localisation=localisationtf.getText();
  String   images=imagetf.getText();
  String   numero=numerotf.getText();
  String   emailadresse=emailtf.getText();
  String confirmpassword =passwordtf1.getText();
  
  
  String  role=roletf.getValue() ;
 
  String  password=passwordtf.getText() ;
     
 if(username.isEmpty()  ||localisation.isEmpty()  || images.isEmpty() || numero.isEmpty() || emailadresse.isEmpty() || role.isEmpty() || password.isEmpty()  )
            {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
            }
           
            else  if (password.length()<6){
                   //errormessage.setText("Password is too weak, please choose atleast 6 characters");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Password is too weak, please choose atleast 6 characters");
            alert.showAndWait();
               }
            else  if (!(password.equals(confirmpassword))){
                   //errormessage.setText("Password is too weak, please choose atleast 6 characters");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Password arent equals , please confirm your password");
            alert.showAndWait();
               }
                else {
                if(  validateEmail()  & validatePassword() )
                {
               getQuery();
            insert();
            clean();
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User details has been updated.");
                alert.showAndWait();
            }
 
                    
            else {
            getQuery();
            insert();
            clean();
               }        
        
    
    }}
   
       
     private boolean validateEmail(){
      Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailtf.getText());
        if(m.find() && m.group().equals(emailtf.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate email");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid email");
                alert.showAndWait();
            
            return false;            
        }
    }
     private boolean validatePassword(){
           String password= passwordtf.getText();
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(passwordtf.getText());
        if(m.find() && m.group().equals(passwordtf.getText())&&(password.length()>6)){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate password");
                alert.setHeaderText(null);
                alert.setContentText("Password is too weak, please choose atleast 6 characters ,Please Enter Valid password");
                alert.showAndWait();
            
            return false;            
        }
    }
      private boolean ExistingUser() throws SQLException
      {
   
            String username = usernametf.getText();
        
              String sql = "select * from `user` where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
               // errorMessage.setText("Username already taken, please try another username");
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Username already taken, please try another username.");
                alert.showAndWait();
             }
            else{
            
        return false; }
        return false;
    }
    
    @FXML
    void clean() {
       usernametf.setText(null) ;
      
       localisationtf.setText(null);
       imagetf.setText(null);
       numerotf.setText(null);
       emailtf.setText(null);
       
       roletf.setValue(null) ;
      
       passwordtf.setText(null) ;
       
       
    }
     int idUsers=0;
 void getQuery() {

        if (update == false) {
            
            
      query="INSERT INTO `user`(`username`, `localisation`, `images`, `numero`, `emailadresse`,`role`, `password`)  VALUES (?,?,?,?,?,?,?)";
        }else{
            query = "UPDATE `user` SET "
                    + "`username`=?,"
                    
                   
                    + "`localisation`=?,"
                    + "`images`=?,"
                     + "`numero`=?,"
                    + "`emailadresse`=?,"
                   
                    + "`role`=?,"
                   
                    
                    + "`password`=? WHERE id = '"+idUsers+"'";
        }

    }
void insert() {
  //ConnectSql connect = new ConnectSql();
        try {

   //Connection con = connect.getconnection();
    preparedStatement = connection.prepareStatement(query);
          
             preparedStatement.setString(1, usernametf.getText());
             
             preparedStatement.setString(2, localisationtf.getText());
             preparedStatement.setString(3, imagetf.getText());
             preparedStatement.setString(4, numerotf.getText());
              preparedStatement.setString(5, emailtf.getText());
//           
           
             preparedStatement.setString(6, roletf.getValue());
           
               preparedStatement.setString(7, passwordtf.getText());
             preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddVisiteurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField ( int  id,String  username ,String   localisation,
  String  images,String numero, String  emailadresse, String role ,
  String password)   {

          idUsers = id;
       usernametf.setText(username) ;
//       firstnametf.setText(first_name) ;
       
       
       localisationtf.setText(localisation);
       imagetf.setText(images);
       numerotf.setText(numero);
       emailtf.setText(emailadresse);
      
       roletf.setValue(role) ;
     
       passwordtf.setText(password) ;

    }

   public  void setUpdate(boolean b) {
        this.update = b;

    }

    String path;
    File selectedFile;
    @FXML
    private void image(ActionEvent event) throws MalformedURLException {
          FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
               path = selectedFile.toURI().toURL().toExternalForm();
            imageview.setImage(new Image(selectedFile.toURI().toURL().toString()));
            imageview.setFitHeight(150);
            imageview.setFitWidth(250);
            imagetf.setText(path);
            

        }
    }
}
