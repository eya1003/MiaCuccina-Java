/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Artiste;
import java.io.IOException;
import services.ServiceArtiste;

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
public class ArtisteController implements Initializable {

    private Label label;
    @FXML
    private TextField tfnoma;
    @FXML
    private TextField tftypea;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfemail;
    @FXML
    private TableView<Artiste> tablea;
    @FXML
    private TableColumn<Artiste, Integer> idat;
    @FXML
    private TableColumn<Artiste, String> emailt;
    @FXML
    private TableColumn<Artiste, String> nomat;
    @FXML
    private TableColumn<Artiste, String> typeat;
    @FXML
    private TableColumn<Artiste, String> numerot;
    @FXML
    private TextField idsup;
    @FXML
    private TextField tfsearch;

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
    private void AjouterArtiste(ActionEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        Artiste c = new Artiste();
        c.setNom_artiste(tfnoma.getText());
        c.setNom_artiste(tfnoma.getText());
        c.setEmail_artiste(tfemail.getText());
        c.setNum_artiste(Integer.parseInt(tfnumero.getText()));


        sc.AjouterArtiste(c);
        this.AfficherArtiste(event);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(0);
                return null;
            }
        };
    }
    
    
    @FXML
    private void AfficherArtiste(ActionEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        ObservableList<Artiste> artistes = sc.AfficherArtiste();

        idat.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("id_artiste"));
        nomat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("nom_artiste"));
        emailt.setCellValueFactory(new PropertyValueFactory<Artiste, String>("email_artiste"));
        numerot.setCellValueFactory(new PropertyValueFactory<Artiste, String>("num_artiste"));
        typeat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("type_artiste"));
        tablea.setItems(artistes);
    }

    @FXML
    private void selectionner(MouseEvent event) {
        Artiste c = tablea.getSelectionModel().getSelectedItem();
        idsup.setText(String.valueOf(c.getId_artiste()));
        tfnoma.setText(c.getNom_artiste());
        tfemail.setText(c.getEmail_artiste());
        tfnumero.setText(String.valueOf(c.getNum_artiste()));
        tftypea.setText(c.getType_artiste());
        
    }


    

    @FXML
    private void Supprimerartiste(ActionEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        sc.supprimerartiste(Integer.parseInt(idsup.getText()));
        this.AfficherArtiste(event);
    }

    @FXML
    private void ModifierArtiste(ActionEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        Artiste c = new Artiste();
        c.setNom_artiste(tfnoma.getText());
        c.setNom_artiste(tfnoma.getText());
        c.setEmail_artiste(tfemail.getText());
       // c.setNum_artiste(tfnumero.getText());
        sc.ModifierArtiste(c);
    }

    @FXML
    private void searchkey(KeyEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        ObservableList<Artiste> artistes = sc.search(tfsearch.getText());
        idat.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("id_artiste"));
        nomat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("nom_artiste"));
        emailt.setCellValueFactory(new PropertyValueFactory<Artiste, String>("email_artiste"));
        numerot.setCellValueFactory(new PropertyValueFactory<Artiste, String>("num_artiste"));
        typeat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("type_artiste"));
        tablea.setItems(artistes);
    }

    @FXML
    private void tributton(MouseEvent event) {
        ServiceArtiste sc = new ServiceArtiste();
        ObservableList<Artiste> artistes;
        if (vartri == 1) {
            vartri = 0;
            artistes = sc.triasc();
            idat.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("id_artiste"));
        nomat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("nom_artiste"));
        emailt.setCellValueFactory(new PropertyValueFactory<Artiste, String>("email_artiste"));
        numerot.setCellValueFactory(new PropertyValueFactory<Artiste, String>("num_artiste"));
        typeat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("type_artiste"));
        tablea.setItems(artistes);

        } else {
            vartri = 1;
            artistes = sc.triadsc();
            idat.setCellValueFactory(new PropertyValueFactory<Artiste, Integer>("id_artiste"));
        nomat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("nom_artiste"));
        emailt.setCellValueFactory(new PropertyValueFactory<Artiste, String>("email_artiste"));
        numerot.setCellValueFactory(new PropertyValueFactory<Artiste, String>("num_artiste"));
        typeat.setCellValueFactory(new PropertyValueFactory<Artiste, String>("type_artiste"));
        tablea.setItems(artistes);

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
    private void btnListecategorie(MouseEvent event) {
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
