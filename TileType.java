/**
 * TileType - Enumeration representing different types of tiles in the game grid.
 * 
 * Each tile type corresponds to a specific game element with unique properties
 * and behavior that affect player movement and game mechanics.
 */
public enum TileType {
     // Basic terrain tiles
    FLOOR(' '),         // Walkable empty space
    WALL('#'),          // Impassable barrier

    // Hazard tiles that damage or affect the player
    WATER('W'),
    FIRE('F'),

     // Goal and objective tiles
    EXIT('E'),

    // Door tiles
    DOOR_RED('R'),
    DOOR_BLUE('B'),

    // Force floor tiles - push the player in specific directions
    FORCE_UP('^'),
    FORCE_DOWN('v'),
    FORCE_LEFT('<'),
    FORCE_RIGHT('>');

    /**
     * The character symbol used to represent this tile type in level data files.
     * This symbol appears in the string arrays used to define level layouts.
     */
    public final char symbol;
    TileType(char s) { this.symbol = s; }

    /**
     * Converts a character symbol to its corresponding TileType.
     * This is used when parsing level data from string representations.
     * 
     * @param c - The character symbol to convert
     * @return TileType - The corresponding tile type, or FLOOR if no match found
     */
    public static TileType fromSymbol(char c) {
        // Iterate through all enum values to find a matching symbol
        for (TileType t : values()) if (t.symbol == c) return t;
        // Return FLOOR as default for any unrecognized characters
        return FLOOR;
    }
}

