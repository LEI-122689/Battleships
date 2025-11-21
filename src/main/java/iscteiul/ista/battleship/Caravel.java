package iscteiul.ista.battleship;

/**
 * Represents a Caravel (Caravela), which is a ship of size 2.
 * It extends in a specific direction based on its bearing.
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Constructs a new Caravel with a specific bearing and starting position.
     * Validates the bearing and calculates the occupied positions based on orientation.
     *
     * @param bearing the bearing where the Caravel heads to
     * @param pos     initial point for positioning the Caravel
     * @throws NullPointerException     if the bearing is null
     * @throws IllegalArgumentException if the bearing is not a valid direction
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}