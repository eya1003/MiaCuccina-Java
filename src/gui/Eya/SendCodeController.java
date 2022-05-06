/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.eya ;
import java.util.logging.Level;
import java.util.logging.Logger;


import entities.User;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author SALAH
 */
public class SendCodeController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button envoyer;
    @FXML
    private TextField code;
    @FXML
    private Button valider;
    
    int randomcode ;
    private Component root;
    @FXML
    private TextField mdp1;
    @FXML
    private TextField mdp2;
    @FXML
    private Button btreset;
    Connection cnx = MyDB.getInstance().getConnexion();
       // User user =new User());
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  private PreparedStatement pst;
    private ResultSet ls;
    private ResultSet rs;
    //emaill= email.getText();
    public boolean findByEmail(String email) {
            //String emailadresse =email.getText();
              // DbConnect connect = new DbConnect();
        String query = "Select 	emailadresse from user where emailadresse='" + email+ "'";
       
         try {
             Statement stmt = cnx.createStatement();
            rs = stmt.executeQuery(query);
             if (rs.next()) {
                 return true;
             }
         } catch (SQLException ex) {
              Logger.getLogger(SendCodeController.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;

    }
 
    @FXML
    public void envoyer (ActionEvent event)
    {
        if (findByEmail(email.getText())== false)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Email enregistrer de ce genre !! Veuiller crée un compte ");
            alert.showAndWait();
        }else
        {
        try {
        Random rand = new Random();
        randomcode = rand.nextInt(999999);
        String host = "smtp.gmail.com";
        String user = "enistest31@gmail.com";
        String pass = "ca3131999";
        String to =email.getText();
        String Subject ="Reseting Code ";
        String message ="Your Reseting Code : "+randomcode;
        boolean sessionDebug =false ;
        Properties pros = System.getProperties();
        pros.put("mail.smtp.starttls.enable","true");
        pros.put("mail.smtp.host","host");
        pros.put("mail.smtp.port","587");
        pros.put("mail.smtp.starttls.auth","true");
        pros.put("mail.smtp.starttls.required","true");
        
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session mailsession =Session.getDefaultInstance(pros,null);
        mailsession.setDebug(sessionDebug);
        Message msg = new MimeMessage(mailsession);
        msg.setFrom(new InternetAddress(user));
        InternetAddress [] address ={new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(Subject);
        msg.setText(message);
        Transport transport =mailsession.getTransport("smtp");
        transport.connect(host,user,pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("code has been send to the mail");
                    alert1.showAndWait();
       // JOptionPane.showMessageDialog(null,"code has been send to the mail");
        }catch (Exception ex)
                {
           JOptionPane.showMessageDialog(root,ex);         
                } 
        }
    }
    @FXML
    public void valider (ActionEvent event) throws IOException
    {
        if (Integer.valueOf(code.getText())==randomcode)
        {
             mdp1.setVisible(true);
             mdp2.setVisible(true);
             btreset.setVisible(true);
          
        }
    }
 public void UpdatePass(String pass,String email) {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE user SET password= '"+pass+"' where emailadresse= '"+email+"'";
        stm.executeUpdate(requete);
        System.out.println("Mot de passe  modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(SendCodeController.class.getName()).log(Level.SEVERE, null, ex);
       
   }
   }
    @FXML
    private void Reset(ActionEvent event) throws IOException {
       if (mdp1.getText().equals(mdp2.getText()))
       {
           if (findByEmail(email.getText())==true)
           {
           UpdatePass(mdp1.getText(),email.getText());
            Alert alertA = new Alert(Alert.AlertType.CONFIRMATION);
            alertA.setTitle("Succes");
            alertA.setHeaderText(null);
            alertA.setContentText("Mot de passe Modifié avec Succés");
            alertA.showAndWait();
            Optional<ButtonType> actionA = alertA.showAndWait();
             if (actionA.get() == ButtonType.OK) {
                  Parent root =FXMLLoader.load(getClass().getResource("Login.fxml")); 
                  Scene scene= new Scene(root);
                  Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                   window.setScene(scene);
                   window.show();}
        
           } else if (findByEmail(email.getText())== true)
           {
           UpdatePass(mdp1.getText(),email.getText());
            Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
            alertC.setTitle("Succes");
            alertC.setHeaderText(null);
            alertC.setContentText("Mot de passe Modifié avec Succés");
            Optional<ButtonType> actionC = alertC.showAndWait();
            if (actionC.get() == ButtonType.OK) {
                  Parent root =FXMLLoader.load(getClass().getResource("signIn.fxml")); 
                  Scene scene= new Scene(root);
                  Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                  window.setScene(scene);
                  window.show();}
          } else if (findByEmail(email.getText())==true)
           {
           UpdatePass(mdp1.getText(),email.getText());
            Alert alertAD = new Alert(Alert.AlertType.CONFIRMATION);
            alertAD.setTitle("Succes");
            alertAD.setHeaderText(null);
            alertAD.setContentText("Mot de passe Modifié avec Succés");
            Optional<ButtonType> actionAD = alertAD.showAndWait();
            if (actionAD.get() == ButtonType.OK) {
                  Parent root =FXMLLoader.load(getClass().getResource("signIn.fxml")); 
                  Scene scene= new Scene(root);
                  Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                   window.setScene(scene);
                   window.show();}
           }
       }else 
       {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Verifier que les deux mot passe sont egaux");
            alert.showAndWait();
       }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm back to Menu");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
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
