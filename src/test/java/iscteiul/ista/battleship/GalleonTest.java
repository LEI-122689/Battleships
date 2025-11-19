package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GalleonTest {

    @Test
    @DisplayName("Test the constructor")
    void testConstructor() {
        Galleon galleon = new Galleon(Compass.NORTH, new Position(5 ,5));

        assertEquals(5, galleon.getPositions().size());
        assertEquals(5, galleon.getPositions().get(0).getRow());
        assertEquals(5, galleon.getPositions().get(0).getColumn());
    }

    @Test
    @DisplayName("Galleon NORTH should form a T shape (Width 3)")
    void testGalleonNorth() {
        // Logic: Loop adds (5,5), (5,6), (5,7). Then explicit adds (6,6) and (7,6).
        IPosition startPos = new Position(5, 5);
        Galleon galleon = new Galleon(Compass.NORTH, startPos);

        assertEquals(5, galleon.getPositions().size());

        // 1. The Horizontal Top Bar (i=0, 1, 2)
        assertEquals(5, galleon.getPositions().get(0).getRow());
        assertEquals(5, galleon.getPositions().get(0).getColumn()); // (5, 5)

        assertEquals(5, galleon.getPositions().get(1).getRow());
        assertEquals(6, galleon.getPositions().get(1).getColumn()); // (5, 6)

        assertEquals(5, galleon.getPositions().get(2).getRow());
        assertEquals(7, galleon.getPositions().get(2).getColumn()); // (5, 7)

        // 2. The Vertical Stem (Explicit adds)
        assertEquals(6, galleon.getPositions().get(3).getRow());
        assertEquals(6, galleon.getPositions().get(3).getColumn()); // (6, 6)

        assertEquals(7, galleon.getPositions().get(4).getRow());
        assertEquals(6, galleon.getPositions().get(4).getColumn()); // (7, 6)
    }

    @Test
    @DisplayName("Galleon SOUTH should form an inverted T (Anchor)")
    void testGalleonSouth() {
        // Logic: Loop 1 adds (5,5), (6,5). Loop 2 adds (7,4), (7,5), (7,6).
        IPosition startPos = new Position(5, 5);
        Galleon galleon = new Galleon(Compass.SOUTH, startPos);

        assertEquals(5, galleon.getPositions().size());

        // Loop 1: Vertical top part
        assertEquals(5, galleon.getPositions().get(0).getRow());
        assertEquals(5, galleon.getPositions().get(0).getColumn()); // (5, 5)

        assertEquals(6, galleon.getPositions().get(1).getRow());
        assertEquals(5, galleon.getPositions().get(1).getColumn()); // (6, 5)

        // Loop 2: Horizontal bottom bar (j starts at 2, subtracts 3)
        // j=2 -> col + 2 - 3 = col - 1
        assertEquals(7, galleon.getPositions().get(2).getRow());
        assertEquals(4, galleon.getPositions().get(2).getColumn()); // (7, 4)

        // j=3 -> col + 3 - 3 = col
        assertEquals(7, galleon.getPositions().get(3).getRow());
        assertEquals(5, galleon.getPositions().get(3).getColumn()); // (7, 5)

        // j=4 -> col + 4 - 3 = col + 1
        assertEquals(7, galleon.getPositions().get(4).getRow());
        assertEquals(6, galleon.getPositions().get(4).getColumn()); // (7, 6)
    }

    @Test
    @DisplayName("Galleon WEST should form a C-Bracket shape")
    void testGalleonWest() {
        // Logic: Add (5,5). Loop adds (6,5), (6,6), (6,7). Add (7,5).
        IPosition startPos = new Position(5, 5);
        Galleon galleon = new Galleon(Compass.WEST, startPos);

        assertEquals(5, galleon.getPositions().size());

        // Explicit Add 1
        assertEquals(5, galleon.getPositions().get(0).getRow());
        assertEquals(5, galleon.getPositions().get(0).getColumn()); // (5, 5)

        // Loop (i=1 to 3): Row+1, Col+i-1
        // i=1 -> 5 + 1 - 1 = 5
        assertEquals(6, galleon.getPositions().get(1).getRow());
        assertEquals(5, galleon.getPositions().get(1).getColumn()); // (6, 5)

        // i=2 -> 5 + 2 - 1 = 6
        assertEquals(6, galleon.getPositions().get(2).getRow());
        assertEquals(6, galleon.getPositions().get(2).getColumn()); // (6, 6)

        // i=3 -> 5 + 3 - 1 = 7
        assertEquals(6, galleon.getPositions().get(3).getRow());
        assertEquals(7, galleon.getPositions().get(3).getColumn()); // (6, 7)

        // Explicit Add 2
        assertEquals(7, galleon.getPositions().get(4).getRow());
        assertEquals(5, galleon.getPositions().get(4).getColumn()); // (7, 5)
    }

    @Test
    @DisplayName("Galleon EAST should form a Left-Pointing shape")
    void testGalleonEast() {
        // Logic: Add (5,5). Loop adds (6,3), (6,4), (6,5). Add (7,5).
        IPosition startPos = new Position(5, 5);
        Galleon galleon = new Galleon(Compass.EAST, startPos);

        assertEquals(5, galleon.getPositions().size());

        // Explicit Add 1
        assertEquals(5, galleon.getPositions().get(0).getRow());
        assertEquals(5, galleon.getPositions().get(0).getColumn()); // (5, 5)

        // Loop (i=1 to 3): Row+1, Col+i-3
        // i=1 -> 5 + 1 - 3 = 3
        assertEquals(6, galleon.getPositions().get(1).getRow());
        assertEquals(3, galleon.getPositions().get(1).getColumn()); // (6, 3)

        // i=2 -> 5 + 2 - 3 = 4
        assertEquals(6, galleon.getPositions().get(2).getRow());
        assertEquals(4, galleon.getPositions().get(2).getColumn()); // (6, 4)

        // i=3 -> 5 + 3 - 3 = 5
        assertEquals(6, galleon.getPositions().get(3).getRow());
        assertEquals(5, galleon.getPositions().get(3).getColumn()); // (6, 5)

        // Explicit Add 2
        assertEquals(7, galleon.getPositions().get(4).getRow());
        assertEquals(5, galleon.getPositions().get(4).getColumn()); // (7, 5)
    }

    @Test
    @DisplayName("Galleon should have a size of 5")
    void testSize() {
        Galleon galleon = new Galleon(Compass.NORTH, new Position(5, 5));

        assertEquals(5, galleon.getSize());
    }

}