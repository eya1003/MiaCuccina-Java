package gui.menu.Controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import gui.menu.main.Main;
import gui.menu.main.MyListener;
import entities.Produit;
import gui.back.QRCodeGenerator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;
import utils.MyDB;

/**
 *
 * @author Aymen Laroussi
 */
public class MarketController implements Initializable {
    private VBox chosenFruitCard;

    private Label fruitNameLable;

    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    

    private List<Produit> carts = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    Connection connexion;
    Statement stm;
    Label total;
    String total1;
    private Label fruitPriceLabel;
    private Rating rating;
    String cartid;
    int cartidd;
    String randomHex;
    String rate;
    private Label CartRefLable1;
    private TextField rech;
    float qte;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView imagee;
   
    float tot;
    public MarketController() {
        connexion = MyDB.getInstance().getConnexion();
    }

   
      public List<Produit> afficheRecherche() throws SQLException {
          
        List<Produit> carts = new ArrayList<>();
        String req = "select * from produit where nom like '%"+rech.getText()+"%' or prix_produit like '%"+rech.getText()+"%'";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit p = new Produit(rst.getInt("id_produit"),//or rst.getInt(1)
                   
                    rst.getString("nom"),
                    rst.getDouble("quantite_produit"),
                    rst.getFloat("prix_produit"),
                    rst.getString("description_produit"),
                     rst.getString("photo_pro")
            )
                
                    
                    ;
            carts.add(p);
        }
        return carts;
    }
     
     
   
    
     public List<Produit> afficheCart() throws SQLException {
        List<Produit> carts = new ArrayList<>();
        String req = "select * from produit";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
                 Produit p = new Produit(rst.getInt("id_produit"),//or rst.getInt(1)
                   
                    rst.getString("nom"),
                    rst.getDouble("quantite_produit"),
                    rst.getFloat("prix_produit"),
                    rst.getString("description_produit"),
                     rst.getString("photo_pro")
            )
                    
                    ;
            carts.add(p);
         //   tot= tot + (rst.getFloat("prix_produit")*rst.getInt("quantite_produit"));
            
            
        }
        return carts;
    }
     

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            carts.addAll(afficheCart());
        //    toto.setText(String.valueOf(tot) + "TND");
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (carts.size() > 0) {
           // setChosenFruit(carts.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit cart) {
                  //  setChosenFruit(cart);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < carts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/menu/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(carts.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
      public void Recherche() {
        
        try {
            carts.removeAll(carts);
            carts.addAll(afficheRecherche());
        } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (carts.size() > 0) {
//            setChosenFruit(carts.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit cart) {
                   // setChosenFruit(cart);
                }
            };
        }
       
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < carts.size(); i++) {
            grid.getChildren().clear();}
            for (int i = 0; i < carts.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/menu/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(carts.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }
                
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajoutPanier(MouseEvent event) {
        //FARES
        if (qte!=0){
        qte=qte--;
        
       
         try {  

            String requete = "UPDATE carts set stock =?,cart_id =? ,message =?,date =? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getConnexion().prepareStatement(requete);

            pst.setFloat(1, qte);
            pst.setInt(2, cartidd);
            pst.executeUpdate();


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());}
        }else{

            Alert alert = new Alert(AlertType.ERROR);

        alert.setTitle("Désolé");
        alert.setHeaderText("Cart n'est plus dans notre boutique");
        alert.setContentText("The 'abc' user does not exists!");

        alert.showAndWait();
        
        }
    }

    private void Commenter(MouseEvent event) {
        
     //  refresh();
    }

    private void Commenter1(MouseDragEvent event) {
        
    }


    
//    private void refresh() {
//        
//               try {
//            FXMLLoader loader=new FXMLLoader(getClass().getResource("/boutique/views/addCommentaire.fxml"));
//            Parent root = (Parent) loader.load();
//
////            AddCommentaireController secController=loader.getController();
//            
//          //  secController.Rating(rating.getRating(),cartid,randomHex);
//            System.out.println(rating.getRating());
//
//            Stage stage=new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//    }
//
//    private void avisList(ActionEvent event) {
//        try {
//            FXMLLoader loader=new FXMLLoader(getClass().getResource("/boutique/views/marketCommentaire.fxml"));
//            Parent root = (Parent) loader.load();
//
////            CommentaireController secController=loader.getController();
//            
//        //    secController.Avis(cartid); 
//
//            Stage stage=new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
    private void Qr(MouseEvent event) {
        String img = "";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "https://www.youtube.com/watch?v=FvNV8R0HkO8";
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(QRCodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        StackPane root = new StackPane();
        root.getChildren().add(qrView);
        
        Scene scene = new Scene(root, 350, 350);
            Stage stage = new Stage();
        stage.setTitle("Mia cuccina");
        stage.setScene(scene);
        stage.show();
        
   
        
    }
    
    

}
