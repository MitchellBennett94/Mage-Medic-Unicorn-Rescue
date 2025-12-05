import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Main method to run the game

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            ConsoleInput input = new ConsoleInput(in);
            ConsoleOutput output = new ConsoleOutput();

            // --- Collect basic character info ---
                output.printSection("Character Creation");
                String name = input.readWord("Enter your character name:");
                String pronounSubject = input.readWord("Choose pronouns (he/she/they):");
                int age = input.readIntInRange("Enter your age (1-120):", 1, 120);
            // Validate pronouns
            String proSubj;
            while (true) {
                proSubj = input.readWord("Choose a pronoun subject (he/she/they): ").toLowerCase();
                if (proSubj.equals("he") || proSubj.equals("she") || proSubj.equals("they")) break;
                output.printWarning("Please enter one of: he, she, they.");
            }
            String proObj;
            while (true) {
                proObj = input.readWord("Choose a pronoun object (him/her/them): ").toLowerCase();
                if (proObj.equals("him") || proObj.equals("her") || proObj.equals("them")) break;
                output.printWarning("Please enter one of: him, her, them.");
            }
            String proPossAdj;
            while (true) {
                proPossAdj = input.readWord("Choose a possessive adjective (his/her/their): ").toLowerCase();
                if (proPossAdj.equals("his") || proPossAdj.equals("her") || proPossAdj.equals("their")) break;
                output.printWarning("Please enter one of: his, her, their.");
            }
            // Age captured above with validated range
            
            // Create player character
            Character player = new Character(name, proSubj, proObj, proPossAdj, age, 100, 15, 5, 12);
            
                output.printSection("Profile");
                output.print("Name: " + name);
                output.print("Age: " + age);
            // --- Prologue paragraph (≥ 5 sentences using ≥ 5 variables) ---
            output.printBlankLine();
            output.printHeader("Adventure Prologue");
            output.printStory(player.getName() + " set out at dawn, " + player.getPossessiveAdjective() + " pack light");
            output.printStory("At only " + player.getAge() + " years old, " + player.getPronounSubject() + " already carries stories that most would never dare to tell.");
            output.printStory("In the pouch at " + player.getPossessiveAdjective() + " side clinked " + player.getGold() + " gold coins— not much, but enough for bread and a bed in a quiet inn.");
            output.printStory("A weathered sign pointed toward the Whispering Woods, and " + player.getPronounSubject() + " felt a shiver that had nothing to do with the cold.");
            output.printStory("Whatever waited beyond the treeline would test " + player.getPronounObject() + ", but " + player.getName() + " walked on without looking back.");
            output.printBlankLine();

            // Create inventory and locations
            String[] requiredIngredients = {"Moonpetal", "Dwarfstone", "Elvishberry"};
            Inventory inventory = new Inventory(requiredIngredients);
            
            String[] locations = {"Ancient Moongrove", "Dwarven Mines", "Volcanic Ruins", "Keebler Forest"};
            String[] ingredientLocations = {"Ancient Moongrove", "Dwarven Mines", "Keebler Forest"};

            // Shuffle locations to add randomness
            List<String> locationList = new ArrayList<>(Arrays.asList(locations));
            Collections.shuffle(locationList);
            locations = locationList.toArray(new String[0]);
            
            // Create game engine and inn
            GameEngine gameEngine = new GameEngine();
            Inn inn = new Inn();
            
            // --- Game Start ---
            output.printHeader("Quest Begins");
            output.printStory("After walking many hours into the heart of the Woods you stumble upon a wounded unicorn.");
            output.printStory("The unicorn, laying badly-injured on the ground, looks up for your help.");
            output.printStory("In order to heal the Unicorn, " + player.getName() + " must find three magical ingredients for the rescue: Moonpetal, Dwarfstone, and Elvishberry.");
            output.printSuccess("Your quest begins!");

            // Main game loop
            while (true) {
                if (inventory.hasAllIngredients()) {
                    output.printSuccess("You have gathered all the ingredients! It's time to return to the unicorn.");
                    SpellHandler.handleSpellInput(input, output, player.getName());
                    break;
                }

                String[] menuOptions = {
                    "Explore a new location",
                    "Check inventory (ingredients)",
                    "View visited locations",
                    "Check player health",
                    "Visit the Inn (rest and heal)",
                    "Check gold",
                    "Exit game"
                };
                output.printMenu("Choose Your Next Move", menuOptions);
                int choice = input.readMenuChoice("Enter your choice: ", 7);

                switch (choice) {
                    case 1:
                        output.printSection("Exploration");
                        output.print("Which location will you visit?");
                        output.printList(locations);
                        int locationChoice = input.readMenuChoice("Enter the number of the location: ", locations.length);

                        if (locationChoice >= 1 && locationChoice <= locations.length) {
                            String chosenLocation = locations[locationChoice - 1];
                            gameEngine.exploreLocation(input, output, player, chosenLocation, inventory, 
                                                      ingredientLocations, requiredIngredients);
                            if (!player.isAlive()) {
                                output.printError("Game Over! You have fallen in battle.");
                                return;
                            }
                        } else {
                            System.out.println("Invalid location choice.");
                        }
                        break;
                    case 2:
                        inventory.printInventory(output);
                        break;
                    case 3:
                        inventory.printVisitedLocations(output);
                        break;
                    case 4:
                        output.printBlankLine();
                        output.printStats(player.getName() + "'s Current Health", player.getHealth());
                        break;
                    case 5:
                        inn.rest(player, input, output);
                        break;
                    case 6:
                        output.printBlankLine();
                        output.print("Gold in pouch: " + player.getGold() + " coins");
                        break;
                    case 7:
                        output.printSuccess("Thank you for playing! Goodbye.");
                        return; // Exit the main method
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                        break;
                }
            }
            // If all ingredients are found, provide a hint for the spell
            SpellHandler.handleSpellInput(input, output, player.getName(), true);

            // Final Boss Battle with Evil Sorcerer
            BossBattle.fightEvilSorcerer(input, output, player);
            
            // Game conclusion
            output.printBlankLine();
            output.printHeader("Quest Complete!");
            output.printStory("With the Evil Sorcerer defeated and the unicorn healed, peace returns to the Whispering Woods.");
            output.printStory("Your name will be remembered by all who know of your heroic deeds.");
            output.printSuccess("Thank you for playing Mage, Medic, Unicorn Rescue!");
        
        }
    }
}
