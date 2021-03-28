package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AquariumTest {
    private Aquarium aquarium;

    @BeforeEach
    public void setup() {
        aquarium = new Aquarium();
    }

    @Test
    public void testAddAndRemoveOctopus() {
        Octopus octopus1 = new Octopus("");
        assertFalse(aquarium.removeOctopus(octopus1.getName()));
        assertFalse(aquarium.hasOctopus("Orange"));
        assertEquals(0, aquarium.getNumOctopus());
        assertTrue(aquarium.addOctopus(octopus1));
        assertEquals(1, aquarium.getNumOctopus());

        Octopus orange = new Octopus("Orange");
        assertTrue(aquarium.addOctopus(orange));
        assertFalse(aquarium.hasOctopus("Squishy"));
        assertTrue(aquarium.hasOctopus("Orange"));
        assertEquals(2, aquarium.getNumOctopus());

        for (int i = 0; i < Aquarium.OCTOPUS_CAPACITY - 3; i++) {
            assertTrue(aquarium.addOctopus(new Octopus()));
        }
        assertEquals(Aquarium.OCTOPUS_CAPACITY - 1, aquarium.getNumOctopus());

        Octopus squishy = new Octopus("Squishy");
        assertTrue(aquarium.addOctopus(squishy));
        assertFalse(aquarium.addOctopus(new Octopus()));
        assertEquals(Aquarium.OCTOPUS_CAPACITY, aquarium.getNumOctopus());

        assertFalse(aquarium.removeOctopus("Octopus"));
        assertTrue(aquarium.removeOctopus("Orange"));
        assertFalse(aquarium.hasOctopus("Orange"));
        assertEquals(Aquarium.OCTOPUS_CAPACITY - 1, aquarium.getNumOctopus());

        aquarium.clearOctopus();
        assertEquals(0, aquarium.getNumOctopus());
    }

    @Test
    public void testAddAndRemoveDecoration() {
        Decoration decoration1 = new Decoration();
        assertFalse(aquarium.removeDecoration(decoration1.getName()));
        assertFalse(aquarium.hasDecoration("Seashell"));
        assertEquals(0, aquarium.getNumDecoration());
        assertTrue(aquarium.addDecoration(decoration1));
        assertEquals(1, aquarium.getNumDecoration());

        Decoration seashell = new Decoration("Seashell", "A seashell", "She sells seashell...");
        assertTrue(aquarium.addDecoration(seashell));
        assertFalse(aquarium.hasDecoration("Pineapple"));
        assertTrue(aquarium.hasDecoration("Seashell"));
        assertEquals(2, aquarium.getNumDecoration());

        for (int i = 0; i < Aquarium.DECORATION_CAPACITY - 3; i++) {
            assertTrue(aquarium.addDecoration(new Decoration()));
        }
        assertEquals(Aquarium.DECORATION_CAPACITY - 1, aquarium.getNumDecoration());

        Decoration pineapple = new Decoration("Pineapple", "A large pineapple shell", "Oooohhhh");
        assertTrue(aquarium.addDecoration(pineapple));
        assertFalse(aquarium.addDecoration(new Decoration()));
        assertEquals(Aquarium.DECORATION_CAPACITY, aquarium.getNumDecoration());

        assertFalse(aquarium.removeDecoration("Decor"));
        assertTrue(aquarium.removeDecoration("Seashell"));
        assertEquals(Aquarium.DECORATION_CAPACITY - 1, aquarium.getNumDecoration());

        aquarium.clearDecoration();
        assertEquals(0, aquarium.getNumDecoration());
    }

    @Test
    public void testFeedAndCheckOctopus() {
        Octopus squishy = new Octopus("Squishy");
        Octopus orange = new Octopus("Orange");
        aquarium.addOctopus(squishy);
        aquarium.addOctopus(orange);
        assertEquals("Squishy(baby)\n\nGrowth Stage: 1", aquarium.checkOctopus("Squishy"));
        assertEquals("Orange(baby)\n\nGrowth Stage: 1", aquarium.checkOctopus("Orange"));

        assertFalse(aquarium.feedOctopus("Orange", 1));
        assertEquals("Orange(baby)\n\nGrowth Stage: 2", aquarium.checkOctopus("Orange"));

        assertFalse(aquarium.feedOctopus("Orange", Octopus.GROWTH_REQ - 2));
        assertEquals("Orange(baby)\n\nGrowth Stage: 5", aquarium.checkOctopus("Orange"));

        assertTrue(aquarium.feedOctopus("Orange", 1));
        assertEquals("Orange\n\nSize: 1", aquarium.checkOctopus("Orange"));

        assertFalse(aquarium.feedOctopus("Orange", 1));
        assertEquals("Orange\n\nSize: 2", aquarium.checkOctopus("Orange"));

        assertFalse(aquarium.feedOctopus("Orange", Octopus.MAX_SIZE - 2));
        assertEquals("Orange\n\nSize: 10(MAX)", aquarium.checkOctopus("Orange"));

        assertFalse(aquarium.feedOctopus("Orange", 10));
        assertEquals("Orange\n\nSize: 10(MAX)", aquarium.checkOctopus("Orange"));
    }

    @Test
    public void testEditAndCheckDecoration() {
        Decoration pineapple = new Decoration("Pineapple", "filler", "filler");
        aquarium.addDecoration(pineapple);
        String details;
        details = "Pineapple\n\nfiller\n\n\033[3m\"filler\"\033[0m";
        assertEquals(details, aquarium.checkDecoration("Pineapple"));

        aquarium.editDecoration("Pineapple", "A large, hollow pineapple", "Oooohh");
        details = "Pineapple\n\n" + "A large, hollow pineapple\n\n" + "\033[3m\"Oooohh\"\033[0m";
        assertEquals(details, aquarium.checkDecoration("Pineapple"));
    }

    @Test
    public void testListOctopus() {
        assertEquals("", aquarium.listOctopus());
        aquarium.addOctopus(new Octopus());
        String list = "- Octopus1(baby)\n";
        assertEquals(list, aquarium.listOctopus());
        for (int i = 2; i <= Aquarium.OCTOPUS_CAPACITY; i++) {
            aquarium.addOctopus(new Octopus());
            list += "- Octopus" + i + "(baby)\n";
        }
        assertEquals(list, aquarium.listOctopus());
        aquarium.feedOctopus("Octopus1", Octopus.GROWTH_REQ + 5);
        list = "- Octopus1\n";
        for (int i = 2; i <= Aquarium.OCTOPUS_CAPACITY; i++) {
            list += "- Octopus" + i + "(baby)\n";
        }
        assertEquals(list, aquarium.listOctopus());
    }

    @Test
    public void testListDecoration() {
        assertEquals("", aquarium.listDecoration());
        aquarium.addDecoration(new Decoration());
        String list = "- Decoration1\n";
        assertEquals(list, aquarium.listDecoration());
        for (int i = 2; i <= Aquarium.DECORATION_CAPACITY; i++) {
            aquarium.addDecoration(new Decoration());
            list += "- Decoration" + i + "\n";
        }
        assertEquals(list, aquarium.listDecoration());
    }
}