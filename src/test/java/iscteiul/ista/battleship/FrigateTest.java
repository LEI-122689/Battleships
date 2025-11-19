package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrigateTest {

    @Test
    @DisplayName("Test the constructor")
    void testConstructor() {
        Frigate frigate = new Frigate(Compass.NORTH, new Position(5 ,5));

        assertEquals(4, frigate.getPositions().size());
        assertEquals(5, frigate.getPositions().get(0).getRow());
        assertEquals(5, frigate.getPositions().get(0).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 4 vertical positions for NORTH")
    void testConstructorNorth() {
        IPosition startPos = new Position(5, 5);
        Frigate frigate = new Frigate(Compass.NORTH, startPos);

        assertEquals(4, frigate.getPositions().size());

        assertEquals(5, frigate.getPositions().get(0).getRow());
        assertEquals(5, frigate.getPositions().get(0).getColumn());

        assertEquals(2, frigate.getPositions().get(3).getRow());
        assertEquals(5, frigate.getPositions().get(3).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 4 vertical positions for SOUTH")
    void testConstructorSouth() {
        IPosition startPos = new Position(5, 5);
        Frigate frigate = new Frigate(Compass.SOUTH, startPos);

        assertEquals(4, frigate.getPositions().size());

        assertEquals(5, frigate.getPositions().get(0).getRow());
        assertEquals(5, frigate.getPositions().get(0).getColumn());

        assertEquals(5, frigate.getPositions().get(3).getColumn());
        assertEquals(8, frigate.getPositions().get(3).getRow());
    }

    @Test
    @DisplayName("Constructor should create 4 horizontal positions for WEST")
    void testConstructorWest() {
        IPosition startPos = new Position(5, 5);
        Frigate frigate = new Frigate(Compass.WEST, startPos);

        assertEquals(4, frigate.getPositions().size());

        assertEquals(5, frigate.getPositions().get(0).getRow());
        assertEquals(5, frigate.getPositions().get(0).getColumn());

        assertEquals(5, frigate.getPositions().get(3).getRow());
        assertEquals(8, frigate.getPositions().get(3).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 4 horizontal positions for EAST")
    void testConstructorEast() {
        IPosition startPos = new Position(5, 5);
        Frigate frigate = new Frigate(Compass.EAST, startPos);

        assertEquals(4, frigate.getPositions().size());

        assertEquals(5, frigate.getPositions().get(0).getRow());
        assertEquals(5, frigate.getPositions().get(0).getColumn());

        assertEquals(5, frigate.getPositions().get(3).getRow());
        assertEquals(2, frigate.getPositions().get(3).getColumn());
    }

    @Test
    @DisplayName("Frigate should have a size of 4")
    void testSize() {
        Frigate frigate = new Frigate(Compass.NORTH, new Position(5 ,5));

        assertEquals(4, frigate.getSize());
    }

}