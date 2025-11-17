package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    @DisplayName("Constructor should throw AssertionError for null bearing")
    void testConstructorNullBearing() {

        IPosition startPos = new Position(1, 1);

        assertThrows(AssertionError.class, () -> {
            new Caravel(null, startPos);
        });
    }

    @Test
    @DisplayName("Constructor should throw AssertionError for null Position")
    void testConstructorNullPosition() {
        assertThrows(AssertionError.class, () -> {
            new Caravel(Compass.NORTH, null);
        });
    }

    @Test
    @DisplayName("Should display the category name")
    void testShipCategory() {
        IPosition startPos = new Position(5, 5);
        Ship ship = new Frigate(Compass.NORTH, startPos);

        assertEquals("Fragata", ship.getCategory());
    }

    @Test
    @DisplayName("Should have a Bearing of NORTH")
    void testShipBearing() {
        IPosition startPos = new Position(5, 5);
        Ship ship = new Frigate(Compass.NORTH, startPos);

        assertEquals(Compass.NORTH, ship.getBearing());
    }

    @Test
    @DisplayName("Should have a starting position of 5,5")
    void testShipPosition() {
        IPosition startPos = new Position(5, 5);
        Ship ship = new Frigate(Compass.NORTH, startPos);

        assertEquals(5, ship.getPosition().getRow());
        assertEquals(5, ship.getPosition().getColumn());
    }

}