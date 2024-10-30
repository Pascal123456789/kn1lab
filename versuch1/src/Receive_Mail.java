import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

public class Receive_Mail {
    public static void main(String[] args) throws Exception {
        fetchMail();												// Mail abrufen und anzeigen
    }

    public static void fetchMail() {
        try {
            
            Properties properties = new Properties();									// Properties erstellen
            properties.put("mail.store.protocol", "pop3");					// Festlegen des Protokolls auf POP3
            properties.put("mail.pop3.host", "localhost");					// Mail-Server-Host festlegen
            properties.put("mail.pop3.folder", "/home/labrat/Maildir");		// Pfad für den Mail-Ordner
			
    
            Session session = Session.getDefaultInstance(properties);					// Session mit Properties erstellen
            Store store = session.getStore("pop3");							// Verbindung zu POP3 Servern aufbauen
            store.connect("localhost", "labrat", "kn1lab");			// Anmelden am Mail-Server mit Benutzername und Passwort

          
            Folder emailFolder = store.getFolder("INBOX");							// Posteingang aufrufen
            emailFolder.open(Folder.READ_ONLY);											// Posteingang im Nur-Lese-Modus öffnen

            
            Message[] messages = emailFolder.getMessages(); 									// Alle Nachrichten aus dem Posteingang abrufen
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];													// Einzelne Nachricht abrufen
                System.out.println("\nMessage Number: " + (i + 1)); 							// Nachrichtennummer
                System.out.println("From: " + InternetAddress.toString(message.getFrom())); 	// Absender
                System.out.println("Subject: " + message.getSubject()); 						// Betreff 
                System.out.println("Sent Date: " + message.getSentDate());						// Sendedatum
                System.out.println("Content: " + message.getContent().toString()); 				// Inhalt anzeigen
            }

            
            emailFolder.close(false);		// Ordner schließen, Änderungen nicht speichern
            store.close();							// Verbindung Mail-Server beenden
        } catch (Exception e) {
            e.printStackTrace();		// Error
        }
    }
}