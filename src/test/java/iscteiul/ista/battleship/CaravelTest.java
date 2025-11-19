package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaravelTest {

    @Test
    @DisplayName("Constructor should create 2 vertical positions for NORTH")
    void testConstructorNorth() {
        IPosition startPos = new Position(5, 5);

        Caravel caravel = new Caravel(Compass.NORTH, startPos);

        assertEquals(2, caravel.getPositions().size());

        assertEquals(5, caravel.getPositions().get(0).getRow());
        assertEquals(5, caravel.getPositions().get(0).getColumn());

        assertEquals(4, caravel.getPositions().get(1).getRow());
        assertEquals(5, caravel.getPositions().get(1).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 2 horizontal positions for East")
    void testConstructorEast() {
        IPosition startPos = new Position(5 ,5);

        Caravel caravel = new Caravel(Compass.EAST, startPos);

        assertEquals(2, caravel.getPositions().size());

        assertEquals(5, caravel.getPositions().get(0).getRow());
        assertEquals(5, caravel.getPositions().get(0).getColumn());

        assertEquals(5, caravel.getPositions().get(1).getRow());
        assertEquals(4, caravel.getPositions().get(1).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 2 vertical positions for SOUTH")
    void testConstructorSouth() {
        IPosition startPos = new Position(5, 5);
        Caravel caravel = new Caravel(Compass.SOUTH, startPos);

        assertEquals(2, caravel.getPositions().size());

        // Position 1 (index 0)
        assertEquals(5, caravel.getPositions().get(0).getRow());
        assertEquals(5, caravel.getPositions().get(0).getColumn());

        // Position 2 (index 1) - Row should be 5 + 1 = 6
        assertEquals(6, caravel.getPositions().get(1).getRow());
        assertEquals(5, caravel.getPositions().get(1).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 2 horizontal positions for WEST")
    void testConstructorWest() {
        IPosition startPos = new Position(5, 5);
        Caravel caravel = new Caravel(Compass.WEST, startPos);

        assertEquals(2, caravel.getPositions().size());

        // Position 1 (index 0)
        assertEquals(5, caravel.getPositions().get(0).getRow());
        assertEquals(5, caravel.getPositions().get(0).getColumn());

        // Position 2 (index 1) - Column should be 5 + 1 = 6
        assertEquals(5, caravel.getPositions().get(1).getRow());
        assertEquals(6, caravel.getPositions().get(1).getColumn());
    }

    @Test
    @DisplayName("A Caravel should have a size of 2")
    void testCaravelSize() {

        IPosition startPos = new Position(0, 0);
        Caravel caravel = new Caravel(Compass.NORTH, startPos);

        Integer size = caravel.getSize();

        assertEquals(2, size);
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException for invalid bearing")
    void testConstructorInvalidBearing() {

        IPosition startPos = new Position(1, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            new Caravel(Compass.UNKNOWN, startPos);
        });
    }
}