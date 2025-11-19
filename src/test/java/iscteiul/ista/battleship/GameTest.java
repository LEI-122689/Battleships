package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private Fleet fleet;
    private IPosition shipPos;

    @BeforeEach
    void setUp() {
        // 1. Create a Fleet
        fleet = new Fleet();

        // 2. Add a Ship (Barge is easiest because it has size 1)
        // We place it at (2, 2) pointing North.
        shipPos = new Position(2, 2);
        Barge barge = new Barge(Compass.NORTH, shipPos);
        fleet.addShip(barge);

        // 3. Create the Game with this fleet
        game = new Game(fleet);
    }

    @Test
    @DisplayName("New game should have zero counters")
    void testInitialState() {
        assertEquals(0, game.getInvalidShots());
        assertEquals(0, game.getRepeatedShots());
        assertEquals(0, game.getHits());
        assertEquals(0, game.getSunkShips());
        assertEquals(0, game.getShots().size());
        // We added 1 ship in setup, so remaining should be 1
        assertEquals(1, game.getRemainingShips());
    }

    @Test
    @DisplayName("Firing out of bounds should count as invalid")
    void testInvalidShot() {
        // Fire at negative coordinates
        IPosition invalidPos = new Position(-1, -1);

        // The return should be null (no ship sunk)
        assertNull(game.fire(invalidPos));

        // Check counters
        assertEquals(1, game.getInvalidShots());
        assertEquals(0, game.getShots().size()); // Invalid shots are NOT added to the list
    }

    @Test
    @DisplayName("Firing at the same spot twice should count as repeated")
    void testRepeatedShot() {
        IPosition pos = new Position(0, 0);

        // First shot (Valid, Miss)
        game.fire(pos);
        assertEquals(0, game.getRepeatedShots());

        // Second shot at same spot
        game.fire(pos);
        assertEquals(1, game.getRepeatedShots());

        // Shot list should still only have 1 recorded shot (the valid one)
        // Note: Your code adds to list BEFORE checking ship, but AFTER checking repeated.
        assertEquals(1, game.getShots().size());
    }

    @Test
    @DisplayName("Firing at water should be recorded but not a hit")
    void testMissedShot() {
        // Our ship is at (2,2), so (0,0) is a miss
        IPosition missPos = new Position(0, 0);

        IShip result = game.fire(missPos);

        assertNull(result); // Didn't sink anything
        assertEquals(1, game.getShots().size());
        assertEquals(0, game.getHits());
    }

    @Test
    @DisplayName("Firing at a ship should count as a hit and sink (for Size 1)")
    void testHitAndSink() {
        // Our Barge is at (2,2)
        IPosition hitPos = new Position(2, 2);

        // Fire!
        // Since Barge is size 1, this ONE shot will Hit AND Sink it.
        IShip sunkShip = game.fire(hitPos);

        // 1. Check Hit Counts
        assertEquals(1, game.getHits());
        assertEquals(1, game.getShots().size());

        // 2. Check Sink Counts
        assertEquals(1, game.getSunkShips());
        assertEquals(0, game.getRemainingShips()); // 1 start - 1 sunk = 0

        // 3. Check Return Value
        // fire() returns the ship object IF it was sunk.
        assertNotNull(sunkShip);
        assertEquals("Barca", sunkShip.getCategory()); // Assuming Barge name is "Barca"
    }

    @Test
    @DisplayName("Should allow partial hits on larger ships without sinking")
    void testPartialHit() {
        // Setup a NEW fleet for this specific test to use a larger ship
        Fleet largeFleet = new Fleet();
        // Caravel is Size 2. Pos (5,5) NORTH -> occupies (5,5) and (4,5) (based on your verified logic)
        Caravel caravel = new Caravel(Compass.NORTH, new Position(5,5));
        largeFleet.addShip(caravel);

        Game largeGame = new Game(largeFleet);

        // Fire at (5,5) - Only 1 part of the ship
        IShip result = largeGame.fire(new Position(5, 5));

        // Assertions
        assertEquals(1, largeGame.getHits());
        assertEquals(0, largeGame.getSunkShips()); // Not sunk yet!
        assertNull(result); // fire() returns null if ship is hit but NOT sunk
    }
}