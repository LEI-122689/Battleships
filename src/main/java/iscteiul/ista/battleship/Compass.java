package iscteiul.ista.battleship;

/**
 * Represents the cardinal directions used for ship orientation.
 * Each direction corresponds to a specific character character ('n', 's', 'e', 'o', 'u').
 *
 * @author fba
 */
public enum Compass {
    /** North direction ('n') */
    NORTH('n'),
    /** South direction ('s') */
    SOUTH('s'),
    /** East direction ('e') */
    EAST('e'),
    /** West direction ('o' for Oeste) */
    WEST('o'),
    /** Unknown direction ('u') */
    UNKNOWN('u');

    private final char c;

    /**
     * Constructs a Compass enum constant with the associated character.
     *
     * @param c The character representing the direction.
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Gets the character representation of the direction.
     *
     * @return The character code (e.g., 'n', 's').
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns the string representation of the direction character.
     *
     * @return A string containing the direction character.
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a character to its corresponding Compass enum constant.
     *
     * @param ch The character to convert (n, s, e, o).
     * @return The matching {@link Compass} constant, or {@link #UNKNOWN} if the character is invalid.
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }
        return bearing;
    }
}