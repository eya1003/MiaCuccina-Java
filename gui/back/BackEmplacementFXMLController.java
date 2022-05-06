/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Emplacement;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.DefaultCategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import services.EmplacementService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class BackEmplacementFXMLController implements Initializable {

  
      @FXML
    private TableView<Emplacement> emplacementTable;
      @FXML
    private TableColumn<Emplacement, String> vueColl;
    @FXML
    private TableColumn<Emplacement, String> descriptionColl;
    @FXML
    private TableColumn<Emplacement, String> actionsColl;
    

    
    ObservableList<Emplacement> EmplacementListe = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Emplacement, Integer> idColl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
        Connection con = MyDB.getInstance().getConnexion();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM emplacement");
            
        while(rs.next()){
        EmplacementListe.add(new Emplacement(rs.getInt("id_emplacement"),rs.getString("type_emplacement"),rs.getString("Description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(BackEmplacementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         idColl.setCellValueFactory(new PropertyValueFactory<Emplacement,Integer>("id_emplacement"));
            vueColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("type_emplacement"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("Description"));  
            emplacementTable.setItems(EmplacementListe);
            
                //add cell of button edit 
         Callback<TableColumn<Emplacement, String>, TableCell<Emplacement, String>> cellFoctory = (TableColumn<Emplacement, String> param) -> {
            // make cell containing buttons
            final TableCell<Emplacement, String> cell = new TableCell<Emplacement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button btnModifier = new Button("Modifier");
                         Button btnsupp = new Button("Supprimer");

                  //supprimer
                        btnsupp.setOnMouseClicked((MouseEvent event) -> {
              EmplacementService T = new EmplacementService();
                           try{
                          T.deleteEmplacement(emplacementTable.getSelectionModel().getSelectedItem().getId_emplacement());
                                       
                                       
           JOptionPane.showMessageDialog(null, "Emplacement supprimée");
           Emplacement selectedItem = emplacementTable.getSelectionModel().getSelectedItem();
            emplacementTable.getItems().remove(selectedItem);       
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
                           emplacementTable.refresh();
                         

        });

                        
                 
                        
                        
                        
                        
                        ////modifier
                        btnModifier.setOnMouseClicked((MouseEvent event) -> {                       
                if (emplacementTable.getSelectionModel().getSelectedItem() != null) {
            try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackAjoutEmplacementFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                    }

             });

                        HBox managebtn = new HBox(btnModifier,btnsupp);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnModifier, new Insets(2, 2, 0, 3));
                         HBox.setMargin(btnsupp, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         actionsColl.setCellFactory(cellFoctory);
         emplacementTable.setItems(EmplacementListe);
    }
    
    
    
    private void refreshTable() {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            EmplacementListe.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM emplacement");
        while(rs.next()){
        EmplacementListe.add(new Emplacement(rs.getString("type_emplacement"),rs.getString("Description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(BackEmplacementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            idColl.setCellValueFactory(new PropertyValueFactory<Emplacement,Integer>("id_emplacement"));
            vueColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("type_emplacement"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("Description"));  
            emplacementTable.setItems(EmplacementListe);
            
            
         
            
        
    }
        //
    
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
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
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
    private void btnListeCategorie(MouseEvent event) {
    }

      @FXML
    private void btnAjouter(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackAjoutEmplacement.fxml"));
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
    private void btnrefresh(MouseEvent event) {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            EmplacementListe.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM emplacement");
        while(rs.next()){
        EmplacementListe.add(new Emplacement(rs.getString("type_emplacement"),rs.getString("Description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(BackEmplacementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            vueColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("type_emplacement"));
            descriptionColl.setCellValueFactory(new PropertyValueFactory<Emplacement,String>("Description"));  
            emplacementTable.setItems(EmplacementListe);
            
    }

    private void getadd(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/BackAjoutEmplacement.fxml"));
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
    private void stat(MouseEvent event) {
      
         
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/back/StatistiqueFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    
    
}
