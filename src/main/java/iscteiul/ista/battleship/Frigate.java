package iscteiul.ista.battleship;

/**
 * Represents a Frigate (Fragata), which is a linear ship of size 4.
 * It extends in a straight line based on its bearing.
 */
public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Constructs a new Frigate with a specific bearing and starting position.
     * The ship occupies 4 sequential positions in the given direction.
     *
     * @param bearing The orientation/direction of the ship.
     * @param pos     The starting position (tail) of the ship.
     * @throws IllegalArgumentException if the bearing is invalid or unknown.
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
                for (int n = 0; n < SIZE; n++)
                    getPositions().add(new Position(pos.getRow() - n, pos.getColumn()));
                break;
            case SOUTH:
                for (int s = 0; s < SIZE; s++)
                    getPositions().add(new Position(pos.getRow() + s, pos.getColumn()));
                break;
            case EAST:
                for (int e = 0; e < SIZE; e++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() - e));
                break;
            case WEST:
                for (int w = 0; w < SIZE; w++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + w));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}