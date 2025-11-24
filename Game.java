/*
 * The backend connecting the MCO1 logic to the GUI.
 */

public class Game {
    private Map currentMap;
    private int currentLevel = 1;
    private final int TOTAL_LEVELS = 3;
    private boolean gameRunning = true;
    private GameState gameState = GameState.PLAYING;

    public enum GameState{
        PLAYING,
        LEVEL_COMPLETE,
        GAME_COMPLETE,
        QUIT
    }

    // constructor
    public Game() {
        loadLevel(currentLevel);
    }

    /*
     * Loads a level based on the level number.
     */
    private void loadLevel(int level) {
    LevelData levelData;
    
    // Load the correct level
    switch(level) {
        case 1: levelData = LevelLoader.loadLevel1(); break;
        case 2: levelData = LevelLoader.loadLevel2(); break;
        case 3: levelData = LevelLoader.loadLevel3(); break;
    }

    gameState = GameState.PLAYING;
    
    /*Chip chip = new Chip(levelData.getStartPosition());
    currentMap = new Map(levelData.getTiles()[0].length, levelData.getTiles().length, chip, levelData.getChipsRequired());
    
    // COPY TILES FROM LevelData TO Map
    Tile[][] levelTiles = levelData.getTiles();
    for (int y = 0; y < levelTiles.length; y++) {
        for (int x = 0; x < levelTiles[y].length; x++) {
            currentMap.setTile(new Position(x, y), levelTiles[y][x]);
        }
    }*/
}

    /*
     * Moves Chip to the target position (GUI will call diz!)
     * 
     * @param targetX the target x-coordinate
     * @param targetY the target y-coordinate
     * @return true if the move was successful, false otherwise
     */
    public boolean moveChip(int targetX, int targetY){
        if(gameState != GameState.PLAYING){
            return false;
        }
         boolean moved = currentMap.moveChip(targetX, targetY);
        
        // Check if level completed
        if (currentMap.isLevelCompleted() && currentMap.takeNextLevelRequest()) {
            if (currentLevel < TOTAL_LEVELS) {
                gameState = GameState.LEVEL_COMPLETE;
            } else {
                gameState = GameState.GAME_COMPLETE;
            }
        }
        
        return moved;
    }
    

    // public methods for gui !!!

     /** Continue to next level after completion */
    public void continueToNextLevel() {
        if (currentLevel < TOTAL_LEVELS) {
            currentLevel++;
            loadLevel(currentLevel);
        } else {
            gameState = GameState.GAME_COMPLETE;
        }
    }

     /** Quit after level complete */
    public void quitAfterLevelComplete() {
        gameState = GameState.QUIT;
        gameRunning = false;
    }

     /** Quit during gameplay */
    public void quitGame() {
        gameState = GameState.QUIT;
        gameRunning = false;
    }

    public GameState getGameState(){
        return gameState;
    }

    /*
     * checks if lvl is completed
     */
    public boolean isLevelComplete(){
        return gameState == GameState.LEVEL_COMPLETE;
    }

    /*
     * returns to the current map
     */
    public Map getCurrentMap(){
        return currentMap;
    }

    public boolean isGameComplete(){
        return gameState == GameState.GAME_COMPLETE;
    }

    public boolean hasMoreLevels(){
        return currentLevel < TOTAL_LEVELS;
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

