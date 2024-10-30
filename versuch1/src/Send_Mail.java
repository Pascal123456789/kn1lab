import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 

public class Send_Mail {

	public static void main(String[] args) {			
		sendMail();   										// Versende die E-Mail
	}
	


	public static void sendMail() {

		
		String to = "labrat@localhost"; 		// Name Ziel
        String from = "sender@localhost";		// Name Absender
        String host = "localhost";				// Name Host des SMTP-Servers

		Properties properties = System.getProperties(); 		// System-Eigenschaften für SMTP-Konfiguration abrufen
        properties.setProperty("mail.smtp.host", host); 	// SMTP-Servereigenschaft festlegen

		// Erstellen einer Sitzung mit den SMTP-Eigenschaften
        Session session = Session.getDefaultInstance(properties);


		try {
			
			MimeMessage message = new MimeMessage(session); 			// Erstellen der Nachricht
            message.setFrom(new InternetAddress(from)); 				// Absender
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 		// Empfänger
            message.setSubject("Hello World"); 					// Betreff
            message.setText("Hello World"); 						// Nachricht
            Transport.send(message); 									// senden


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
