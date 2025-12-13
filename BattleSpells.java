import java.util.Random;

public abstract class BattleSpells {
    protected String name;
    protected int health;
    protected int mana;
    protected int baseDamage;

    public BattleSpells(String name, int health, int mana, int baseDamage) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.baseDamage = baseDamage;
    }

    public abstract void performAttack(BattleSpells target, ConsoleOutput output);

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

class SuperDamageEnemy extends BattleSpells {
    public SuperDamageEnemy(String name, int health, int mana, int baseDamage) {
        super(name, health, mana, baseDamage);
    }

    @Override
    public void performAttack(BattleSpells target, ConsoleOutput output) {
        int damage = baseDamage * 2;
        output.printCombat(name + " unleashes a powerful spell, dealing " + damage + " damage!");
        target.takeDamage(damage);
    }
}
