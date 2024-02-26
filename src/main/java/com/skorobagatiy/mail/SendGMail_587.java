package com.skorobagatiy.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendGMail_587 {
    private static final String from = "nick1980457@gmail.com"; //requires valid gmail id
    private static final String password = "password"; // correct password for gmail id
    private static final String to = "nick1980457@gmail.com"; // can be any email id

    private static String smtpPort = "995";
    private static String host = "smtp.gmail.com";

    // Run the mail example
    public static void main(String[] args) {
        // Send email
        System.out.println("=================  START  ======================== ");

        Properties properties = new Properties();
        properties.put("mail.transport.protocol","smtps");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // properties.put("mail.smtp.ssl.protocols", "TLSv1.2");


        properties.put("mail.debug", "true");
        System.out.println("Creating the session ....");
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        session.setDebug(true);
        System.out.println("done!");

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("587 5 java point");
            message.setText("This is simple program of sending email using JavaMail API");

            //send the message
            System.out.println("Test...");
            Transport.send(message);
            System.out.println("message sent successfully...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
