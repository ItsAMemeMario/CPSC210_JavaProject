package ui;

import model.Octopus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

// The interactive component corresponding to octopus object
public class InteractiveOctopus extends JLabel {
    public static final int BASE_HEIGHT = 20;
    public static final int GROWTH_RATE = 4;
    public static final int BASE_HEIGHT_GROWN = BASE_HEIGHT + (GROWTH_RATE * Octopus.GROWTH_REQ);
    public static final int TOP_POSITION = 200;

    private ImageIcon imageIcon;
    private Image babyOctopus; // "./data/baby_octopus.png"
    private Image grownOctopus; // "./data/octopus.png"
    private Image image;
    private int width;
    private int height;
    private Point imageCorner;
    private Point prevPt;

    private Octopus octopus;
    private AquariumGameGUI game;
    private OctopusDetailsWindow detailsWindow;

    // EFFECTS: constructs octopus' interactive components
    public InteractiveOctopus(Octopus octopus, AquariumGameGUI game) {
        super(octopus.getName());
        this.octopus = octopus;
        this.game = game;
        this.imageIcon = new ImageIcon();
        initializeDimensions();

        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        addMouseListener(clickListener);
        addMouseMotionListener(dragListener);

        detailsWindow = new OctopusDetailsWindow(game, this, octopus);
    }

    // EFFECTS: initializes the location(random) and size of the octopus
    private void initializeDimensions() {
        createBaseImage();
        updateImage();
        int xcoord = (int) (Math.random() * (AquariumGameGUI.WIDTH - width));
        int ycoord = (int) (Math.random() * (AquariumGameGUI.HEIGHT - TOP_POSITION - height) + TOP_POSITION);
        imageCorner = new Point(xcoord, ycoord);
        setLocation(imageCorner);
    }

    // EFFECTS: scales the octopus images to desired sizes
    private void createBaseImage() {
        try {
            babyOctopus = ImageIO.read(new File("./data/baby_octopus.png"));
            grownOctopus = ImageIO.read(new File("./data/octopus.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: updates the appearance of the octopus based on its statistics
    private void updateImage() {
        int scale;
        if (octopus.isBaby()) {
            scale = BASE_HEIGHT + ((octopus.getGrowth() - 1) * GROWTH_RATE);
            image = babyOctopus.getScaledInstance(-1, scale, 4);
        } else {
            scale = BASE_HEIGHT_GROWN + (octopus.getSize() * GROWTH_RATE);
            image = grownOctopus.getScaledInstance(-1, scale, 4);
        }
        imageIcon.setImage(image);
        setIcon(imageIcon);
        width = imageIcon.getIconWidth();
        height = imageIcon.getIconHeight();
        setSize(width, height);
    }

    // Mouse listener for octopus
    private class ClickListener extends MouseAdapter {

        // EFFECTS: captures mouse location
        public void mousePressed(MouseEvent e) {
            if (game.getCursorMode().equals(CursorMode.MOVE)) {
                prevPt = e.getPoint();
            }
        }

        // EFFECTS: performs actions on mouse click
        public void mouseClicked(MouseEvent e) {
            if (game.getCursorMode().equals(CursorMode.CHECK)) {
                detailsWindow.setVisible(true);
            } else if (game.getCursorMode().equals(CursorMode.FEED)) {
                if (!(octopus.getSize() == Octopus.MAX_SIZE)) {
                    octopus.eat();
                    updateImage();
                }
                detailsWindow.updateLabels();
            }
        }
    }

    // Mouse motion capturer for octopus
    private class DragListener extends MouseMotionAdapter {

        // EFFECTS: drags octopus along with mouse
        // DOES NOT YET WORK AS INTENDED: more research needed
        //      The octopus only moves half as far as the mouse motion,
        //      but doubling dx and dy completely breaks its behaviour
        public void mouseDragged(MouseEvent e) {
            if (game.getCursorMode().equals(CursorMode.MOVE)) {
                Point currentPt = e.getPoint();

                int dx = (int) (currentPt.getX() - prevPt.getX());
                int dy = (int) (currentPt.getY() - prevPt.getY());
                setLocation(getX() + dx, getY() + dy);
                prevPt = currentPt;
            }
        }
    }
}
