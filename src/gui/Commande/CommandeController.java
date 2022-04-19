package Gui.Commande;

import entities.Commande;
//import entities.Produit;
import services.commandeService;
//import Services.ServiceProduit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import utils.MyDB;

public class CommandeController implements Initializable {

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
    private TableColumn<?, ?> idUser;
    @FXML
    private Button removebtn;
    @FXML
    private Pane banner;
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
             
                updateSer.update(45);
                updateSer.update(59);
                updateSer.update(68);
                updateSer.update(69);
                updateSer.update(70);
                showCommandes();
            } catch (SQLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
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
                                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

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
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          numCommande.setCellValueFactory(new PropertyValueFactory<>("id_com"));
            totalPaiement.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
            state.setCellValueFactory(new PropertyValueFactory<>("etatcommande"));
            date.setCellValueFactory(new PropertyValueFactory<>("datecommande"));
          
            tableViewCom.setItems(commandeList);
            
        
    }

}