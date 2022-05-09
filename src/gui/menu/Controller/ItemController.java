package gui.menu.Controller;

import entities.Cart;
import entities.Commande;
import entities.Produit;
import gui.front.Panier1Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import gui.menu.main.Main;
import gui.menu.main.MyListener;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import services.CartService;
import utils.MyDB;

/**
 *
 * @author Aymen Laroussi
 */
public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    private TextField quantite;
    @FXML
    private Label desc;
    @FXML
    private Label idlabel;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(cart);
    }
      int qte;   
    private Produit cart;
    private MyListener myListener;
    float total;
    int qt;
    String total1;
    float stq;
    int productId;
    int currentValueQty;
    String productImage ;

    CartService cartt = new CartService() ;

    Cart produitPanier;
    public void setData(Produit cart, MyListener myListener) {
        this.cart = cart;
        this.myListener = myListener;
        nameLabel.setText(cart.getNom());
        priceLable.setText(String.valueOf(cart.getPrix_produit()));
        desc.setText(cart.getDescription_produit());

       idlabel.setText(String.valueOf(cart.getId_produit()));
productImage = cart.getPhoto_pro();
System.out.println(productImage);
             String A = "C:\\wamp64\\www\\Pi-Dev-Deligent-dev-zeineb\\zeineb\\PI-DEV\\public\\uploads\\images\\"+cart.getPhoto_pro();
                                          File F1 = new File(A);
                                           Image image = new Image(F1.toURI().toString());
//        Image image = new Image(getClass().getResourceAsStream(cart.getImgSrc()));
        img.setImage(image);
    }


    @FXML
    private void ajoutpanier(MouseEvent event) {
       Float prix = Float.parseFloat(priceLable.getText());
       Integer produit = Integer.parseInt(idlabel.getText());
       String nom= nameLabel.getText();
       String image= productImage;
       System.out.println(image);
        //System.out.println("Qté"+produitPanier.getQuantite());
        Cart c = new Cart(produit,nom ,prix,1,productImage);
        cartt.ajouter(c);
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/front/Panier1.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Panier1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        }
    
    
  
 

    
  
    
}
