/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eya;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Asus
 */
public class Eya extends Application {
     private Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
          this.primaryStage = primaryStage; // connect primary stage
        
        mainWindow();
    }
    public void mainWindow() {
             try {
           Parent view7=FXMLLoader.load(getClass().getResource("signIn.fxml"));
                Scene scene7=new Scene(view7);
                //Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                Stage window =new Stage();
                window.setScene(scene7);
                window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    }

  