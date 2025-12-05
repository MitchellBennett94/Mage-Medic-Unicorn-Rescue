// Inn system for resting and healing
public class Inn {
    private int restCost = 10; // Gold cost to rest

    public void rest(Character player, ConsoleInput input, ConsoleOutput output) {
        if (player.getGold() < restCost) {
            output.printWarning("You don't have enough gold to rest at the inn. (Costs " + restCost + " gold)");
            return;
        }

        output.printSection("Welcome to the Inn");
        output.print("The innkeeper offers you a warm meal and a comfortable bed.");
        
        String[] restOptions = {
            "Rest here (" + restCost + " gold) - Fully heal",
            "Leave without resting"
        };
        output.printMenu("What will you do?", restOptions);
        
        int choice = input.readMenuChoice("Enter your choice: ", 2);
        
        if (choice == 1) {
            player.setHealth(player.getMaxHealth());
            player.addGold(-restCost);
            output.printSuccess("You rest well and are fully healed!");
            output.print("Gold remaining: " + player.getGold());
        } else {
            output.print("You decide to leave the inn.");
        }
    }
}
