package iscteiul.ista.battleship;

/**
 * Represents a Galleon (Galeao), which is a large ship of size 5.
 * Unlike linear ships, the Galleon has a complex, non-linear shape that changes based on its bearing.
 */
public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Constructs a new Galleon with a specific bearing and starting position.
     * The shape of the Galleon is determined by the {@code fill} methods corresponding to the direction.
     *
     * @param bearing The orientation of the Galleon (North, South, East, West).
     * @param pos     The reference position for the ship (pivot point).
     * @throws NullPointerException     if the bearing is null.
     * @throws IllegalArgumentException if the bearing is invalid (e.g., UNKNOWN).
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Calculates positions for a North-facing Galleon (T-shape).
     * @param pos The reference position.
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Calculates positions for a South-facing Galleon (Inverted T/Anchor).
     * @param pos The reference position.
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Calculates positions for an East-facing Galleon.
     * @param pos The reference position.
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Calculates positions for a West-facing Galleon.
     * @param pos The reference position.
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}