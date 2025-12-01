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

    // Removed no-arg print methods to enforce ConsoleOutput usage explicitly

    public void printInventory(ConsoleOutput output) {
        output.printSection("Inventory");
        output.print("Ingredients Found:");
        for (int i = 0; i < requiredIngredients.length; i++) {
            if (foundIngredients[i]) {
                output.printInventoryItem(requiredIngredients[i]);
            }
        }
    }

    public void printVisitedLocations(ConsoleOutput output) {
        output.printSection("Visited Locations");
        if (visitedLocations.isEmpty()) {
            output.print("No locations have been visited yet.");
        } else {
            String[] locationsArray = visitedLocations.toArray(new String[0]);
            output.printList(locationsArray);
        }
    }
}
