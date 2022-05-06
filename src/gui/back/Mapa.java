package gui.back;

import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

//import com.teamdev.jxmaps.swing.MapView;

//import com.teamdev.jxmaps.*;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Mapa //extends MapView
{
	
/**
 * The map object
 */
 @FXML
    private WebView maps;


    @FXML
    private void cons_map(javafx.scene.input.MouseEvent event) {
          WebEngine webEngine = maps.getEngine();
          webEngine.load("https://www.google.com/maps/");
    }
}
	