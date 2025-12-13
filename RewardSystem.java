public class RewardSystem {
    public static void awardRewards(Character player, Enemy enemy, ConsoleOutput output) {
        int manaReward = 10 + (enemy.getHealth() / 5);
        player.addMana(manaReward);
        output.printSuccess("Victory! You absorbed " + manaReward + " mana!");
        output.print("💫 Total Mana: " + player.getMana() + "/" + player.getMaxMana());
    }

    public static void awardBossRewards(Character player, Enemy boss, ConsoleOutput output) {
        int manaReward = player.getMaxMana();
        player.addMana(manaReward);
        output.printSuccess("BOSS DEFEATED! You absorbed massive mana!");
    }
}
