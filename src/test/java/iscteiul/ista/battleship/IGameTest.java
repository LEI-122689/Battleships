package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IGameTest {
    @Test
    @DisplayName("IGame interface can be implemented")
    void testInterfaceImplementation() {
        IGame dummyGame = new IGame() {
            @Override
            public IShip fire(IPosition pos) { return null; }

            @Override
            public List<IPosition> getShots() { return null; }

            @Override
            public int getRepeatedShots() { return 0; }

            @Override
            public int getInvalidShots() { return 0; }

            @Override
            public int getHits() { return 0; }

            @Override
            public int getSunkShips() { return 0; }

            @Override
            public int getRemainingShips() { return 0; }

            @Override
            public void printValidShots() {}

            @Override
            public void printFleet() {}
        };

        assertNotNull(dummyGame);
    }
}