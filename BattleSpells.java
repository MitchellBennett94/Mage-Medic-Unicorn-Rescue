import java.util.Random;

// Abstract base class for all enemies with spell-based attacks
public abstract class BattleSpells {
    protected String name;
    protected int health;
    protected int mana;
    protected int baseDamage;

    public BattleSpells(String name, int health, int mana, int baseDamage) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Enemy name cannot be null or empty.");
        }
        if (health <= 0) {
            throw new IllegalArgumentException("Health must be greater than 0.");
        }
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.baseDamage = baseDamage;
    }

    // Abstract method for polymorphic attacks
    public abstract void performAttack(BattleSpells target, ConsoleOutput output);

    // Common damage method
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, mana);
    }

    public boolean isAlive() {
        return health > 0;
    }
}

// Subclass 1: Mana Generator Enemy (generates mana, weak attack)
class ManaGeneratorEnemy extends BattleSpells {
    public ManaGeneratorEnemy(String name, int health, int mana, int baseDamage) {
        super(name, health, mana, baseDamage);
    }

    @Override
    public void performAttack(BattleSpells target, ConsoleOutput output) {
        int damage = baseDamage / 2;
        this.mana += 15;
        output.printCombat(name + " casts a weak mana spell, dealing " + damage + " damage to " + target.getName() + " and generating 15 mana.");
        target.takeDamage(damage);
    }
}

// Subclass 2: Lightning Enemy (fast attacks with crit chance)
class LightningEnemy extends BattleSpells {
    public LightningEnemy(String name, int health, int mana, int baseDamage) {
        super(name, health, mana, baseDamage);
    }

    @Override
    public void performAttack(BattleSpells target, ConsoleOutput output) {
        Random rand = new Random();
        int damage = baseDamage;
        if (rand.nextInt(100) < 30) {
            damage += baseDamage / 2;
            output.printCombat(name + " strikes with a FAST lightning bolt, dealing " + damage + " damage to " + target.getName() + " (critical hit!)");
        } else {
            output.printCombat(name + " strikes with lightning, dealing " + damage + " damage to " + target.getName());
        }
        target.takeDamage(damage);
    }
}

// Subclass 3: Super Damage Enemy (high-cost, high-damage attacks)
class SuperDamageEnemy extends BattleSpells {
    private final int superAttackCost = 30;

    public SuperDamageEnemy(String name, int health, int mana, int baseDamage) {
        super(name, health, mana, baseDamage);
    }

    @Override
    public void performAttack(BattleSpells target, ConsoleOutput output) {
        if (this.mana >= superAttackCost) {
            int damage = baseDamage * 3;
            this.mana -= superAttackCost;
            output.printCombat(name + " unleashes a SUPER spell, dealing " + damage + " damage to " + target.getName() + "!");
            target.takeDamage(damage);
        } else {
            int damage = baseDamage / 2;
            output.printCombat(name + " lacks mana for the super spell. Deals " + damage + " regular damage to " + target.getName());
            target.takeDamage(damage);
        }
    }
}

// Subclass 4: Player Character (supports Normal Attack, Heavy Attack, and Potions)
class PlayerCharacter extends BattleSpells {
    private int defense;
    private int potionsAvailable;
    private int defenseBuff; // 0 = normal, 2 = defended (reduced damage)

    public PlayerCharacter(String name, int health, int mana, int baseDamage, int defense) {
        super(name, health, mana, baseDamage);
        this.defense = defense;
        this.potionsAvailable = 2;
        this.defenseBuff = 1;
    }

    @Override
    public void performAttack(BattleSpells target, ConsoleOutput output) {
        // Normal attack (placeholder; real attacks handled via battleWithSpells menu)
        int damage = Math.max(0, baseDamage - 2);
        output.printCombat(name + " attacks, dealing " + damage + " damage!");
        target.takeDamage(damage);
    }

    // Heavy attack: 1.5x damage, defense drops to 1 next turn
    public void heavyAttack(BattleSpells target, ConsoleOutput output) {
        int damage = Math.max(0, (int)(baseDamage * 1.5) - 2);
        output.printCombat(name + " unleashes a heavy strike, dealing " + damage + " damage!");
        target.takeDamage(damage);
        this.defenseBuff = 0; // Guard is down next turn
    }

    // Normal attack: balanced damage and defense
    public void normalAttack(BattleSpells target, ConsoleOutput output) {
        int damage = Math.max(0, baseDamage - 2);
        output.printCombat(name + " attacks, dealing " + damage + " damage!");
        target.takeDamage(damage);
        this.defenseBuff = 1; // Normal defense next turn
    }

    // Defend: raise guard and reduce damage taken
    public void defend(ConsoleOutput output) {
        output.print(name + " braces for the incoming attack, raising defenses!");
        this.defenseBuff = 2; // 2x defense multiplier next turn
    }

    // Use potion: heal 20 HP
    public boolean usePotion(ConsoleOutput output) {
        if (potionsAvailable > 0) {
            health += 20;
            potionsAvailable--;
            output.printSuccess(name + " drinks a potion and heals 20 HP. Health: " + health);
            return true;
        } else {
            output.printWarning("No potions left!");
            return false;
        }
    }

    // Apply defense multiplier to incoming damage
    public void takeDamageWithDefense(int damage) {
        int reducedDamage = damage / defenseBuff;
        this.health -= reducedDamage;
    }

    public int getPotionsAvailable() {
        return potionsAvailable;
    }

    public int getDefense() {
        return defense;
    }

    public int getDefenseBuff() {
        return defenseBuff;
    }

    public void resetDefenseBuff() {
        this.defenseBuff = 1;
    }
}
