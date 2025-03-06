package File;

import Utility.IO;
import java.io.*;

public class ListTxt {

	public static void main(String[] args) {

		File dir = new File("src/directory").getAbsoluteFile();
		IO.pl("Analizzo il contenuto di: " + dir + "\n");

		File[] files = dir.listFiles();

		if (files == null) {
			IO.err("La directory non esiste o non è accessibile.");
			return;
		}

		int count = 0;

		// Conto quanti file .txt ci sono
		for (File f : files) {
			if (f != null && f.isFile() && f.getName().endsWith(".txt")) {
				count++;
			}
		}

		File[] txtFiles = new File[count];
		int i = 0;

		// Carichiamo i file .txt
		for (File f : files) {
			if (f != null && f.isFile() && f.getName().endsWith(".txt")) {
				txtFiles[i] = f;
				i++;
			}
		}

		// Stampiamo file .txtFiles
		if (txtFiles.length == 0)
			IO.pl("Non vi sono file .txt");

		else {
			IO.pl("La directory contiene " + txtFiles.length + " file di tipo .txt");
			for (int j = 0; j < txtFiles.length; j++) {
				IO.pt((j + 1) + ") " + txtFiles[j].getName());
			}
		}

		IO.sepL();

		// Leggere un file
		while (true) {
			IO.pl("Scegli un file da leggere dal suo numero, oppure 0 per uscire");
			int choice = IO.getInt();

			if (choice == 0) {
				IO.exit();
				break;
			}

			if (choice > 0 && choice <= txtFiles.length) {
				try {
					File file = txtFiles[choice - 1];
					BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
					String line;

					while ((line = reader.readLine()) != null) {
						IO.pl(line);
					}

					reader.close();

					IO.pl("Vuoi leggere un altro file? (1 = Sì, 0 = No)");
					int readAgain = IO.getInt();

					if (readAgain == 0) {
						IO.exit();
						break;
					}
				} catch (IOException e) {
					IO.err("Errore durante la lettura del file: " + e.getMessage());
				}
			} else {
				IO.pl("File non esistente. Scegli tra 1 e " + txtFiles.length + ", oppure 0 per uscire.");
			}
		}
	}
}
