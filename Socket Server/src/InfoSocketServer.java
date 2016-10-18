/**
 * Implementierung eines Socket Servers der auf eingehende Anfragen antwortet (Kopie aus Lehrbuch Netzwerk- und Datensicherheit)
 * 
 * @author cgm
 * @version 0.1
 *
 */
import java.net.*;
import java.io.*;

public class InfoSocketServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Versuchen das Socekt zu erzeugen
		try {
			// Erzeugen eines ServerSocket auf Port 80
			ServerSocket meinserversocket = new ServerSocket(80);
			// ServerSocket meinserversocket ist aktiv und
			// wartet auf Port 80 auf eingehende Verbindungen
			while (true) {
			try{
				// Blockierendes Warten auf eingehende Verbindungen
				Socket meinsocket = meinserversocket.accept();
				// Verbindung ist eingegangen und an den Socket meinsocket
				// gebunden
				System.out.println("Accepted connection from "
				+ meinsocket.getInetAddress() + ":" + meinsocket.getPort());
				// Datenstroeme des Socket an in und out binden
				OutputStream out = meinsocket.getOutputStream();
				InputStream in = meinsocket.getInputStream();
				// Auslesen und Ausgeben der verfuegbaren Daten
				// Es wird davon ausgegangen, dass der Client
				// dem Server tatsaechlich etwas sendet
				int b;
				boolean etwasgelesen=false;
				while((in.available()> 0) | (!etwasgelesen)){ 	
					etwasgelesen=true;
					b=in.read();
					System.out.print((char)b);
				}
				// Erzeugen und Abschicken einer Antwort an
				// den Kommunikationspartner
				// Erzeugung der Antwort
					String antwort = "Connection Information: \n";
					antwort += "Client connecting from " + meinsocket.getInetAddress()
					+ ":" + meinsocket.getPort() + "\n";
					antwort += "Server from " + meinsocket.getLocalAddress()
					+ ":" + meinsocket.getLocalPort() + "\n";
					antwort += meinsocket.toString() + "\n";
				// Abschicken
					out.write(antwort.getBytes());
				// Lokale Ausgabe der Antwort
					System.out.println("" + antwort);
				// Schliessen der Verbindung mit dem Kommunikationspartner
				meinsocket.close();
				
				}catch (Exception e){
					System.out.println("Problem bei eingehender Verbindungsanfrage: "+e);
				}
			meinserversocket.close();
				}
			}catch (Exception e) {
				System.out.println("Problem beim Erzeugen des ServerSocket: "+e);
			}
			
	}

}
