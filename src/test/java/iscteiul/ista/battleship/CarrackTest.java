package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrackTest {

    @Test
    @DisplayName("Test the constructor")
    void testConstructor() {
        Carrack carrack = new Carrack(Compass.NORTH, new Position(5 ,5));

        assertEquals(3, carrack.getPositions().size());
        assertEquals(5, carrack.getPositions().get(0).getRow());
        assertEquals(5, carrack.getPositions().get(0).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 3 vertical positions for NORTH")
    void testConstructorNorth() {
        IPosition startPos = new Position(5, 5);
        Carrack carrack = new Carrack(Compass.NORTH, startPos);

        assertEquals(3, carrack.getPositions().size());

        assertEquals(5, carrack.getPositions().get(0).getRow());
        assertEquals(5, carrack.getPositions().get(0).getColumn());

        assertEquals(3, carrack.getPositions().get(2).getRow());
        assertEquals(5, carrack.getPositions().get(2).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 3 vertical positions for SOUTH")
    void testConstructorSouth() {
        IPosition startPos = new Position(5, 5);
        Carrack carrack = new Carrack(Compass.SOUTH, startPos);

        assertEquals(3, carrack.getPositions().size());

        assertEquals(5, carrack.getPositions().get(0).getRow());
        assertEquals(5, carrack.getPositions().get(0).getColumn());

        assertEquals(5, carrack.getPositions().get(2).getColumn());
        assertEquals(7, carrack.getPositions().get(2).getRow());
    }

    @Test
    @DisplayName("Constructor should create 3 horizontal positions for WEST")
    void testConstructorWest() {
        IPosition startPos = new Position(5, 5);
        Carrack carrack = new Carrack(Compass.WEST, startPos);

        assertEquals(3, carrack.getPositions().size());

        assertEquals(5, carrack.getPositions().get(0).getRow());
        assertEquals(5, carrack.getPositions().get(0).getColumn());

        assertEquals(5, carrack.getPositions().get(2).getRow());
        assertEquals(7, carrack.getPositions().get(2).getColumn());
    }

    @Test
    @DisplayName("Constructor should create 3 horizontal positions for EAST")
    void testConstructorEast() {
        IPosition startPos = new Position(5, 5);
        Carrack carrack = new Carrack(Compass.EAST, startPos);

        assertEquals(3, carrack.getPositions().size());

        assertEquals(5, carrack.getPositions().get(0).getRow());
        assertEquals(5, carrack.getPositions().get(0).getColumn());

        assertEquals(5, carrack.getPositions().get(2).getRow());
        assertEquals(3, carrack.getPositions().get(2).getColumn());
    }

    @Test
    @DisplayName("Carrack should have a size of 3")
    void testSize() {
        Carrack carrack = new Carrack(Compass.NORTH, new Position(5, 5));

        assertEquals(3, carrack.getSize());
    }

}