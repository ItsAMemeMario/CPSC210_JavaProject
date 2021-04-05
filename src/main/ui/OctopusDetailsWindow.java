package ui;

import model.Octopus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Octopus details window, includes function to remove octopus
public class OctopusDetailsWindow extends JFrame implements ActionListener {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 150;

    private JLabel text;
    private JButton removeButton;

    private AquariumGameGUI game;
    private InteractiveOctopus interactiveOctopus;
    private Octopus octopus;

    // Constructs details window
    public OctopusDetailsWindow(AquariumGameGUI game, InteractiveOctopus interactiveOctopus, Octopus octopus) {
        super(octopus.getName() + "'s Details");
        this.setSize(WIDTH + 5, HEIGHT + 35);
        this.setResizable(false);
        this.setLayout(null);

        this.game = game;
        this.interactiveOctopus = interactiveOctopus;
        this.octopus = octopus;
        this.text = new JLabel();
        text.setFont(new Font("", Font.PLAIN, 20));
        text.setBounds((int)(WIDTH / 2) - 75, (int)(HEIGHT / 2) - 50, 150, 100);
        this.add(text);
        //text.setHorizontalAlignment(JLabel.CENTER);
        //text.setVerticalAlignment(JLabel.CENTER);
        this.updateLabels();

        removeButton = new JButton("Remove");
        removeButton.setBounds(WIDTH - 90, HEIGHT - 40, 80, 30);
        removeButton.setFocusable(false);
        removeButton.addActionListener(this);
        this.add(removeButton);
    }

    // EFFECTS: updates texts to match with the octopus' statistics
    public void updateLabels() {
        String header = "<html><div style='text-align: center;'>";
        String tail = "</div></html>";
        if (octopus.isBaby()) {
            text.setText(header + octopus.getName() + "(Baby)<br><br>Growth stage: " + octopus.getGrowth() + tail);
        } else if (octopus.getSize() == Octopus.MAX_SIZE) {
            text.setText(header + octopus.getName() + "<br><br>Size: " + octopus.getSize() + "(MAX)" + tail);
        } else {
            text.setText(header + octopus.getName() + "<br><br>Size: " + octopus.getSize() + tail);
        }
    }

    // EFFECTS: removes octopus upon pressing remove button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            game.remove(interactiveOctopus);
            game.getAquarium().removeOctopus(octopus.getName());
            this.setVisible(false);
            game.update();
        }
    }
}
