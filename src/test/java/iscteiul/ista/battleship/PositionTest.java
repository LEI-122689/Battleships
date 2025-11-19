package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test
    @DisplayName("Constructor should initialize row, col and default flags")
    void testConstructor() {
        Position pos = new Position(3, 7);

        assertEquals(3, pos.getRow());
        assertEquals(7, pos.getColumn());

        assertFalse(pos.isOccupied(), "New position should not be occupied");
        assertFalse(pos.isHit(), "New position should not be hit");
    }

    @Test
    @DisplayName("occupy() and shoot() should update state flags")
    void testStateChanges() {
        Position pos = new Position(1, 1);

        pos.occupy();
        assertTrue(pos.isOccupied(), "Position should be occupied after call");

        pos.shoot();
        assertTrue(pos.isHit(), "Position should be hit after call");
    }

    @Test
    @DisplayName("equals() should compare based on Row and Column only")
    void testEquality() {
        Position p1 = new Position(5, 5);
        Position p2 = new Position(5, 5);
        Position p3 = new Position(5, 6);
        Position p4 = new Position(4, 5);

        assertEquals(p1, p1);

        assertEquals(p1, p2);
        assertEquals(p2, p1);

        assertNotEquals(p1, p3);
        assertNotEquals(p1, p4);
        assertNotEquals(p1, "StringObject"); // Different class
        assertNotEquals(p1, null);
    }

    @Test
    @DisplayName("isAdjacentTo() should detect neighbors correctly")
    void testAdjacency() {
        Position center = new Position(5, 5);

        assertTrue(center.isAdjacentTo(new Position(5, 6)), "Should be adjacent to Right");
        assertTrue(center.isAdjacentTo(new Position(5, 4)), "Should be adjacent to Left");
        assertTrue(center.isAdjacentTo(new Position(6, 5)), "Should be adjacent to Bottom");
        assertTrue(center.isAdjacentTo(new Position(4, 5)), "Should be adjacent to Top");

        assertTrue(center.isAdjacentTo(new Position(6, 6)), "Should be adjacent to Bottom-Right");
        assertTrue(center.isAdjacentTo(new Position(4, 4)), "Should be adjacent to Top-Left");

        assertTrue(center.isAdjacentTo(center), "Should be adjacent to itself");

        assertFalse(center.isAdjacentTo(new Position(5, 7)), "Too far Right");
        assertFalse(center.isAdjacentTo(new Position(7, 5)), "Too far Down");
        assertFalse(center.isAdjacentTo(new Position(3, 3)), "Too far Diagonal");
    }

    @Test
    @DisplayName("toString should match specific format")
    void testToString() {
        Position pos = new Position(1, 9);
        String expected = "Linha = 1 Coluna = 9";
        assertEquals(expected, pos.toString());
    }
}