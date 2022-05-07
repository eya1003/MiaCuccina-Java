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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property;
import entities.Avis;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class TableViewController implements Initializable {

    @FXML
    private TableView<Avis> studentsTable;
    @FXML
    private TableColumn<Avis, String> idCol;
    @FXML
    private TableColumn<Avis, String> nameCol;
    @FXML
    private TableColumn<Avis, String> birthCol;
    @FXML
    private TableColumn<Avis, String> adressCol;
    @FXML
    private TableColumn<Avis, String> emailCol;
    @FXML
    private TableColumn<Avis, String> editCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Avis avis = null ;
    
    ObservableList<Avis>  StudentList = FXCollections.observableArrayList();
    @FXML
    private TextField tfsearch;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
         searching();
        
    }    
    
    
    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getAddView(MouseEvent event) {
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/addAvis.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refreshTable() {
        try {
            StudentList.clear();
            
            query = "SELECT * FROM `avis`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                StudentList.add(new  Avis(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getDate("date"),
                        resultSet.getString("objet"),
                        resultSet.getString("contenu")));
                studentsTable.setItems(StudentList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }


    private void loadDate() {
        Connection connection = MyDB.getInstance().getConnexion();
        refreshTable();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        adressCol.setCellValueFactory(new PropertyValueFactory<>("objet"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        
        //add cell of button edit 
         Callback<TableColumn<Avis, String>, TableCell<Avis, String>> cellFoctory = (TableColumn<Avis, String> param) -> {
            // make cell containing buttons
            final TableCell<Avis, String> cell = new TableCell<Avis, String>() {
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
                                avis = studentsTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `avis` WHERE id  ="+avis.getId();
                                        Connection connection = MyDB.getInstance().getConnexion();

                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            avis = studentsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/addAvis.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddSAvisController addStudentController = loader.getController();
                            addStudentController.setUpdate(true);
                            addStudentController.setTextField(avis.getId(), avis.getEmail(), 
                                    avis.getDate().toLocalDate(),avis.getObjet(), avis.getContenu());
                            Parent parent = loader.getRoot();
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
         studentsTable.setItems(StudentList);
         
         
    }
      public void searching() {
        // TODO
       
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        adressCol.setCellValueFactory(new PropertyValueFactory<>("objet"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        ObservableList<Avis>usersList = FXCollections.observableArrayList();
       
                Connection connection = MyDB.getInstance().getConnexion();

        String query = "SELECT * FROM avis";
        Statement st;
        ResultSet rs;
        
        try{
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Avis avis;
            while(rs.next()){
               avis = new Avis(rs.getInt("id"),rs.getString("email"), rs.getDate("date"), rs.getString("objet"), rs.getString("contenu"));
                StudentList.add(avis);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
      
        FilteredList<Avis> filteredData = new FilteredList<>( StudentList, b -> true);
	
		tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(avis -> {
		
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (avis.getEmail().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (avis.getObjet().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(avis.getId()).contains(lowerCaseFilter))
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Avis> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(studentsTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		studentsTable.setItems(sortedData);
      
               
    }

    @FXML
    private void STAT(MouseEvent event) throws IOException {
         Parent view6=FXMLLoader.load(getClass().getResource("/gui/chart.fxml"));
                Scene scene6=new Scene(view6);
                Stage window =new Stage();
                //Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene6);
                window.show();
    }
}
