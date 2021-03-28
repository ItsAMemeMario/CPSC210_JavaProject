package ui.buttons;

import ui.AquariumGameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.CursorMode.MOVE;

// Button to enable moving octopuses
public class MoveButton extends JButton implements ActionListener {
    private AquariumGameGUI game;

    // EFFECTS: constructs button
    public MoveButton(AquariumGameGUI game) {
        super("Move");
        setFocusable(false);
        setBounds(100, 10, 80, 30);
        addActionListener(this);

        this.game = game;
    }

    // EFFECTS: changes cursor mode to MOVE upon being clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            game.setCursorMode(MOVE);
        }
    }
}
