import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class GameGUI extends JFrame {
 // UI Components
 private JTextArea gameTextArea;
 private JPanel buttonPanel;
 private JPanel statusPanel;
 private JLabel healthLabel;
 private JLabel manaLabel;
 private JProgressBar healthBar;
 private JProgressBar manaBar;
 
 // Game state
 private Character player;
 private Inventory inventory;
 private GameEngine gameEngine;
 private String[] locations;
 private String[] ingredientLocations;
 private String[] requiredIngredients;
 private GameState currentState;
 private int battlesWon = 0; // Track progressive difficulty
 
 // Enum for game states
 private enum GameState {
 CHARACTER_CREATION,
 MAIN_MENU,
 EXPLORATION,
 COMBAT,
 BOSS_BATTLE,
 GAME_OVER,
 VICTORY
 }
 
 public GameGUI() {
 setTitle("Mage Medic: Unicorn Rescue");
 setSize(900, 700);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setLayout(new BorderLayout(10, 10));
 
 // Create components
 createGameTextArea();
 createStatusPanel();
 createButtonPanel();
 
 // Initialize game
 currentState = GameState.CHARACTER_CREATION;
 showCharacterCreation();
 
 setVisible(true);
 }
 
 private void createGameTextArea() {
 gameTextArea = new JTextArea();
 gameTextArea.setEditable(false);
 gameTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
 gameTextArea.setLineWrap(true);
 gameTextArea.setWrapStyleWord(true);
 gameTextArea.setBackground(new Color(30, 30, 40));
 gameTextArea.setForeground(new Color(220, 220, 220));
 gameTextArea.setMargin(new Insets(10, 10, 10, 10));
 
 JScrollPane scrollPane = new JScrollPane(gameTextArea);
 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 add(scrollPane, BorderLayout.CENTER);
 }
 
 private void createStatusPanel() {
 statusPanel = new JPanel();
 statusPanel.setLayout(new GridLayout(5, 1, 5, 5));
 statusPanel.setBackground(new Color(50, 50, 60));
 statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
 
 healthLabel = new JLabel("Health: 0/0");
 healthLabel.setForeground(Color.WHITE);
 healthLabel.setFont(new Font("Arial", Font.BOLD, 14));
 
 healthBar = new JProgressBar(0, 100);
 healthBar.setValue(100);
 healthBar.setStringPainted(true);
 healthBar.setForeground(new Color(50, 200, 50));
 
 manaLabel = new JLabel("Mana: 0/100");
 manaLabel.setForeground(new Color(100, 149, 237));
 manaLabel.setFont(new Font("Arial", Font.BOLD, 14));
 
 manaBar = new JProgressBar(0, 100);
 manaBar.setValue(0);
 manaBar.setStringPainted(true);
 manaBar.setForeground(new Color(100, 149, 237));
 
 statusPanel.add(healthLabel);
 statusPanel.add(healthBar);
 statusPanel.add(manaLabel);
 statusPanel.add(manaBar);
 
 add(statusPanel, BorderLayout.NORTH);
 }
 
 private void createButtonPanel() {
 buttonPanel = new JPanel();
 buttonPanel.setLayout(new GridLayout(0, 2, 10, 10));
 buttonPanel.setBackground(new Color(40, 40, 50));
 buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
 add(buttonPanel, BorderLayout.SOUTH);
 }
 
 private void appendText(String text) {
 gameTextArea.append(text + "\n");
 gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength());
 }
 
 private void clearText() {
 gameTextArea.setText("");
 }
 
 private void clearButtons() {
 buttonPanel.removeAll();
 buttonPanel.revalidate();
 buttonPanel.repaint();
 }
 
 private void updateStatus() {
 if (player != null) {
 healthLabel.setText("Health: " + player.getHealth() + "/" + player.getMaxHealth());
 manaLabel.setText("Mana: " + player.getMana() + "/" + player.getMaxMana());
 
 int healthPercent = (int) ((double) player.getHealth() / player.getMaxHealth() * 100);
 healthBar.setValue(healthPercent);
 
 int manaPercent = (int) ((double) player.getMana() / player.getMaxMana() * 100);
 manaBar.setValue(manaPercent);
 
 if (healthPercent > 60) {
 healthBar.setForeground(new Color(50, 200, 50));
 } else if (healthPercent > 30) {
 healthBar.setForeground(new Color(255, 165, 0));
 } else {
 healthBar.setForeground(new Color(255, 50, 50));
 }
 }
 }
 
 private void showCharacterCreation() {
 clearText();
 clearButtons();
 
 appendText("");
 appendText(" WELCOME TO MAGE MEDIC: UNICORN RESCUE! ");
 appendText("\n");
 appendText("Create Your Character\n");
 
 JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
 inputPanel.setBackground(new Color(40, 40, 50));
 
 JLabel nameLabel = new JLabel("Character Name:");
 nameLabel.setForeground(Color.WHITE);
 JTextField nameField = new JTextField();
 
 JLabel pronounLabel = new JLabel("Pronoun Subject (he/she/they):");
 pronounLabel.setForeground(Color.WHITE);
 JTextField pronounField = new JTextField();
 
 JLabel objLabel = new JLabel("Pronoun Object (him/her/them):");
 objLabel.setForeground(Color.WHITE);
 JTextField objField = new JTextField();
 
 JLabel possLabel = new JLabel("Possessive (his/her/their):");
 possLabel.setForeground(Color.WHITE);
 JTextField possField = new JTextField();
 
 JLabel ageLabel = new JLabel("Age:");
 ageLabel.setForeground(Color.WHITE);
 JTextField ageField = new JTextField();
 
 inputPanel.add(nameLabel);
 inputPanel.add(nameField);
 inputPanel.add(pronounLabel);
 inputPanel.add(pronounField);
 inputPanel.add(objLabel);
 inputPanel.add(objField);
 inputPanel.add(possLabel);
 inputPanel.add(possField);
 inputPanel.add(ageLabel);
 inputPanel.add(ageField);
 
 int result = JOptionPane.showConfirmDialog(this, inputPanel, 
 "Character Creation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
 
 if (result == JOptionPane.OK_OPTION) {
 try {
 String name = nameField.getText().trim();
 String proSubj = pronounField.getText().trim().toLowerCase();
 String proObj = objField.getText().trim().toLowerCase();
 String proPossAdj = possField.getText().trim().toLowerCase();
 int age = Integer.parseInt(ageField.getText().trim());
 
 if (name.isEmpty()) {
 name = "Hero";
 }
 
 player = new Character(name, proSubj, proObj, proPossAdj, age, 100, 15, 5, 12);
 
 // Initialize game components
 requiredIngredients = new String[]{"Moonpetal", "Dwarfstone", "Elvishberry"};
 inventory = new Inventory(requiredIngredients);
 
 locations = new String[]{"Ancient Moongrove", "Dwarven Mines", "Volcanic Ruins", "Keebler Forest"};
 ingredientLocations = new String[]{"Ancient Moongrove", "Dwarven Mines", "Keebler Forest"};
 
 List<String> locationList = new ArrayList<>(Arrays.asList(locations));
 Collections.shuffle(locationList);
 locations = locationList.toArray(new String[0]);
 
 gameEngine = new GameEngine();
 
 showPrologue();
 } catch (NumberFormatException e) {
 JOptionPane.showMessageDialog(this, "Invalid age! Using default values.");
 showCharacterCreation();
 }
 } else {
 System.exit(0);
 }
 }
 
 private void showPrologue() {
 clearText();
 updateStatus();
 
 appendText("\n~ ~ ~ Adventure Prologue ~ ~ ~\n");
 appendText(player.getName() + " set out at dawn, " + player.getPossessiveAdjective() + " pack light.");
 appendText("At only " + player.getAge() + " years old, " + player.getPronounSubject() + 
 " already carries stories that most would never dare to tell.");
 appendText("With " + player.getMana() + " mana stored within, " + player.getPronounSubject() +
 " feels the magical energy pulsing through " + player.getPossessiveAdjective() + " veins.");
 appendText("A weathered sign pointed toward the Whispering Woods, and " + player.getPronounSubject()
 + " felt a shiver that had nothing to do with the cold.");
 appendText("Whatever waited beyond the treeline would test " +
 player.getPronounObject() + ", but " + player.getName()
 + " walked on without looking back.\n");
 
 appendText("\nAfter walking many hours into the heart of the Woods you stumble upon a wounded unicorn.");
 appendText("The unicorn, laying badly-injured on the ground, looks up for your help.");
 appendText("In order to heal the Unicorn, " + player.getName() + 
 " must find three magical ingredients: Moonpetal, Dwarfstone, and Elvishberry.");
 appendText("Your quest begins!\n");
 
 clearButtons();
 JButton continueButton = createStyledButton("Continue Adventure", new Color(70, 130, 180));
 continueButton.addActionListener(e -> showMainMenu());
 buttonPanel.add(continueButton);
 buttonPanel.revalidate();
 buttonPanel.repaint();
 }
 
 private void showMainMenu() {
 currentState = GameState.MAIN_MENU;
 clearButtons();
 
 if (inventory.hasAllIngredients()) {
 appendText("\n");
 appendText(" You have all the ingredients! ");
 appendText(" Return to heal the unicorn! ");
 appendText("\n");
 
 JButton healButton = createStyledButton("Heal the Unicorn", new Color(50, 200, 50));
 healButton.addActionListener(e -> healUnicorn());
 buttonPanel.add(healButton);
 } else {
 appendText("\n=== What would you like to do? ===\n");
 
 // Show mana heal option if mana is available
 if (player.canUseMana()) {
 appendText(" You have " + player.getMana() + " mana available!");
 JButton manaHealButton = createStyledButton(" Mana Heal (Convert " + player.getMana() + " mana HP)", new Color(138, 43, 226));
 manaHealButton.addActionListener(e -> useManaHeal());
 buttonPanel.add(manaHealButton);
 }
 
 JButton exploreButton = createStyledButton(" Explore Location", new Color(70, 130, 180));
 exploreButton.addActionListener(e -> showLocationChoice());
 buttonPanel.add(exploreButton);
 
 JButton inventoryButton = createStyledButton(" Check Inventory", new Color(160, 82, 45));
 inventoryButton.addActionListener(e -> showInventory());
 buttonPanel.add(inventoryButton);
 
 JButton locationsButton = createStyledButton(" Visited Locations", new Color(106, 90, 205));
 locationsButton.addActionListener(e -> showVisitedLocations());
 buttonPanel.add(locationsButton);
 
 JButton statusButton = createStyledButton(" Player Status", new Color(220, 20, 60));
 statusButton.addActionListener(e -> showPlayerStatus());
 buttonPanel.add(statusButton);
 
 JButton exitButton = createStyledButton(" Exit Game", new Color(128, 128, 128));
 exitButton.addActionListener(e -> exitGame());
 buttonPanel.add(exitButton);
 }
 
 buttonPanel.revalidate();
 buttonPanel.repaint();
 }
 
 private void showLocationChoice() {
 clearButtons();
 appendText("\n=== Choose a Location to Explore ===\n");
 
 for (int i = 0; i < locations.length; i++) {
 String location = locations[i];
 boolean visited = inventory.hasVisited(location);
 String buttonText = location + (visited ? " (Visited)" : "");
 
 JButton locationButton = createStyledButton(buttonText,
 visited ? new Color(100, 100, 120) : new Color(70, 130, 180));
 locationButton.addActionListener(e -> exploreLocation(location));
 buttonPanel.add(locationButton);
 }
 
 JButton backButton = createStyledButton(" Back", new Color(128, 128, 128));
 backButton.addActionListener(e -> showMainMenu());
 buttonPanel.add(backButton);
 
 buttonPanel.revalidate();
 buttonPanel.repaint();
 }
 
 private void exploreLocation(String location) {
 appendText("\n");
 appendText(" Exploring: " + String.format("%-26s", location) + "");
 appendText("\n");
 
 clearButtons();
 inventory.addVisitedLocation(location);
 
 // Special: Volcanic Ruins triggers immediate boss battle
 if (location.equals("Volcanic Ruins")) {
 appendText("You enter the scorching Volcanic Ruins...");
 appendText("The air is thick with ash and the ground trembles.");
 appendText("\n SUDDENLY, A MASSIVE FLAME DEMI-GOD EMERGES! ");
 
 Timer timer = new Timer(2000, e -> triggerVolcanicBoss());
 timer.setRepeats(false);
 timer.start();
 return;
 }
 
 // Check for ingredient at this location
 appendText("You travel to " + location + "...");
 boolean ingredientFound = false;
 for (int i = 0; i < ingredientLocations.length; i++) {
 if (location.equals(ingredientLocations[i]) && !inventory.hasIngredient(i)) {
 appendText(" You find the " + requiredIngredients[i] + "!");
 inventory.addIngredient(i);
 ingredientFound = true;
 break;
 }
 }
 if (!ingredientFound) {
 appendText("You search the area but find nothing of value.");
 }
 
 // 50% chance of battle
 if (Math.random() < 0.5) {
 appendText("\nSuddenly, you sense danger...");
 Timer timer = new Timer(1000, e -> triggerLocationBattle(location));
 timer.setRepeats(false);
 timer.start();
 } else {
 appendText("The area is quiet and safe.\n");
 updateStatus();
 
 Timer timer = new Timer(2000, e -> showMainMenu());
 timer.setRepeats(false);
 timer.start();
 }
 }
 
 private void triggerVolcanicBoss() {
 // Create a powerful mid-boss for Volcanic Ruins
 Enemy volcanicBoss = new Enemy("Flame Guardian", 100, 22, 13);
 
 appendText("");
 appendText(" VOLCANIC BOSS BATTLE! ");
 appendText("");
 
 startCombat(volcanicBoss, false);
 }
 
 private void triggerLocationBattle(String location) {
 // Generate random enemy based on location
 String[] enemyTypes = {"Pesky Goblin", "Ash Claw", "Cursed Spirit", "Bridge Troll", "Flame Demon"};
 String enemyName = enemyTypes[(int)(Math.random() * enemyTypes.length)];
 
 // Progressive difficulty: enemies get stronger with each battle won
 int difficultyMultiplier = battlesWon * 3; // +3 to each stat per battle won
 
 int enemyHealth = 25 + (int)(Math.random() * 20) + difficultyMultiplier; // Scales up
 int enemyAttack = 8 + (int)(Math.random() * 5) + (difficultyMultiplier / 3); // Scales slower
 int enemyDefense = 2 + (int)(Math.random() * 3) + (difficultyMultiplier / 5); // Scales slowest
 
 if (battlesWon > 0) {
 appendText(" Enemies are getting stronger! (Battles won: " + battlesWon + ")");
 }
 
 Enemy enemy = new Enemy(enemyName, enemyHealth, enemyAttack, enemyDefense);
 
 appendText("\n A wild " + enemyName + " appears!");
 appendText("Prepare for battle!\n");
 
 startCombat(enemy, false);
 }
 
 private void showInventory() {
 appendText("\n");
 appendText(" INVENTORY ");
 appendText("");
 
 List<String> collected = inventory.getCollectedIngredients();
 if (collected.isEmpty()) {
 appendText("\n No ingredients collected yet.");
 } else {
 appendText("\n Collected Ingredients:");
 for (String ingredient : collected) {
 appendText(" " + ingredient);
 }
 }
 
 appendText("\n Still Needed:");
 for (String required : requiredIngredients) {
 if (!collected.contains(required)) {
 appendText(" " + required);
 }
 }
 appendText("");
 }
 
 private void showVisitedLocations() {
 appendText("\n");
 appendText(" VISITED LOCATIONS ");
 appendText("");
 
 List<String> visited = inventory.getVisitedLocations();
 if (visited.isEmpty()) {
 appendText("\n No locations visited yet.");
 } else {
 appendText("\n You have been to:");
 for (String location : visited) {
 appendText(" " + location);
 }
 }
 appendText("");
 }
 
 private void showPlayerStatus() {
 appendText("\n");
 appendText(" CHARACTER STATUS ");
 appendText("");
 appendText("\n Name: " + player.getName());
 appendText(" Age: " + player.getAge());
 appendText(" Health: " + player.getHealth() + "/" + player.getMaxHealth());
 appendText(" Mana: " + player.getMana() + "/" + player.getMaxMana() + 
 (player.canUseMana() ? " (Can heal " + player.getMana() + " HP!)" : ""));
 appendText(" Attack: " + player.getAttack());
 appendText(" Defense: " + player.getDefense());
 appendText(" Battles Won: " + battlesWon + " (Enemy difficulty increases!)");
 appendText("");
 }
 
 private void useManaHeal() {
 if (player.canUseMana()) {
 int oldHealth = player.getHealth();
 int manaUsed = player.getMana();
 player.useManaHeal();
 int healAmount = player.getHealth() - oldHealth;
 
 appendText("\n");
 appendText(" MANA HEALING ACTIVATED ");
 appendText("");
 appendText("\n You channel all your mana into healing energy!");
 appendText(" Converted " + manaUsed + " mana " + healAmount + " HP!");
 appendText(" Health: " + oldHealth + " " + player.getHealth());
 appendText(" Mana depleted: " + manaUsed + " 0\n");
 
 updateStatus();
 
 Timer timer = new Timer(2000, e -> showMainMenu());
 timer.setRepeats(false);
 timer.start();
 }
 }
 
 private void healUnicorn() {
 appendText("\n");
 appendText(" HEALING THE UNICORN... ");
 appendText("\n");
 appendText("You combine the magical ingredients and create a healing potion...");
 appendText("You must now speak the correct healing spell!\n");
 
 // Show spell choices
 showSpellChoice();
 }
 
 private void showSpellChoice() {
 clearButtons();
 
 // Create spell list
 List<String> spells = new ArrayList<>();
 spells.add("Luminara Vitae"); // Correct spell
 spells.add("Ignis Draconis");
 spells.add("Aqua Regia");
 spells.add("Terra Firma");
 Collections.shuffle(spells);
 
 appendText("");
 appendText(" CHOOSE THE HEALING SPELL ");
 appendText("");
 appendText("\nHint: The spell is related to light and life.\n");
 appendText("Available Spells:");
 
 // Create buttons for each spell
 for (String spell : spells) {
 JButton spellButton = createStyledButton(spell, new Color(138, 43, 226));
 spellButton.addActionListener(e -> castSpell(spell));
 buttonPanel.add(spellButton);
 }
 
 buttonPanel.revalidate();
 buttonPanel.repaint();
 }
 
 private void castSpell(String spell) {
 clearButtons();
 
 if (spell.equals("Luminara Vitae")) {
 appendText("\n You cast " + spell + "! ");
 appendText("The unicorn's wounds begin to heal, and it stands tall once more.");
 appendText("Golden light surrounds the unicorn as it regains its strength!");
 appendText("\nBut suddenly, a dark presence emerges...\n");
 
 Timer timer = new Timer(2000, e -> startBossBattle());
 timer.setRepeats(false);
 timer.start();
 } else {
 appendText("\n You cast " + spell + ", but nothing happens.");
 appendText("The unicorn remains injured. That wasn't the right spell!");
 appendText("\nTry again...\n");
 
 Timer timer = new Timer(2000, e -> showSpellChoice());
 timer.setRepeats(false);
 timer.start();
 }
 }
 
 private void startBossBattle() {
 appendText("\n");
 appendText(" FINAL BOSS BATTLE! ");
 appendText("\n");
 appendText("The Evil Sorcerer appears, dark magic swirling around him!");
 
 clearButtons();
 currentState = GameState.BOSS_BATTLE;
 
 Enemy boss = new Enemy("Evil Sorcerer", 77, 17, 9);
 startCombat(boss, true);
 }
 
 private void startCombat(Enemy enemy, boolean isBoss) {
 currentState = GameState.COMBAT;
 clearButtons();
 
 appendText("\n " + player.getName() + " vs " + enemy.getName() + " ");
 appendText("Your HP: " + player.getHealth() + " | Enemy HP: " + enemy.getHealth() + "\n");
 
 showCombatOptions(enemy, isBoss);
 }
 
 private void showCombatOptions(Enemy enemy, boolean isBoss) {
 clearButtons();
 
 JButton attackButton = createStyledButton(" Attack", new Color(220, 20, 60));
 attackButton.addActionListener(e -> performCombatAction(enemy, "attack", isBoss));
 buttonPanel.add(attackButton);
 
 JButton defendButton = createStyledButton(" Defend & Heal", new Color(70, 130, 180));
 defendButton.addActionListener(e -> performCombatAction(enemy, "defend", isBoss));
 buttonPanel.add(defendButton);
 
 JButton specialButton = createStyledButton(" Special Attack", new Color(148, 0, 211));
 specialButton.addActionListener(e -> performCombatAction(enemy, "special", isBoss));
 buttonPanel.add(specialButton);
 
 // Ultimate Attack - requires 80% mana (80 out of 100)
 if (player.getMana() >= 80) {
 JButton ultimateButton = createStyledButton(" ULTIMATE ATTACK (80 Mana)", new Color(255, 69, 0));
 ultimateButton.addActionListener(e -> performCombatAction(enemy, "ultimate", isBoss));
 buttonPanel.add(ultimateButton);
 }
 
 buttonPanel.revalidate();
 buttonPanel.repaint();
 }
 
 private void performCombatAction(Enemy enemy, String action, boolean isBoss) {
 clearButtons();
 boolean shieldActive = false;
 
 appendText("\n--- Combat Round ---");
 
 switch (action) {
 case "attack":
 int damage = player.getAttack() + (int)(Math.random() * 8);
 int oldEnemyHealth = enemy.getHealth();
 enemy.takeDamage(damage);
 appendText(" " + player.getName() + " attacks!");
 appendText(" Dealt " + damage + " damage!");
 appendText(" " + enemy.getName() + " HP: " + oldEnemyHealth + " " + enemy.getHealth());
 break;
 case "defend":
 int heal = (int)(Math.random() * 15) + 10;
 int oldPlayerHealth = player.getHealth();
 player.heal(heal);
 appendText(" " + player.getName() + " defends and heals!");
 appendText(" Healed " + heal + " HP!");
 appendText(" " + player.getName() + " HP: " + oldPlayerHealth + " " + player.getHealth());
 break;
 case "special":
 int specialDmg = player.getAttack() * 2 + (int)(Math.random() * 10);
 int oldEnemyHP = enemy.getHealth();
 enemy.takeDamage(specialDmg);
 appendText(" " + player.getName() + " unleashes a devastating special attack!");
 appendText(" Dealt " + specialDmg + " damage!");
 appendText(" " + enemy.getName() + " HP: " + oldEnemyHP + " " + enemy.getHealth());
 break;
 case "ultimate":
 if (player.getMana() >= 80) {
 int ultimateDamage = (int)(enemy.getHealth() * 0.99); // 99% of current HP
 int preUltimateHP = enemy.getHealth();
 enemy.takeDamage(ultimateDamage);
 player.addMana(-80); // Consume 80 mana
 
 appendText(" ULTIMATE ATTACK UNLEASHED! ");
 appendText(" " + player.getName() + " channels 80 mana into a devastating blast!");
 appendText(" Dealt " + ultimateDamage + " MASSIVE damage!");
 appendText(" " + enemy.getName() + " HP: " + preUltimateHP + " " + enemy.getHealth());
 appendText(" Mana consumed: 80 (Remaining: " + player.getMana() + ")");
 } else {
 appendText(" Not enough mana! Need 80, have " + player.getMana());
 }
 break;
 case "shield":
 appendText(" " + player.getName() + " casts a protective magic shield!");
 shieldActive = true;
 break;
 }
 
 updateStatus();
 
 if (enemy.isAlive()) {
 appendText("");
 int enemyDmg = enemy.getAttack() + (int)(Math.random() * 10);
 if (shieldActive) {
 enemyDmg /= 2;
 appendText(" Your shield reduces the damage!");
 }
 int oldHP = player.getHealth();
 player.takeDamage(enemyDmg);
 appendText(" " + enemy.getName() + " counter-attacks!");
 appendText(" Dealt " + enemyDmg + " damage!");
 appendText(" " + player.getName() + " HP: " + oldHP + " " + player.getHealth());
 updateStatus();
 }
 
 appendText("\n Current Status:");
 appendText(" Your HP: " + player.getHealth() + " | " + enemy.getName() + " HP: " + enemy.getHealth());
 
 if (!player.isAlive()) {
 gameOver();
 } else if (!enemy.isAlive()) {
 if (isBoss) {
 victory();
 } else {
 battlesWon++; // Increment for progressive difficulty
 appendText("\n Victory! You defeated the " + enemy.getName() + "!");
 appendText(" Battles Won: " + battlesWon);
 
 // Special reward for defeating Flame Guardian
 int manaGained;
 if (enemy.getName().equals("Flame Guardian")) {
 manaGained = player.getMaxMana() - player.getMana(); // Fill to max
 player.addMana(manaGained);
 appendText("\n VOLCANIC BOSS DEFEATED! ");
 appendText(" The Flame Guardian's essence fills you completely!");
 appendText(" MANA FULLY RESTORED: +" + manaGained + " mana!");
 } else {
 manaGained = (int)(Math.random() * 15) + 10;
 player.addMana(manaGained);
 appendText("You absorbed " + manaGained + " mana!");
 }
 
 appendText(" Total Mana: " + player.getMana() + "/" + player.getMaxMana() + "\n");
 updateStatus();
 
 Timer timer = new Timer(2000, e -> showMainMenu());
 timer.setRepeats(false);
 timer.start();
 }
 } else {
 Timer timer = new Timer(1500, e -> showCombatOptions(enemy, isBoss));
 timer.setRepeats(false);
 timer.start();
 }
 }
 
 private void victory() {
 currentState = GameState.VICTORY;
 clearButtons();
 
 appendText("\n");
 appendText(" CONGRATULATIONS! ");
 appendText(" You defeated the Evil Sorcerer! ");
 appendText(" The unicorn is saved! ");
 appendText("\n");
 
 JButton restartButton = createStyledButton(" Play Again", new Color(50, 200, 50));
 restartButton.addActionListener(e -> {
 dispose();
 new GameGUI();
 });
 buttonPanel.add(restartButton);
 
 JButton exitButton = createStyledButton(" Exit", new Color(128, 128, 128));
 exitButton.addActionListener(e -> System.exit(0));
 buttonPanel.add(exitButton);
 
 buttonPanel.revalidate();
 buttonPanel.repaint();
 }
 
 private void gameOver() {
 currentState = GameState.GAME_OVER;
 clearButtons();
 appendText("GAME OVER!");
 JButton restartButton = createStyledButton(" Try Again", new Color(220, 20, 60));
 restartButton.addActionListener(e -> {
 dispose();
 new GameGUI();
 });
 buttonPanel.add(restartButton);
 
 JButton exitButton = createStyledButton(" Exit", new Color(128, 128, 128));
 exitButton.addActionListener(e -> System.exit(0));
 buttonPanel.add(exitButton);
 
 buttonPanel.revalidate();
 buttonPanel.repaint();
 }
 
 private void exitGame() {
 int choice = JOptionPane.showConfirmDialog(this, 
 "Are you sure you want to exit?", 
 "Exit Game", 
 JOptionPane.YES_NO_OPTION);
 
 if (choice == JOptionPane.YES_OPTION) {
 System.exit(0);
 }
 }
 
 private JButton createStyledButton(String text, Color color) {
 JButton button = new JButton(text);
 button.setFont(new Font("Arial", Font.BOLD, 14));
 button.setBackground(color);
 button.setForeground(Color.WHITE);
 button.setFocusPainted(false);
 button.setBorderPainted(false);
 button.setOpaque(true);
 button.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
 button.addMouseListener(new MouseAdapter() {
 public void mouseEntered(MouseEvent e) {
 button.setBackground(color.brighter());
 }
 public void mouseExited(MouseEvent e) {
 button.setBackground(color);
 }
 });
 
 return button;
 }
 
 // Helper classes for GUI input/output
 private class GUIOutput extends ConsoleOutput {
 private GameGUI gui;
 
 public GUIOutput(GameGUI gui) {
 this.gui = gui;
 }
 
 public void println(String message) {
 gui.appendText(message);
 }
 
 public void print(String message) {
 gui.appendText(message);
 }
 }
 
 private class GUIInput extends ConsoleInput {
 private GameGUI gui;
 
 public GUIInput(GameGUI gui) {
 super(null);
 this.gui = gui;
 }
 
 public int readInt() {
 return (int)(Math.random() * 3) + 1; // Auto-choose for GUI
 }
 }
 
 public static void main(String[] args) {
 SwingUtilities.invokeLater(() -> {
 try {
 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
 } catch (Exception e) {
 e.printStackTrace();
 }
 new GameGUI();
 });
 }
}
