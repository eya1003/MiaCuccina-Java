/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class StatRecController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private BarChart<?, ?> bc;
    @FXML
    private NumberAxis na;
    @FXML
    private CategoryAxis ca;
    @FXML
    private Button btnPDF;
    @FXML
    private ComboBox<?> cbYear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnPDFClicked(MouseEvent event) {
    }

    @FXML
    private void cbYearAction(ActionEvent event) {
    }
    
}
