package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IFleetTest {
    @Test
    @DisplayName("Interface constants should have correct values")
    void testConstants() {
        assertEquals(10, IFleet.BOARD_SIZE, "Board size should be 10");
        assertEquals(10, IFleet.FLEET_SIZE, "Fleet size should be 10");
    }
}