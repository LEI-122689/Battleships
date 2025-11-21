package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the core logic of a Battleship game round.
 * Tracks shots fired, hits, sinks, and invalid attempts.
 *
 * @author fba
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;


    /**
     * Initializes a new Game instance with the provided fleet.
     * Resets all counters (invalid, repeated, hits, sinks) to zero.
     *
     * @param fleet The fleet of ships to be used in this game.
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        this.fleet = fleet;

        // Initialize counters
        countInvalidShots = 0;
        countRepeatedShots = 0;
        countHits = 0;
        countSinks = 0;
    }

    /**
     * Fires a shot at the specified position.
     * This method handles the game logic:
     * 1. Validates if the shot is within the board.
     * 2. Checks if the shot is repeated.
     * 3. Checks if the shot hit a ship.
     * 4. Updates the status of the ship (hit/sunk).
     *
     * @param pos The position to fire at.
     * @return The {@link IShip} object if the shot sunk a ship, otherwise null.
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Retrieves the list of all valid shots fired so far.
     *
     * @return A list of positions representing valid shots.
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Gets the total number of shots fired at positions that were already hit.
     *
     * @return The count of repeated shots.
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Gets the total number of shots fired outside the board boundaries.
     *
     * @return The count of invalid shots.
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Gets the total number of successful hits on ships.
     *
     * @return The count of hits.
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Gets the total number of ships that have been completely sunk.
     *
     * @return The count of sunk ships.
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Calculates the number of ships currently remaining (floating) in the fleet.
     *
     * @return The number of ships that have not been sunk.
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Validates if a position is within the game board boundaries.
     *
     * @param pos The position to check.
     * @return true if the position is inside the board (0 to BOARD_SIZE), false otherwise.
     */
    private boolean validShot(IPosition pos) {
        // Note: Using < BOARD_SIZE usually fits 0-indexed arrays better, 
        // but keeping logic consistent with your provided code.
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Checks if a shot has already been fired at the given position.
     *
     * @param pos The position to check.
     * @return true if the position is already in the list of shots, false otherwise.
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Prints a visual representation of the board with specific markers.
     *
     * @param positions The list of positions to mark on the board.
     * @param marker    The character to use as a marker (e.g., 'X' or '#').
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            // Ensure we don't go out of bounds when printing
            if(pos.getRow() < Fleet.BOARD_SIZE && pos.getColumn() < Fleet.BOARD_SIZE) {
                map[pos.getRow()][pos.getColumn()] = marker;
            }

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }


    /**
     * Prints the board showing valid shots that have been fired (marked with 'X').
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }


    /**
     * Prints the board showing the positions of all ships in the fleet (marked with '#').
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}