package clases;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	
	//VARIABLES
	static final String HOST = "smtp.gmail.com";
	static final int PORT = 587;

	static final String SMTP_USERNAME = "ps3c.mail@gmail.com";
	static final String SMTP_PASSWORD = "a1234567890*";
	//VARIABLES

	
	static final String CONFIGSET = "ConfigSet";

	static final String FROM = "no-responder@ps3c.com";
	static final String FROMNAME = "PS3C Mail";

	
	public Email(String TO, String SUBJECT, String BODY) {
		
		try {

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
		msg.setContent(BODY, "text/html; charset=utf-8");

		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		// msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		
			System.out.println("Sending...");

			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			
			System.out.println("Email sent!");
			
		} catch (Exception ex) {
			
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
			
		}
	}
	
	
	public Email(String TO, String SUBJECT,String HEADER, Envio envio) {
		
		String BODY = String.join(System.getProperty("line.separator"), "<h2>",HEADER,"</h2>", 
				"<p>Origen: ",envio.getOrigen(), 
				"<br>Destino:", envio.getDestino(), 
				"<br>Espacio: ",String.valueOf(envio.getEspacio()), "metros cubicos ",
				"<br>Peso: ",String.valueOf(envio.getPeso()), "Kg",
				"<br>Tipo:", envio.getTipo(), 
				"<br>Descripci&oacute;n: ",envio.getDescripcion(), 
				"<br>Estado del env&iacute;o: <strong>",envio.getEstado(),"</strong> </p>");
		
		try {

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
		msg.setContent(BODY, "text/html; charset=utf-8");

		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		// msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
			System.out.println("Sending...");

			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			
			System.out.println("Email sent!");
			
		} catch (Exception ex) {
			
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
			
		}
	}
	
	
}