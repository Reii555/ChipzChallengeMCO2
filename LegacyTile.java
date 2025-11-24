/**
 * Represents a single tile on the game map.
 * Each tile has a specific type (e.g., floor, wall, water) and may contain an item.
 * Tiles determine whether the player (Chip) can move into them and may trigger special actions.
 */
public class LegacyTile {
    private TileType type; // The type of tile (e.g., FLOOR, WALL, WATER, FIRE, EXIT)
    private ItemType item; // The item on this tile, if any
    private boolean doorOpened; // Tracks if a door tile has been opened

    /**
     * Creates a tile with the specified type and no item.
     *
     * @param type the type of this tile
     */
    public Tile(TileType type) {
        this.type = type;
        this.item = ItemType.NONE;
        this.doorOpened = false;
    }

    /**
     * Creates a tile with both a specific type and an item.
     *
     * @param type the type of this tile
     * @param item the item present on this tile
     */
    public Tile(TileType type, ItemType item) {
        this.type = type;
        this.item = (item == null ? ItemType.NONE : item);
        this.doorOpened = false;
    }

    /**
     * Returns the display symbol of this tile on the map.
     * If an item is present, it shows the item's symbol instead.
     *
     * @return the character symbol representing this tile
     */
    public char symbol() {
        if (item != ItemType.NONE) 
            return item.symbol; // show item if present
        if (type == TileType.DOOR_RED || type == TileType.DOOR_BLUE) 
            return doorOpened ? '/' : type.symbol;
        return type.symbol;
    }
    
    /**
     * Determines if the tile can be entered by the player (Chip).
     * Takes into account special abilities like swimming or walking on fire.
     *
     * @param chip the player attempting to enter
     * @return true if the tile can be entered, false if blocked
     */
    public boolean isPassable(Chip chip) {
        Inventory inv = chip.getInventory();
        switch (type) {
            case WALL:      return false;
            case WATER:     return inv.canSwim();
            case FIRE:      return inv.canWalkOnFire();
            case DOOR_RED:  return doorOpened || inv.hasRedKey();
            case DOOR_BLUE: return doorOpened || inv.hasBlueKey();
            default:        return true; // floor, exit, force floors, item-floor
        }
    }

    /**
     * Provides a message explaining why the player cannot enter this tile.
     * Called when movement is blocked by walls, doors, or missing equipment.
     *
     * @return a descriptive message indicating why movement is blocked
     */
    public String blockMessage() {
        switch (type) {
            case WALL:       return "A wall blocks your path.";
            case WATER:      return "Water! You need flippers (w).";
            case FIRE:       return "Fire! You need fire boots (f).";
            case DOOR_RED:   return "Red door locked. Need a red key (r).";
            case DOOR_BLUE:  return "Blue door locked. Need a blue key (b).";
            case EXIT:       return "You can’t pass! Get all microchips first!";
            default:         return "You can't move there.";
        }
    }
    
    /**
     * Defines actions that occur when the player steps onto this tile.
     * May involve collecting items, unlocking doors, or finishing a level.
     *
     * @param map  the current game map
     * @param chip the player character stepping onto this tile
     */
    public void onEnter(Map map, Chip chip) {
        Inventory inv = chip.getInventory();

        // 1) Pick up item if present (keys/boots/chip)
        if (item != ItemType.NONE) {
            if (item == ItemType.MICROCHIP){
                inv.addMicrochip();
                map.incrementChipsCollected();
            } else if (item == ItemType.RED_KEY){
                inv.addRedKey();
            } else if (item == ItemType.BLUE_KEY){
                inv.addBlueKey();
            } else if (item == ItemType.FLIPPERS){
                inv.equipFlippers();
            } else if (item == ItemType.FIRE_BOOTS){
                inv.equipFireBoots();
            }
            item = ItemType.NONE; // remove from ground, convert to floor-ish
        }

        // 2) Doors consume key and open 
        if (type == TileType.DOOR_RED) {
            if (!doorOpened && inv.hasRedKey()){ 
                inv.useRedKey(); 
                doorOpened = true; 
                type = TileType.FLOOR; 
            }
        } else if (type == TileType.DOOR_BLUE){
            if (!doorOpened && inv.hasBlueKey()){ 
                inv.useBlueKey(); 
                doorOpened = true; 
                type = TileType.FLOOR; 
            }
        }

        // 3) Exit: ask Map to advance only if chip goal met
        if (type == TileType.EXIT) {
            if (map.getChipsCollected() >= map.getChipsRequired()) {
                map.requestNextLevel();
            } else {
                System.out.println("Exit locked — collect all microchips first!");
            }
        }

        // 4) Force floors: tell Map to push once; Map handles chaining
        if (type == TileType.FORCE_UP)         
            map.requestForce(0, -1);
        else if (type == TileType.FORCE_DOWN)  
            map.requestForce(0, 1);
        else if (type == TileType.FORCE_LEFT)  
            map.requestForce(-1, 0);
        else if (type == TileType.FORCE_RIGHT) 
            map.requestForce(1, 0);
    }

    /**
     * Converts a character symbol into its corresponding Tile.
     * Used when loading map data from a character grid.
     *
     * @param c the character symbol
     * @return the corresponding Tile
     */
    public static Tile fromChar(char c) {
        if (ItemType.isItemChar(c)) return new Tile(TileType.FLOOR, ItemType.fromSymbol(c));
        switch (c) {
            case '#': return new Tile(TileType.WALL);
            case 'W': return new Tile(TileType.WATER);
            case 'F': return new Tile(TileType.FIRE);
            case 'E': return new Tile(TileType.EXIT);
            case 'R': return new Tile(TileType.DOOR_RED);
            case 'B': return new Tile(TileType.DOOR_BLUE);
            case '^': return new Tile(TileType.FORCE_UP);
            case 'v': return new Tile(TileType.FORCE_DOWN);
            case '<': return new Tile(TileType.FORCE_LEFT);
            case '>': return new Tile(TileType.FORCE_RIGHT);
            default:  return new Tile(TileType.FLOOR);
        }
    }
}
