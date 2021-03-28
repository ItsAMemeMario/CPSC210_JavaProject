package model.buttons;

import ui.AquariumGameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.CursorMode.CHECK;

// Button to enable checking octopuses
public class CheckButton extends JButton implements ActionListener {
    AquariumGameGUI game;

    // EFFECTS: constructs button
    public CheckButton(AquariumGameGUI game) {
        super("Check");
        setFocusable(false);
        setBounds(10, 10, 80, 30);
        addActionListener(this);

        this.game = game;
    }

    // EFFECTS: changes cursor mode to CHECK upon being clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            game.setCursorMode(CHECK);
        }
    }
}
