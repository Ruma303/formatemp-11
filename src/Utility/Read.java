package Utility;

import java.util.Scanner;

public class Read {

    // Scanner statico per evitare chiusure premature di System.in
    private static final Scanner scanner = new Scanner(System.in);

    public static String readStr() {
        try {
            return scanner.nextLine(); // Legge una linea intera
        } catch (Exception e) {
            System.err.println("Errore durante la lettura della stringa.");
            e.printStackTrace();
            return ""; // Valore di fallback in caso di errore
        }
    }

    public static Integer readInt() {
        try {
            while (!scanner.hasNextInt()) {
                System.out.println("Errore: Inserisci un numero intero valido.");
                scanner.next(); // Consuma l'input errato
            }
            return scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Errore durante la lettura di un numero intero.");
            e.printStackTrace();
            return null; // Valore di fallback
        } finally {
            scanner.nextLine(); // Consumare il newline rimasto nel buffer
        }
    }

    public static Double readDec() {
        try {
            while (!scanner.hasNextDouble()) {
                System.out.println("Errore: Inserisci un numero decimale valido.");
                scanner.next(); // Consuma l'input errato
            }
            return scanner.nextDouble();
        } catch (Exception e) {
            System.err.println("Errore durante la lettura di un numero decimale.");
            e.printStackTrace();
            return null; // Valore di fallback
        } finally {
            scanner.nextLine(); // Consumare il newline rimasto nel buffer
        }
    }

    public static Boolean readBool() {
        try {
            while (!scanner.hasNextBoolean()) {
                System.out.println("Errore: Inserisci 'true' o 'false'.");
                scanner.next(); // Consuma l'input errato
            }
            return scanner.nextBoolean();
        } catch (Exception e) {
            System.err.println("Errore durante la lettura di un valore booleano.");
            e.printStackTrace();
            return null; // Valore di fallback
        } finally {
            scanner.nextLine(); // Consumare il newline rimasto nel buffer
        }
    }

    public static Character readChar() {
        try {
            String input = scanner.next();
            return input.charAt(0);
        } catch (Exception e) {
            System.err.println("Errore durante la lettura di un carattere.");
            e.printStackTrace();
            return null; // Valore di fallback
        }
    }

    // Metodo per chiudere lo scanner manualmente alla fine del programma
    public static void closeScanner() {
        try {
        	IO.pl("Chiusura dello scanner. Bye!");
            scanner.close();
        } catch (Exception e) {
            System.err.println("Errore durante la chiusura dello scanner.");
            e.printStackTrace();
        }
    }
}