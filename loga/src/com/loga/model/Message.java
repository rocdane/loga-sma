package com.loga.model;

import com.loga.vendor.Mailer;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Message extends MimeMessage implements Serializable {

    public Message(Address from,Address to,Address[] cc,String subject, String message, List<File> files) throws MessagingException, IOException {
        super(Mailer.getInstance().getSession());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(message,"text/html;charset=utf-8");

        if(files!=null){
            for (File file:files) {
                mimeBodyPart.attachFile(file);
            }
        }

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        this.setFrom(from);
        this.setRecipient(Message.RecipientType.TO,to);
        this.setRecipients(Message.RecipientType.BCC,cc);
        this.setSubject(subject);
        this.setContent(multipart);
    }
}
