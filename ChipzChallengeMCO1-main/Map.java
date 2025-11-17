/**
 * Represents the game map and core game logic.
 * Handles rendering, tile management, player movement, and level transitions.
 */
public class Map {
    private static final java.util.Scanner sc = new java.util.Scanner(System.in);
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

    public int getWidth(){ 
        return width; 
    }

    public int getHeight(){ 
        return height; 
    }

    public Chip getChip(){ 
        return chip; 
    }

    /**
     * Increments the total number of collected chips.
     */
    public void incrementChipsCollected(){
        chipsCollected++; 
    }

    /**
     * Retrieves the number of chips collected so far.
     *
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
    
    /**
     * Loads a 2D character grid into the map by converting each character into a Tile.
     *
     * @param chars the 2D grid of characters representing the map layout
     */
    private void loadFromChars(char[][] grid) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                tiles[y][x] = Tile.fromChar(grid[y][x]);
    }

    /**
     * Public method to load a 2D character grid into the map.
     *
     * @param grid the 2D grid of characters representing the map layout
     */
    public void load(char[][] grid) {
        loadFromChars(grid);
    }

    /**
     * Draws the map in the console.
     * Displays tiles and the player’s current position.
     */
    private void draw() {
        System.out.println("CHIPS : " + chipsCollected + "/" + chipsRequired);
        System.out.println(
                  "INVENTORY : Red Key = " + (chip.getInventory().hasRedKey() ? "yes" : "no")
                + "  Blue Key = " + (chip.getInventory().hasBlueKey() ? "yes" : "no")
                + "  Swim = " + (chip.getInventory().canSwim() ? "yes" : "no")
                + "  Fire Walk = " + (chip.getInventory().canWalkOnFire() ? "yes" : "no")
                );
        System.out.println("\nUse W/A/S/D to move, Q to quit\n");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == chip.getPosition().getX() && y == chip.getPosition().getY()) 
                    System.out.print("C ");
                else 
                    System.out.print(tiles[y][x].symbol() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Manages the main gameplay loop for a level.
     * Handles user input, movement, tile interaction, and progression.
     *
     * @param map the map being played
     * @return true if the player completes the level, false otherwise
     */
    private boolean playLevel() {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("\nLEGEND:");
            System.out.println("# = Wall            R = Red Door        F = Fire");
            System.out.println("C = YOU!            r = Red Key         f = Fire Boots");
            System.out.println("m = Microchip       B = Blue Door       W = Water");
            System.out.println("E = Exit            b = Blue Key        w = Water Flippers\n");

            draw();

            System.out.print("\n> ");

            if (!sc.hasNextLine()) 
                return false;

            String s = sc.nextLine().trim().toLowerCase();
            if (s.isEmpty()) 
                continue;
            char cmd = s.charAt(0);
            if (cmd == 'q') {
                return false;
            }

            int nx = chip.getPosition().getX();
            int ny = chip.getPosition().getY();
            if (cmd == 'w') 
                ny--; 
            else if (cmd == 's') 
                ny++;
            else if (cmd == 'a') 
                nx--; 
            else if (cmd == 'd') 
                nx++;
            else 
                continue;

            if (!inBounds(nx, ny)) 
                continue;

            Tile target = getTileAt(nx, ny);

            // 1) passability
            if (!target.isPassable(chip)) {
                System.out.println(target.blockMessage());
                
                try { Thread.sleep(260); 
                } catch (InterruptedException ignored) {}
                
                continue;
            }

            // 2) move into tile
            chip.setPosition(new Position(nx, ny));

            // 3) trigger tile effects (items, doors, exit, force)
            target.onEnter(this, chip);

            // 4) apply force floor chain
            while (hasForce()) {
                int tx = chip.getPosition().getX() + takeForceDx();
                int ty = chip.getPosition().getY() + takeForceDy();
                if (!inBounds(tx, ty)) 
                    break;

                Tile t2 = getTileAt(tx, ty);
                if (!t2.isPassable(chip)) 
                    break;

                chip.setPosition(new Position(tx, ty));
                t2.onEnter(this, chip);
            }

            // 5) next level?
            if (takeNextLevelRequest()) {
                System.out.println("Level complete! )");
                try { Thread.sleep(600); 
                } catch (InterruptedException ignored) {}
                return true;
            }
        }
    }

    /**
     * Main entry point for the game.
     * Initializes the first level and handles level progression.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        int currentLevel = 1;

        while (currentLevel <= NextLevel.levelCount()) {
            NextLevel.load(currentLevel);

            Position start = new Position(NextLevel.startX, NextLevel.startY);
            Chip chip = new Chip(start);
            Map map = new Map(NextLevel.grid[0].length, NextLevel.grid.length, chip, NextLevel.chipsRequired);

            map.loadFromChars(NextLevel.grid);

            boolean done = map.playLevel();
            if (!done) { 
                System.out.println("Goodbye! o_O"); 
                return; 
            }

            currentLevel++;
        }

        System.out.println("You win! ^_^)/");
    }
}

