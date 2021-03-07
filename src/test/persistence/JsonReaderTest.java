package persistence;

import model.Aquarium;
import model.Decoration;
import model.Octopus;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    Aquarium aquarium;

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            aquarium = reader.read();
            fail("Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAquarium() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAquarium.json");
        try {
            aquarium = reader.read();
            assertEquals(0, aquarium.getNumOctopus());
            assertEquals(0, aquarium.getNumDecoration());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAquarium() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAquarium.json");
        try {
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
            fail("Couldn't read from file");
        }
    }
}
