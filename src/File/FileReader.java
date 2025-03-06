package File;

import java.io.*;
import Utility.IO;

public class FileReader {

	public static void main(String[] args) {
		String nomeFile;
		
		// Esempio con la classe FileReader
		File file;
		String text = "";
	
		
		// Creazione file
		do {
			nomeFile = IO.getString("Nome (e percorso) del file: ");
			file = new File(nomeFile);

			if (file.exists()) {
				IO.warn("Il file esiste già. Cambia nome.");
			}

		} while (file.exists());
		
		
		//Scrittura nel file
        try(FileWriter fw = new FileWriter(file)){
            fw.write(IO.getString("inserisci del testo da scrivere nel file"));
        } catch (IOException e){
            IO.err("Errore durante la scrittura del file");
        }

		
        // Lettura file con la classe FileReader
        java.io.FileReader reader = null;
        
        try {
            reader = new java.io.FileReader(nomeFile);
            int userChar; 

            do {
                userChar = reader.read(); // read() restituisce un int
                if (userChar != -1) {
                    IO.pl("Il valore inserito è: " + userChar);
                    text += (char) userChar; // Conversione a char
                }
            } while (userChar != -1);

            IO.pl("Hai scritto: " + text);

        } catch (IOException e) {
            IO.err("Errore durante la lettura dell'input: " + e.getMessage());
        } finally {
            // Chiudere stream
            if (reader != null) {
                try {
                    reader.close();
                    IO.pl("Chiusura reader effettuata correttamente.");
                } catch (IOException e) {
                    IO.err("Errore durante la chiusura del reader: " + e.getMessage());
                }
            }
        }

	}

}
