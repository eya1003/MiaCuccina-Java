/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author NADA_USER
 */
public class MailUtil {
    public static void sendMail (String recepient) throws Exception{
        
                System.out.println("preparing to send email");

        Properties properties = new Properties(); 
        
           
     properties.put("mail.smtp.auth", "true");    
    
      properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail="nadounouissee@gmail.com";
        String password="NAda@@123";
        
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
            
}); 
        Message message = prepareMessage(session,myAccountEmail,recepient);
                      Transport.send(message);
                      System.out.println("mess sent successfully !!");}
        private static Message prepareMessage (Session session , String myAccountEmail ,String recepient )
          {
try{
         Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(" Mia cuccina");
                       // String msg= (code+" est votre code de recupération de compte");

           // String msg= code+" est votre code de recupération de compte";
           
             // message.setText("Hi ! it's HabHub_Application 🐶 ! \n");
          message.setText("Produit ajoutée ");
          return message;
          
                }catch(Exception ex){
             
                    Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE,null,ex);}
                return null;
}

   
    }