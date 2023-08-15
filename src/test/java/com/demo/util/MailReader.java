package com.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;


public class MailReader {
	
	
	public static String GmailRead(String email, String password, String subject, String MatchingString) throws MessagingException, IOException
	{
		String url = null;
		
		String host = "imap.gmail.com";
		String mailStoreType = "imap";

		// create properties field
		Properties properties = new Properties();
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", "993");
		
		properties.put("mail.imap.ssl.trust", "*");
		properties.put("mail.imap.starttls.enable", "true");
		properties.setProperty("mail.imap.socketFactory.class", "com.sun.mail.util.MailSSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", "993");
		Session emailSession = Session.getDefaultInstance(properties);

		// create the POP3 store object and connect with the pop server
		Store store = emailSession.getStore(mailStoreType);
		store.connect(host, email, password);

		// create the folder object and open it
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);

		// retrieve the messages from the folder in an array and print it
		Message[] messages = emailFolder.getMessages();
		System.out.println("messages.length---" + messages.length);
		int n = messages.length;
		for (int i = 0; i < n; i++) {
			Message message = messages[i];
			ArrayList<String> links = new ArrayList<String>();
			
			if (message.getSubject().contains(subject)) {
				
				/*MimeMultipart content = (MimeMultipart) message.getContent();
				
				IMAPBodyPart bodyPart = (IMAPBodyPart) content.getBodyPart(0);

				MimeMultipart mimeMultipart = (MimeMultipart) bodyPart.getContent();
				
				bodyPart = (IMAPBodyPart) mimeMultipart.getBodyPart(0);
				
				content = (MimeMultipart) bodyPart.getContent();
				
				String desc=	(String) ((IMAPBodyPart) content.getBodyPart(1)).getContent();*/
				
				String desc=message.getContent().toString();
				
				Pattern linkPattern = Pattern.compile("href=\"(.*?)\"",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
				Matcher pageMatcher = linkPattern.matcher(desc);

				while (pageMatcher.find()) {
					links.add(pageMatcher.group(1));
				}
			} else {
				System.out.println("Email:" + i + " is not a wanted email");
			}
			for (String temp : links) {
				if (temp.contains(MatchingString)) {
					System.out.println("Link is : " + temp);
					url= temp;
					
					break;
				}
			}
			}
		// close the store and folder objects
				emailFolder.close(false);
				store.close();
				return url;
	}
	
	public void sendMailReport(String fromMailId, String fromMailPassword, String toMailIds, String mailSubject, String mailBody, String filePath) 
	{	 
		// Create object of Property file
		Properties props = new Properties();
 
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");
 
		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");
 
		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
 
		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
 
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() 
		{
					protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromMailId, fromMailPassword);
		}});
		try {
 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
 
			// Set the from address
			message.setFrom(new InternetAddress(fromMailId));
 
			// Set the recipient address - mail ids should be separated by comma "mail1, mail2, mail3"
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toMailIds));
            
            // Add the subject link
			message.setSubject(mailSubject);
 
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
 
			// Set the body of email
			messageBodyPart1.setText(mailBody);
 
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
			// Mention the file which you want to send
			String filename = filePath;
 
			// Create data source and pass the filename
			FileDataSource source = new FileDataSource(filename);
 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// set the file
			messageBodyPart2.setFileName(filename);
 
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
 
			// add body part 1
			multipart.addBodyPart(messageBodyPart2);
 
			// add body part 2
			multipart.addBodyPart(messageBodyPart1);
 
			// set the content
			message.setContent(multipart);
 
			// finally send the email
			Transport.send(message);
 
			System.out.println("=====Email Sent=====");
 
		} catch (MessagingException e) {
 
			throw new RuntimeException(e);
 
		}
 
	}

}
