public class Character {
    private String name;
    private String pronounSubject;
    private String pronounObject;
    private String possessiveAdjective;
    private int age;
    private int health;
    private int attack;
    private int defense;
    private int mana;
    private static final int MAX_MANA = 100;

    public Character(String name, String pronounSubject, String pronounObject, String possessiveAdjective, int age, int health, int attack, int defense, int mana) {
        this.name = name;
        this.pronounSubject = pronounSubject;
        this.pronounObject = pronounObject;
        this.possessiveAdjective = possessiveAdjective;
        this.age = age;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public String getPronounSubject() {
        return pronounSubject;
    }

    public String getPronounObject() {
        return pronounObject;
    }

    public String getPossessiveAdjective() {
        return possessiveAdjective;
    }

    public int getAge() {
        return age;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return MAX_MANA;
    }

    public void addMana(int amount) {
        this.mana = Math.min(MAX_MANA, this.mana + amount);
    }

    public boolean canUseMana() {
        return mana > 0;
    }

    public void useManaHeal() {
        if (canUseMana()) {
            int healAmount = mana;
            this.health = Math.min(getMaxHealth(), this.health + healAmount);
            this.mana = 0;
        }
    }

    public int getMaxHealth() {
        return 100;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public void heal(int amount) {
        this.health += amount;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
