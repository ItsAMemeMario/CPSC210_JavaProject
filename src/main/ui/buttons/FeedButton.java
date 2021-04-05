package ui.buttons;

import ui.AquariumGameGUI;

import static ui.CursorMode.FEED;

// Button to enable feeding octopuses
public class FeedButton extends CursorModeButton {
    private AquariumGameGUI game;

    // EFFECTS: constructs button
    public FeedButton(AquariumGameGUI game) {
        super("Feed", game, FEED);
    }
}