/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;




import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import entities.Commande;
import entities.LigneCommande;
//import Entitie.User.User;
import entities.Cart;
//import entities.Produit;
import services.LigneCommandeService;
import services.commandeService;
import animatefx.animation.*;
import entities.Produit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.smartcardio.Card;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.CartService;
import utils.MyDB;
import utils.email;

/**
 * FXML Controller class
 *
 * @author toshiba
 */
public class Panier1Controller implements Initializable {
    //@FXML
   // private Pane banner;

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
    @FXML
    private Button imprimebtn;
    @FXML
    private Button tribtn;
    @FXML
    private TextField tfsearchmember;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView imagee;
    @FXML
    private Label vide;
    @FXML
    private Pane opa;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      

   showCommandes();
       
        
    }

        

 @FXML
   private void update(MouseEvent event) {
        CartService sp = new CartService();
        
       
        Integer quantite = Integer.parseInt(tquantite.getText());
      
        
    
          if (tquantite.getText().equals("")) {
              JOptionPane.showMessageDialog(null, "la qauntite ne doit pas etre vide");
             new Shake(tquantite).play();
    } 
            if ((quantite >= 'A' && quantite <= 'Z') || (quantite >= 'a' && quantite <= 'z')) {
                 new Shake(tquantite).play();
                 JOptionPane.showMessageDialog(null, "champs invalide");
            }
                
        else {
              Cart p=  new Cart(quantite);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
         sp.Update(p,2);
         sp.Update(p,7);
         sp.Update(p,8);

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
        
         Notifications notificationBuilder = Notifications.create()
                    .title("Commande")
                    .text("Votre Commande est Confirmé")//affichage notif
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("Clicked on notif");
                        }
                    });
            notificationBuilder.showConfirm();

        for (Cart p : panier){
            try {
                servicePanier.add(new LigneCommande(p.getQuantite(),p.getIdProduit(), serviceCommande.getLastCommande() ));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        
        }
    
        }
        new Hinge(panierView).play();
        new FadeOut(opa).play();
        new FadeInUpBig(vide).play();
        new FadeOut(total).play();
        new FadeOut(subTotal).play();
        new GlowText(vide, Color.BLACK, Color.WHITE).play();
           email n = new email();
            n.s();
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
         new BounceIn(logo).play();
         new FadeIn(panierView).play();
        
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            panier.clear();
          
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM cart");
        while(rs.next()){
                panier.add(new Cart(rs.getString("nomProduit"), rs.getFloat("prix"),rs.getInt("quantite"), rs.getString("image")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(Panier1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            nom.setCellValueFactory(new PropertyValueFactory<Cart, String>("nomProduit"));
        image.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("image"));
        quantite.setCellValueFactory(new PropertyValueFactory<Cart, Spinner<Integer>>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<Cart, Float>("prix"));
       
        panierView.setItems(panier);
        
        this.prixTotal();
    }


    public void prixTotal() {
        
            try {
            
           Connection con = MyDB.getInstance().getConnexion();
            panier.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Cart");
        while(rs.next()){
 panier.add(new Cart(rs.getString("nomProduit"), rs.getFloat("prix"),rs.getInt("quantite"), rs.getString("image")));        }
        
        } catch (SQLException ex) {
            Logger.getLogger(Panier1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            JFrame f = new JFrame();
        int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
        if (a == JOptionPane.YES_OPTION) {
            try{ 
                               
                             Cart commande = panierView.getSelectionModel().getSelectedItem();
                              String  query = "DELETE FROM cart WHERE prix  ="+commande.getPrix();
                               Connection connection = MyDB.getInstance().getConnexion();
                               PreparedStatement preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable(event);
                           } catch (SQLException ex) {
                                Logger.getLogger(Panier1Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
        }
    }

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
          Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

   @FXML
    private void imprimerTable(ActionEvent event) throws SQLException, DocumentException, ClassNotFoundException, BadElementException, IOException {
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diligent", "root", "");
            PreparedStatement pt = con.prepareStatement("select * from cart" );
            ResultSet rs = pt.executeQuery();


            
            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();
            
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("facture.pdf"));
            
                  // Creating an ImageData object       
                 //PDImageXObject pdImage = PDImageXObject.createFromFile("/eclipse-workspace/java.jpeg",my_pdf_report); 

            my_pdf_report.open();
            
         String filename = "C:\\Users\\Zeineb Hamdi\\Downloads\\Java\\MiaCuccina-Java-ReservationTables\\src\\img\\logo.png";
            Image image = Image.getInstance(filename);
               my_pdf_report.add(image);
                Paragraph ligne = new Paragraph("------------------------------");
                my_pdf_report.add(ligne);
                ligne.setAlignment(Element.ALIGN_LEFT);  
                 my_pdf_report.add(new Paragraph(" Client(e): HAMDI Zeineb"));
                 my_pdf_report.add(new Paragraph("Adress: Tuniss , Omran Sup"));
                 my_pdf_report.add(new Paragraph("Code Postal: 1091"));
            Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph pdfTitle = new Paragraph("Facture", font);
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(pdfTitle);
            my_pdf_report.add(new Chunk("\n"));
            my_pdf_report.addCreationDate();
 
            //we have five columns in our table
            PdfPTable my_report_table = new PdfPTable(3);

            //create a cell object
            PdfPCell table_cell;

            table_cell = new PdfPCell(new Phrase("Nom Produit"));
            table_cell.setBackgroundColor(BaseColor.RED);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Quantite"));
            table_cell.setBackgroundColor(BaseColor.RED);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Prix"));
            table_cell.setBackgroundColor(BaseColor.RED);
            my_report_table.addCell(table_cell);
  


            while (rs.next()) {

                String nom = rs.getString("nomProduit");
                table_cell = new PdfPCell(new Phrase(nom));
                my_report_table.addCell(table_cell);
                String qt = rs.getString("quantite");
                table_cell = new PdfPCell(new Phrase(qt));
                my_report_table.addCell(table_cell);
                String prixP = rs.getString("prix");
                table_cell = new PdfPCell(new Phrase(prixP));
                my_report_table.addCell(table_cell);
                
          
            }
            /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);
            my_pdf_report.add(new Paragraph(" "));
            Paragraph p = new Paragraph("+ 850"); 
            Paragraph pdf = new Paragraph("----------------- " ); 
            Paragraph pdfT = new Paragraph("TOTAL : " + this.Total); 
            pdfT.setAlignment(Element.ALIGN_RIGHT);
            p.setAlignment(Element.ALIGN_RIGHT);
            pdf.setAlignment(Element.ALIGN_RIGHT);
            my_pdf_report.add(p);
            my_pdf_report.add(pdf);
            my_pdf_report.add(pdfT);
            my_pdf_report.add(new Chunk("\n"));
            my_pdf_report.add(new Paragraph(" "));
            my_pdf_report.add(new Paragraph("MERCI"));

            System.out.println(my_pdf_report);
            my_pdf_report.close();
            JOptionPane.showMessageDialog(null, "imprimer avec succes");
            new Tada(imprimebtn).play();

            /* Close all DB related objects */
            rs.close();
            pt.close();
            con.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
        @FXML
    private void trierProduit(ActionEvent event) {

        CartService ms = new CartService();
        ObservableList<Cart> list = FXCollections.observableArrayList(ms.TrierParId());

         nom.setCellValueFactory(new PropertyValueFactory<Cart, String>("nomProduit"));
        image.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("image"));
        quantite.setCellValueFactory(new PropertyValueFactory<Cart, Spinner<Integer>>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<Cart, Float>("prix"));
        panierView.setItems(list);
         this.prixTotal();
         new Bounce(panierView).play();
    }
    
     
    @FXML
    private void search() {

        CartService ms = new CartService();
       ObservableList<Cart> list = FXCollections.observableArrayList(ms.RechercherProduit(tfsearchmember.getText()));

         nom.setCellValueFactory(new PropertyValueFactory<Cart, String>("nomProduit"));
        image.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("image"));
        quantite.setCellValueFactory(new PropertyValueFactory<Cart, Spinner<Integer>>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<Cart, Float>("prix"));
        panierView.setItems(list);
         this.prixTotal();
         
         new Tada(tfsearchmember).play();
    }

}
