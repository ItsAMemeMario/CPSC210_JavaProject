# My Personal Project

## Octopus Aquarium

For my term project, I will design a simple incremental game. An incremental game is one which the user plays by merely
clicking on several objects, perhaps to purchase items, move constructs, or upgrade gadgets, and then waiting in real
time to make progress. For simplicity, my game will only consist of an aquarium and some octopuses, and my target
audience will be casual gamers of any age.

I chose to design an incremental game because I want to become a game developer in the future. An incremental game is
the easiest one to design and one I reckon will provide a good learning experience. In this project, I hope to achieve
the following:
- let the user customize the aquarium to some degree
- implement a feeding system
- create a sense of progression to motivate gameplay activity

## User Stories

- As a user, I want to be able to add a new octopus to my aquarium
- As a user, I want to be able to add decorations to my aquarium
- As a user, I want to be able to view the list of octopuses I have
- As a user, I want to be able to select an octopus and view its statistics
- As a user, I want to be able to select a decoration and view its details
- As a user, I want to be able to feed my octopuses
- As a user, I want to be able to save my aquarium to file
- As a user, I want to be reminded to save my aquarium before exiting the game
- As a user, I want to be given the option to load my aquarium from file when I restart the game

## Phase 4: Task 2

I have chosen to implement the second option for phase 4 of this project. The CursorModeButton class is the superclass
of all buttons which change the cursor mode upon being clicked (check, move, and feed).

## Phase 4: Task 3

The UML class design diagram shows an excessive amount of coupling. For example, ClearButton calls clearOctopus() in
Aquarium and AquariumGameGUI, when the call to Aquarium.clearOctopus() could have just been included in
AquariumGameGUI.clearOctopus to remove the need for an Aquarium field in ClearButton. With the lecture material from the
Design module in mind, a lot of redundant design can be avoided.