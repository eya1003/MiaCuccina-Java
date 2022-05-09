package gui.back;


import services.commandeService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import utils.MyDB;

public class StatController implements Initializable {

    @FXML
    private PieChart pie;

    private ImageView close;
    @FXML
    private BarChart<String, Integer> barchart;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showstat();
        
                
        String query = "select nomProduit,prix from `cart`  ";
        System.out.println(query);
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            Connection con = MyDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(query);
               barchart.setTitle("Stats");
         
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString("nomProduit").toString(), rs.getInt("prix")));
            }
            barchart.getData().add(series);
        } catch (Exception e) {
        }
        
        
    }

    private void showstat() {
        pie.setTitle("Orders Stats");
        pie.setData(new commandeService().getdata());
        
        for (PieChart.Data data : pie.getData()) {
            data.nameProperty().set(data.getName() + " : " + (int) data.getPieValue() + "Non Payé");
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    JOptionPane.showMessageDialog(null, "Date = " + data.getName() + " Total Orders = " + (int) data.getPieValue());
                }
            });
        }
    }

    void handleClose(MouseEvent event) {
        if(event.getSource() == close){
            Stage stg = (Stage) close.getScene().getWindow();
            stg.close();
        }
    }
}
