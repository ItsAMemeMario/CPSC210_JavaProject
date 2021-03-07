package ui;

import model.Aquarium;
import model.Decoration;
import model.Octopus;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new AquariumGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
