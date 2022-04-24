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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import services.ProductServices;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfqte;
    @FXML
    private TextField tfimg;
    String productImage;
    @FXML
     private ImageView imgview;
    @FXML
    private PieChart pirchrt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
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
    private void ajout(ActionEvent event) throws FileNotFoundException {
         
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
            Produit p=  new Produit(nom,prix,description,quantite,image);
             //tfnom.getText(),tfprice.getText(),tfdesc.getText(),tfqte.getText(),tfimg.getText()
           sp.ajouter1(p);
            JOptionPane.showMessageDialog(null, "produit ajoutée !");
           
            
            
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
            imgview.setImage(new Image(getClass().getResourceAsStream("C:\\Users\\ACER\\OneDrive\\Bureau\\PI-DEV\\public\\front\\images\\"+productImage)));
        }else {
            System.out.println("File is not valid");
        } }
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
//     String s ="C:\\Users\\ACER\\OneDrive\\Bureau\\PIDEV\\src\\gui\\Produit\\menu.fxml";
//           String dest = s+e.getName();
//         
//            try {
//                try{
//                Path tmp = Files.copy(Paths.get(path), Paths.get(dest));
//            
//                } catch(FileAlreadyExistsException q)
//        {
//            JOptionPane.showMessageDialog(null, "Le fichier:"+e.getName()+"  est deja choisie");
//            
//        }
//            } catch (IOException ex) {
//                 Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//         
//         }
// }}
 
//    }}

    
    


