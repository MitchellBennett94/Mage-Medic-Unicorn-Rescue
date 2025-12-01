// import java.util.Random; // not used

public class BattleSystem {
    
    // Regular battle method
    public static void battle(ConsoleInput input, ConsoleOutput output, Character player, Enemy enemy) {
        output.printHeader("Battle vs. " + enemy.getName());
        output.printSection("A Wild " + enemy.getName() + " Appears!");

        while (player.isAlive() && enemy.isAlive()) {
            output.printBlankLine();
            output.printStats(player.getName(), player.getHealth());
            output.printStats(enemy.getName(), enemy.getHealth());
            
            char action = input.readChar("What do you do? (a = attack, d = defend): ");

            // Player's turn
            if (action == 'a') {
                int playerDamage = Math.max(0, player.getAttack() - enemy.getDefense());
                enemy.takeDamage(playerDamage);
                output.printCombat("You attack the " + enemy.getName() + " and deal " + playerDamage + " damage!");
            } else if (action == 'd') {
                output.print("You brace for the incoming attack, raising your guard.");
            } else {
                output.printWarning("Invalid action. You lose your turn.");
            }

            // Check if enemy is defeated
            if (!enemy.isAlive()) {
                output.printSuccess("You have defeated the " + enemy.getName() + "!");
                return;
            }

            // Enemy's turn
            output.print("The " + enemy.getName() + " prepares to strike...");
            int enemyDamage = Math.max(0, enemy.getAttack() - (action == 'd' ? player.getDefense() * 2 : player.getDefense()));
            player.takeDamage(enemyDamage);
            output.printCombat("The " + enemy.getName() + " strikes, dealing " + enemyDamage + " damage to you.");

            // Check if player is defeated
            if (!player.isAlive()) {
                output.printBlankLine();
                output.printError(player.getName() + " has been defeated by the " + enemy.getName() + ".");
                return;
            }
        }
    }

    // Boss battle method with potions
    public static void bossBattle(ConsoleInput input, ConsoleOutput output, Character player, Enemy boss) {
        output.printHeader("Boss Battle: " + boss.getName());
        output.printBossEncounter(boss.getName());
        int potionsAvailable = 2;

        while (player.isAlive() && boss.isAlive()) {
            output.printBlankLine();
            output.printStats(player.getName(), player.getHealth());
            output.printStats(boss.getName(), boss.getHealth());
            output.print("Potions left: " + potionsAvailable);
            
            String[] battleOptions = {
                "Attack (Normal Damage)",
                "Heavy Attack (High Damage, lower defense)",
                "Use Potion (Heal 20 HP)"
            };
            output.printMenu("Choose your action", battleOptions);
            
            int choice = input.readMenuChoice("Enter your choice (1, 2, or 3): ", 3);
            int defenseMultiplier = 1;

            // Player's turn using a switch statement
            switch (choice) {
                case 1: // Attack
                    int damageToBoss = Math.max(0, player.getAttack() - boss.getDefense());
                    boss.takeDamage(damageToBoss);
                    output.printCombat("You attack the " + boss.getName() + " and deal " + damageToBoss + " damage!");
                    break;
                case 2: // Heavy Attack
                    damageToBoss = Math.max(0, (int)(player.getAttack() * 1.5) - boss.getDefense());
                    boss.takeDamage(damageToBoss);
                    defenseMultiplier = 0;
                    output.printCombat("You unleash a heavy blow! Deal " + damageToBoss + " damage. Your guard is down!");
                    break;
                case 3: // Use Potion
                    if (potionsAvailable > 0) {
                        player.heal(20);
                        potionsAvailable--;
                        output.printSuccess("You drink a potion and heal 20 HP. " + player.getName() + "'s HP is now " + player.getHealth() + ".");
                    } else {
                        output.printWarning("You are out of potions!");
                    }
                    break;
            }

            // Check if boss is defeated
            if (!boss.isAlive()) {
                output.printBlankLine();
                output.printSuccess(player.getName() + " has defeated the mighty " + boss.getName() + "!");
                return;
            }

            // Boss's turn
            output.print("The " + boss.getName() + " retaliates...");
            int effectiveDefense = player.getDefense() * defenseMultiplier;
            int damageToPlayer = Math.max(0, boss.getAttack() - effectiveDefense);
            player.takeDamage(damageToPlayer);
            output.printCombat("The " + boss.getName() + " strikes, dealing " + damageToPlayer + " damage to you.");

            // Check if player is defeated
            if (!player.isAlive()) {
                output.printBlankLine();
                output.printError(player.getName() + " has been defeated by the " + boss.getName() + ".");
                return;
            }
        }
    }
}
