/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.eya ;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import com.sun.scenario.effect.ImageData;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import utils.MyDB;



//import com.gembox.spreadsheet.*;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Element;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
//import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.control.Menu;
import javax.imageio.ImageIO;
import jdk.nashorn.internal.objects.annotations.Property;
import entities.User;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UserTableController implements Initializable {

    @FXML
    private TableColumn<User, Integer> colid;
    @FXML
    private TableColumn<User, String> colusername;
   
    
    @FXML
    private TableColumn<User, String> collocalisation;
    @FXML
    private TableColumn<User, String> colimages;
    @FXML
    private TableColumn<User, String> colnumero;
    @FXML
    private TableColumn<User, String> colemail;
   
    @FXML
    private TableColumn<User, String> colrole;
    
    @FXML
    private TableColumn<User, String> colpassword;
      String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    User users  ;
   private final 
    ObservableList<User>usersList = FXCollections.observableArrayList();
    @FXML
    private TableView<User> usersTables;
    @FXML
    private TableColumn<User, String> editCol;
    @FXML
    private TextField searchtf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
         searching();
    }    
 void close(MouseEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
     
    private void getAddView(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddVisiteur.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
        void refreshTable()  {
        try {
            usersList.clear();
            
            query = "SELECT * FROM `user`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            
            
            while (resultSet.next()){
                usersList.add(new  User(
                         resultSet.getInt("id"),
                          
       resultSet.getString("username") ,
         
         
       resultSet.getString("localisation"),
       resultSet.getString("images"),
       resultSet.getString("numero"),
       resultSet.getString("emailadresse"),
              resultSet.getString("role") ,
       
       resultSet.getString("password") ));
                usersTables.setItems(usersList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
 void loadDate()  {
          
        Connection connection = MyDB.getInstance().getConnexion();
        refreshTable();
          colid.setCellValueFactory(new PropertyValueFactory<>("id"));
         
          colusername.setCellValueFactory(new PropertyValueFactory<>("username"));
          
           collocalisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
         colimages.setCellValueFactory(new PropertyValueFactory<>("images"));
          colnumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
      
           colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
     
       
         colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
          
        //add cell of button edit 
         Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory;
        cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell;
          cell = new TableCell<User, String>() {
              @Override
              public void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  //that cell created only on non-empty rows
                  if (empty) {
                      setGraphic(null);
                      setText(null);
                      
                  } else {
                      
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                      
                      deleteIcon.setStyle(
                              " -fx-cursor: hand ;"
                                      + "-glyph-size:28px;"
                                      + "-fx-fill:#ff1744;"
                      );
                      editIcon.setStyle(
                              " -fx-cursor: hand ;"
                                      + "-glyph-size:28px;"
                                      + "-fx-fill:#00E676;"
                      );
                      deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                users = usersTables.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `user` WHERE id  ="+users.getId();
                                Connection connection = MyDB.getInstance().getConnexion();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                        });
                      editIcon.setOnMouseClicked((MouseEvent event) -> {
                          
                          users = usersTables.getSelectionModel().getSelectedItem();
                          FXMLLoader loader1 = new FXMLLoader ();
                         
                          
                          loader1.setLocation(getClass().getResource("/gui/AddVisiteur.fxml"));
                         
                         
                      
                          try {
                             
                              loader1.load();
                             // loader2.load();
                            
                             
                          } catch (IOException ex) {
                              Logger.getLogger(UserTableController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                         
                         AddVisiteurController addVisiteurController = loader1.getController();
                           addVisiteurController.setUpdate(true);
                            addVisiteurController.setTextField(users.getId(),users.getUsername(),users.getLocalisation(),
  users.getNumero(),users.getImages(), users.getEmailadresse(), users.getRole(),
 users.getPassword());
                            Parent parent = loader1.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                          
                      
                          
                      });
                      
                      HBox managebtn = new HBox(editIcon, deleteIcon);
                      managebtn.setStyle("-fx-alignment:center");
                      HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                      HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                      
                      setGraphic(managebtn);
                      
                      setText(null);
                      
                  }
              }
              
          };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         usersTables.setItems(usersList);
         
         
    }

    @FXML
    private void getAddJOBSEEKERView(MouseEvent event) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddVisiteur.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getAddCompanyView(MouseEvent event) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("MenuBack.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void searching() {
        // TODO
       
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
          
          colusername.setCellValueFactory(new PropertyValueFactory<>("username"));
         
           collocalisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
         colimages.setCellValueFactory(new PropertyValueFactory<>("images"));
          colnumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
      colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
     
      
         colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
      // ObservableList<User>usersList = FXCollections.observableArrayList();
        Connection connection = MyDB.getInstance().getConnexion();
        String query = "SELECT * FROM user";
        Statement st;
        ResultSet rs;
        
        try{
            st = connection.createStatement();
            rs = st.executeQuery(query);
            User user;
            while(rs.next()){
               user = new User(rs.getInt("id"),rs.getString("username"), rs.getString("localisation"), rs.getString("images"), rs.getString("numero"), rs.getString("emailadresse"), rs.getString("role"), rs.getString("password"));
                usersList.add(user);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
      
        FilteredList<User> filteredData = new FilteredList<>( usersList, b -> true);
	
		searchtf.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
		
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getEmailadresse().toLowerCase().contains(lowerCaseFilter) ) {
					return true;
				} 
                                /* else if (user.getFirst_name().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				}
                                 else if (user.getUsername().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				}
                                */
				else if (String.valueOf(user.getId()).contains(lowerCaseFilter))
				     return true;
                                
                                else if (String.valueOf(user.getLocalisation()).contains(lowerCaseFilter))
				     return true;
                                else if (String.valueOf(user.getNumero()).contains(lowerCaseFilter))
				     return true;
				     else  
				    	 return false;
                                // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind( usersTables.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		usersTables.setItems(sortedData);
      
               
    }

    @FXML
    private void EMAIL(MouseEvent event) throws IOException {
         Parent view6=FXMLLoader.load(getClass().getResource("/gui/mail.fxml"));
                Scene scene6=new Scene(view6);
                Stage window =new Stage();
                //Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene6);
                window.show();
    }
}
