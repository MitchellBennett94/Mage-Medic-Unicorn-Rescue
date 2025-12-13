import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        // Allow null scanner for GUI mode
        this.scanner = scanner;
    }

    public String readWord(String prompt) {
        if (scanner == null) {
            return "default";
        }
        System.out.print(prompt);
        String word = scanner.next();
        scanner.nextLine();
        return word;
    }

    public int readInt(String prompt) {
        if (scanner == null) {
            return 1; // Default choice for GUI
        }
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public int readIntInRange(String prompt, int min, int max) {
        if (scanner == null) {
            return min; // Return minimum value for GUI
        }
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    public int readMenuChoice(String prompt, int numberOfOptions) {
        return readIntInRange(prompt, 1, numberOfOptions);
    }

    public Scanner getScanner() {
        return scanner;
    }
}
