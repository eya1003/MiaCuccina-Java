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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaam
 */
public class StatistiqueTableFXMLController implements Initializable {

    @FXML
    private BarChart BarChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      String dataa= "SELECT stock_tab, nb_chaise_tab FROM table";
      Connection con = MyDB.getInstance().getConnexion();
        JDBCCategoryDataset dataset= new  JDBCCategoryDataset(con);
        JFreeChart chart =ChartFactory.createLineChart("Query chart", "stock_tab", "nb_chaise_tab", dataset, PlotOrientation.VERTICAL, false, true, true);
        BarRenderer render=null;
        CategoryPlot plot = null;
        render = new BarRenderer();
        ChartFrame frame = new ChartFrame("query chart", chart);
        
        frame.setVisible(true);
        frame.setSize(400, 600);
    }    
    
}
