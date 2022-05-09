/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back; 
import entities.Produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.ProductServices;
import utils.MyDB;
import utils.email;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AjoutProduit1Controller implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfqte;
    private TextField tfimg;
    String productImage;
    @FXML
     private ImageView imgview;
    @FXML
    private Button vrsmen ;
@FXML
    private ComboBox  btncb;
@FXML
    private Label tfTp;  

Connection connection = null;
  ResultSet resultSet = null; 
  PreparedStatement preparedStatement ;
  String query = null; 
  int categorieID ; 
    @FXML
    private Text title;
  
    @FXML
    void Select(ActionEvent event) {
 String s =  btncb.getSelectionModel().getSelectedItem().toString();
 tfTp.setText(s);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { // awél haja tekhdem kif tethal l prog nalguha fi kol scene 
      
        try { 
      ResultSet rs;
      connection= MyDB.getInstance().getConnexion();
      rs = connection.createStatement().executeQuery("SELECT nom_cat FROM categorie");
      ObservableList data = FXCollections.observableArrayList();
      while (rs.next()) {
        data.add(new String(rs.getString(1)));
      }
      System.out.println(data);
      btncb.setItems(data);
    } catch (Exception e) { 
      e.printStackTrace();
    }
//        ObservableList <String> list = FXCollections.observableArrayList("lunch","dessert","start","drink","dinner");
//         btncb.setItems(list);
        
        
        
        
        
        
        
        
        
        
        
         vrsmen.setOnMouseClicked((event) -> {
             Parent parent;
         
             try {
                 parent = FXMLLoader.load(getClass().getResource("/gui/front/Menu1.fxml"));
             
           
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            } catch (IOException ex) {
                 Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
    }    
//    private void add(ActionEvent event) {
//         ProductServices sp = new ProductServices();
//        
//        String nom= tfnom.getText();
//        String prix = tfprice.getText();
//        String description = tfdesc.getText();
//        String quantite  = tfqte.getText();
//        String image =tfimg.getText();
//        
//        float f = Float.parseFloat(prix);
//        double d = Double.parseDouble(quantite); 
//        
//        if (nom.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "le nom ne doit pas etre vide");
//            
//        } else if (prix.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "la prix ne doit pas etre vide");
//        }
//         else if (description.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "la description  ne doit pas etre vide");
//        }
//         else if (quantite.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "la quantite  ne doit pas etre vide");
//        }
//    }

    @FXML
    private void ajout(ActionEvent event) throws FileNotFoundException, SQLException {
        
 
        String choicecat = btncb.getValue().toString();  
        System.out.println(choicecat);
        
        
      
      try { 
          
        ResultSet rs1;
        connection = MyDB.getInstance().getConnexion();
        rs1 = connection.createStatement().executeQuery("SELECT id_cat FROM categorie where nom_cat like '" + choicecat + "'");
        String data1 ;
        while (rs1.next()) {
          data1 = (new String(rs1.getString(1))); 
          
          System.out.println(data1);
          categorieID = Integer.parseInt(data1); 
         }} catch (Exception e) {
        e.printStackTrace();
      } 
          
          
         //Float prix = Float.parseFloat(tfprice.getText());
         
        ProductServices sp = new ProductServices();
        String nom= tfnom.getText();
       Float prix = Float.parseFloat(tfprice.getText());
        String description = tfdesc.getText();
        Double quantite = Double.parseDouble(tfqte.getText());
        String image =productImage;
        
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le nom ne doit pas etre vide");
            
      } 
     else if (prix.toString().isEmpty()) {
           JOptionPane.showMessageDialog(null, "la prix ne doit pas etre vide");
        }
         else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la description  ne doit pas etre vide");
        }
         else if (quantite.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "la quantite  ne doit pas etre vide");
        } 
         
        else { 
             System.out.println("categorie : "+ categorieID); 
             
            Produit p=  new Produit(categorieID,nom,prix,description,quantite,image);
             //tfnom.getText(),tfprice.getText(),tfdesc.getText(),tfqte.getText(),tfimg.getText()
           sp.ajouter1(p);
            JOptionPane.showMessageDialog(null, "produit ajoutée !");  
          Notifications notificationBuilder = Notifications.create()
                    .title("Produit")
                    .text("Votre Produit est Confirmé")//affichage notif
                    .graphic(null)
                    //.hideAfter(Duration.seconds(10))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("Clicked on notif");
                        }
                    });
            notificationBuilder.showConfirm();
            
            
            
           email n = new email();
            n.s(); 
           
        }

    } 
 
    @FXML
    private void delete(ActionEvent event) {
        
       ProductServices sp = new ProductServices();
        sp.delete(tfnom.getText());
        JOptionPane.showMessageDialog(null, "produit supprimé !"); 
        
    }

    @FXML
    private void update(ActionEvent event) {
        ProductServices sp = new ProductServices();
        
        
        String nom= tfnom.getText();
       Float prix = Float.parseFloat(tfprice.getText());
        String description = tfdesc.getText();
        Double quantite = Double.parseDouble(tfqte.getText());
        String image =tfimg.getText();
        
        if (nom.isEmpty()) { 
            JOptionPane.showMessageDialog(null, "le nom  ne doit pas etre vide");
        }  else if (prix.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "le prix ne doit pas etre vide");
    }
          else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la description ne doit pas etre vide");
    } 
          else if (quantite.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "la qauntite ne doit pas etre vide");
    } 
        else {
              Produit p=  new Produit(nom,prix,description,quantite,image);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
         sp.Update(p,34);

            JOptionPane.showMessageDialog(null, "produit modifiée !");
    }
    } 

    @FXML 
 private void Upload(ActionEvent event) throws FileNotFoundException { 
     
//     FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("image", new String[]{"*.png"});
//        this.fileChooser.getExtensionFilters().add(fileExtensions);
//        File file = this.fileChooser.showOpenDialog(new Stage());
//        System.out.println(file.toURI().toString());
//        this.productImage.setText(file.getName());
 
     
//   try {
//        con= ConnexionDB.connect();
//        LesClients.clear();
//        String sql="SELECT * FROM client";
//        Stat = con.prepareStatement(sql);
//        rst = Stat.executeQuery();
//        while(rst.next()) {
//            LesClients.add(new Client(
//                    rst.getInt("id"),
//                    rst.getString("nom"),
//                    rst.getString("prenom"),
//                    imagee
//                    ));    
//            Image iii = new Image(new FileInputStream(rst.getString("url")));
//            imagee.setImage(iii);
//            }
//            TableClient.setItems(LesClients);
//    }catch (Exception e){
//        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, e);
//    } 
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            productImage= selectedFile.getName();
           // Image i = new Image(new FileInputStream("C:\\Users\\ACER\\OneDrive\\Bureau\\PI-DEV\\public\\front\\images\\"+productImage)); 
            //imgview.setImage(i);
           // imgview.setImage(new Image(getClass().getResourceAsStream("C:\\Users\\ACER\\OneDrive\\Bureau\\PI-DEV\\public\\front\\images\\"+productImage)));
      imgview.setImage(new Image(getClass().getResourceAsStream("gui/img/"+productImage)));
        }else {
            System.out.println("File is not valid");
        }
 }

