package iscteiul.ista.battleship;

/**
 * Interface representing a position (cell) on the Battleship game board.
 * Defines behavior for coordinates, occupancy, and hit status.
 *
 * @author fba
 */
public interface IPosition {
    /**
     * Gets the row index of this position.
     * @return The row number.
     */
    int getRow();

    /**
     * Gets the column index of this position.
     * @return The column number.
     */
    int getColumn();

    /**
     * Compares this position with another object for equality.
     * @param other The object to compare.
     * @return true if the coordinates match, false otherwise.
     */
    boolean equals(Object other);

    /**
     * Checks if this position is adjacent (neighboring) to another position.
     * Includes diagonals.
     * @param other The position to check against.
     * @return true if adjacent, false otherwise.
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marks the position as occupied by a ship segment.
     */
    void occupy();

    /**
     * Marks the position as hit by a shot.
     */
    void shoot();

    /**
     * Checks if the position is currently occupied by a ship.
     * @return true if occupied.
     */
    boolean isOccupied();

    /**
     * Checks if the position has been hit.
     * @return true if hit.
     */
    boolean isHit();
}