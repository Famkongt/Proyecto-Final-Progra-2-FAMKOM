/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Usuario
 */
public class clsSendEmail {
    
     public static void sendEmail(String to, String subject, String body) {
        // Configurar las propiedades para conectarse al servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Gmail
        props.put("mail.smtp.port", "587");

        // Autenticación
        String from = "famkongt24@gmail.com"; // Cambia por tu correo
        String password = "suqt xgaq vfjr ecuw";  // Cambia por tu contraseña

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado con éxito");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar correo: " + e);
        }
    }
    
}
