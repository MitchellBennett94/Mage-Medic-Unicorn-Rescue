public class Enemy {
    private String name;
    private int health;
    private int attack;
    private int defense;

    // Full constructor with all parameters
    public Enemy(String name, int health, int attack, int defense) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Enemy name cannot be null or empty");
        }
        if (health <= 0) {
            throw new IllegalArgumentException("Enemy health must be positive");
        }
        if (attack < 0) {
            throw new IllegalArgumentException("Attack cannot be negative");
        }
        if (defense < 0) {
            throw new IllegalArgumentException("Defense cannot be negative");
        }
        
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    // Overloaded constructor for weak enemies with default low stats
    public Enemy(String name) {
        this(name, 30, 8, 2);
    }

    // Overloaded constructor with health only (uses default attack/defense)
    public Enemy(String name, int health) {
        this(name, health, 10, 3);
    }

    public String getName() {
        return name;
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

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
