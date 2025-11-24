/*
 * The backend connecting the MCO1 logic to the GUI.
 */

public class Game {
    private Map currentMap;
    private int currentLevel = 1;
    private boolean gameRunning = true;

    // constructor
    public Game() {
        loadLevel(currentLevel);
    }

    /*
     * Loads a level based on the level number.
     */
    private void loadLevel(int level) {
        NextLevel.load(level);
        Position start = new Position(NextLevel.startX, NextLevel.startY);
        Chip chip = new Chip(start);
        currentMap = new Map(NextLevel.grid[0].length, NextLevel.grid.length, chip, NextLevel.chipsRequired);
        currentMap.load(NextLevel.grid);

    }

    /*
     * Moves Chip to the target position (GUI will call diz!)
     * 
     * @param targetX the target x-coordinate
     * @param targetY the target y-coordinate
     * @return true if the move was successful, false otherwise
     */
    public boolean moveChip(int targetX, int targetY){
        if(!currentMap.inBounds(targetX, targetY)){
            return false;
        }

        Tile target = currentMap.getTileAt(targetX, targetY);
        Chip chip = currentMap.getChip();

        //checks if the tile is passable
        if(!target.isPassable(chip)){
            return false;
        }

        //moves Chip to a new position
        chip.setPosition(new Position(targetX, targetY));

        //trigger tile effects
        target.onEnter(currentMap, chip);

        return true;
    }

    // public methods for gui !!!

    /*
     * returns to the current map
     */
    public Map getCurrentMap(){
        return currentMap;
    }

    /*
     * returns Chip's character
     */
    public Chip getChip(){
        return currentMap.getChip();
    }

    /*
     * returns no. of chips collected
     */
    public int getChipsCollected(){
        return currentMap.getChipsCollected();
    }

    /*
     * returns no. of chips required
     */
    public int getChipsCollectedRequired(){
        return currentMap.getChipsRequired();
    }

    /*
     * returns current lvl no.
     */
    public int getLevel(){
        return currentLevel;
    }

    /*
     * checks if lvl is completed
     */
    public boolean isLevelComplete(){
        return currentMap.getChipsCollected() >= currentMap.getChipsRequired();
    }

    /*
     * gets tile at a specific position
     */
    public Tile getTileAt(int x, int y){
        return currentMap.getTileAt(x, y);
    }

    /*
     * returns the map's width
     */
    public int getMapWidth(){
        return currentMap.getWidth();

    }

    /*
     * returns map height
     */
    public int getMapHeight(){
        return currentMap.getHeight();
    }

    /*
     * checks if game is still running
     */
    public boolean isGameRunning(){
        return gameRunning;
    }

    /*
     * advances Chip to next lvl
     */
    public void advanceToNextLevel(){
        currentLevel++;
        if(currentLevel <= NextLevel.levelCount()){
            loadLevel(currentLevel);
        }else{
            gameRunning = false;
        }
    }

}

