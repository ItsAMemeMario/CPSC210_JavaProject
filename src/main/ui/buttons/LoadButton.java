package ui.buttons;

import model.Aquarium;
import model.Octopus;
import persistence.JsonReader;
import ui.AquariumGameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Button to load octopuses from save
public class LoadButton extends JButton implements ActionListener {
    private static final String JSON_STORE = "./data/aquarium.json";

    private AquariumGameGUI game;
    private Aquarium aquarium;
    private JsonReader jsonReader;

    // EFFECTS: constructs button
    public LoadButton(AquariumGameGUI game, Aquarium aquarium) {
        super("Load");
        setFocusable(false);
        setBounds(AquariumGameGUI.WIDTH - 90, 10, 80, 30);
        addActionListener(this);

        this.game = game;
        this.aquarium = aquarium;

        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: loads aquarium from file upon being clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            try {
                aquarium = jsonReader.read();
                game.clearOctopus();
                for (Octopus o: aquarium.getOctopuses()) {
                    game.addOctopus(o);
                }
                game.update();
                System.out.println("Loaded aquarium from " + JSON_STORE);
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }
}
