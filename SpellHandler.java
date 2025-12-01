import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellHandler {
    
    public static void handleSpellInput(ConsoleInput input, ConsoleOutput output, String name) {
        handleSpellInput(input, output, name, false);
    }

    public static void handleSpellInput(ConsoleInput input, ConsoleOutput output, String name, boolean provideHint) {
        output.printHeader("Healing Spell");
        output.printBlankLine();
        output.printSuccess("All ingredients have been gathered! Return to the unicorn.");
        output.print("It is time to speak the healing spell. Choose the correct spell from the options below.");

        List<String> spells = new ArrayList<>();
        spells.add("Luminara Vitae"); // Correct spell
        spells.add("Ignis Draconis");
        spells.add("Aqua Regia");
        spells.add("Terra Firma");

        Collections.shuffle(spells);
        
        if (provideHint) {
            output.printWarning("Hint: The spell is related to light and life.");
        }
        
        output.print("Available Spells:");
        String[] spellArray = spells.toArray(new String[0]);
        output.printList(spellArray);
        
        int spellChoice = input.readMenuChoice("Enter the number of the spell you wish to cast: ", spells.size());

        if (spellChoice >= 1 && spellChoice <= spells.size()) {
            String chosenSpell = spells.get(spellChoice - 1);
            if (chosenSpell.equals("Luminara Vitae")) {
                output.printBlankLine();
                output.printSuccess("You cast " + chosenSpell + "! The unicorn's wounds begin to heal, and it stands tall once more.");
            } else {
                output.printBlankLine();
                output.printWarning("You cast " + chosenSpell + ", but nothing happens. The unicorn remains injured.");
                output.print("Perhaps you should have chosen differently.");
            }
        } else {
            output.printError("Invalid choice. The unicorn remains injured.");
        }
    }
}
