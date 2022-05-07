/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Categorie;
import entities.Produit;
import gui.ListReservationController;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.CategorieServices;
import services.ProductServices;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class Cat1Controller implements Initializable {

    @FXML
    private Button bntadd;
    @FXML
    private Button bntupd;
    @FXML
    private TextField tfnomcat;
    @FXML
    private TextField tfdesccat;

    void Select(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
      
    }    

    @FXML
    private void btndashboardd(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeTable(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void btnListeEpmlacement(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void btnListeReservation(MouseEvent event) { 
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void btnlListeEvent(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void btnListePanier(MouseEvent event) { 
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }

    @FXML
    private void btnListeCommandes(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void btnListecategorie(MouseEvent event) { 
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void btnListeProduit(MouseEvent event) { 
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void btnListeLivreur(MouseEvent event) { 
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void btnListeLivraison(MouseEvent event) { 
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void btnListeUser(MouseEvent event) { 
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void close(MouseEvent event) { 
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cat1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void addCat(ActionEvent event) throws SQLException {
        
         CategorieServices sp = new CategorieServices();
        String nom= tfnomcat.getText();
        String description = tfdesccat.getText();
        
       
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le nom ne doit pas etre vide");
        } 
     
         else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la description  ne doit pas etre vide");
        }
 
        else {
            Categorie c=  new Categorie(nom,description);
             //tfnom.getText(),tfprice.getText(),tfdesc.getText(),tfqte.getText(),tfimg.getText()
           sp.ajouter(c);
            JOptionPane.showMessageDialog(null, "Catégorie ajoutée !");
    } 
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
         CategorieServices sp = new CategorieServices();
        
        
        String nom= tfnomcat.getText();
       
        String description = tfdesccat.getText();
        
     
        
        if (nom.isEmpty()) { 
            JOptionPane.showMessageDialog(null, "le nom  ne doit pas etre vide");
        }
          else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la description ne doit pas etre vide");
    } 
      
        else {
              Categorie c=  new Categorie (nom,description);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
         sp.Update(c,17);

            JOptionPane.showMessageDialog(null, "Catégorie modifiée !");
    }
    }
    
}
