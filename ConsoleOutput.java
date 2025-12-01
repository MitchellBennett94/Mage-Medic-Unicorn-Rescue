/**
 * Centralized output handler for console interactions.
 * Provides consistent formatting and themed output methods.
 */
public class ConsoleOutput {
    
    private static final String SEPARATOR = "=".repeat(50);
    private static final String DASH_LINE = "-".repeat(50);

    /**
     * Prints a standard message.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints a blank line.
     */
    public void printBlankLine() {
        System.out.println();
    }

    /**
     * Prints a header with decorative separators.
     */
    public void printHeader(String title) {
        System.out.println("\n" + SEPARATOR);
        System.out.println(centerText(title, 50));
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a section title.
     */
    public void printSection(String title) {
        System.out.println("\n--- " + title + " ---");
    }

    /**
     * Prints a story/narrative paragraph.
     */
    public void printStory(String text) {
        System.out.println(text);
    }

    /**
     * Prints a combat/action message with emphasis.
     */
    public void printCombat(String message) {
        System.out.println(">>> " + message);
    }

    /**
     * Prints an error message.
     */
    public void printError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Prints a success message.
     */
    public void printSuccess(String message) {
        System.out.println("✓ " + message);
    }

    /**
     * Prints a warning message.
     */
    public void printWarning(String message) {
        System.out.println("! " + message);
    }

    /**
     * Prints a menu with numbered options.
     */
    public void printMenu(String title, String[] options) {
        printSection(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    /**
     * Prints character stats.
     */
    public void printStats(String name, int health) {
        System.out.println(name + " HP: " + health);
    }

    /**
     * Prints detailed character stats.
     */
    public void printDetailedStats(String name, int health, int attack, int defense, int gold) {
        printSection(name + "'s Stats");
        System.out.println("Health: " + health);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Gold: " + gold);
    }

    /**
     * Prints a separator line.
     */
    public void printSeparator() {
        System.out.println(DASH_LINE);
    }

    /**
     * Prints a dramatic boss encounter message.
     */
    public void printBossEncounter(String bossName) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("!!! BOSS BATTLE: " + bossName + " !!!");
        System.out.println(SEPARATOR);
    }

    /**
     * Centers text within a given width.
     */
    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    /**
     * Prints a list of items.
     */
    public void printList(String[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i]);
        }
    }

    /**
     * Prints an inventory item.
     */
    public void printInventoryItem(String item) {
        System.out.println("- " + item);
    }
}
