package Eya;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller  {
 File selectedFile;
    public TextField emailToField;
    public TextField emailFromField;
    public TextArea emailMessageField;
    public TextField emailSubjectField;
    public PasswordField emailPasswordField;
    public Label sentBoolValue;
    @FXML
    private Button sendEmailButton;
    @FXML
    private ImageView imageview;
    @FXML
    private JFXButton image;

    @FXML
    public void buttonClicked(ActionEvent actionEvent) throws IOException{
        sendEmail();
    }

  
     
    public void sendEmail() throws IOException{
        String to = emailToField.getText();
        String from = emailFromField.getText();
        String host = "smtp.gmail.com";
        final String username = emailFromField.getText();
        final String password = emailPasswordField.getText();
         Image DragFile =imageview.getImage();
        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(emailSubjectField.getText());
            m.setText(emailMessageField.getText());
            
                    Multipart emailContent = new MimeMultipart();
			
			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("My multipart text");
			
			//Attachment body part.
                         String path;
			MimeBodyPart pdfAttachment = new MimeBodyPart();
                        MimeBodyPart imageattachment = new MimeBodyPart();
			pdfAttachment.attachFile("C:/Users/Asus/Documents/Documents.pdf");
                       imageattachment.attachFile("C:/Users/Asus/Desktop/userplus.png");
                       
                    
			
                        
			//Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);
			emailContent.addBodyPart(imageattachment);
                      
			//Attach multipart to message
			m.setContent(emailContent);

            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }
  

    }

    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles())
        {        
    event.acceptTransferModes(TransferMode.ANY);
        }

    }

    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
Image img = new Image ( new FileInputStream(files.get(0)));
 imageview.setImage(img);
 
}
String path;
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
            image.setText(path);
            

        }
    }

    

   

   
   
       
}
