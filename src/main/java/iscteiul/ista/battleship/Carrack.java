package iscteiul.ista.battleship;

/**
 * Represents a Carrack (Nau), which is a ship of size 3.
 * It extends linearly in a specific direction based on its bearing.
 */
public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Constructs a new Carrack with a specific bearing and starting position.
     * Calculates the three positions occupied by the ship based on its direction.
     *
     * @param bearing the orientation/direction of the ship (North, South, East, West)
     * @param pos     the starting position (tail) of the ship
     * @throws IllegalArgumentException if the bearing is invalid or unknown
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}