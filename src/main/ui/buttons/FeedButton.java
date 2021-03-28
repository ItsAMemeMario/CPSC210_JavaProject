package ui.buttons;

import ui.AquariumGameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.CursorMode.FEED;

// Button to enable feeding octopuses
public class FeedButton extends JButton implements ActionListener {
    private AquariumGameGUI game;

    // EFFECTS: constructs button
    public FeedButton(AquariumGameGUI game) {
        super("Feed");
        setFocusable(false);
        setBounds(190, 10, 80, 30);
        addActionListener(this);

        this.game = game;
    }

    // EFFECTS: changes cursor mode to FEED upon being clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            game.setCursorMode(FEED);
        }
    }
}