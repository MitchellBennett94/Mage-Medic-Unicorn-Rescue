import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private String[] requiredIngredients;
    private boolean[] foundIngredients;
    private List<String> visitedLocations;

    public Inventory(String[] requiredIngredients) {
        this.requiredIngredients = requiredIngredients;
        this.foundIngredients = new boolean[requiredIngredients.length];
        this.visitedLocations = new ArrayList<>();
    }

    public String[] getRequiredIngredients() {
        return requiredIngredients;
    }

    public boolean[] getFoundIngredients() {
        return foundIngredients;
    }

    public void addIngredient(int index) {
        if (index >= 0 && index < foundIngredients.length) {
            foundIngredients[index] = true;
        }
    }

    public boolean hasIngredient(int index) {
        return index >= 0 && index < foundIngredients.length && foundIngredients[index];
    }

    public boolean hasAllIngredients() {
        for (boolean found : foundIngredients) {
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public void addVisitedLocation(String location) {
        visitedLocations.add(location);
    }

    public List<String> getVisitedLocations() {
        return visitedLocations;
    }

    public void printInventory() {
        System.out.println("\n--- Inventory ---");
        System.out.println("Ingredients Found:");
        for (int i = 0; i < requiredIngredients.length; i++) {
            if (foundIngredients[i]) {
                System.out.println("- " + requiredIngredients[i]);
            }
        }
    }

    // Overload using ConsoleOutput
    public void printInventory(ConsoleOutput output) {
        output.printSection("Inventory");
        output.print("Ingredients Found:");
        for (int i = 0; i < requiredIngredients.length; i++) {
            if (foundIngredients[i]) {
                output.print("- " + requiredIngredients[i]);
            }
        }
    }

    public void printVisitedLocations() {
        System.out.println("\n--- Visited Locations ---");
        if (visitedLocations.isEmpty()) {
            System.out.println("No locations have been visited yet.");
        } else {
            for (int i = 0; i < visitedLocations.size(); i++) {
                System.out.println((i + 1) + ". " + visitedLocations.get(i));
            }
        }
    }

    // Overload using ConsoleOutput
    public void printVisitedLocations(ConsoleOutput output) {
        output.printSection("Visited Locations");
        if (visitedLocations.isEmpty()) {
            output.print("No locations have been visited yet.");
        } else {
            for (int i = 0; i < visitedLocations.size(); i++) {
                output.print((i + 1) + ". " + visitedLocations.get(i));
            }
        }
    }

    // Simple dependency check stub
    public boolean canCraftIngredient(String ingredient) {
        for (int i = 0; i < requiredIngredients.length; i++) {
            if (requiredIngredients[i].equals(ingredient)) {
                return foundIngredients[i];
            }
        }
        return false;
    }
    
    // Check if location has been visited
    public boolean hasVisited(String location) {
        return visitedLocations.contains(location);
    }
    
    // Get collected ingredients as list
    public List<String> getCollectedIngredients() {
        List<String> collected = new ArrayList<>();
        for (int i = 0; i < requiredIngredients.length; i++) {
            if (foundIngredients[i]) {
                collected.add(requiredIngredients[i]);
            }
        }
        return collected;
    }
    
    // Add ingredient by name
    public void addIngredient(String ingredientName) {
        for (int i = 0; i < requiredIngredients.length; i++) {
            if (requiredIngredients[i].equals(ingredientName)) {
                foundIngredients[i] = true;
                System.out.println("You found: " + ingredientName);
                return;
            }
        }
    }
    
    // Check if ingredient is collected
    public boolean hasIngredient(String ingredientName) {
        for (int i = 0; i < requiredIngredients.length; i++) {
            if (requiredIngredients[i].equals(ingredientName)) {
                return foundIngredients[i];
            }
        }
        return false;
    }
}
