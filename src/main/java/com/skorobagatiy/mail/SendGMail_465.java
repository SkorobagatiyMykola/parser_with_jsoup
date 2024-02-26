package com.skorobagatiy.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendGMail_465 {
    private static String from = "nick1980457@gmail.com";
    private static String to = "nick1980457@gmail.com";
    private static String host = "smtp.gmail.com";
    private static String password = "password";
    private static String smtpPort = "465";

    public static void main(String[] args) throws Exception {
        System.out.println("Seng email from ukr.net");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", smtpPort);

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);

        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.port", smtpPort);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");


        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");


        properties.put("mail.debug", "true");
        System.out.println("Creating the session ....");
        //Session session = Session.getDefaultInstance(properties);
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        session.setDebug(true);
        System.out.println("res: " + session.getTransport().isConnected());
        System.out.println("done!");


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("GMail 465 java point");
            message.setText("This is simple program of sending email using JavaMail API");

            //send the message
            System.out.println("Test...");
            Transport.send(message);

            /*Transport transport = session.getTransport("smtp");
            transport.connect(from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();*/

            System.out.println("message sent successfully...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
