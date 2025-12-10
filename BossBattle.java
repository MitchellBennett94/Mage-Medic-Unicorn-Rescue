// Boss battle orchestrator using recursive boss combat
public class BossBattle {
    public static void fightEvilSorcerer(ConsoleInput input, ConsoleOutput output, Character player) {
        output.printBlankLine();
        output.printStory("As you finish healing the unicorn, a dark presence looms nearby.");
        output.printStory("The Evil Sorcerer, the one who harmed the unicorn, emerges to challenge you!");
        output.printBlankLine();

        // Create boss as regular Enemy for compatibility with bossBattle
        Enemy evilSorcerer = new Enemy("Evil Sorcerer", 100, 15, 12);
        BattleSystem.bossBattle(input, output, player, evilSorcerer);
        if (player.isAlive()) {
            RewardSystem.awardBossRewards(player, null, output);
            output.printSuccess("The Evil Sorcerer has been defeated!");
            output.printStory("The land is safe once more. The unicorn stands proud, fully healed.");
            output.printStory("Your legend grows with each step you take.");
        } else {
            output.printError("You have fallen... The unicorn remains trapped in darkness.");
        }
    }
}
