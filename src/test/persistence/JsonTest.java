package persistence;

import model.Decoration;
import model.Octopus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
    protected void checkOctopus(String name, int growth, int size, Octopus octopus) {
        assertEquals(name, octopus.getName());
        assertEquals(growth, octopus.getGrowth());
        assertEquals(size, octopus.getSize());
    }

    protected void checkDecoration(String name, String description, String tooltip, Decoration decoration) {
        assertEquals(name, decoration.getName());
        assertEquals(description, decoration.getDescription());
        assertEquals(tooltip, decoration.getTooltip());
    }
}
