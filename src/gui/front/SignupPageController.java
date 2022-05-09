/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author eyaam
 */

import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.ConnectSql;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.net.MalformedURLException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class SignupPageController implements Initializable {
  String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement ps;

    @FXML
    private ImageView recaptchaCheckMark;
    @FXML
    private Button createBtn;
    @FXML
    private Label errorMessage;
    
    @FXML
    private JFXTextField localisationtf;
    @FXML
    private JFXTextField imagetf;
    @FXML
    private JFXTextField numerotf;
    @FXML
    private JFXTextField emailtf;
    
    @FXML
    private JFXTextField passwordtf;
    @FXML
    private ComboBox<String> roletf;
    @FXML
    private JFXTextField passwordtf1;
    @FXML
    private JFXTextField usernametf;
    
    @FXML
    private FontAwesomeIconView QrCode;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
   @FXML
     void register(ActionEvent event) throws IOException {
       ConnectSql connect = new ConnectSql();
        
        try{
            
            Connection con = connect.getconnection();
            
              String  username =usernametf.getText() ;
  
  String   localisation=localisationtf.getText();
  String   images=imagetf.getText();
  String   numero=numerotf.getText();
  String   emailadresse=emailtf.getText();
  
  
  String  role=roletf.getValue() ;
  
  String  password=passwordtf.getText() ;
            
            if(username.isEmpty()  ||localisation.isEmpty() || images.isEmpty() || numero.isEmpty() || emailadresse.isEmpty()||role.isEmpty()||password.isEmpty()   )
            {
                errorMessage.setText("Please complete all the fills");
            }
            else {
               if (password.length()<6){
                   errorMessage.setText("Password is too weak, please choose atleast 6 characters");
               }
            
            else {
                   
            String sql = "select * from user where emailadresse = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                errorMessage.setText("Email already taken, please try another email");
            }
            else {
              if( etatrecaptcha==0){
                    errorMessage.setText("You must verify recapctha");
            
            
            }
            else{
                
            String sql2 = "INSERT INTO `user`(`username`,  `localisation`, `images`, `numero`, `emailadresse`,  `role`,  `password`)  VALUES (?,?,?,?,?,?,?)";  
            ps = con.prepareStatement(sql2);
             ps.setString(1, usernametf.getText());
             
             ps.setString(2, localisationtf.getText());
             ps.setString(3, imagetf.getText());
             ps.setString(4, numerotf.getText());
              ps.setString(5, emailtf.getText());
             
             
             ps.setString(6, roletf.getValue());
             
               ps.setString(7, passwordtf.getText());

            ps.execute();
            
            errorMessage.setText("Account successfully registered");
         
        }
       }
        }
       }
     }
        catch(Exception e)
        {
            System.out.println("error" + e);
        }
    }
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // questionBox.setValue("Choose your question");
    //questionBox.setItems(questionBoxList);
    roletf.getItems().addAll("Admin","Visiteur");
         window = new Stage();
        webView2 = new WebView();
        webEngine = webView2.getEngine();
        window.setOnCloseRequest(e -> {
            if (webEngine != null && webEngine.getTitle().contains("success")) {
                etatrecaptcha = 1;
                recaptchaCheckMark.setImage(new Image("gui/checkMark.png"));
            }
            System.out.println("etat recaptcha=" + etatrecaptcha);
        });
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
    }  
     int etatrecaptcha = 1;
    Stage window;
    WebView webView2;
    WebEngine webEngine;
    @FXML
    private void recaptcha(MouseEvent event) {

        //Block events to other windows
//            window.setTitle("Veuillez choisir un compte");
        webView2.setPrefSize(400, 500);
        webEngine.setUserAgent("use required / intended UA string");
        webEngine.load("http://localhost/recaptcha/WebContent/login.html");

        Button closeButton = new Button("Fermer");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(webView2);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    String path;
    File selectedFile;
    @FXML
    private void image(ActionEvent event) throws MalformedURLException {
          FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
               path = selectedFile.toURI().toURL().toExternalForm();
            imageview.setImage(new Image(selectedFile.toURI().toURL().toString()));
            imageview.setFitHeight(150);
            imageview.setFitWidth(250);
            imagetf.setText(path);
            

        }
    }

    private void QrCode(MouseEvent event) {
        try {
           // String qrCodeData = "www.chillyfacts.com";
           String qrCodeData = usernametf.getText();
            String filePath = "chillyfacts.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel > hintMap = new HashMap < com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel > ();
            hintMap.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L);
            com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
