package iscteiul.ista.battleship;

/**
 * Represents a Barge (Barca), which is a ship of size 1.
 * It occupies a single position on the board.
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Constructs a new Barge with a specific bearing and position.
     * Since the Barge is size 1, it occupies only the start position.
     *
     * @param bearing - barge bearing (orientation)
     * @param pos     - upper left position of the barge (starting point)
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Gets the fixed size of the Barge.
     *
     * @return The size of the ship (1).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}