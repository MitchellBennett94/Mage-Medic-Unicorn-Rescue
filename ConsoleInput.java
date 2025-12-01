import java.util.Scanner;

/**
 * Centralized input handler for console interactions.
 * Provides validated input methods with error handling.
 */
public class ConsoleInput {
    private Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }
        this.scanner = scanner;
    }

    /**
     * Reads a non-empty string from the user.
     */
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Reads a single word (no spaces) from the user.
     */
    public String readWord(String prompt) {
        System.out.print(prompt);
        String word = scanner.next();
        scanner.nextLine(); // Consume remaining newline
        return word;
    }

    /**
     * Reads an integer with validation and error handling.
     */
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return value;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Reads an integer within a specific range.
     */
    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    /**
     * Reads a single character input (like 'a' or 'd').
     */
    public char readChar(String prompt) {
        System.out.print(prompt);
        String input = scanner.next().toLowerCase();
        scanner.nextLine(); // Consume newline
        return input.charAt(0);
    }

    /**
     * Reads a choice from a menu (1-based indexing).
     */
    public int readMenuChoice(String prompt, int numberOfOptions) {
        return readIntInRange(prompt, 1, numberOfOptions);
    }

    /**
     * Gets the underlying Scanner (for compatibility with existing code).
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
