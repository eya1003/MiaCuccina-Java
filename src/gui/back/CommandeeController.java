package gui.back;

import entities.Cart;
import entities.Commande;
import gui.ListReservationController;
import java.io.IOException;
//import entities.Produit;
import services.commandeService;
//import Services.ServiceProduit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.collections.ObservableList;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CartService;
import utils.MyDB;

public class CommandeeController implements Initializable {

    @FXML
    private TableView<Commande> tableViewCom;

    @FXML
    private TableColumn<?, ?> numCommande;

    @FXML
    private TableColumn<?, ?> totalPaiement;

    @FXML
    private TableColumn<?, ?> state;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TextField tfstate;
    @FXML
    private Button removeBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button setUpdateBtn;
 
    
ObservableList<Commande>  commandeList = FXCollections.observableArrayList();
  
    @FXML
    private Pane banner;
    @FXML
    private Button tribtn;
    @FXML
    private TextField tfstate1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicReference<Commande> commandeAtR = new AtomicReference<>(new Commande());
        showCommandes();
        

        updateBtn.setOnAction(e->{
            Commande cmd = tableViewCom.getSelectionModel().getSelectedItem();
            if(cmd != null){
                commandeAtR.set(cmd);
                tfstate.setText(String.valueOf(cmd.getEtatcommande()));
               /* tfdesc.setText(produit.getDescription());
                tfref.setText(produit.getRef());
                tfprice.setText(String.valueOf(produit.getPrice()));
                tfimage.setText(produit.getImage());
                tfquantity.setText(String.valueOf(produit.getQuantity()));*/
            }
        });
        setUpdateBtn.setOnAction(e->{
            commandeService updateSer = new commandeService();
        
            try {
             
                updateSer.update(44);
                 updateSer.update(59);
                  updateSer.update(67);
            
         
                showCommandes();
            } catch (SQLException ex) {
                Logger.getLogger(CommandeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
         



        });
        
        
        removeBtn.setOnMouseClicked(event -> {
                           try{ 
                               
                               Commande commande = tableViewCom.getSelectionModel().getSelectedItem();
                              String  query = "DELETE FROM commande WHERE id_com  ="+commande.getId_com();
                               Connection connection = MyDB.getInstance().getConnexion();
                               PreparedStatement preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                           } catch (SQLException ex) {
                                Logger.getLogger(CommandeeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

    }

     @FXML
    private void btndashboardd(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void btnListeTable(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeEpmlacement(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeReservation(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListecategorie(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnListeLivraison(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(""));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    

    @FXML
    private void close(MouseEvent event) {
        
          Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    
               @FXML
    private void trierCommande(ActionEvent event) {

            commandeService ms = new commandeService();
        ObservableList<Commande> list = FXCollections.observableArrayList(ms.TrierParId());

            numCommande.setCellValueFactory(new PropertyValueFactory<>("id_com"));
            totalPaiement.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
            state.setCellValueFactory(new PropertyValueFactory<>("etatcommande"));
            date.setCellValueFactory(new PropertyValueFactory<>("datecommande"));
       tableViewCom.setItems(list);
         
    }
    @FXML
    private void search(ActionEvent event) {

       commandeService ms = new commandeService();
       ObservableList<Commande> list = FXCollections.observableArrayList(ms.RechercherCommande(tfstate1.getText()));

                numCommande.setCellValueFactory(new PropertyValueFactory<>("id_com"));
            totalPaiement.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
            state.setCellValueFactory(new PropertyValueFactory<>("etatcommande"));
            date.setCellValueFactory(new PropertyValueFactory<>("datecommande"));
       tableViewCom.setItems(list);
    }
    
    public void showCommandes() {
        try {
            ObservableList<Commande> commandes = new commandeService().getAll();
            numCommande.setCellValueFactory(new PropertyValueFactory<>("id_com"));
            totalPaiement.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
            state.setCellValueFactory(new PropertyValueFactory<>("etatcommande"));
            date.setCellValueFactory(new PropertyValueFactory<>("datecommande"));
            tableViewCom.setItems(commandes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @FXML
    private void refreshTable(MouseEvent event) {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            commandeList.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM commande");
        while(rs.next()){
         commandeList.add(new Commande(rs.getInt("id_com"),rs.getInt("etatcommande"),rs.getString("datecommande"),rs.getFloat("prixtotal")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(CommandeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          numCommande.setCellValueFactory(new PropertyValueFactory<>("id_com"));
            totalPaiement.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
            state.setCellValueFactory(new PropertyValueFactory<>("etatcommande"));
            date.setCellValueFactory(new PropertyValueFactory<>("datecommande"));
          
            tableViewCom.setItems(commandeList);
            
        
    }



}
