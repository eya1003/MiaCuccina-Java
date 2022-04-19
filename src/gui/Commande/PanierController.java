/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Commande;

import entities.Commande;
import entities.LigneCommande;
//import Entitie.User.User;
import entities.Cart;
//import entities.Produit;
import services.LigneCommandeService;
import services.commandeService;
import animatefx.animation.Bounce;
import animatefx.animation.FadeInDown;
import entities.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.smartcardio.Card;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import services.CartService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author toshiba
 */
public class PanierController implements Initializable {
    @FXML
    private Pane banner;

    @FXML
    private TableColumn<Cart, Spinner<Integer>> quantite;

    @FXML
    private TableColumn<Cart, ImageView> image;

    @FXML
    private TableColumn<Cart, String> nom;

    @FXML
    private TableColumn<Cart, Float> prix;
    private TableColumn<Cart, Button> remove;

    @FXML
    private TableView<Cart> panierView;
    AnchorPane centerContent;
    ObservableList<Cart> panier = FXCollections.observableArrayList();
  

         

//    ObservableList<Produit> card = FXCollections.observableArrayList();
    @FXML
    private Label subTotal;

    @FXML
    private Label total;
   // User user;
    int Total;
    @FXML
    private Button supprimer;
    @FXML
    private Button updatebtn;
    @FXML
    private TextField tquantite;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Bounce(banner).play();

   showCommandes();
       
        
    }

        

 @FXML
   private void update(MouseEvent event) {
        CartService sp = new CartService();
        
        
    
        Integer quantite = Integer.parseInt(tquantite.getText());
      
        
    
          if (quantite.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "la qauntite ne doit pas etre vide");
    } 
        else {
              Cart p=  new Cart(quantite);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
         sp.Update(p,2);

            JOptionPane.showMessageDialog(null, "quantite modifiée !");
    }
    }
    
    @FXML
    void passerCommande(ActionEvent event) {
           JFrame f = new JFrame();
        int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
        if (a == JOptionPane.YES_OPTION) {
        commandeService serviceCommande = new commandeService();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateCommande = dateFormat.format(date);

        Commande commande = new Commande(0,dateCommande,Total);
        serviceCommande.create(commande);

        LigneCommandeService servicePanier = new LigneCommandeService();
        for (Cart p : panier){
            try {
                servicePanier.add(new LigneCommande(p.getQuantite(),p.getIdProduit(), serviceCommande.getLastCommande() ));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        
        }
        }
      }        

        public void showCommandes() {

        try {
           ObservableList<Cart> commandes = new CartService().getAll();
        nom.setCellValueFactory(new PropertyValueFactory<Cart, String>("nomProduit"));
        image.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("image"));
        quantite.setCellValueFactory(new PropertyValueFactory<Cart, Spinner<Integer>>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<Cart, Float>("prix"));
       
        panierView.setItems(commandes);
        
        this.prixTotal();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    
      
                        
    @FXML
    private void refreshTable(MouseEvent event) {
            JFrame f = new JFrame();
        int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
        if (a == JOptionPane.YES_OPTION) {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            panier.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM cart");
        while(rs.next()){
                panier.add(new Cart(rs.getString("nomProduit"), rs.getFloat("prix"),rs.getInt("quantite"), rs.getString("image")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        nom.setCellValueFactory(new PropertyValueFactory<Cart, String>("nomProduit"));
        image.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("image"));
        quantite.setCellValueFactory(new PropertyValueFactory<Cart, Spinner<Integer>>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<Cart, Float>("prix"));
        
            panierView.setItems(panier);
        }
    }


    public void prixTotal() {
        
            try {
            
           Connection con = MyDB.getInstance().getConnexion();
            panier.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Cart");
        while(rs.next()){
         panier.add(new Cart(rs.getFloat("prix"),rs.getInt("quantite")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int subtotal = 0, total2 = 850;
        for (Cart panier1 : panier) {
            subtotal += panier1.getQuantite()* panier1.getPrix();
        }
        total2 = total2 + subtotal;
        Total = total2;
        total.setText("$" + Integer.toString(total2));
        subTotal.setText("$" + Integer.toString(subtotal));
    }

    

    @FXML
    private void supprimer(MouseEvent event) {
            try{ 
                               
                             Cart commande = panierView.getSelectionModel().getSelectedItem();
                              String  query = "DELETE FROM cart WHERE prix  ="+commande.getPrix();
                               Connection connection = MyDB.getInstance().getConnexion();
                               PreparedStatement preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable(event);
                           } catch (SQLException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    }


    
    
    
    

}
