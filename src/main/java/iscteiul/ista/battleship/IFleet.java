package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface defining the behavior of a Fleet in the Battleship game.
 * A fleet is a collection of ships that can be managed, queried, and printed.
 */
public interface IFleet {
    /** The dimension of the square game board (10x10). */
    Integer BOARD_SIZE = 10;
    /** The maximum number of ships allowed in the fleet. */
    Integer FLEET_SIZE = 10;

    /**
     * Retrieves the list of all ships in the fleet.
     * @return A list of {@link IShip} objects.
     */
    List<IShip> getShips();

    /**
     * Attempts to add a ship to the fleet.
     * Implementations should check for fleet size limits, board boundaries, and collisions.
     *
     * @param s The ship to add.
     * @return true if added successfully, false otherwise.
     */
    boolean addShip(IShip s);

    /**
     * Retrieves ships matching a specific category name.
     *
     * @param category The category name (e.g., "Barca", "Nau").
     * @return A list of matching ships.
     */
    List<IShip> getShipsLike(String category);

    /**
     * Retrieves all ships that have not yet been sunk.
     *
     * @return A list of floating ships.
     */
    List<IShip> getFloatingShips();

    /**
     * Finds a ship at a specific position on the board.
     *
     * @param pos The position to check.
     * @return The ship at that position, or null if none found.
     */
    IShip shipAt(IPosition pos);

    /**
     * Prints the detailed status of the fleet to the console.
     */
    void printStatus();
}