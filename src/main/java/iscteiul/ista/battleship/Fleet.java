package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of ships (a fleet).
 * Manages adding ships, checking collisions, and retrieving fleet status.
 */
public class Fleet implements IFleet {

    /**
     * Helper method to print a list of ships to the console.
     *
     * @param ships The list of ships to print.
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    /**
     * Constructs an empty Fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Retrieves all ships currently in the fleet.
     *
     * @return A list of {@link IShip} objects.
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Attempts to add a ship to the fleet.
     * The ship is added only if:
     * 1. The fleet is not full.
     * 2. The ship fits inside the board boundaries.
     * 3. The ship does not collide with or get too close to existing ships.
     *
     * @param s The ship to add.
     * @return true if the ship was successfully added, false otherwise.
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Retrieves a list of ships matching a specific category name.
     *
     * @param category The category name (e.g., "Barca", "Caravela").
     * @return A list of matching ships.
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Retrieves all ships that are still floating (not fully sunk).
     *
     * @return A list of ships that are still floating.
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Finds the ship occupying a specific position on the board.
     *
     * @param pos The position to query.
     * @return The ship at the given position, or null if the position is empty.
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks if the ship's coordinates are fully within the board boundaries.
     *
     * @param s The ship to check.
     * @return true if the ship is inside the board, false otherwise.
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks if the given ship collides with or is placed too close to any existing ship.
     *
     * @param s The ship to check for collisions.
     * @return true if there is a collision risk, false if the placement is safe.
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * Prints the full status of the fleet to the console.
     * Includes all ships, floating ships, and ships grouped by category.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints all ships of a specific category.
     *
     * @param category The category of ships to print.
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Prints all ships that have not yet been sunk.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints every ship currently in the fleet.
     */
    void printAllShips() {
        printShips(ships);
    }
}