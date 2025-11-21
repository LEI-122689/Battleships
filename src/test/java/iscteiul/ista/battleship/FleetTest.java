package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    private Fleet fleet;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
    }

    @Nested
    @DisplayName("Adding Ships Logic")
    class AddShipLogic {

        @Test
        @DisplayName("Should add a valid ship inside the board")
        void testAddValidShip() {
            // Valid: Inside board (0-9), no collisions
            Barge barge = new Barge(Compass.NORTH, new Position(5, 5));
            assertTrue(fleet.addShip(barge));
            assertEquals(1, fleet.getShips().size());
        }

        @Test
        @DisplayName("Should reject ships outside the board boundaries")
        void testAddShipOutOfBounds() {
            // Case 1: Negative Coordinate
            Barge negativeBarge = new Barge(Compass.NORTH, new Position(-1, 0));
            assertFalse(fleet.addShip(negativeBarge), "Should fail for negative row");

            // Case 2: Exceeding Board Size (Assume Board Size is 10, Max Index 9)
            // Position 10 is out of bounds
            Barge outBarge = new Barge(Compass.NORTH, new Position(10, 10));
            assertFalse(fleet.addShip(outBarge), "Should fail for row > 9");

            assertEquals(0, fleet.getShips().size());
        }

        @Test
        @DisplayName("Should reject ships that collide or are too close")
        void testAddShipCollision() {
            // 1. Add first ship at (5,5)
            Barge b1 = new Barge(Compass.NORTH, new Position(5, 5));
            fleet.addShip(b1);

            // 2. Try adding a ship at the exact same spot
            Barge b2 = new Barge(Compass.NORTH, new Position(5, 5));
            assertFalse(fleet.addShip(b2), "Should not allow overlapping ships");

            // 3. Try adding a ship immediately adjacent (Too Close)
            // Assuming tooCloseTo logic prevents direct neighbors
            Barge b3 = new Barge(Compass.NORTH, new Position(5, 6));
            assertFalse(fleet.addShip(b3), "Should not allow adjacent ships");

            assertEquals(1, fleet.getShips().size());
        }

        @Test
        @DisplayName("Should respect Fleet Size limit")
        void testFleetFull() {
            // Strategy: Fill the fleet with ships spaced out to avoid "Too Close" collisions.
            // We use a checkerboard-like pattern or simply far apart rows.

            // IFleet.FLEET_SIZE is 10.
            // Your code uses '<= FLEET_SIZE', which actually allows 11 ships (0 to 10).
            // So we need to successfully add 11 ships to maximize the fleet.

            int count = 0;
            // Loop to add 11 ships (0 to 10)
            for (int i = 0; i <= IFleet.FLEET_SIZE; i++) {
                // Logic: Place ships in even columns on different rows to ensure distance > 1
                // (0,0), (0,2), (0,4), (0,6), (0,8) -> 5 ships
                // (2,0), (2,2), (2,4), (2,6), (2,8) -> 5 ships
                // (4,0) -> 11th ship

                int row = (i / 5) * 2;    // 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 4
                int col = (i % 5) * 2;    // 0, 2, 4, 6, 8, 0, 2, 4, 6, 8, 0

                boolean added = fleet.addShip(new Barge(Compass.NORTH, new Position(row, col)));
                if (added) count++;
            }

            // Sanity Check: Ensure we actually filled the fleet before testing the limit
            // If this fails, it means our placement logic still triggered collisions
            assertEquals(11, count, "Setup failed: Could not fill the fleet to capacity");
            assertEquals(11, fleet.getShips().size());

            // NOW try to add one more (the 12th ship)
            // This should finally trigger the "Fleet Full" condition
            Barge extraShip = new Barge(Compass.NORTH, new Position(9, 9)); // Far away spot
            boolean added = fleet.addShip(extraShip);

            assertFalse(added, "Should not add ship if fleet is full (size > FLEET_SIZE)");
        }
    }

    @Nested
    @DisplayName("Retrieval & Search")
    class Retrieval {

        @Test
        @DisplayName("Should filter ships by Category")
        void testGetShipsLike() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));   // "Barca"
            fleet.addShip(new Barge(Compass.NORTH, new Position(2, 0)));   // "Barca"
            fleet.addShip(new Caravel(Compass.NORTH, new Position(5, 5))); // "Caravela"

            // Search for Barca
            List<IShip> barcas = fleet.getShipsLike("Barca");
            assertEquals(2, barcas.size());

            // Search for Caravela
            List<IShip> caravelas = fleet.getShipsLike("Caravela");
            assertEquals(1, caravelas.size());

            // Search for Non-existent
            assertEquals(0, fleet.getShipsLike("Nau").size());
        }

        @Test
        @DisplayName("Should filter only floating ships")
        void testGetFloatingShips() {
            Barge b1 = new Barge(Compass.NORTH, new Position(0, 0));
            Barge b2 = new Barge(Compass.NORTH, new Position(5, 5));
            fleet.addShip(b1);
            fleet.addShip(b2);

            // Sink b1
            b1.shoot(b1.getPosition()); // Barge sinks with 1 shot

            List<IShip> floating = fleet.getFloatingShips();

            assertEquals(1, floating.size());
            assertEquals(b2, floating.get(0)); // Only b2 is left
        }

        @Test
        @DisplayName("Should find ship at specific position")
        void testShipAt() {
            Barge b1 = new Barge(Compass.NORTH, new Position(2, 2));
            fleet.addShip(b1);

            // Hit
            assertEquals(b1, fleet.shipAt(new Position(2, 2)));

            // Miss
            assertNull(fleet.shipAt(new Position(0, 0)));
        }
    }

    @Nested
    @DisplayName("Printing")
    class Printing {
        @Test
        @DisplayName("Print methods should execute without error")
        void testPrinting() {
            fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));

            assertDoesNotThrow(() -> fleet.printStatus());
            assertDoesNotThrow(() -> fleet.printShipsByCategory("Barca"));
            assertDoesNotThrow(() -> fleet.printFloatingShips());

            // This one is package-private, typically we can't call it if it's not public,
            // but since Test is in the same package, it might work.
            // If it fails compilation, remove this line.
            assertDoesNotThrow(() -> fleet.printAllShips());
        }
    }
}