package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BargeTest {

    @Test
    @DisplayName("Test the constructor")
    void testConstructor() {
        Barge barge = new Barge(Compass.NORTH, new Position(5, 5));

        assertEquals(1, barge.getPositions().size());
        assertEquals(5, barge.getPosition().getRow());
        assertEquals(5, barge.getPosition().getColumn());
    }


    @Test
    @DisplayName("A Barge should have a size of 1")
    void testSize() {
        Barge barge = new Barge(Compass.NORTH, new Position(5, 5));

        assertEquals(1, barge.getSize());
    }

}