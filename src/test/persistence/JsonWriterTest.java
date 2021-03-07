package persistence;

import model.Aquarium;
import model.Decoration;
import model.Octopus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Largely imitated code from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterTest extends JsonTest{
    Aquarium aquarium;

    @BeforeEach
    void setup() {
        aquarium = new Aquarium();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAquarium() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAquarium.json");
            writer.open();
            writer.write(aquarium);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAquarium.json");
            aquarium = reader.read();
            assertEquals(0, aquarium.getNumOctopus());
            assertEquals(0, aquarium.getNumDecoration());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAquarium() {
        try {
            aquarium.addOctopus(new Octopus("Tomato", 5, 6));
            aquarium.addOctopus(new Octopus("Orange", 1, 0));
            aquarium.addDecoration(new Decoration("a", "a", "a"));
            aquarium.addDecoration(new Decoration("b", "b", "b"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAquarium.json");
            writer.open();
            writer.write(aquarium);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAquarium.json");
            aquarium = reader.read();
            List<Octopus> octopuses = aquarium.getOctopuses();
            List<Decoration> decorations = aquarium.getDecorations();
            assertEquals(2, octopuses.size());
            assertEquals(2, decorations.size());
            checkOctopus("Tomato", 5, 6, octopuses.get(0));
            checkOctopus("Orange", 1, 0, octopuses.get(1));
            checkDecoration("a", "a", "a", decorations.get(0));
            checkDecoration("b", "b", "b", decorations.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
