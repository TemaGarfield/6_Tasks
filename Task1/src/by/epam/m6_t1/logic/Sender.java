package by.epam.m6_t1.logic;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender{
    public static void sendMail(InternetAddress[] recepients, String mailText) throws Exception {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "flomaster4679@gmail.com";
        String myAccountPassword = "i<3MyLapa";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myAccountPassword);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepients, mailText);

        Transport.send(message);

        System.out.println("Message send successfully");
    }

    private static Message prepareMessage(Session session, String email, InternetAddress[] recepients, String mailText) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, recepients);
            message.setSubject("We added a book");
            message.setText(mailText);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
