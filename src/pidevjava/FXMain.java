/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava ;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author souso
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) { 
        try { 
         Parent parent = FXMLLoader.load(getClass().getResource("/gui/back/AjoutProduit1.fxml"));
        //Parent parent = FXMLLoader.load(getClass().getResource("/gui/Produit/menu.fxml"));
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show(); 
            primaryStage.setTitle("Mia cuccina");
        } catch (IOException ex) { 
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        public static void main(String[] args) {
      launch(args);
    } 
//      URL root_url = new File("src/Gui/Produit/AddProduct.fxml").toURI().toURL();
//      Parent parent = FXMLLoader.load(root_url);
//        Scene scene = new Scene(parent);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        scene.setFill(Color.TRANSPARENT);
//        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                xOffset = event.getSceneX();
//                yOffset = event.getSceneY();
//            }
//        });
//        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                primaryStage.setX(event.getScreenX() - xOffset);
//                primaryStage.setY(event.getScreenY() - yOffset);
//            }
//        });
//        primaryStage.setTitle("Mia cuccuna Application");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    /**
//     * @param args the command line arguments
//     */


}