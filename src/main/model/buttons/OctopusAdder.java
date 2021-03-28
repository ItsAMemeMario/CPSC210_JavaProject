package model.buttons;

import model.Aquarium;
import model.Octopus;
import ui.AquariumGameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Button to add octopus to aquarium
public class OctopusAdder extends JButton implements ActionListener {
    private AquariumGameGUI game;
    private Aquarium aquarium;
    private JFrame assignName;
    private JLabel text;
    private JTextField inputBox;
    private JButton confirmInput;

    // EFFECTS: constructs button
    public OctopusAdder(AquariumGameGUI game, Aquarium aquarium) {
        super("+Octopus");
        setFocusable(false);
        setBounds(280, 10, 90, 30);
        addActionListener(this);

        this.game = game;
        this.aquarium = aquarium;

        uiSetup();
    }

    // EFFECTS: constructs octopus name input window
    private void uiSetup() {
        assignName = new JFrame("Assign name");
        assignName.setSize(300, 120);
        assignName.setResizable(false);
        assignName.setLayout(null);

        text = new JLabel();
        text.setBounds(10, -5, 280, 50);

        inputBox = new JTextField();
        inputBox.setBounds(100, 40, 180, 40);

        confirmInput = new JButton("Confirm");
        confirmInput.setFocusable(false);
        confirmInput.setBounds(10, 45, 80, 30);
        confirmInput.addActionListener(this);

        assignName.add(text);
        assignName.add(confirmInput);
        assignName.add(inputBox);
    }

    // EFFECTS: opens text input window upon being clicked. When "Confirm" is clicked, adds octopus with input name
    //          to aquarium. Rejects request if either aquarium is full or an octopus with the same name exists
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            inputBox.setText("");
            if (aquarium.getNumOctopus() == Aquarium.OCTOPUS_CAPACITY) {
                text.setText("Your aquarium is full!");
                inputBox.setVisible(false);
                confirmInput.setVisible(false);
            } else {
                text.setText("Give your octopus a name:");
                inputBox.setVisible(true);
                confirmInput.setVisible(true);
            }
            assignName.setVisible(true);
        } else if (e.getSource() == confirmInput) {
            if (aquarium.hasOctopus(inputBox.getText())) {
                text.setText("There is already an octopus with that name!");
            } else {
                Octopus octopus = new Octopus(inputBox.getText());
                game.addOctopus(octopus);
                assignName.setVisible(false);
            }
        }
    }
}
