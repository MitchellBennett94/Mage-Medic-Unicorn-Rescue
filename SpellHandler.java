import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SpellHandler {
    
    public static void handleSpellInput(Scanner in, String name) {
        handleSpellInput(in, name, false);
    }

    public static void handleSpellInput(Scanner in, String name, boolean provideHint) {
        System.out.println("\nAll ingredients have been gathered! Return to the unicorn.");
        System.out.println("It is time to speak the healing spell. Choose the correct spell from the options below.");

        List<String> spells = new ArrayList<>();
        spells.add("Luminara Vitae"); // Correct spell
        spells.add("Ignis Draconis");
        spells.add("Aqua Regia");
        spells.add("Terra Firma");

        Collections.shuffle(spells);
        
        if (provideHint) {
            System.out.println("Hint: The spell is related to light and life.");
        }
        
        System.out.println("Available Spells:");
        for (int i = 0; i < spells.size(); i++) {
            System.out.println((i + 1) + ". " + spells.get(i));
        }
        
        System.out.print("Enter the number of the spell you wish to cast: ");
        int spellChoice = in.nextInt();
        in.nextLine();

        if (spellChoice >= 1 && spellChoice <= spells.size()) {
            String chosenSpell = spells.get(spellChoice - 1);
            if (chosenSpell.equals("Luminara Vitae")) {
                System.out.println("\nYou cast " + chosenSpell + "! The unicorn's wounds begin to heal, and it stands tall once more.");
            } else {
                System.out.println("\nYou cast " + chosenSpell + ", but nothing happens. The unicorn remains injured.");
                System.out.println("Perhaps you should have chosen differently.");
            }
        } else {
            System.out.println("Invalid choice. The unicorn remains injured.");
        }
    }
}
