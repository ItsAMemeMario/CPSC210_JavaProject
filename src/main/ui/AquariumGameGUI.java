package ui;

import model.Aquarium;
import model.Octopus;
import ui.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 Main component of the aquarium game's graphical user interface
 All code used in this phase of the project is heavily inspired by
 Bro Code's youtube tutorial on Java Swing
 URL: https://www.youtube.com/watch?v=Kmgo00avvEw
*/

public class AquariumGameGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    public static final int FLOOR_HEIGHT = 250;

    private JPanel floor;

    private Aquarium aquarium;
    private java.util.List<InteractiveOctopus> octopuses = new ArrayList<InteractiveOctopus>();

    private CursorMode cursorMode;

    private CheckButton checkButton;
    private MoveButton moveButton;
    private FeedButton feedButton;

    private OctopusAdder octopusAdder;

    private ClearButton clear;
    private SaveButton save;
    private LoadButton load;

    // EFFECTS: constructs aquarium game user interface
    private AquariumGameGUI() {
        super("Octopus Aquarium");
        setSize(WIDTH + 5, HEIGHT + 35);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(80, 80, 255));

        aquarium = new Aquarium();
        cursorMode = cursorMode.CHECK;
        generateButtons();

        setVisible(true);
    }

    // EFFECTS: returns cursor mode
    public CursorMode getCursorMode() {
        return cursorMode;
    }

    // EFFECTS: generates main menu buttons
    private void generateButtons() {
        checkButton = new CheckButton(this);
        moveButton = new MoveButton(this);
        feedButton = new FeedButton(this);

        octopusAdder = new OctopusAdder(this, aquarium);

        clear = new ClearButton(this, aquarium);
        save = new SaveButton(aquarium);
        load = new LoadButton(this, aquarium);

        add(checkButton);
        add(moveButton);
        add(feedButton);
        add(octopusAdder);

        add(clear);
        add(save);
        add(load);
    }

    // EFFECTS: sets cursor mode
    public void setCursorMode(CursorMode cursorMode) {
        this.cursorMode = cursorMode;
    }

    // EFFECTS: reloads visual components
    public void update() {
        setVisible(false);
        setVisible(true);
    }

    // EFFECTS: adds interactive octopus to aquarium game GUI
    public void addOctopus(Octopus octopus) {
        if (aquarium.addOctopus(octopus)) {
            InteractiveOctopus interactiveOctopus = new InteractiveOctopus(octopus, this);
            add(interactiveOctopus);
            octopuses.add(interactiveOctopus);
            update();
        }
    }

    // EFFECTS: removes all octopus from aquarium
    public void clearOctopus() {
        for (InteractiveOctopus o: octopuses) {
            remove(o);
        }
        octopuses.clear();
        aquarium.clearOctopus();
        update();
    }

    // EFFECTS: initializes aquarium game
    public static void main(String[] args) {
        new AquariumGameGUI();
    }
}
