/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front; 

//import com.jfoenix.svg.SVGGlyphLoader;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
//import javafx.beans.property.ReadOnlyFloatWrapper;
//import javafx.beans.property.ReadOnlyIntegerWrapper;
//import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.activation.DataSource;
import javax.swing.JOptionPane;
import services.ProductServices;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class Menu1Controller implements Initializable {


    /**
     * Initializes the controller class.
     * 
     */
    
    ObservableList <Produit> produitLIST = new ProductServices().getAll();
    
    @FXML
    private TableView<Produit> menutable;
    @FXML
    private TableColumn<Produit,String> nomcol;
    @FXML
    private TableColumn<Produit,Float > prixcol;
    @FXML
    private TableColumn<Produit, String> desccol; 
     @FXML
    private TableColumn<Produit, String> imgccol;
        @FXML
    private TableColumn<Produit, String> catcol;
    @FXML
    private TextField srch;
    @FXML
    private Button tri;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{  
            
        Connection cnx = MyDB.getInstance().getConnexion();
        ResultSet rs = cnx.createStatement().executeQuery("SELECT p.id_produit,p.id_categorie,p.nom,p.quantite_produit,p.prix_produit,p.description_produit,p.photo_pro,p.like_dislike,c.id_cat,c.nom_cat FROM produit p,`categorie` c where c.id_cat=p.id_categorie");
            
        while(rs.next()){
        produitLIST.add(new Produit(rs.getString("nom"),rs.getFloat("prix_produit"),rs.getString("description_produit"),rs.getString("photo_pro"),rs.getString("nom_cat")));
        } 
        } catch (SQLException ex) {
            Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            nomcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
            prixcol.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit")); 
            desccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("Description_produit"));
            imgccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("photo_pro")); 
            catcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_cat")); 
            menutable.setItems(produitLIST); 
        // TODO
    }    

//public void showCommandes() {
//    
//    ObservableList<Produit> commandes = new ProductServices().getAll();
//    nomcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
//    prixcol.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit"));
//    desccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("Description_produit"));
//    menutable.setItems(commandes); 


//    private void ajout(ActionEvent event) {
//        
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutProduit.fxml"));
//            Parent root =loader.load();
//             
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));  
//            stage.show();
//            stage.setTitle("Ajouter produit");
//            
//                 
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    } 
       
    
    
   
     

   
//    private void Refresh(ActionEvent event) { 
//        
//    try {
//           Connection con = MyDB.getInstance().getConnexion();
//               
//        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM produit");
//        while(rs.next()){
//         produitLIST.add(new Produit(rs.getString("nom"),rs.getFloat("prix_produit"),rs.getString("description_produit")));
//        } 
//        
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//          nomcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
//            prixcol.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit")); 
//            desccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("Description_produit"));
//          
//           menutable.setItems(produitLIST); 
//           menutable.refresh();
//    } 
//      @FXML
//    private void Refreshtable() { 
//        
//    try { 
//           Connection con = MyDB.getInstance().getConnexion();
//           
//        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM produit");
//        while(rs.next()){
//         produitLIST.add(new Produit(rs.getString("nom"),rs.getFloat("prix_produit"),rs.getString("description_produit")));
//        } 
//        
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//            
//          nomcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
//            prixcol.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit")); 
//            desccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("Description_produit"));
//          
//           menutable.setItems(produitLIST); 
//           menutable.refresh();
//    } 

    @FXML
    private void btnmenu(MouseEvent event) {
    }

    @FXML
    private void btnReservationfront(MouseEvent event) {
    }

    @FXML
    private void btneventFront(MouseEvent event) {
    }

    @FXML
    private void btnpanierFront(MouseEvent event) {
    }

    @FXML
    private void btnLivraisonFront(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) { 
        
    }

    @FXML 
    
    private void search(ActionEvent event) { 
        
         ProductServices ps = new ProductServices();
       ObservableList<Produit> produitLIST = FXCollections.observableArrayList(ps.RechercherProduit(srch.getText()));

                nomcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
            prixcol.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit")); 
            desccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("Description_produit"));
            imgccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("photo_pro"));
            catcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_cat"));
            menutable.setItems(produitLIST);
    }

    @FXML
    private void btntri(ActionEvent event) {
        
            ProductServices ps = new ProductServices();
        ObservableList<Produit> produitLIST = FXCollections.observableArrayList(ps.TrierParId());

           nomcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
            prixcol.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit")); 
            desccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("Description_produit"));
            imgccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("photo_pro"));
            catcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_cat"));
            menutable.setItems(produitLIST);
    }
    
   
}
           
//            
//         ObservableList<Produit> List = FXCollections.observableArrayList();
//        try {
//            Connection cnx = MyDB.getInstance().getConnexion();
//           
//            
//            ResultSet rs = cnx.createStatement().executeQuery("SELECT * from publication");
//            while (rs.next()) {
//                List.add(new Produit(rs.getString("nom"), rs.getFloat("prix_produit"), rs.getString("description")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
//         nomcol.setCellValueFactory(data ->
//        {
//            return new ReadOnlyStringWrapper(data.getValue().getNom());
//        });
//    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));
//    
//prixcol.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
//     prixcol.setCellValueFactory(data ->
//        {
//            return new ReadOnlyFloatWrapper(data.getValue().getPrix_produit());
//        });
//    desccol.setCellValueFactory(data ->
//        {
//            return new ReadOnlyStringWrapper(data.getValue().getDescription_produit());
//        });
//     
//        menutable.setItems(List);
//        menutable.refresh();
//    }
    
    
//    


//    private void refresh(ActionEvent event) {
//        try {
//            
//           Connection con = MyDB.getInstance().getConnexion();
//           produitLIST.clear();
//        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM produit");
//        while(rs.next()){
//        produitLIST.add(new Produit(rs.getString("nom"),rs.getFloat("prix"),rs.getString("description")));
//        }
//        
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//            nomcol.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
//           prixcol.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix"));
//           desccol.setCellValueFactory(new PropertyValueFactory<Produit,String>("Description"));  
//            MenuController.setItems(produitLIST);
//    }

    
//    private void refresh() {
//       ObservableList<Publication> List = FXCollections.observableArrayList();
//        try {
//            Connection cnx = DataSource.getInstance().getCnx();
//           
//            
//            ResultSet rs = cnx.createStatement().executeQuery("SELECT * from publication");
//            while (rs.next()) {
//                List.add(new Publication(rs.getInt(1), rs.getString("texte"), rs.getString("date"), rs.getString("time"), rs.getString("tag"), rs.getString("photo")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(PublicationGrudController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       id_pubColumn.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
//         texteColumn.setCellValueFactory(data ->
//        {
//            return new ReadOnlyStringWrapper(data.getValue().gettexte());
//        });
//    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));
//
//    tagColumn.setCellValueFactory(data ->
//        {
//            return new ReadOnlyStringWrapper(data.getValue().gettag());
//        });
//    dateColumn.setCellValueFactory(data ->
//        {
//            return new ReadOnlyStringWrapper(data.getValue().getDate());
//        });
//     photoColumn.setCellValueFactory(data ->
//        {
//            return new ReadOnlyStringWrapper(data.getValue().getphoto());
//        });
//     timeColumn.setCellValueFactory(data ->
//        {
//            return new ReadOnlyStringWrapper(data.getValue().getTime());
//        });
//   
//        TableView.setItems(List);
//        TableView.refresh();
//    
    


   
    

    
   
