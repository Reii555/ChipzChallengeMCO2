/**
 * Enum representing different types of items in the game with their corresponding symbols
 * Provides utility methods for identifying and converting item characters
 */
public enum ItemType {
    
    // Item types with their corresponding display symbols
    NONE(' '),            // Represents no item (empty space)
    MICROCHIP('m'),       // Collectible microchip for level completion
    RED_KEY('r'),         // Key for opening red doors
    BLUE_KEY('b'),        // Key for opening blue doors
    FLIPPERS('w'),        // Equipment for swimming through water
    FIRE_BOOTS('f');      // Equipment for walking on fire

    // The character symbol used to represent this item on the map
    public final char symbol;
    
    /**
     * (Constructor) associates a character symbol with each item type
     * @param s the character symbol to represent this item on the map
     */
    ItemType(char s){ 
        this.symbol = s; 
    }

   /**
     * Checks if the given character represents a valid item
     * Useful when parsing level data to identify item tiles
     * @param c the character to check
     * @return true if the character corresponds to any item type (excluding NONE)
     */
    public static boolean isItemChar(char c) {
        return c=='m' || c=='r' || c=='b' || c=='f' || c=='w';
    }

   /**
     * Converts a character symbol to the corresponding ItemType
     * Used when loading level data to create the appropriate items
     * @param c the character symbol to convert
     * @return the matching ItemType, or NONE if no match found
     */
    public static ItemType fromSymbol(char c) {
        for (ItemType it : values()) 
            if (it.symbol == c) 
                return it;
        return NONE;
    }
}

