package Exercise1;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MyCode {

	private static final String FILE_NAME;
	private static final Scanner scanner;

	static {
		FILE_NAME = "registro.txt";
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {

		int choise = 0;
		do {
			menu();
			choise = scanner.nextInt();
			scanner.nextLine();

			switch (choise) {
			case 1:
				add();
				break;
			case 2:
				list();
				break;
			case 3:
				find();
				break;
			case 4:
				del();
				break;
			case 5:
				System.out.println("Uscita dal programma.");
				break;
			default:
				System.out.println("Scelta non valida, riprova.");
			}

		} while (choise != 5);
	}

	public static void menu() {
		System.out.println("\n===== REGISTRO STUDENTI =====");
		System.out.println("\t1. Aggiungi studente");
		System.out.println("\t2. Visualizza studenti");
		System.out.println("\t3. Cerca studente per nome");
		System.out.println("\t4. Cancella il registro");
		System.out.println("\t5. Esci");
		System.out.println("\n");
	}

	public static void add() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

			System.out.print("Inserisci nome: ");
			String name = scanner.nextLine();

			System.out.print("Inserisci cognome: ");
			String lastname = scanner.nextLine();

			System.out.print("Inserisci matricola: ");
			String number = scanner.nextLine();

			System.out.print("Inserisci età: ");

			int age = scanner.nextInt();
			scanner.nextLine();

			String student = name + "|" + lastname + "|" + number + "|" + age;
			writer.write(student);
			writer.newLine();

		} catch (IOException e) {
			System.out.println("Errore nella scrittura: " + e.getMessage());
		}
	}

	public static void list() {
		if (!exists()) return;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	        String line = reader.readLine();
	        
	        if (line == null) {
	            System.out.println("Il file " + FILE_NAME + " è vuoto!");
	            return;
	        }
	        
	        System.out.println("\nElenco studenti:");
	        while (line != null) {
	            String[] studentData = line.split("\\|");
	            System.out.println("\t" + studentData[1] + " " + studentData[0] + 
	                               ", età: " + studentData[3] + 
	                               ", matricola: " + studentData[2]);
	            line = reader.readLine();
			}

		} catch (IOException e) {
			System.out.println("Errore nella lettura: " + e.getMessage());
		}
	}

	public static void find() {

	}

	public static void del() {
		File file;

		try {
			file = new File(FILE_NAME);

			if (file.exists()) {
				file.delete();
				System.out.println("Registro cancellato");
			} else {
				throw new IOException("Errore nella cancellazione");
			}

		} catch (IOException e) {
			System.out.println("Registro cancellato");
		} catch (RuntimeException e) {
			System.out.println("Eccezione :" + e.getClass().getName());
		} catch (Exception e) {
			System.out.println("Eccezione :" + e.getClass().getName());
		}

	}

	private static boolean exists() {
		File file = new File(FILE_NAME).getAbsoluteFile();
		if (file.exists() && file.isFile()) return true;
		else {
			System.out.println("\nIl file " + FILE_NAME + " non esiste");
			System.out.println("Aggiungi uno studente per creare il file\n");
			return false;
		}
	}
}
