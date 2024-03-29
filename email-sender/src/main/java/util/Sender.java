package util;


  
import java.util.Date;   
import java.util.Properties;  
import javax.mail.Address;   
import javax.mail.BodyPart;   
import javax.mail.Message;   
import javax.mail.MessagingException;   
import javax.mail.Multipart;   
import javax.mail.Session;   
import javax.mail.Transport;   
import javax.mail.internet.InternetAddress;   
import javax.mail.internet.MimeBodyPart;   
import javax.mail.internet.MimeMessage;   
import javax.mail.internet.MimeMultipart;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
* mail without attachments sender
*/
public class Sender  {   

    public boolean sendTextMail(Email mailInfo) {   
      MyAuthenticator authenticator = null;
      Properties pro = mailInfo.getProperties();  
      if (mailInfo.isValidate()) {   
        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
      }  
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);
      try {   
      Message mailMessage = new MimeMessage(sendMailSession);
      Address from = new InternetAddress(mailInfo.getFrom());
      mailMessage.setFrom(from);
      Address to = new InternetAddress(mailInfo.getTo());
      mailMessage.setRecipient(Message.RecipientType.TO,to);   
      mailMessage.setSubject(mailInfo.getSubject());
      mailMessage.setSentDate(new Date());
      String mailContent = mailInfo.getContent();
      mailMessage.setText(mailContent);   
      Transport.send(mailMessage);
      return true;   
      } catch (MessagingException ex) {   
          ex.printStackTrace();   
      }   
      return false;   
    }   
      

    public static boolean sendHtmlMail(Email mailInfo){   
      MyAuthenticator authenticator = null;
      Properties pro = mailInfo.getProperties();  
      if (mailInfo.isValidate()) {
        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());  
      }   
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);
      try {   
      Message mailMessage = new MimeMessage(sendMailSession);
      Address from = new InternetAddress(mailInfo.getFrom());

      mailMessage.setFrom(from);   
       
      Address to = new InternetAddress(mailInfo.getTo());   
      mailMessage.setRecipient(Message.RecipientType.TO,to);
      mailMessage.setSubject(mailInfo.getSubject());
      mailMessage.setSentDate(new Date());
      Multipart mainPart = new MimeMultipart();
      BodyPart html = new MimeBodyPart();
      html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
      mainPart.addBodyPart(html);   
      mailMessage.setContent(mainPart);
      Transport.send(mailMessage);
      return true;   
      } catch (MessagingException ex) {   
          ex.printStackTrace();   
      }   
      return false;   
    }   
} 