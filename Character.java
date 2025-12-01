public class Character {
    private String name;
    private String pronounSubject;
    private String pronounObject;
    private String possessiveAdjective;
    private int age;
    private int health;
    private int attack;
    private int defense;
    private int gold;

    // Full constructor with all parameters
    public Character(String name, String pronounSubject, String pronounObject, String possessiveAdjective, int age, int health, int attack, int defense, int gold) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Character name cannot be null or empty");
        }
        if (pronounSubject == null || pronounSubject.trim().isEmpty()) {
            throw new IllegalArgumentException("Pronoun subject cannot be null or empty");
        }
        if (pronounObject == null || pronounObject.trim().isEmpty()) {
            throw new IllegalArgumentException("Pronoun object cannot be null or empty");
        }
        if (possessiveAdjective == null || possessiveAdjective.trim().isEmpty()) {
            throw new IllegalArgumentException("Possessive adjective cannot be null or empty");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be negative");
        }
        if (attack < 0) {
            throw new IllegalArgumentException("Attack cannot be negative");
        }
        if (defense < 0) {
            throw new IllegalArgumentException("Defense cannot be negative");
        }
        if (gold < 0) {
            throw new IllegalArgumentException("Gold cannot be negative");
        }
        
        this.name = name;
        this.pronounSubject = pronounSubject;
        this.pronounObject = pronounObject;
        this.possessiveAdjective = possessiveAdjective;
        this.age = age;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.gold = gold;
    }

    // Overloaded constructor with default stats (starter character)
    public Character(String name, String pronounSubject, String pronounObject, String possessiveAdjective, int age) {
        this(name, pronounSubject, pronounObject, possessiveAdjective, age, 100, 15, 5, 12);
    }

    // Overloaded constructor with custom stats but default gold
    public Character(String name, String pronounSubject, String pronounObject, String possessiveAdjective, int age, int health, int attack, int defense) {
        this(name, pronounSubject, pronounObject, possessiveAdjective, age, health, attack, defense, 12);
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

    public int getGold() {
        return gold;
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
