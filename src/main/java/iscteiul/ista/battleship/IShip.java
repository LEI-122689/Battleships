package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface defining the behavior of a ship in the Battleship game.
 * A ship occupies multiple positions, has a bearing, and can be hit or sunk.
 */
public interface IShip {
    /**
     * Gets the category/name of the ship (e.g., "Barca", "Nau").
     * @return The category string.
     */
    String getCategory();

    /**
     * Gets the size (number of cells) of the ship.
     * @return The size integer.
     */
    Integer getSize();

    /**
     * Retrieves the list of positions occupied by this ship.
     * @return A list of {@link IPosition} objects.
     */
    List<IPosition> getPositions();

    /**
     * Gets the starting reference position of the ship (tail).
     * @return The reference position.
     */
    IPosition getPosition();

    /**
     * Gets the bearing (orientation) of the ship.
     * @return The {@link Compass} direction.
     */
    Compass getBearing();

    /**
     * Checks if the ship is still floating (not fully sunk).
     * A ship is floating if at least one of its positions has not been hit.
     * @return true if floating, false if sunk.
     */
    boolean stillFloating();

    /**
     * Gets the row index of the topmost part of the ship.
     * @return The minimum row index occupied.
     */
    int getTopMostPos();

    /**
     * Gets the row index of the bottommost part of the ship.
     * @return The maximum row index occupied.
     */
    int getBottomMostPos();

    /**
     * Gets the column index of the leftmost part of the ship.
     * @return The minimum column index occupied.
     */
    int getLeftMostPos();

    /**
     * Gets the column index of the rightmost part of the ship.
     * @return The maximum column index occupied.
     */
    int getRightMostPos();

    /**
     * Checks if the ship occupies a specific position.
     * @param pos The position to check.
     * @return true if the ship is at that position.
     */
    boolean occupies(IPosition pos);

    /**
     * Checks if the ship is too close to another ship (collision or adjacent).
     * @param other The other ship to check against.
     * @return true if they are too close.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Checks if the ship is too close to a specific position.
     * @param pos The position to check.
     * @return true if the ship is adjacent to or on that position.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Registers a shot at a specific position on the ship.
     * Marks the corresponding position as hit.
     * @param pos The position being shot.
     */
    void shoot(IPosition pos);
}