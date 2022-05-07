/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author eyaam
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;

import java.util.Properties;
import javax.mail.internet.MimeMessage;

public class email {

    public void s() {

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
                    InternetAddress.parse("bouounnour81@gmail.com , hatemmajouri12@gmail.com")
            );
            message.setSubject("Ajouter de Produits");
            message.setText("product added with sucess");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}

