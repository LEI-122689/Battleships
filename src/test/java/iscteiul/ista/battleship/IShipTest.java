package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IShipTest {
    @Test
    void testInterfaceCanBeImplemented() {
        // Simple sanity check (optional)
        IShip dummyShip = new IShip() {
            @Override public String getCategory() { return "Dummy"; }
            @Override public Integer getSize() { return 0; }
            @Override public java.util.List<IPosition> getPositions() { return null; }
            @Override public IPosition getPosition() { return null; }
            @Override public Compass getBearing() { return null; }
            @Override public boolean stillFloating() { return false; }
            @Override public int getTopMostPos() { return 0; }
            @Override public int getBottomMostPos() { return 0; }
            @Override public int getLeftMostPos() { return 0; }
            @Override public int getRightMostPos() { return 0; }
            @Override public boolean occupies(IPosition pos) { return false; }
            @Override public boolean tooCloseTo(IShip other) { return false; }
            @Override public boolean tooCloseTo(IPosition pos) { return false; }
            @Override public void shoot(IPosition pos) {}
        };

        assertEquals("Dummy", dummyShip.getCategory());
    }
}