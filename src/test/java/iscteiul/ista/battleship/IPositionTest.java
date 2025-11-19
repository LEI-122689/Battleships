package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPositionTest {
    @Test
    @DisplayName("IPosition interface can be implemented")
    void testInterfaceImplementation() {
        IPosition dummyPos = new IPosition() {
            @Override public int getRow() { return 0; }
            @Override public int getColumn() { return 0; }
            @Override public boolean equals(Object other) { return false; }
            @Override public boolean isAdjacentTo(IPosition other) { return false; }
            @Override public void occupy() {}
            @Override public void shoot() {}
            @Override public boolean isOccupied() { return false; }
            @Override public boolean isHit() { return false; }
        };

        assertNotNull(dummyPos);
    }
}