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

    public Character(String name, String pronounSubject, String pronounObject, String possessiveAdjective, int age, int health, int attack, int defense, int gold) {
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
