public class ConsoleOutput {
    private static final String SEPARATOR = "=".repeat(50);

    public void print(String message) {
        System.out.println(message);
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printHeader(String title) {
        System.out.println("\n" + SEPARATOR);
        System.out.println(centerText(title, 50));
        System.out.println(SEPARATOR);
    }

    public void printSection(String title) {
        System.out.println("\n--- " + title + " ---");
    }

    public void printStory(String text) {
        System.out.println(text);
    }

    public void printCombat(String message) {
        System.out.println(">>> " + message);
    }

    public void printError(String message) {
        System.out.println("ERROR: " + message);
    }

    public void printSuccess(String message) {
        System.out.println("\u2713 " + message);
    }

    public void printWarning(String message) {
        System.out.println("! " + message);
    }

    public void printMenu(String title, String[] options) {
        printSection(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public void printStats(String name, int health) {
        System.out.println(name + " HP: " + health);
    }

    public void printBossEncounter(String bossName) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("!!! BOSS BATTLE: " + bossName + " !!!");
        System.out.println(SEPARATOR);
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    public void printList(String[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i]);
        }
    }
}
