package model;

public class Octopus {
    private static int id = 1;               // tracks the number of octopuses. Assigned to name if no name is given
    private String name;                     // name of the octopus
    private int size = 0;                    // 0 if octopus is a baby. Increases when fed if octopus is grown
    private int growth = 1;                  // < 5 when octopus is a baby. Increases when fed and stops at 5
    public static final int GROWTH_REQ = 5; // number of feedings required to grow
    public static final int MAX_SIZE = 10;

    // EFFECTS: constructs baby octopus with name automatically assigned
    public Octopus() {
        this.name = "Octopus" + id++;
    }

    // REQUIRES: name is not empty
    // EFFECTS: constructs a baby octopus with given name
    public Octopus(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: increases octopus growth if octopus is a baby, grows octopus by 1 size and stop at limit otherwise
    //          if growth requirement is reached, octopus grows up
    public void eat() {
        if (growth == GROWTH_REQ) {
            growth++;
            size = 1;
        } else if (growth < GROWTH_REQ) {
            growth++;
        } else if (size < MAX_SIZE) {
            size++;
        } else {
            size += 0;
        }
    }

    // EFFECTS: returns octopus name
    public String getName() {
        return name;
    }

    // EFFECTS: returns true if octopus is a baby
    public boolean isBaby() {
        return size == 0;
    }

    // EFFECTS: returns octopus' stats in a string
    public String getStats() {
        if (isBaby()) {
            return name + "(baby)\n\nGrowth Stage: " + growth;
        } else if (size < MAX_SIZE) {
            return name + "\n\nSize: " + size;
        } else {
            return name + "\n\nSize: " + size + "(MAX)";
        }
    }
}
