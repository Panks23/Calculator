package com.pankaj.calculatorJavaAssessment.logging.notification;

import static com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.NotificationConstants.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.pankaj.calculatorJavaAssessment.notification.NotificationConstant.ConfigLoggerProperties;



public class MailSender implements NotificationsSender {
	private static Logger logger = Logger.getLogger(MailSender.class.getName());
	
	public MailSender() {
		ConfigLoggerProperties.configLoggerProperties();
	}

    @Override
    public void sendNotification(Notification notification) {
    	
    	logger.info("Setting Properties for creating session");
    	Properties props = prepareProperties();  
    	logger.info("Preparing Session");
    	Session session = getSession(props, notification);
    	logger.info("Preparing Message with Attachement");
    	MimeMessage message = prepareMessageWithAttachement(session, notification);
    	try {
    		logger.info("Sending Email");
			Transport.send(message);
	    	System.out.println("Email sent successfully...");  
		} catch (MessagingException e) {
			logger.error("Something went wrong while sending email");
				e.printStackTrace();
		}  
    }
    
    public Properties prepareProperties() {	
    	String host="smtp.gmail.com";  
    	
    	Properties props = new Properties();  
    	props.put("mail.smtp.auth", "true"); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.host",host);  
    	props.put("mail.smtp.port", "587");
    	
    	return props;
    }
    
    public Session getSession(Properties props, Notification notification) {
    	final String user = notification.getFromAddress();
    	final String password = SENDER_EMAIL_PASSWORD;
    	Session session = Session.getInstance(props,  new Authenticator() {  
  	      protected PasswordAuthentication getPasswordAuthentication() {  
  	    	  return new PasswordAuthentication(user,password);  
  	      }  
  	    });  
    	return session;
    }
    
    public MimeMessage prepareMessageWithAttachement(Session session, Notification notification) {
    	try {
	    	String from = notification.getFromAddress();
	    	String to= notification.getToAddress();
	    	String subject = notification.getSubject();
	    	String bodyMessage = notification.getMessage();
	    	File fileattached = notification.getAttachment();
	    	
	    	MimeMessage message = new MimeMessage(session);  
	 	    message.setFrom(new InternetAddress(from));  
	 	    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	 	    message.setSubject(subject);  
	 	     
			Multipart emailContent = new MimeMultipart();    	     
	 	     
	 	    MimeBodyPart textBodyPart = new MimeBodyPart();
	 	    
			textBodyPart.setText(bodyMessage);
				
			MimeBodyPart fileAttachment = new MimeBodyPart();
			try {
				fileAttachment.attachFile(fileattached);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(fileAttachment);
			message.setContent(emailContent);
	    	return message;
    	}catch (MessagingException e) {
    		e.printStackTrace();
    	}  
    	return null;
    }
}
