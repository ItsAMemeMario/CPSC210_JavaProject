package ui;

import model.Aquarium;
import model.Decoration;
import model.Octopus;

import java.util.Scanner;

public class AquariumGame {
    private Aquarium aquarium;
    private Scanner input;

    // initializes fields and runs game
    public AquariumGame() {
        aquarium = new Aquarium();
        input = new Scanner(System.in);
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        String command;

        while (true) {
            mainMenu();
            command = input.nextLine().toLowerCase();

            if (command.equals("q")) {
                break;
            } else {
                processMainCommand(command);
            }
        }


        System.out.println("\nThanks for playing!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command on main menu
    private void processMainCommand(String command) {
        if (command.equals("o")) {
            octopusMenu();
            command = input.nextLine().toLowerCase();
            processOctopusCommand(command);
        } else if (command.equals("d")) {
            decorationMenu();
            command = input.nextLine().toLowerCase();
            processDecorationCommand(command);
        } else if (command.equals("e")) {
            System.out.println("Are you sure you want to clear all your octopuses and decorations?");
            System.out.println("Type \"yes\" to confirm");
            command = input.nextLine().toLowerCase();
            if (command.equals("yes")) {
                aquarium.clearOctopus();
                aquarium.clearDecoration();
                System.out.println("Your aquarium has been emptied.");
            } else {
                System.out.println("Emptying cancelled.");
            }
        } else {
            System.out.println("Invalid input.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command on octopus menu
    private void processOctopusCommand(String command) {
        if (command.equals("a")) {
            doAddOctopus();
        } else if (command.equals("r")) {
            doRemoveOctopus();
        } else if (command.equals("f")) {
            doFeedOctopus();
        } else if (command.equals("c")) {
            doCheckOctopus();
        } else if (command.equals("l")) {
            doListOctopus();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command on decoration menu
    private void processDecorationCommand(String command) {
        if (command.equals("a")) {
            doAddDecoration();
        } else if (command.equals("r")) {
            doRemoveDecoration();
        } else if (command.equals("e")) {
            doEditDecoration();
        } else if (command.equals("c")) {
            doCheckDecoration();
        } else if (command.equals("l")) {
            doListDecoration();
        }
    }

    // EFFECTS: displays a menu of main categories of actions
    private void mainMenu() {
        System.out.println("\nSelect an action category:");
        System.out.println("\to -> octopus");
        System.out.println("\td -> decoration");
        System.out.println("\te -> empty aquarium");
        System.out.println("\tq -> quit game");
    }

    // EFFECTS: displays a menu of actions on octopuses
    private void octopusMenu() {
        System.out.println("\nSelect an action:");
        System.out.println("\ta -> add an octopus");
        System.out.println("\tr -> remove an octopus");
        System.out.println("\tf -> feed an octopus");
        System.out.println("\tc -> check an octopus");
        System.out.println("\tl -> list all octopuses");
        System.out.println("\tInput any other command to go back to main menu.");
    }

    // EFFECTS: displays of menu of actions on decorations
    private void decorationMenu() {
        System.out.println("\nSelect an action:");
        System.out.println("\ta -> add a decoration");
        System.out.println("\tr -> remove a decoration");
        System.out.println("\te -> edit a decoration");
        System.out.println("\tc -> check a decoration");
        System.out.println("\tl -> list all decorations");
        System.out.println("\tInput any other command to go back to main menu.");
    }

    private void doAddOctopus() {
        Octopus octopus;

        System.out.println("Give your new octopus a name:");
        String name = input.nextLine();
        if (name.equals("")) {
            octopus = new Octopus();
        } else {
            octopus = new Octopus(name);
        }

        aquarium.addOctopus(octopus);
        System.out.println(octopus.getName() + " has been added to your aquarium!");
    }

    private void doRemoveOctopus() {
        System.out.println("Which octopus do you want to remove?");
        String name = input.nextLine();

        if (aquarium.hasOctopus(name)) {
            aquarium.removeOctopus(name);
            System.out.println(name + " has been released back into the wild.");
        } else {
            notFoundMessage(name);
        }
    }

    private void doFeedOctopus() {
        System.out.println("Which octopus do you want to feed?");
        String name = input.nextLine();

        if (aquarium.hasOctopus(name)) {
            System.out.println("How many times?");
            // int feedings = input.nextInt();
            int feedings = Integer.parseInt(input.nextLine());
            // Reasoning for doing this instead of int feedings = input.nextInt();
            // For some reason, if I do it that way runGame() will ignore my next input and run once with command = "",
            // which results in the invalid input message. Unfortunately, I cannot find the reason behind this strange
            // behavior with my shallow knowledge in Java, so I resorted to converting a string input into an integer.
            if (aquarium.feedOctopus(name, feedings)) {
                if (feedings == 1) {
                    System.out.println("Fed " + name + " once.");
                } else {
                    System.out.println("Fed " + name + " " + feedings + " times.");
                }
                System.out.println(name + " grew up!");
            } else {
                if (feedings == 1) {
                    System.out.println("Fed " + name + " once.");
                } else {
                    System.out.println("Fed " + name + " " + feedings + " times.");
                }
            }
        } else {
            notFoundMessage(name);
        }
    }

    private void doCheckOctopus() {
        System.out.println("Which octopus do you want to check?");
        String name = input.nextLine();

        if (aquarium.hasOctopus(name)) {
            System.out.println("\n" + aquarium.checkOctopus(name));
        } else {
            notFoundMessage(name);
        }
    }

    private void doListOctopus() {
        System.out.println("\n" + aquarium.listOctopus());
    }

    private void doAddDecoration() {
        Decoration decoration;

        System.out.println("Enter your decoration's name:");
        String name = input.nextLine();
        System.out.println("Describe your decoration:");
        String description = input.nextLine();
        System.out.println("Give it a tooltip:");
        String tooltip = input.nextLine();
        if (name.equals("")) {
            decoration = new Decoration();
            aquarium.editDecoration(decoration.getName(), description, tooltip);
        } else {
            decoration = new Decoration(name, description, tooltip);
        }

        aquarium.addDecoration(decoration);
        System.out.println(decoration.getName() + " has been added to your aquarium!");
    }

    private void doRemoveDecoration() {
        System.out.println("Which decoration do you want to remove?");
        String name = input.nextLine();

        if (aquarium.hasDecoration(name)) {
            aquarium.removeDecoration(name);
            System.out.println(name + " has been discarded.");
        } else {
            notFoundMessage(name);
        }
    }

    private void doEditDecoration() {
        System.out.println("Which decoration do you want to edit?");
        String name = input.nextLine();

        if (aquarium.hasDecoration(name)) {
            System.out.println("Enter its new description: ");
            String description = input.nextLine();
            System.out.println("Enter its new tooltip: ");
            String tooltip = input.nextLine();

            aquarium.editDecoration(name, description, tooltip);
            System.out.println(name + "'s description and tooltip have been changed.");
        } else {
            notFoundMessage(name);
        }
    }

    private void doCheckDecoration() {
        System.out.println("Which decoration do you want to check?");
        String name = input.nextLine();

        if (aquarium.hasDecoration(name)) {
            System.out.println("\n" + aquarium.checkDecoration(name));
        } else {
            notFoundMessage(name);
        }
    }

    private void doListDecoration() {
        System.out.println("\n" + aquarium.listDecoration());
    }

    private void notFoundMessage(String name) {
        System.out.println(name + " is not in your aquarium.");
    }
}
