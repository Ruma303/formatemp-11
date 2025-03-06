package File;

import Utility.IO;
import java.io.*;

public class CreateFile {
	public static void main(String[] args) {
		
		String nomeFile;
		File file;

		do {
			nomeFile = IO.getString("Nome (e percorso) del file: ");
			file = new File(nomeFile);

			if (file.exists()) {
				IO.warn("Il file esiste già. Cambia nome.");
			}

		} while (file.exists());

		try {
			FileWriter writer = new FileWriter(file);
			writer.write("Questo file è stato creato da Java!\n");
			writer.write("E sta funzionando alla grande!\n");
			
			String userText = IO.getString("Scrivi qualcosa nel file...\n");

			writer.append(userText);
			writer.close();
			
			System.out.println("Scrittura completata! Il file è stato creato in\n: " + file.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Errore nella scrittura: " + e.getMessage());
		}
	}
}
