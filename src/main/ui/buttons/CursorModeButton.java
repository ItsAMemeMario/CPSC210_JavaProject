package ui.buttons;

import ui.AquariumGameGUI;
import ui.CursorMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.CursorMode.CHECK;

// Button for switching cursor modes
public abstract class CursorModeButton extends JButton implements ActionListener {
    protected static int position = 0;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final int SPACING = 10;

    protected AquariumGameGUI game;
    protected CursorMode cursorMode;

    public CursorModeButton(String name, AquariumGameGUI game, CursorMode cursorMode) {
        super(name);
        setFocusable(false);
        setSize(WIDTH, HEIGHT);
        setLocation(OctopusAdder.WIDTH + (2 * SPACING) + (position * (WIDTH + SPACING)), 10);
        addActionListener(this);

        this.game = game;
        this.cursorMode = cursorMode;

        position++;
    }

    // EFFECTS: changes cursor mode to specified value
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            game.setCursorMode(cursorMode);
        }
    }
}
