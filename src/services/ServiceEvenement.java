/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Evenement;
import services.IserviceEvenement;
import utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author User
 */
public class ServiceEvenement implements IserviceEvenement {

    Connection cnx;

    public ServiceEvenement() {
        cnx = MyDB.getInstance().getConnexion();
    }

    @Override
    public void AjouterEvenement(Evenement c) {
        try {
            Statement stm = cnx.createStatement();

            String query = "INSERT INTO evenement(date_event,nom_event,type_event,description_event) VALUES ('" + c.getDate_event() + "','" + c.getNom_event() + "','" + c.getType_event() + "','" + c.getDescription_event() + "')";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir ajouter cet evenement ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Ajout");
                alert2.setHeaderText("Evenement ajouté");
                alert2.setContentText("L'evenement a été ajouté avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Evenement> AfficherEvenement() {
        ObservableList<Evenement> evenements = FXCollections.observableArrayList();
        try {
            Statement stm;

            stm = cnx.createStatement();

            String query = "SELECT * from `evenement`";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Evenement c = new Evenement();
                c.setId(rst.getInt("id"));
                c.setDate_event(rst.getDate("date_event"));
                c.setNom_event(rst.getString("nom_event"));
                c.setType_event(rst.getString("type_event"));
                c.setDescription_event(rst.getString("description_event"));
                evenements.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return evenements;
    }

    @Override
    public void supprimerevenement(int id) {
        try {
            Statement stm = cnx.createStatement();

            String query = " Delete FROM evenement where id='" + id + "'";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir supprimer cet evenement ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Suppression");
                alert2.setHeaderText("Evenement Supprimé");
                alert2.setContentText("L'evenement a été supprimé avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void ModifierEvenement(Evenement c) {
        try {

            PreparedStatement ps;

            ps = cnx.prepareStatement("UPDATE  evenement set `date_event`=?,`nom_event`=?,`type_event`=?,`description_event`=? where id=" + c.getId());
            ps.setDate(1, (java.sql.Date) c.getDate_event());
            ps.setString(2, c.getNom_event());
            ps.setString(3, c.getType_event());
            ps.setString(4, c.getDescription_event());
            ps.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Evenement Modifié");
            alert.setContentText("L'evenement a été modifié avec success!");
            alert.showAndWait();

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout");
            alert.setHeaderText("Evenement Ajouté");
            alert.setContentText("L'evenement a été Ajouté avec success!");
            alert.showAndWait();
        }
    }

    public ObservableList<Evenement> search(String input) {
        ObservableList<Evenement> evenements = FXCollections.observableArrayList();
        try {
            Statement stm;
            stm = cnx.createStatement();

            String query = "SELECT * from evenement where nom_event like '%" + input + "%'";
            ResultSet rst = stm.executeQuery(query);
            Evenement form;
            while (rst.next()) {
                Evenement c = new Evenement();
                c.setId(rst.getInt("id"));
                c.setDate_event(rst.getDate("date_event"));
                c.setNom_event(rst.getString("nom_event"));
                c.setType_event(rst.getString("type_event"));
                c.setDescription_event(rst.getString("description_event"));
                form = new Evenement(rst.getInt("id"), rst.getDate("date_event"), rst.getString("nom_event"), rst.getString("type_event"), rst.getString("description_event"));
                evenements.add(form);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return evenements;
    }

    public ObservableList<Evenement> triasc() {
        ObservableList<Evenement> evenements = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * from evenement ORDER by type_event ASC";
            ResultSet rst = stm.executeQuery(query);
            Evenement form;
            while (rst.next()) {
                Evenement c = new Evenement();
                c.setId(rst.getInt("id"));
                c.setDate_event(rst.getDate("date_event"));
                c.setNom_event(rst.getString("nom_event"));
                c.setType_event(rst.getString("type_event"));
                c.setDescription_event(rst.getString("description_event"));
                form = new Evenement(rst.getInt("id"), rst.getDate("date_event"), rst.getString("nom_event"), rst.getString("type_event"), rst.getString("description_event"));
                evenements.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return evenements;
    }

    public ObservableList<Evenement> triadsc() {
        ObservableList<Evenement> evenements = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * from evenement ORDER by type_event DESC";
            ResultSet rst = stm.executeQuery(query);
            Evenement form;
            while (rst.next()) {
                Evenement c = new Evenement();
                c.setId(rst.getInt("id"));
                c.setDate_event(rst.getDate("date_event"));
                c.setNom_event(rst.getString("nom_event"));
                c.setType_event(rst.getString("type_event"));
                c.setDescription_event(rst.getString("description_event"));
                form = new Evenement(rst.getInt("id"), rst.getDate("date_event"), rst.getString("nom_event"), rst.getString("type_event"), rst.getString("description_event"));
                evenements.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return evenements;
    }
    
    public void sendmail(String mail,String nom_event) {
		
		String to = mail;
		String from = "mia.cucinaaa@gmail.com";
		final String username = "mia.cucinaaa@gmail.com";
		final String password = "balalaw123$";
		
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");  
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		
		javax.mail.Session session = javax.mail.Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
     }
         });
		
		try {
    //create a MimeMessage object
    Message message = new MimeMessage(session);
 
    //set From email field 
    message.setFrom(new InternetAddress(from));
 
    //set To email field
    message.setRecipients(javax.mail.Message.RecipientType.TO,
               InternetAddress.parse(to));
 
    //set email subject field
    message.setSubject("UPCOMING EVENT!");
 
    //set the content of the email message
    message.setText("Our upcoming event event is: " + nom_event+"\n" + "Come join us!");

    //send the email message
    Transport.send(message);

    System.out.println("Our upcoming event event is: " + nom_event+"\n" + "Come join us!");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    
    
}

    public List<Evenement> getAll() {
        List<Evenement>evenements= new ArrayList<>();
 
        try {
             Statement  stm;
             stm= cnx.createStatement();
             String query="Select * from evenement";
             ResultSet rst=stm.executeQuery(query);
             while (rst.next()){
                 Evenement c=new Evenement();
                 c.setId(rst.getInt("id"));
                c.setDate_event(rst.getDate("date_event"));
                c.setNom_event(rst.getString("nom_event"));
                c.setType_event(rst.getString("type_event"));
                c.setDescription_event(rst.getString("description_event"));

                 System.out.println(rst.getInt("id"));
                 evenements.add(c);

             }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return evenements;
}
    
    

}
