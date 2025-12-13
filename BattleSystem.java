
import java.util.Random;
import java.util.Scanner;

public class BattleSystem {
    
    // Regular battle method (scanner-based for compatibility)
    public static void battle(Scanner in, Character player, Enemy enemy, ConsoleOutput output) {
        // If scanner is null (GUI mode), do auto-battle
        if (in == null) {
            autoBattle(player, enemy, output);
            return;
        }
        
        System.out.println("\n--- A Wild " + enemy.getName() + " Appears! ---");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\n" + player.getName() + " HP: " + player.getHealth());
            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.print("What do you do? (attack/defend) Type: a for attack, d for defend: ");
            String action = in.next().toLowerCase();

            if (action.equals("a")) {
                int playerDamage = Math.max(0, player.getAttack() - enemy.getDefense());
                enemy.takeDamage(playerDamage);
                System.out.println("You attack the " + enemy.getName() + " and deal " + playerDamage + " damage!");
            } else if (action.equals("d")) {
                System.out.println("You brace for the incoming attack, raising your guard.");
            } else {
                System.out.println("Invalid action. You lose your turn.");
            }

            if (!enemy.isAlive()) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                return;
            }

            System.out.println("The " + enemy.getName() + " prepares to strike...");
            int enemyDamage = Math.max(0, enemy.getAttack() - (action.equals("d") ? player.getDefense() * 2 : player.getDefense()));
            player.takeDamage(enemyDamage);
            System.out.println("The " + enemy.getName() + " strikes, dealing " + enemyDamage + " damage to you.");

            if (!player.isAlive()) {
                System.out.println("\n" + player.getName() + " has been defeated by the " + enemy.getName() + ".");
                return;
            }
        }
    }
    
    // Auto-battle for GUI mode
    private static void autoBattle(Character player, Enemy enemy, ConsoleOutput output) {
        output.print("\n⚔️  A Wild " + enemy.getName() + " Appears! ⚔️");
        output.print(player.getName() + " HP: " + player.getHealth() + " | " + enemy.getName() + " HP: " + enemy.getHealth());
        Random rand = new Random();
        
        while (player.isAlive() && enemy.isAlive()) {
            // Player attacks
            int playerDamage = Math.max(0, player.getAttack() - enemy.getDefense());
            int oldEnemyHealth = enemy.getHealth();
            enemy.takeDamage(playerDamage);
            output.print("⚔️  " + player.getName() + " attacks! Dealt " + playerDamage + " damage!");
            output.print("   " + enemy.getName() + ": " + oldEnemyHealth + " → " + enemy.getHealth() + " HP");
            
            if (!enemy.isAlive()) {
                output.print("\n✅ Victory! You defeated the " + enemy.getName() + "!");
                return;
            }
            
            // Enemy attacks
            int enemyDamage = Math.max(0, enemy.getAttack() - player.getDefense());
            int oldPlayerHealth = player.getHealth();
            player.takeDamage(enemyDamage);
            output.print("\n🗡️  " + enemy.getName() + " strikes back! Dealt " + enemyDamage + " damage!");
            output.print("   " + player.getName() + ": " + oldPlayerHealth + " → " + player.getHealth() + " HP");
            
            if (!player.isAlive()) {
                output.print("\n❌ " + player.getName() + " has been defeated!");
                return;
            }
        }
    }

    // Recursive boss battle using ConsoleInput/ConsoleOutput
    public static void bossBattle(ConsoleInput input, ConsoleOutput output, Character player, Enemy boss) {
        output.printBossEncounter(boss.getName());
        bossBattleRecursive(input, output, player, boss, 2);
    }

    private static void bossBattleRecursive(ConsoleInput input, ConsoleOutput output, Character player, Enemy boss, int potionsLeft) {
        // Base case: someone defeated
        if (!player.isAlive()) {
            output.printWarning(player.getName() + " has been defeated by the " + boss.getName() + ".");
            return;
        }
        if (!boss.isAlive()) {
            output.printSuccess(player.getName() + " has defeated the mighty " + boss.getName() + "!");
            return;
        }

        // Display status
        output.printBlankLine();
        output.printStats(player.getName() + " HP", player.getHealth());
        output.printStats(boss.getName() + " HP", boss.getHealth());
        output.print("Potions left: " + potionsLeft);

        String[] battleOptions = {
            "Attack (Normal Damage)",
            "Heavy Attack (High Damage, lower defense)",
            "Use Potion (Heal 20 HP)"
        };
        output.printMenu("Choose your action", battleOptions);
        int choice = input.readMenuChoice("Enter your choice (1, 2, or 3): ", 3);

        int defenseMultiplier = 1;
        switch (choice) {
            case 1:
                int damageToBoss = Math.max(0, player.getAttack() - boss.getDefense());
                boss.takeDamage(damageToBoss);
                output.printCombat("You attack the " + boss.getName() + " and deal " + damageToBoss + " damage!");
                break;
            case 2:
                damageToBoss = Math.max(0, (int)(player.getAttack() * 1.5) - boss.getDefense());
                boss.takeDamage(damageToBoss);
                defenseMultiplier = 0;
                output.printCombat("You unleash a heavy blow! Deal " + damageToBoss + " damage. Your guard is down!");
                break;
            case 3:
                if (potionsLeft > 0) {
                    player.heal(20);
                    potionsLeft--;
                    output.printSuccess("You drink a potion and heal 20 HP. " + player.getName() + "'s HP is now " + player.getHealth() + ".");
                } else {
                    output.printWarning("You are out of potions!");
                }
                break;
            default:
                output.printWarning("Invalid action. You hesitate.");
                break;
        }

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

        if (!player.isAlive()) {
            output.printWarning(player.getName() + " has been defeated by the " + boss.getName() + ".");
            return;
        }

        // Recursive call for next round
        bossBattleRecursive(input, output, player, boss, potionsLeft);
    }
}
