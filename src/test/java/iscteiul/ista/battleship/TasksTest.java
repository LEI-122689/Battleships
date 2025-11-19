package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TasksTest {

    @Test
    @DisplayName("readPosition() should parse row and column correctly")
    void testReadPosition() {
        // 1. Simulate User Input: "5 7" (Row 5, Col 7)
        String simulatedInput = "5 7";
        Scanner scanner = new Scanner(simulatedInput);

        // 2. Call the static method
        Position pos = Tasks.readPosition(scanner);

        // 3. Assert the result
        assertNotNull(pos);
        assertEquals(5, pos.getRow());
        assertEquals(7, pos.getColumn());
    }

    @Test
    @DisplayName("readShip() should parse ship data correctly")
    void testReadShip() {
        // 1. Simulate Input: "Barca" (Type), "2 3" (Pos), "n" (Compass)
        String simulatedInput = "barca 2 3 n";
        Scanner scanner = new Scanner(simulatedInput);

        Ship ship = Tasks.readShip(scanner);

        assertNotNull(ship);
        assertEquals("Barca", ship.getCategory()); // Or whatever name Barge has
        assertEquals(2, ship.getPosition().getRow());
        assertEquals(3, ship.getPosition().getColumn());
        assertEquals(Compass.NORTH, ship.getBearing());
    }

    @Test
    @DisplayName("firingRound() should fire 3 shots at the game")
    void testFiringRound() {
        Fleet fleet = new Fleet();

        fleet.addShip(new Barge(Compass.NORTH, new Position(0,0)));
        Game game = new Game(fleet);

        // 2. Simulate Input: 3 coordinates for the 3 shots
        // Shot 1: (0,0) -> HIT
        // Shot 2: (5,5) -> MISS
        // Shot 3: (9,9) -> MISS
        String simulatedInput = "0 0 5 5 9 9";
        Scanner scanner = new Scanner(simulatedInput);

        Tasks.firingRound(scanner, game);

        assertEquals(3, game.getShots().size(), "Should have recorded 3 shots");
        assertEquals(1, game.getHits(), "Should have recorded 1 hit");
    }

}