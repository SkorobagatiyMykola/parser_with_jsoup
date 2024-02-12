package com.skorobagatiy.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendUkrNet {
    private static String from = "nikolays2001@ukr.net";
    private static String to = "nikolays2001@ukr.net";
    private static String host = "smtp.ukr.net";
    private static String password = "******";
    private static String smtpPort = "465";

    public static void main(String[] args) {
        System.out.println("Seng email from ukr.net");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        //properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.transport.protocol", "smtp");

        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //properties.put("mail.smtp.ssl.enable", "true");


        //properties.put("mail.transport.protocol", "smtp");
        //properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.starttls.required", "true");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.connectiontimeout", "t1");
//        properties.put("mail.smtp.timeout", "t2");

        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.connectiontimeout", "t1");
        properties.put("mail.smtp.timeout", "t2");


        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        session.setDebug(true);


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("java point");
            message.setText("This is simple program of sending email using JavaMail API");

            //send the message
            System.out.println("Test...");

            Transport.send(message);
           /* try (Transport transport = session.getTransport("smtp")) {
                transport.connect(host, from, password);
                transport.sendMessage(message, message.getAllRecipients());
            }*/

            //Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
