public class RewardSystem {
    public static void awardRewards(Character player, Enemy enemy, ConsoleOutput output) {
        int goldReward = 5 + (enemy.getHealth() / 10);
        player.addGold(goldReward);
        output.printSuccess("Victory! You earned " + goldReward + " gold coins!");
    }

    public static void awardBossRewards(Character player, Enemy boss, ConsoleOutput output) {
        int goldReward = 50;
        player.addGold(goldReward);
        output.printSuccess("BOSS DEFEATED! You earned " + goldReward + " gold coins!");
    }
}
