package model.buttons;

import model.Aquarium;
import persistence.JsonWriter;
import ui.AquariumGameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Button to save aquarium to save
public class SaveButton extends JButton implements ActionListener {
    private static final String JSON_STORE = "./data/aquarium.json";

    private Aquarium aquarium;
    private JsonWriter jsonWriter;

    // EFFECTS: constructs button
    public SaveButton(Aquarium aquarium) {
        super("Save");
        setFocusable(false);
        setBounds(AquariumGameGUI.WIDTH - 180, 10, 80, 30);
        addActionListener(this);

        this.aquarium = aquarium;

        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // EFFECTS: saves aquarium to save upon being clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            try {
                jsonWriter.open();
                jsonWriter.write(aquarium);
                jsonWriter.close();
                System.out.println("Saved aquarium to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }
}