// JFileChooser chooser = new JFileChooser(new File("C:\\Users\\ACER\\OneDrive\\Bureau\\PI-DEV\\public\\front\\images"));
//      chooser.setMultiSelectionEnabled(true);
//     // chooser.setFileFilter(new FileNameExtensionFilter("PNG Photo","png"));
//        chooser.showOpenDialog(null);
//        File files = chooser.getSelectedFile();
//        // for(File e:files){
//          String path = files.getAbsolutePath(); 
//          tfimg.setText(path);
//           productImage= chooser.getName();
//             //Path tmp = Files.copy(Paths.get(path), Paths.get(dest));
//            
//         }
         



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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjoutProduit1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close(MouseEvent event) { 
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void satatistique(MouseEvent event) {
        
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/back/statCat.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StatCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 }

    
    
    
    
    
    
    




//    
//        JFileChooser chooser = new JFileChooser(new File("C:\\Users\\ACER\\OneDrive\\Bureau\\PI-DEV\\public\\front\\images"));
//      chooser.setMultiSelectionEnabled(true);
//     // chooser.setFileFilter(new FileNameExtensionFilter("PNG Photo","png"));
//        chooser.showOpenDialog(null);
//        File[] files = chooser.getSelectedFiles();
//         for(File e:files){
//          String path = e.getAbsolutePath(); 
//          tfimg.setText(path);
//             Path tmp = Files.copy(Paths.get(path), Paths.get(dest));
//            
//                } catch(FileAlreadyExistsException q)
//        {
//            JOptionPane.showMessageDialog(null, "Le fichier:"+e.getName()+"  est deja choisie");
//            
//        }
//            } catch (IOException ex) {
//                 Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//         }
// }}
//    }}   String s ="C:\\Users\\ACER\\OneDrive\\Bureau\\PIDEV\\src\\gui\\Produit\\menu.fxml";
//           String dest = s+e.getName();
//         
//            try {
//                try{
//                Path tmp = Files.copy(Paths.get(path), Paths.get(dest));
//            
//                } catch(FileAlreadyExistsException q)
//        {
//            JOptionPane.showMessageDialog(null, "Le fichier:"+e.getName()+"  est deja choisie");
//        }
//            } catch (IOException ex) {
//                 Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//         
//         }
// }}
 
//    }}

    
    


