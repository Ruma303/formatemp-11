package Exercise1;

import java.io.*;
import java.util.Scanner;

public class RegistroStudenti {

	private static final String FILE_NAME = "registro.txt";
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int scelta;
		do {
			System.out.println("\n===== REGISTRO STUDENTI =====");
			System.out.println("1. Aggiungi studente");
			System.out.println("2. Visualizza studenti");
			System.out.println("3. Cerca studente per nome");
			System.out.println("4. Cancella il registro");
			System.out.println("5. Esci");
			System.out.print("Scelta: ");
			scelta = scanner.nextInt();
			scanner.nextLine(); 

			switch (scelta) {
			case 1:
				aggiungiStudente();
				break;
			case 2:
				visualizzaStudenti();
				break;
			case 3:
				cercaStudente();
				break;
			case 4:
				cancellaRegistro();
				break;
			case 5:
				System.out.println("Uscita dal programma.");
				break;
			default:
				System.out.println("Scelta non valida, riprova.");
			}
		} while (scelta != 5);
	}

	private static void aggiungiStudente() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
			System.out.print("Inserisci nome: ");
			String nome = scanner.nextLine();
			System.out.print("Inserisci cognome: ");
			String cognome = scanner.nextLine();
			System.out.print("Inserisci matricola: ");
			String matricola = scanner.nextLine();
			System.out.print("Inserisci età: ");
			int eta = scanner.nextInt();
			scanner.nextLine(); // Consuma il newline

			String studente = nome + "," + cognome + "," + matricola + "," + eta;
			writer.write(studente);
			writer.newLine();
			System.out.println("Studente aggiunto con successo!");
		} catch (IOException e) {
			System.out.println("Errore nella scrittura: " + e.getMessage());
		}
	}

	private static void visualizzaStudenti() {

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String linea;
			System.out.println("\nElenco studenti:");
			while ((linea = reader.readLine()) != null) {
				String[] dati = linea.split(",");
				System.out.println(dati[0] + " " + dati[1] + " - Matricola: " + dati[2] + " - Età: " + dati[3]);
			}
		} catch (IOException e) {
			System.out.println("Errore nella lettura: " + e.getMessage());
		}
	}

	private static void cercaStudente() {
		System.out.print("Inserisci il nome da cercare: ");
		String nomeCercato = scanner.nextLine();
		boolean trovato = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] dati = linea.split(",");
				if (dati[0].equalsIgnoreCase(nomeCercato)) {
					System.out.println(dati[0] + " " + dati[1] + " - Matricola: " + dati[2] + " - Età: " + dati[3]);
					trovato = true;
				}
			}
		} catch (IOException e) {
			System.out.println("Errore nella lettura: " + e.getMessage());
		}

		if (!trovato) {
			System.out.println("Nessuno studente trovato con quel nome.");
		}
	}

	private static void cancellaRegistro() {
		File file = new File(FILE_NAME);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("Registro eliminato con successo.");
			} else {
				System.out.println("Errore nella cancellazione del registro.");
			}
		} else {
			System.out.println("Il registro è già vuoto.");
		}

	}

}
