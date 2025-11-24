/**
 * Represents the game map and core game logic.
 * Handles tile management, player movement, and level progression for GUI !
 */
public class Map {

    private final int width, height; // Width and Height of the map
    private final Tile[][] tiles; // 2D grid representing map tiles
    private final Chip chip; // The player character (Chip)
    
    private final int chipsRequired; // Total number of chips required to unlock the exit
    private int chipsCollected; // Number of chips collected so far

    private boolean nextLevelRequested = false; // Flag for transitioning to the next level
    private int forceDx = 0, // Forced movement in the x-direction (e.g., by force tiles) 
                forceDy = 0; // Forced movement in the y-direction (e.g., by force tiles)

    /**
     * Constructs a new game map with the given dimensions, player, and chip requirements.
     *
     * @param width the width of the map
     * @param height the height of the map
     * @param chip the player character on the map
     * @param chipsRequired the number of chips needed to unlock the exit
     */
    public Map(int width, int height, Chip chip, int chipsRequired) {
        this.width = width; this.height = height;
        this.chip = chip; this.chipsRequired = chipsRequired;
        this.chipsCollected = 0;
        this.tiles = new Tile[height][width];
    }

    // public getters for gui to call !

    /*
    * retrieves the width of the map.
    * @return the width of the map
    */
    public int getWidth(){ 
        return width; 
    }

    /*
    * retrieves the height of the map.
    * @return the height of the map
    */
    public int getHeight(){ 
        return height; 
    }

    /*
    * retrieves the player character (Chip) on the map.
    * @return the Chip instance
    */
    public Chip getChip(){ 
        return chip; 
    }

    /**
     * Retrieves the number of chips collected so far.
     * @return the number of chips collected
     */
    public int getChipsCollected(){
        return chipsCollected; 
    }

    /**
     * Retrieves the total number of chips required to open the exit.
     *
     * @return the number of required chips
     */
    public int getChipsRequired(){ 
        return chipsRequired;  
    }

    /*
    * Attempts to move Chip to the target coordinates.
    * @param targetX the target x-coordinate
    * @param targetY the target y-coordinate
    * @return true if the move was successful, false otherwise
    */
    public boolean moveChip(int targetX, int targetY){
        if(!inBounds(targetX, targetY)){
            return false; 
        }

        Tile target = getTileAt(targetX, targetY);

        // 1) passability
        if (!target.isPassable(chip)) {
            return false;
        }

        // 2) move into tile
        chip.setPosition(new Position(targetX, targetY));

        // 3) trigger tile effects (items, doors, exit, force)
        target.onEnter(this, chip);

        //handleForceMovement(); suggested by deepseek?

        return true;
    }

    /**
     * Checks if the level is completed based on chips collected.
     *
     * @return true if the level is completed, false otherwise
     */
    public boolean isLevelCompleted(){
        return chipsCollected >= chipsRequired;
    }

    /*
    * Checks if a position is passable for Chip
    * @param pos the position to check
    * @param c the Chip trying to pass
    * @return true if the position is passable, false otherwise
    */
   public boolean isPositionPassable(Position pos, Chip c){
        if(!isValidPosition(pos)){
            return false; 
        }

        Tile tile = getTileAt(pos.getX(), pos.getY());
        if(tile == null){
            return false; 
        } else {
            return tile.isPassable(c);
        }
    }

    /*
    * Validates if a position is within the map boundaries.
    * @param pos the position to validate
    * @return true if the position is valid, false otherwise
    */
    public boolean isValidPosition(Position pos){
        return inBounds(pos.getX(), pos.getY());
    }

    /**
     * Increments the total number of collected chips.
     */
    public void incrementChipsCollected(){
        chipsCollected++; 
    }


    /**
     * Sets a tile at the specified position on the map.
     *
     * @param pos the position of the tile
     * @param t the tile to be placed
     */
    public void setTile(Position pos, Tile t){
        tiles[pos.getY()][pos.getX()] = t;
    }

    /**
     * Requests to proceed to the next level after completion.
     */
    public void requestNextLevel(){ 
        nextLevelRequested = true;
    }
    
    /**
     * Takes the current next-level request and resets it.
     *
     * @return true if the next level was requested, false otherwise
     */
    private boolean takeNextLevelRequest() {
        boolean v = nextLevelRequested; 
        nextLevelRequested = false; 
        return v;
    }

    /**
     * Requests forced movement (used by force tiles).
     *
     * @param dx change in x direction
     * @param dy change in y direction
     */
    public void requestForce(int dx, int dy){ 
        forceDx = dx; forceDy = dy; 
    }

    /**
     * Checks if forced movement is currently active.
     *
     * @return true if force movement exists, false otherwise
     */
    private boolean hasForce(){ 
        return forceDx != 0 || forceDy != 0; 
    }

    /**
     * Consumes and returns the forced movement in the x-direction.
     *
     * @return the forced x movement
     */
    private int takeForceDx(){ 
        int d = forceDx; 
        forceDx = 0; 
        return d;
    }
    
    /**
     * Consumes and returns the forced movement in the y-direction.
     *
     * @return the forced y movement
     */
    private int takeForceDy(){ 
        int d = forceDy; 
        forceDy = 0; 
        return d;
    }

    /**
     * Checks if the specified coordinates are within the map boundaries.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the coordinates are within bounds, false otherwise
     */
    public boolean inBounds(int x, int y){ 
        return x>=0 && y>=0 && x<width && y<height; 
    }
    
    /**
     * Retrieves the tile at a specific coordinate.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the tile at that position, or null if out of bounds
     */  
    public Tile getTileAt(int x, int y){ 
        if(!inBounds(x, y)){
            return null;
        }
        return tiles[y][x]; 
    }

}

