package model;

import org.json.JSONObject;
import persistence.Writable;

public class Decoration implements Writable {
    private static int id = 1;      // tracks the number of decorations. Assigned to name if no name is given
    private String name;        // name of decoration
    private String description; // description of decoration
    private String tooltip;     // additional comment/description

    // EFFECTS: constructs decoration with an automatically-assigned name and empty descriptions
    public Decoration() {
        this.name = "Decoration" + id++;
        this.description = "";
        this.tooltip = "";
    }

    // EFFECTS: constructs decoration with given name, description, and tooltip
    public Decoration(String name, String description, String tooltip) {
        this.name = name;
        this.description = description;
        this.tooltip = tooltip;
    }

    // EFFECTS: returns decoration name
    public String getName() {
        return name;
    }

    // EFFECTS: returns decoration description
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns decoration description
    public String getTooltip() {
        return tooltip;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    // EFFECTS: returns details of decoration in a String
    public String getDetails() {
        return name + "\n\n" + description + "\n\n\033[3m\"" + tooltip + "\"\033[0m";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("tooltip", tooltip);
        return json;
    }
}
