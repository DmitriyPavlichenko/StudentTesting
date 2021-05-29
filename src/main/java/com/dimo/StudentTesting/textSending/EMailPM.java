package com.dimo.StudentTesting.textSending;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EMailPM implements IPostman, Runnable {
    String to;
    String sub;
    List<String> answersList;

    public EMailPM(String to, String sub, List<String> answersList) {
        this.to = to;
        this.sub = sub;
        this.answersList = answersList;
    }

    @Override
    public void run() {
        sendText();
    }

    @Override
    public void sendText() {
        final String user = "YourEMail"; //Must to be changed
        final String pass = "YourPassword"; //Must to be changed
        final String msg = ListToNormalizedString(answersList);
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props,new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user,pass);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);

            System.out.println("Answers was successfully send to EMail");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
