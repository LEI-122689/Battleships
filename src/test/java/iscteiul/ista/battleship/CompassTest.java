package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompassTest {

    @Test
    @DisplayName("Should return correct char for each direction")
    void testGetDirection() {
        assertEquals('n', Compass.NORTH.getDirection());
        assertEquals('s', Compass.SOUTH.getDirection());
        assertEquals('e', Compass.EAST.getDirection());
        assertEquals('o', Compass.WEST.getDirection());
        assertEquals('u', Compass.UNKNOWN.getDirection());
    }

    @Test
    @DisplayName("Should convert chars to Compass enum correctly")
    void testCharToCompass() {

        assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        assertEquals(Compass.EAST, Compass.charToCompass('e'));
        assertEquals(Compass.WEST, Compass.charToCompass('o'));

        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('z'));
    }

}