/**
 * The NextLevel class handles the logic for progressing to the next level
 * in the game. It checks whether the player has met the required conditions
 * (e.g., collecting a specific number of items or completing the objective)
 * before loading the next map or ending the game.
 *
 * This class is responsible for managing level transitions, maintaining
 * player progression, and initializing the new map for the next level.
 */
public class NextLevel {
    public static char[][] grid; // 2D character grid representing the loaded level
    public static int startX, startY; // Starting column (x) and row (y) for Chip
    public static int chipsRequired; //Number of microchips required to complete the level

    /**
     * Returns the total number of available levels.
     * @return int - Total number of levels (currently 2)
     */
    public static int levelCount() { 
        return 2; 
    }

    /**
     * Loads the specified level by initializing grid, start position, and chip requirement.
     * @param level - The level number to load (1 or 2)
     */
    public static void load(int level) {
        if (level == 2) level2();
        else level1();
    }

    /**
     * Initializes level 1 with predefined grid layout and game parameters.
     * Grid contains walls (#), chips (b/B), monsters (m), water (W/w), fire (F/f),
     * ice (r/R), force floors (^), and exit (E).
     */
    private static void level1() {
        String[] rows = {
            "##########",  
            "#b  B   m#",
            "#W#w## ###",
            "#w# ## #m#",
            "#W#^## #F#",
            "#W#^## # #",
            "#W#^#r # E",
            "#W#^## R #",
            "#m#C## #f#",
            "##########"
        };

        grid = toGrid(rows);
        int[] s = findAndClearStart(grid);
        startX = s[0]; startY = s[1];
        chipsRequired = 3;
    }

    /**
     * Initializes level 2 with predefined grid layout and game parameters.
     * Grid contains different arrangement of obstacles, items, and the exit.
     */
    private static void level2() {
        String[] rows = {
            "##########",
            "#m ##Wbm##",
            "#W ##WWW##",
            "# W#### ##",
            "#W #    ##",
            "#W B    C#",
            "####R##w##",
            "#FFF  #r##",
            "#mFFFf####",
            "##E#######"
        };

        grid = toGrid(rows);
        int[] s = findAndClearStart(grid);
        startX = s[0]; startY = s[1];
        chipsRequired = 3;
    }

    /**
     * Converts an array of strings into a 2D character grid.
     * @param arr - Array of strings representing each row of the grid
     * @return char[][] - 2D character array representing the game grid
     */
    private static char[][] toGrid(String[] arr) {
        int h = arr.length, w = arr[0].length();
        char[][] g = new char[h][w];
        for (int y = 0; y < h; y++) g[y] = arr[y].toCharArray();
        return g;
    }

    /**
     * Finds the player's starting position (marked 'C') in the grid and replaces it with empty space.
     * @param g - The game grid to search
     * @return int[] - Array containing [x, y] coordinates of the start position
     */
    private static int[] findAndClearStart(char[][] g) {
        for (int y = 0; y < g.length; y++)
            for (int x = 0; x < g[0].length; x++)
                if (g[y][x] == 'C') { g[y][x] = '.'; return new int[]{x, y}; }
        return new int[]{1, 1};
    }
}

