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
}
