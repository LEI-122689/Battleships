package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface defining the contract for a Battleship game logic manager.
 * Handles shooting mechanics, score tracking, and board visualization.
 */
public interface IGame {

    /**
     * Fires a shot at a specific position on the board.
     * Validates the shot and updates game state.
     *
     * @param pos The position to target.
     * @return The {@link IShip} if it was sunk by this shot, otherwise null.
     */
    IShip fire(IPosition pos);

    /**
     * Retrieves the history of valid shots fired.
     *
     * @return A list of positions representing valid shots.
     */
    List<IPosition> getShots();

    /**
     * Gets the count of shots fired at positions that were already hit.
     *
     * @return Number of repeated shots.
     */
    int getRepeatedShots();

    /**
     * Gets the count of shots fired outside the valid board area.
     *
     * @return Number of invalid shots.
     */
    int getInvalidShots();

    /**
     * Gets the count of shots that successfully hit a ship.
     *
     * @return Number of hits.
     */
    int getHits();

    /**
     * Gets the count of ships that have been completely destroyed.
     *
     * @return Number of sunk ships.
     */
    int getSunkShips();

    /**
     * Gets the count of ships remaining alive in the fleet.
     *
     * @return Number of remaining ships.
     */
    int getRemainingShips();

    /**
     * Prints the board showing where valid shots have landed.
     */
    void printValidShots();

    /**
     * Prints the board showing the locations of the fleet's ships.
     */
    void printFleet();
}