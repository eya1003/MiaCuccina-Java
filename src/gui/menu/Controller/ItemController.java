package gui.menu.Controller;

import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import gui.menu.main.Main;
import gui.menu.main.MyListener;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    @FXML
    private TextField quantite;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(cart);
    }

    private Produit cart;
    private MyListener myListener;
    float total;
    int qt;
    String total1;
    float stq;

    public void setData(Produit cart, MyListener myListener) {
        this.cart = cart;
        this.myListener = myListener;
        nameLabel.setText(cart.getNom());
        priceLable.setText(String.valueOf(cart.getPrix_produit()));
        quantite.setText(String.valueOf(cart.getQuantite_produit()));

       

             String A = "C:\\xampp\\htdocs\\PI-DEV\\public\\front\\images"+cart.getPhoto_pro();
                                          File F1 = new File(A);
                                           Image image = new Image(F1.toURI().toString());
//        Image image = new Image(getClass().getResourceAsStream(cart.getImgSrc()));
        img.setImage(image);
    }

    @FXML
    private void plus(ActionEvent event) {
        
        qt=Integer.parseInt(quantite.getText());
        qt++;
        quantite.setText(String.valueOf(qt));
         
    }

    @FXML
    private void moin(ActionEvent event) {
            
        qt=Integer.parseInt(quantite.getText());
        if (qt>1){
        qt--;
        quantite.setText(String.valueOf(qt));
        }
       else
        {
         JOptionPane.showMessageDialog(null, "erreur !");
        }
        
    }
    
  
    
}
