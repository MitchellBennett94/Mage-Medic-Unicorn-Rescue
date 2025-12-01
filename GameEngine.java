import java.util.Random;

public class GameEngine {
    private Random rand;
    
    public GameEngine() {
        this.rand = new Random();
    }
    
    // Method to handle location exploration with random battles
    public void exploreLocation(ConsoleInput input, ConsoleOutput output, Character player, String location, Inventory inventory, 
                                String[] ingredientLocations, String[] requiredIngredients) {
        inventory.addVisitedLocation(location);
        output.printHeader("Exploration: " + location);
        output.print("You travel to " + location + ".");
        
        // Check if there's an ingredient here
        boolean ingredientFound = false;
        for (int i = 0; i < ingredientLocations.length; i++) {
            if (location.equals(ingredientLocations[i]) && !inventory.hasIngredient(i)) {
                output.printSuccess("You find the " + requiredIngredients[i] + "!");
                inventory.addIngredient(i);
                ingredientFound = true;
                break;
            }
        }
        
        if (!ingredientFound) {
            output.print("You find nothing of value here.");
        }
        
        // Random chance for a battle (50% chance)
        if (shouldTriggerBattle()) {
            triggerRandomBattle(input, output, player);
        }
    }
    
    // Method to determine if a battle should occur
    private boolean shouldTriggerBattle() {
        return rand.nextDouble() < 0.5; // 50% chance
    }
    
    // Method to create and trigger a random enemy encounter
    private void triggerRandomBattle(ConsoleInput input, ConsoleOutput output, Character player) {
        int enemyHealth = 30 + rand.nextInt(30); // Random health from 30 to 60
        Enemy goblin = new Enemy("Goblin", enemyHealth, 10, 3);
        BattleSystem.battle(input, output, player, goblin);
    }
}
