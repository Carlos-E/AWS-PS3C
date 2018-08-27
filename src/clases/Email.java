package clases;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	static final String HOST = "snap.wnkserver8.com";
	static final int PORT = 587;

	static final String SMTP_USERNAME = "sistemas@nauticaintegral.com";
	static final String SMTP_PASSWORD = "JGzRbG8zpAD5sGkk";
	
	static final String CONFIGSET = "ConfigSet";

	
	static final String FROM = "noresponder@ps3c.com";
	static final String FROMNAME = "PS3C Mail";

	
	public Email(String TO, String SUBJECT, String BODY) throws Exception {

		// Create a Properties object to contain connection configuration
		// information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the
		// specified properties.
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY, "text/html; charset=UTF-8");

		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		// msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try {
			System.out.println("Sending...");

			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			
			System.out.println("Email sent!");
			
		} catch (Exception ex) {
			
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
			
		} finally {
			
			// Close and terminate the connection.
			transport.close();
		}
	}
	
}