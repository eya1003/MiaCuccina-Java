/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Evenement;
import services.ServiceEvenement;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aziza
 */
public class EvenementController implements Initializable {
    
  @FXML
    private Button ArtisteBtn;
    @FXML
    private Button stat;     
    @FXML
    private DatePicker datec;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftype;
    @FXML
    private TableView<Evenement> tablec;
    @FXML
    private TableColumn<Evenement, Integer> idt;
    @FXML
    private TableColumn<Evenement, Date> datet;
    @FXML
    private TableColumn<Evenement, String> nomt;
    @FXML
    private TableColumn<Evenement, String> typet;
    @FXML
    private TableColumn<Evenement, String> descriptiont;
    @FXML
    private TextField idsup;
    @FXML
    private TextField tfsearch;
    @FXML
    private TextField tfdescription;

    private int vartri = 0;
    @FXML
    private ImageView logo;
    @FXML
    private Button a;
    @FXML
    private Button b;
    @FXML
    private Button c;
    @FXML
    private Button d;
    @FXML
    private Button e;
    @FXML
    private Button f;
    @FXML
    private Button g;
    @FXML
    private Button i;
    @FXML
    private Button k;
    @FXML
    private Button l;
    @FXML
    private Button m;
    @FXML
    private Button n;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    @FXML
    private void statScene(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("StatRec.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StatRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    @FXML
    private void btnListeArtiste(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ArtisteFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ArtisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    

    @FXML
    private void AjouterEvenement(ActionEvent event) {
        
        if ( datec.getValue().equals("") || tfnom.getText().equals("")|| tftype.getText().equals("") || tfdescription.getText().equals("") )
        {
            Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("non.");
		alert.setHeaderText(null);
		alert.setContentText("Verifiez les champs vides ");
		alert.showAndWait();
        }
        
        else {
        ServiceEvenement sc = new ServiceEvenement();
        Evenement c = new Evenement();
        c.setDate_event(java.sql.Date.valueOf(datec.getValue()));
        c.setNom_event(tfnom.getText());
        c.setType_event(tftype.getText());
        c.setDescription_event(tfdescription.getText());

        sc.AjouterEvenement(c);
        this.AfficherEvenement(event);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(0);
                return null;
            }
        };
    
        task.setOnSucceeded(event2 -> {
            
            //IL FAUT RECUBERER LE mail de utili
            sc.sendmail("mohamedaziz.ali@esprit.tn", c.getNom_event());
            
        });

        new Thread(task).run();
        
        System.out.println("mail en cours");
    }
    }
    
    
    @FXML
    private void AfficherEvenement(ActionEvent event) {
        ServiceEvenement sc = new ServiceEvenement();
        ObservableList<Evenement> evenements = sc.AfficherEvenement();

        idt.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_event"));
        nomt.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_event"));
        typet.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_event"));
        descriptiont.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description_event"));
        tablec.setItems(evenements);
    }

    @FXML
    private void selectionner(MouseEvent event) {
        Evenement c = tablec.getSelectionModel().getSelectedItem();
        java.sql.Date dateget = convertUtilToSql(c.getDate_event());
        idsup.setText(String.valueOf(c.getId()));
        datec.setValue(dateget.toLocalDate());
        tfnom.setText(c.getNom_event());
        tftype.setText(c.getType_event());
        tfdescription.setText(c.getDescription_event());
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;

    }

    @FXML
    private void Supprimerevenement(ActionEvent event) {
        ServiceEvenement sc = new ServiceEvenement();
        sc.supprimerevenement(Integer.parseInt(idsup.getText()));
        this.AfficherEvenement(event);
    }

    @FXML
    private void ModifierEvenement(ActionEvent event) {
        ServiceEvenement sc = new ServiceEvenement();
        Evenement c = new Evenement();
        c.setId(Integer.parseInt(idsup.getText()));
        c.setDate_event(java.sql.Date.valueOf(datec.getValue()));
        c.setNom_event(tfnom.getText());
        c.setType_event(tftype.getText());
        c.setDescription_event(tfdescription.getText());
        sc.ModifierEvenement(c);
    }

    @FXML
    private void searchkey(KeyEvent event) {
        ServiceEvenement sc = new ServiceEvenement();
        ObservableList<Evenement> evenements = sc.search(tfsearch.getText());
        idt.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_event"));
        nomt.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_event"));
        typet.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_event"));
        descriptiont.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description_event"));
        tablec.setItems(evenements);
    }

    @FXML
    private void tributton(MouseEvent event) {
        ServiceEvenement sc = new ServiceEvenement();
        ObservableList<Evenement> evenements;
        if (vartri == 1) {
            vartri = 0;
            evenements = sc.triasc();
            idt.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
            datet.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_event"));
            nomt.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_event"));
            typet.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_event"));
            descriptiont.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description_event"));
            tablec.setItems(evenements);

        } else {
            vartri = 1;
            evenements = sc.triadsc();
            idt.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
            datet.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_event"));
            nomt.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom_event"));
            typet.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type_event"));
            descriptiont.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description_event"));
            tablec.setItems(evenements);

        }
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

}
