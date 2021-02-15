package model;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    public static final int OCTOPUS_CAPACITY = 10;
    public static final int DECORATION_CAPACITY = 10;
    private List<Octopus> octopuses;       // list of octopuses
    private List<Decoration> decorations;  // list of decorations

    // EFFECTS: constructs aquarium with empty lists of octopuses and decorations
    public Aquarium() {
        this.octopuses = new ArrayList<Octopus>();
        this.decorations = new ArrayList<Decoration>();
    }

    // REQUIRES: octopus with the same name is not already in aquarium
    // MODIFIES: this
    // EFFECTS: adds octopus to aquarium and return true. Returns false if aquarium is full
    public boolean addOctopus(Octopus octopus) {
        if (octopuses.size() < OCTOPUS_CAPACITY) {
            octopuses.add(octopus);
            return true;
        }
        return false;
    }

    // REQUIRES: decoration with the same name is not already in aquarium
    // MODIFIES: this
    // EFFECTS: adds decoration to aquarium and return true. Returns false if aquarium is full
    public boolean addDecoration(Decoration decoration) {
        if (decorations.size() < DECORATION_CAPACITY) {
            decorations.add(decoration);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes octopus with given name from aquarium and returns true, false if not found
    public boolean removeOctopus(String name) {
        if (hasOctopus(name)) {
            octopuses.remove(getOctopus(name));
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes decoration with given name from aquarium and returns true, false if not found
    public boolean removeDecoration(String name) {
        if (hasDecoration(name)) {
            decorations.remove(getDecoration(name));
            return true;
        }
        return false;
    }

    // EFFECTS: clears all octopuses
    public void clearOctopus() {
        octopuses.clear();
    }

    // EFFECTS: clears all decorations
    public void clearDecoration() {
        decorations.clear();
    }

    // REQUIRES: octopus with given name is in aquarium
    // MODIFIES: Octopus
    // EFFECTS: feed octopus of given name a given number of times, returns true if octopus grows up, false otherwise
    public boolean feedOctopus(String name, int reps) {
        boolean wasBaby = getOctopus(name).isBaby();
        for (int i = 0; i < reps; i++) {
            getOctopus(name).eat();
        }
        return wasBaby != getOctopus(name).isBaby();
    }

    // REQUIRES: decoration with given name is in aquarium
    // MODIFIES: Decoration
    // EFFECTS: changes the description and tooltip of the decoration of given name
    public void editDecoration(String name, String description, String tooltip) {
        getDecoration(name).changeDescription(description);
        getDecoration(name).changeTooltip(tooltip);
    }

    // EFFECTS: returns the number of octopuses in this aquarium
    public int getNumOctopus() {
        return octopuses.size();
    }

    // EFFECTS: returns the number of decorations in this aquarium
    public int getNumDecoration() {
        return decorations.size();
    }

    // REQUIRES: octopus with given name is in aquarium
    // EFFECTS: returns the octopus with given name
    private Octopus getOctopus(String name) {
        Octopus octopus = null;
        for (Octopus o: octopuses) {
            if (name.equals(o.getName())) {
                octopus = o;
            }
        }
        return octopus;
    }

    // REQUIRES: decoration with given name is in aquarium
    private Decoration getDecoration(String name) {
        Decoration decoration = null;
        for (Decoration d: decorations) {
            if (name.equals(d.getName())) {
                decoration = d;
            }
        }
        return decoration;
    }

    // EFFECTS: given name, returns true if octopus with the same name is in aquarium
    public boolean hasOctopus(String name) {
        for (Octopus o: octopuses) {
            if (name.equals(o.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: given name, returns true if decoration with the same name is in aquarium
    public boolean hasDecoration(String name) {
        for (Decoration d: decorations) {
            if (name.equals(d.getName())) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: octopus with given name is in aquarium
    // EFFECTS: returns the stats of the octopus with given name
    public String checkOctopus(String name) {
        return getOctopus(name).getStats();
    }

    // REQUIRES: decoration with given name is in aquarium
    // EFFECTS: returns the details of the decoration with given name
    public String checkDecoration(String name) {
        return getDecoration(name).getDetails();
    }

    // EFFECTS: returns a String list of octopuses in aquarium
    public String listOctopus() {
        String list = "";
        for (Octopus o: octopuses) {
            if (o.isBaby()) {
                list += "- " + o.getName() + "(baby)\n";
            } else {
                list += "- " + o.getName() + "\n";
            }
        }
        return list;
    }

    // EFFECTS: returns a String list of decorations in aquarium
    public String listDecoration() {
        String list = "";
        for (Decoration d: decorations) {
            list += "- " + d.getName() + "\n";
        }
        return list;
    }
}
