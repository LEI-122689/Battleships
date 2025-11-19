/**
 *
 */
package iscteiul.ista.battleship;

public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * @param bearing
     * @param pos
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
