package model.buttons;

import model.Aquarium;
import ui.AquariumGameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Button to clear all octopuses
public class ClearButton extends JButton implements ActionListener {
    private AquariumGameGUI game;
    private Aquarium aquarium;

    private JFrame confirmWindow;
    private JLabel text;
    private JButton confirmButton;

    // EFFECTS: constructs button and the confirmation window
    public ClearButton(AquariumGameGUI game, Aquarium aquarium) {
        super("Clear");
        setFocusable(false);
        setBounds(AquariumGameGUI.WIDTH - 270, 10, 80, 30);
        addActionListener(this);

        uiSetup();

        this.game = game;
        this.aquarium = aquarium;


    }

    // EFFECTS: constructs confirmation window
    private void uiSetup() {
        confirmWindow = new JFrame();
        confirmWindow.setSize(205, 195);
        confirmWindow.setResizable(false);
        confirmWindow.setLayout(null);

        text = new JLabel();
        String msg = "Are you sure you want to<br>release your octopuses?";
        text.setText("<html><div style='text-align: center;'>" + msg + "</div></html>");
        text.setBounds(0, 0, 200, 100);

        confirmButton = new JButton("Confirm");
        confirmButton.setFocusable(false);
        confirmButton.setBounds(50, 120, 100, 40);
        confirmButton.addActionListener(this);

        confirmWindow.add(text);
        confirmWindow.add(confirmButton);
    }

    // EFFECTS: opens confirmation window upon being clicked, then clears aquarium if the confirm button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            confirmWindow.setVisible(true);
        } else if (e.getSource() == confirmButton) {
            game.clearOctopus();
            aquarium.clearOctopus();
            confirmWindow.setVisible(false);
        }
    }
}
