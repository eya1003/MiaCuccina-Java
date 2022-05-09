/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class StatCatController implements Initializable {

    @FXML
    private BarChart<String,Double> barchart;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String query = "SELECT nom, quantite_produit FROM produit ";
        System.out.println(query);
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            Connection con = MyDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString("nom").toString(), rs.getDouble("quantite_produit")));
            }
            barchart.getData().add(series);
        } catch (Exception e) {
        
        // TODO
    }    
 
}

    @FXML
    private void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    }

