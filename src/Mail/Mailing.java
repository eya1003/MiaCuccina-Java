package Mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author NADA_USER 
 */
public class Mailing {
    public static void main(String[] args) throws Exception {
       final String username = "madara1234g@gmail.com";
        final String password = "MozillaNaruto";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }); 

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("madara1234g@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("zeinebhamdi2013@gmail.com")
            );
            message.setSubject("Mia cuccina");
            message.setText("nouveau commande ajoutée!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        }
    
}  
        
        
        
    
