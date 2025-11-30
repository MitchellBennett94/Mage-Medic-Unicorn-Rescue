import java.util.Random;
import java.util.Scanner;

public class BattleSystem {
    
    // Regular battle method
    public static void battle(Scanner in, Character player, Enemy enemy) {
        Random rand = new Random();
        System.out.println("\n--- A Wild " + enemy.getName() + " Appears! ---");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\n" + player.getName() + " HP: " + player.getHealth());
            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.print("What do you do? (attack/defend) Type: a for attack, d for defend: ");
            String action = in.next().toLowerCase();

            // Player's turn
            if (action.equals("a")) {
                int playerDamage = Math.max(0, player.getAttack() - enemy.getDefense());
                enemy.takeDamage(playerDamage);
                System.out.println("You attack the " + enemy.getName() + " and deal " + playerDamage + " damage!");
            } else if (action.equals("d")) {
                System.out.println("You brace for the incoming attack, raising your guard.");
            } else {
                System.out.println("Invalid action. You lose your turn.");
            }

            // Check if enemy is defeated
            if (!enemy.isAlive()) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                return;
            }

            // Enemy's turn
            System.out.println("The " + enemy.getName() + " prepares to strike...");
            int enemyDamage = Math.max(0, enemy.getAttack() - (action.equals("d") ? player.getDefense() * 2 : player.getDefense()));
            player.takeDamage(enemyDamage);
            System.out.println("The " + enemy.getName() + " strikes, dealing " + enemyDamage + " damage to you.");

            // Check if player is defeated
            if (!player.isAlive()) {
                System.out.println("\n" + player.getName() + " has been defeated by the " + enemy.getName() + ".");
                return;
            }
        }
    }

    // Boss battle method with potions
    public static void bossBattle(Scanner in, Character player, Enemy boss) {
        System.out.println("\n!!! BOSS BATTLE: " + boss.getName() + " Appears !!!");
        Random rand = new Random();
        int potionsAvailable = 2;

        while (player.isAlive() && boss.isAlive()) {
            System.out.println("\n" + player.getName() + " HP: " + player.getHealth());
            System.out.println(boss.getName() + " HP: " + boss.getHealth());
            System.out.println("Potions left: " + potionsAvailable);
            System.out.println("Choose your action:");
            System.out.println("1. Attack (Normal Damage)");
            System.out.println("2. Heavy Attack (High Damage, lower defense)");
            System.out.println("3. Use Potion (Heal 20 HP)");
            System.out.print("Enter your choice (1, 2, or 3): ");

            int choice = 0;
            if (in.hasNextInt()) {
                choice = in.nextInt();
                in.nextLine();
            } else {
                System.out.println("Invalid input. You lose your turn.");
                in.next();
                continue;
            }

            int damageToBoss = 0;
            int defenseMultiplier = 1;

            // Player's turn using a switch statement
            switch (choice) {
                case 1: // Attack
                    damageToBoss = Math.max(0, player.getAttack() - boss.getDefense());
                    boss.takeDamage(damageToBoss);
                    System.out.println("You attack the " + boss.getName() + " and deal " + damageToBoss + " damage!");
                    break;
                case 2: // Heavy Attack
                    damageToBoss = Math.max(0, (int)(player.getAttack() * 1.5) - boss.getDefense());
                    boss.takeDamage(damageToBoss);
                    defenseMultiplier = 0;
                    System.out.println("You unleash a heavy blow! Deal " + damageToBoss + " damage. Your guard is down!");
                    break;
                case 3: // Use Potion
                    if (potionsAvailable > 0) {
                        player.heal(20);
                        potionsAvailable--;
                        System.out.println("You drink a potion and heal 20 HP. " + player.getName() + "'s HP is now " + player.getHealth() + ".");
                    } else {
                        System.out.println("You are out of potions!");
                    }
                    break;
                default:
                    System.out.println("Invalid action. You hesitate.");
                    break;
            }

            // Check if boss is defeated
            if (!boss.isAlive()) {
                System.out.println("\n" + player.getName() + " has defeated the mighty " + boss.getName() + "!");
                return;
            }

            // Boss's turn
            System.out.println("The " + boss.getName() + " retaliates...");
            int effectiveDefense = player.getDefense() * defenseMultiplier;
            int damageToPlayer = Math.max(0, boss.getAttack() - effectiveDefense);
            player.takeDamage(damageToPlayer);
            System.out.println("The " + boss.getName() + " strikes, dealing " + damageToPlayer + " damage to you.");

            // Check if player is defeated
            if (!player.isAlive()) {
                System.out.println("\n" + player.getName() + " has been defeated by the " + boss.getName() + ".");
                return;
            }
        }
    }
}
