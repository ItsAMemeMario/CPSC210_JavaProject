package ui.buttons;

import ui.AquariumGameGUI;

import static ui.CursorMode.MOVE;

// Button to enable moving octopuses
public class MoveButton extends CursorModeButton {
    private AquariumGameGUI game;

    // EFFECTS: constructs button
    public MoveButton(AquariumGameGUI game) {
        super("Move", game, MOVE);
    }
}
