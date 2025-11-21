package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Fleet fleet;
    private IPosition shipPos;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();

        shipPos = new Position(2, 2);
        Barge barge = new Barge(Compass.NORTH, shipPos);
        fleet.addShip(barge);

        game = new Game(fleet);
    }

    @Nested
    @DisplayName("Initialization Checks")
    class Initialization {
        @Test
        @DisplayName("New game should have zero counters")
        void testInitialState() {
            assertEquals(0, game.getInvalidShots());
            assertEquals(0, game.getRepeatedShots());
            assertEquals(0, game.getHits());
            assertEquals(0, game.getSunkShips());
            assertEquals(0, game.getShots().size());
            assertEquals(1, game.getRemainingShips());
        }
    }

    @Nested
    @DisplayName("Shot Validation Logic")
    class ShotValidation {
        @Test
        @DisplayName("Firing out of bounds should count as invalid")
        void testInvalidShot() {
            IPosition negativePos = new Position(-1, -1);
            assertNull(game.fire(negativePos));
            assertEquals(1, game.getInvalidShots());

            IPosition tooLargePos = new Position(Fleet.BOARD_SIZE + 1, Fleet.BOARD_SIZE + 1);
            assertNull(game.fire(tooLargePos));
            assertEquals(2, game.getInvalidShots());
        }

        @Test
        @DisplayName("Firing at the same spot twice should count as repeated")
        void testRepeatedShot() {
            IPosition pos = new Position(0, 0);

            game.fire(pos);
            assertEquals(0, game.getRepeatedShots());

            game.fire(pos);
            assertEquals(1, game.getRepeatedShots());

            assertEquals(1, game.getShots().size());
        }
    }

    @Nested
    @DisplayName("Game Mechanics (Hit/Miss/Sink)")
    class GameMechanics {
        @Test
        @DisplayName("Firing at water should be recorded but not a hit")
        void testMissedShot() {
            IPosition missPos = new Position(0, 0);
            IShip result = game.fire(missPos);

            assertNull(result);
            assertEquals(1, game.getShots().size());
            assertEquals(0, game.getHits());
        }

        @Test
        @DisplayName("Firing at a ship should count as a hit and sink (Size 1)")
        void testHitAndSink() {
            IPosition hitPos = new Position(2, 2);
            IShip sunkShip = game.fire(hitPos);

            assertEquals(1, game.getHits());
            assertEquals(1, game.getSunkShips());
            assertEquals(0, game.getRemainingShips());

            assertNotNull(sunkShip);
            // Ensure this string matches exactly what your Barge returns (Case Sensitive!)
            assertEquals("Barca", sunkShip.getCategory());
        }

        @Test
        @DisplayName("Should allow partial hits on larger ships without sinking")
        void testPartialHit() {
            Fleet largeFleet = new Fleet();
            // Caravel (Size 2) at (5,5) NORTH -> occupies (5,5) and (4,5)
            Caravel caravel = new Caravel(Compass.NORTH, new Position(5, 5));
            largeFleet.addShip(caravel);

            Game largeGame = new Game(largeFleet);

            IShip result = largeGame.fire(new Position(5, 5));

            assertEquals(1, largeGame.getHits());
            assertEquals(0, largeGame.getSunkShips());
            assertNull(result);
        }
    }

    @Nested
    @DisplayName("Console Output")
    class Printing {
        @Test
        @DisplayName("printValidShots should execute without error")
        void testPrintValidShots() {
            game.fire(new Position(0, 0));
            assertDoesNotThrow(() -> game.printValidShots());
        }

        @Test
        @DisplayName("printFleet should execute without error")
        void testPrintFleet() {
            assertDoesNotThrow(() -> game.printFleet());
        }
    }
}