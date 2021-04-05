package ui.buttons;

import ui.AquariumGameGUI;

import static ui.CursorMode.CHECK;

// Button to enable checking octopuses
public class CheckButton extends CursorModeButton {
    AquariumGameGUI game;

    // EFFECTS: constructs button
    public CheckButton(AquariumGameGUI game) {
        super("Check", game, CHECK);
    }
}
