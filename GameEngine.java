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
    
    // Method to create and trigger a random enemy encounter (with spell variants)
    private void triggerRandomBattle(ConsoleInput input, ConsoleOutput output, Character player) {
        int battleType = rand.nextInt(3); // 0: mana, 1: lightning, 2: super
        
        // 50% chance for spell battle, 50% for regular
        if (rand.nextBoolean()) {
            BattleSpells spellEnemy;
            switch (battleType) {
                case 0:
                    spellEnemy = new ManaGeneratorEnemy("Mana Mage", 40, 30, 10);
                    break;
                case 1:
                    spellEnemy = new LightningEnemy("Storm Elemental", 35, 20, 12);
                    break;
                default:
                    spellEnemy = new SuperDamageEnemy("Chaos Wizard", 50, 10, 8);
            }
            BattleSystem.battleWithSpells(input, output, player, spellEnemy);
            if (player.isAlive()) {
                RewardSystem.awardRewardsForSpell(player, spellEnemy, output);
            }
        } else {
            int enemyHealth = 30 + rand.nextInt(30);
            Enemy goblin = new Enemy("Goblin", enemyHealth, 10, 3);
            BattleSystem.battle(input, output, player, goblin);
            if (player.isAlive()) {
                RewardSystem.awardRewards(player, goblin, output);
            }
        }
    }
}
