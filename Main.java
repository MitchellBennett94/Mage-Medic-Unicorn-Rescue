import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Main method to run the game

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            ConsoleInput input = new ConsoleInput(in);
            ConsoleOutput output = new ConsoleOutput();

            // --- Collect basic character info ---
            System.out.print("Enter your character's name: ");
            String name = in.nextLine();
            System.out.print("Choose a pronoun subject (he/she/they): ");
            String proSubj = in.next().toLowerCase();
            System.out.print("Choose a pronoun object (him/her/them): ");
            String proObj = in.next().toLowerCase();
            System.out.print("Choose a possessive adjective (his/her/their): ");
            String proPossAdj = in.next().toLowerCase();
            System.out.print("Enter your character's age: ");
            int age = in.nextInt();
            
            // Create player character
            Character player = new Character(name, proSubj, proObj, proPossAdj, age, 100, 15, 5, 12);
            
            // --- Prologue paragraph (≥ 5 sentences using ≥ 5 variables) ---
            System.out.println();
            System.out.println("~ ~ ~ Adventure Prologue ~ ~ ~");
            System.out.println(player.getName() + " set out at dawn, " + player.getPossessiveAdjective() + " pack light");
            System.out.println("At only " + player.getAge() + " years old, " + player.getPronounSubject() + " already carries stories that most would never dare to tell.");
            System.out.println("With " + player.getMana() + " mana stored within, " + player.getPronounSubject() +
            " feels the magical energy pulsing through " + player.getPossessiveAdjective() + " veins.");
            System.out.println("A weathered sign pointed toward the Whispering Woods, and " + player.getPronounSubject()
            + " felt a shiver that had nothing to do with the cold.");
            System.out.println("Whatever waited beyond the treeline would test " +
            player.getPronounObject() + ", but " + player.getName()
            + " walked on without looking back.");
            System.out.println();

            // Create inventory and locations
            String[] requiredIngredients = {"Moonpetal", "Dwarfstone", "Elvishberry"};
            Inventory inventory = new Inventory(requiredIngredients);
            
            String[] locations = {"Ancient Moongrove", "Dwarven Mines", "Volcanic Ruins", "Keebler Forest"};
            String[] ingredientLocations = {"Ancient Moongrove", "Dwarven Mines", "Keebler Forest"};

            // Shuffle locations to add randomness
            List<String> locationList = new ArrayList<>(Arrays.asList(locations));
            Collections.shuffle(locationList);
            locations = locationList.toArray(new String[0]);
            
            // Create game engine
            GameEngine gameEngine = new GameEngine();
            
            // --- Game Start ---
            System.out.println("After walking many hours into the heart of the Woods you stumble upon a wounded unicorn.");
            System.out.println("The unicorn, laying badly-injured on the ground, looks up for your help.");
            System.out.println("In order to heal the Unicorn, " + player.getName() + " must find three magical ingredients: Moonpetal, Dwarfstone, and Elvishberry.");
            System.out.println("Your quest begins!");

            // Main game loop
            while (true) {
                if (inventory.hasAllIngredients()) {
                    System.out.println("You have gathered all the ingredients! It's time to return to the unicorn.");
                    SpellHandler.handleSpellInput(input, output, player.getName(), false);
                    break;
                }

                System.out.println("\n--- Choose Your Next Move ---");
                System.out.println("1. Explore a new location");
                System.out.println("2. Check inventory (ingredients)");
                System.out.println("3. View visited locations");
                System.out.println("4. Check player health");
                System.out.println("5. Exit game");
                System.out.print("Enter your choice: ");

                String choiceStr = in.next();
                int choice;
                try {// Pass choice input
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("\n--- Exploration ---");
                        // Display available locations
                        System.out.println("Which location will you visit?");
                        for (int i = 0; i < locations.length; i++) {
                            System.out.println((i + 1) + ". " + locations[i]);
                        }
                        System.out.print("Enter the number of the location: ");
                        int locationChoice = in.nextInt();
                        in.nextLine();

                        if (locationChoice >= 1 && locationChoice <= locations.length) {
                            String chosenLocation = locations[locationChoice - 1];
                            gameEngine.exploreLocation(input, output, player, chosenLocation, inventory, 
                                                      ingredientLocations, requiredIngredients);
                            if (!player.isAlive()) {
                                System.out.println("Game Over!");
                                return; // Exit the game if player dies
                            }
                        } else {
                            System.out.println("Invalid location choice.");
                        }
                        break;
                    case 2:
                        inventory.printInventory();
                        break;
                    case 3:
                        inventory.printVisitedLocations();
                        break;
                    case 4:
                        System.out.println("\n" + player.getName() + "'s Current Health: " + player.getHealth());
                        break;
                    case 5:
                        System.out.println("Thank you for playing! Goodbye.");
                        return; // Exit the game
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                        break;
                }
            }

            // Final Boss Battle
            System.out.println("\nAs you finish healing the unicorn, a dark presence looms nearby.");
            System.out.println("The Evil Sorcerer, the one who harmed the unicorn, emerges to challenge you!");
            Enemy evilSorcerer = new Enemy("Evil Sorcerer", 61, 15, 8);// Adjusted health for balance
            BattleSystem.bossBattle(input, output, player, evilSorcerer);

            // End game message
            System.out.println();
            if (player.isAlive()) {// Player won
                System.out.println("=".repeat(50));
                System.out.println("CONGRATULATIONS!");
                System.out.println("You have defeated the Evil Sorcerer and saved the unicorn!");
                System.out.println("=".repeat(50));
            } else {
                System.out.println("=".repeat(50));// Player lost
                System.out.println("GAME OVER");
                System.out.println("You were defeated by the Evil Sorcerer.");
                System.out.println("Try again? The unicorn still needs your help!");
                System.out.println("=".repeat(50));
            }
        
        }
    }
}