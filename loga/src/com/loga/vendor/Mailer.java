package com.loga.vendor;

import com.loga.model.Message;

import javax.mail.*;
import javax.mail.Session;
import java.util.Properties;

/**
 * Classe de gestion des mails.
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 */
public class Mailer {
    private static Mailer mailer;
    private final Session session;

    private Mailer() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.mailtrap.io");
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.ssl.trust","smtp.mailtrap.id");
        properties.put("mail.user","username");
        properties.put("mail.password","password");

        this.session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return super.getPasswordAuthentication();
            }
        });

    }

    public static Mailer getInstance() {
        if(mailer==null){
            mailer = new Mailer();
        }
        return mailer;
    }

    public Session getSession() {
        return session;
    }

    public void sendMail(Message message ) throws MessagingException {
        Transport.send(message);
    }
}
