// Reward system for battle victories
public class RewardSystem {
    
    // Award gold and experience after defeating an enemy
    public static void awardRewards(Character player, Enemy enemy, ConsoleOutput output) {
        int goldReward = 5 + (enemy.getHealth() / 10);
        player.addGold(goldReward);
        output.printSuccess("Victory! You earned " + goldReward + " gold coins!");
    }

    // Award rewards for spell-based battles
    public static void awardRewardsForSpell(Character player, BattleSpells enemy, ConsoleOutput output) {
        int goldReward = 7 + (enemy.getHealth() / 8);
        player.addGold(goldReward);
        output.printSuccess("Victory! You earned " + goldReward + " gold coins from the spell battle!");
    }

    // Award bonus for boss victory
    public static void awardBossRewards(Character player, Enemy boss, ConsoleOutput output) {
        int goldReward = 50;
        player.addGold(goldReward);
        output.printSuccess("BOSS DEFEATED! You earned " + goldReward + " gold coins!");
    }
}
