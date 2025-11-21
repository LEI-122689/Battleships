package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Represents a coordinate on the game board.
 * Maintains state for row, column, whether it is occupied by a ship, and if it has been hit.
 */
public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Constructs a new Position with specific coordinates.
     * Initially, the position is neither occupied nor hit.
     *
     * @param row    The row index.
     * @param column The column index.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Gets the row index of this position.
     *
     * @return The row number.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Gets the column index of this position.
     *
     * @return The column number.
     */
    @Override
    public int getColumn() {
        return column;
    }


    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compares this position to another object for equality.
     * Two positions are considered equal if they have the same row and column.
     *
     * @param otherPosition The object to compare.
     * @return true if coordinates match, false otherwise.
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Checks if this position is adjacent (neighboring) to another position.
     * Adjacency includes horizontal, vertical, and diagonal neighbors within 1 unit distance.
     *
     * @param other The other position to check against.
     * @return true if the positions are adjacent, false otherwise.
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marks this position as occupied by a ship.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Marks this position as having been hit by a shot.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Checks if this position is currently occupied by a ship.
     *
     * @return true if occupied, false otherwise.
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Checks if this position has been hit by a shot.
     *
     * @return true if hit, false otherwise.
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Returns a string representation of the position.
     *
     * @return String format "Linha = [row] Coluna = [column]".
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}