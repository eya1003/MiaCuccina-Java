/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

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
import com.itextpdf.text.Document;
import entities.Livreur;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.LivreurService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class BackLivreurFXMLController implements Initializable {

    @FXML
    private TableView<Livreur> tableViewlivreur;
    @FXML
    private TableColumn<?, ?> Nom;
    @FXML
    private TableColumn<?, ?> Prenom;
    @FXML
    private TableColumn<?, ?> numtel;
    @FXML
    private TableColumn<?, ?> Region;
    @FXML
    private TableColumn<?, ?> Matricule;
    @FXML
    private TableColumn<?, ?> Dispo;
    @FXML
    private TableColumn<?, ?> id_livreur;
        ObservableList<Livreur>  livreurListe = FXCollections.observableArrayList();
    ObservableList<Livreur>  livreur = FXCollections.observableArrayList();
    @FXML
    private TextField tnom;
    @FXML
    private TextField tprenom;
    @FXML
    private TextField tlivtel;
    @FXML
    private TextField tregion;
    @FXML
    private TextField tmatricule;
    @FXML
    private TextField tdispo;
    @FXML
    private Button removeBtn;
    @FXML
    private TextField tfsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            livreurListe.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM livreur");
        while(rs.next()){
         livreurListe.add(new Livreur(rs.getInt("id_livreur"),rs.getString("nom_liv"),rs.getString("prenom_liv"),rs.getString("num_tel_liv"),rs.getString("Region"),rs.getString("mat_liv"),rs.getString("disponibilite_liv")));
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            ObservableList<Livreur> livreurs;
        try {
            livreurs = new LivreurService().getAll();
        
            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom_liv"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_liv"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel_liv"));
            Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("mat_liv"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_liv"));
            tableViewlivreur.setItems(livreurs);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        removeBtn.setOnMouseClicked(event -> {
                           try{ 
                               Livreur livreur = (Livreur) tableViewlivreur.getSelectionModel().getSelectedItem();
                               String  query = "DELETE FROM livreur WHERE id_livreur  ="+livreur.getId_livreur();
                              Connection connection = MyDB.getInstance().getConnexion();
                              PreparedStatement preparedStatement = connection.prepareStatement(query);
                              preparedStatement.execute();
                           } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
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
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeTable(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackTableFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackTableFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeEpmlacement(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackEmplacementFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackEmplacementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void btnListeReservation(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackReservationFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnlListeEvent(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("event/Evenement.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListePanier(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
          //  Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeCommandes(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           // Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeProduit(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
          //  Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeLivreur(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackLivreurFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           Logger.getLogger(BackLivreurFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeLivraison(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/back/BackLivraisonFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackLivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void btnListeUser(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           // Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnrefresh(MouseEvent event) {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            livreurListe.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM livreur");
        while(rs.next()){
         livreurListe.add(new Livreur(rs.getInt("id_livreur"),rs.getString("nom_liv"),rs.getString("prenom_liv"),rs.getString("num_tel_liv"),rs.getString("Region"),rs.getString("mat_liv"),rs.getString("disponibilite_liv")));
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            ObservableList<Livreur> livreurs;
        try {
            livreurs = new LivreurService().getAll();
        
            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom_liv"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_liv"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel_liv"));
            Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("mat_liv"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_liv"));
            tableViewlivreur.setItems(livreurs);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnAjouter(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BackAjoutLivreur.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
//            stage.setTitle("Ajouter produit");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Modifier(MouseEvent event) throws SQLException {
        LivreurService sp = new LivreurService();
        
        
        String nom= tnom.getText();
        String prenom = tprenom.getText();
        String num_tel_liv = tlivtel.getText();
        String Region =tregion.getText();
        String matricule = tmatricule.getText();
        String dispo = tdispo.getText();
        
        if (nom.isEmpty()) { 
            JOptionPane.showMessageDialog(null, "le nom  ne doit pas etre vide");
        }  else if (prenom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le prenom ne doit pas etre vide");
    }
          else if (num_tel_liv.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la numero tel ne doit pas etre vide");
    } 
          else if (Region.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la region ne doit pas etre vide");
    }
          else if (matricule.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la matricule ne doit pas etre vide");
          }
          else if (dispo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la dispo ne doit pas etre vide");}
        else {
              Livreur l=  new Livreur(nom,prenom,num_tel_liv,Region,matricule,dispo);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
//         sp.modifierL(l,2);
//         sp.modifierL(l,3);
 //        sp.modifierL(l,4);
  //       sp.modifierL(l,5);
  //       sp.modifierL(l,6);
//         sp.modifierL(l,7);
//         sp.modifierL(l,8);
//         sp.modifierL(l,9);
//         sp.modifierL(l,10);
//         sp.modifierL(l,11);
//         sp.modifierL(l,12);
//         sp.modifierL(l,13);
//         sp.modifierL(l,14);
//         sp.modifierL(l,15);
//         sp.modifierL(l,16);
//         sp.modifierL(l,17);
          NotificationH.NotifcationOnAction("Envoie de modification ", "Livreur modifiée");
            JOptionPane.showMessageDialog(null, "livreur modifiée !");
    }
    }

    @FXML
    private void trierLivreur(MouseEvent event) {
        LivreurService ls = new LivreurService();
        ObservableList<Livreur> list = FXCollections.observableArrayList(ls.TrierParId());

            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom_liv"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_liv"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel_liv"));
            Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("mat_liv"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_liv"));
       tableViewlivreur.setItems(list);
    }


    @FXML
    private void searchNom(MouseEvent event) {
               LivreurService ls = new LivreurService();
       ObservableList<Livreur> list = FXCollections.observableArrayList(ls.RechercherLivreurNom(tfsearch.getText()));

            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom_liv"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_liv"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel_liv"));
            Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("mat_liv"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_liv"));
       tableViewlivreur.setItems(list);
    }

    @FXML
    private void searchPrenom(MouseEvent event) {
       LivreurService ls = new LivreurService();
       ObservableList<Livreur> list = FXCollections.observableArrayList(ls.RechercherLivreurPrenom(tfsearch.getText()));

            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom_liv"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_liv"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel_liv"));
            Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("mat_liv"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_liv"));
       tableViewlivreur.setItems(list);
    }

    @FXML
    private void searchNumero(MouseEvent event) {
       LivreurService ls = new LivreurService();
       ObservableList<Livreur> list = FXCollections.observableArrayList(ls.RechercherLivreurNumero(tfsearch.getText()));

            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom_liv"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_liv"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel_liv"));
            Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("mat_liv"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_liv"));
       tableViewlivreur.setItems(list);
    }

    @FXML
    private void imprimer(MouseEvent event)throws SQLException, ClassNotFoundException,IOException, DocumentException {
  {
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = MyDB.getInstance().getConnexion();
            PreparedStatement pt = con.prepareStatement("select * from livreur" );
            ResultSet rs = pt.executeQuery();


            
            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();
            
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("list.pdf"));
            
                  // Creating an ImageData object       
                 //PDImageXObject pdImage = PDImageXObject.createFromFile("/eclipse-workspace/java.jpeg",my_pdf_report); 

            my_pdf_report.open();
            
         String filename = "C:\\\\Users\\\\hanna\\\\Downloads\\\\loggg.png";
            Image image = Image.getInstance(filename);
               my_pdf_report.add(image);
                Paragraph ligne = new Paragraph("------------------------------");
                my_pdf_report.add(ligne);
                ligne.setAlignment(Element.ALIGN_LEFT);  
//                 my_pdf_report.add(new Paragraph(" Client(e): HAMDI Zeineb"));
//                 my_pdf_report.add(new Paragraph("Adress: Tuniss , Omran Sup"));
//                 my_pdf_report.add(new Paragraph("Code Postal: 1091"));
            Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph pdfTitle = new Paragraph("Liste des livreurs", font);
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(pdfTitle);
            my_pdf_report.add(new Chunk("\n"));
            my_pdf_report.addCreationDate();
 
            //we have five columns in our table
            PdfPTable my_report_table = new PdfPTable(3);

            //create a cell object
            PdfPCell table_cell;

            table_cell = new PdfPCell(new Phrase("nom livreur"));
            table_cell.setBackgroundColor(BaseColor.RED);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("prenom livreur"));
            table_cell.setBackgroundColor(BaseColor.RED);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("numero livreur"));
            table_cell.setBackgroundColor(BaseColor.RED);
            my_report_table.addCell(table_cell);
//            table_cell = new PdfPCell(new Phrase("Region livreur"));
//            table_cell.setBackgroundColor(BaseColor.RED);
//            my_report_table.addCell(table_cell);
//            table_cell = new PdfPCell(new Phrase("Matricule livreur"));
//            table_cell.setBackgroundColor(BaseColor.RED);
//            my_report_table.addCell(table_cell);
//            table_cell = new PdfPCell(new Phrase("Disponibilite livreur"));
//            table_cell.setBackgroundColor(BaseColor.RED);
//            my_report_table.addCell(table_cell);
  


            while (rs.next()) {

                String nom = rs.getString("nom_liv");
                table_cell = new PdfPCell(new Phrase(nom));
                my_report_table.addCell(table_cell);
                String qt = rs.getString("prenom_liv");
                table_cell = new PdfPCell(new Phrase(qt));
                my_report_table.addCell(table_cell);
                String prixP = rs.getString("num_tel_liv");
                table_cell = new PdfPCell(new Phrase(prixP));
                my_report_table.addCell(table_cell);
//                 String reg = rs.getString("Region");
//                table_cell = new PdfPCell(new Phrase(nom));
//                my_report_table.addCell(table_cell);
//                String mat = rs.getString("mat_liv");
//                table_cell = new PdfPCell(new Phrase(qt));
//                my_report_table.addCell(table_cell);
//                String dis = rs.getString("disponibilite_liv");
//                table_cell = new PdfPCell(new Phrase(prixP));
//                my_report_table.addCell(table_cell);
                
          
            }
            /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);
            my_pdf_report.add(new Paragraph(" "));
//            Paragraph p = new Paragraph("+ 850"); 
//            Paragraph pdf = new Paragraph("----------------- " ); 
//            Paragraph pdfT = new Paragraph("TOTAL : " + this.Total); 
//            pdfT.setAlignment(Element.ALIGN_RIGHT);
//            p.setAlignment(Element.ALIGN_RIGHT);
//            pdf.setAlignment(Element.ALIGN_RIGHT);
//            my_pdf_report.add(p);
//            my_pdf_report.add(pdf);
//            my_pdf_report.add(pdfT);
//            my_pdf_report.add(new Chunk("\n"));
//            my_pdf_report.add(new Paragraph(" "));
//            my_pdf_report.add(new Paragraph("MERCI"));

            System.out.println(my_pdf_report);
            my_pdf_report.close();
            JOptionPane.showMessageDialog(null, "imprimer avec succes");
            //new Tada(imprimebtn).play();

            /* Close all DB related objects */
            rs.close();
            pt.close();
            con.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    }

    @FXML
    private void stat(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatistiqueLivFXML.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
//            stage.setTitle("Ajouter produit");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnListeCategorie(MouseEvent event) {
    }
    }
    
