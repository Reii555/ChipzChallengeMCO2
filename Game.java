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
        GAME_OVER,
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
    Map map;
    switch(level) {
        case 1: map = LevelLoader.loadLevel1(); break;
        case 2: map = LevelLoader.loadLevel2(); break;
        case 3: map = LevelLoader.loadLevel3(); break;
        default: throw new IllegalArgumentException("Invalid level: " + level);
    }
    
    this.currentMap = map; // Assign the loaded map
    this.gameState = GameState.PLAYING; // Reset game state AFTER map is loaded
}

    /*
     * Moves Chip to the target position (GUI will call diz!)
     * 
     * @param targetX the target x-coordinate
     * @param targetY the target y-coordinate
     * @return true if the move was successful, false otherwise
     */
    public boolean moveChip(int targetX, int targetY){
        if(gameState != GameState.PLAYING || !getChip().isAlive()){
            return false;
        }
         boolean moved = currentMap.moveChip(targetX, targetY);
        
        // (in lvl 3 only) Chip takes dmg from enemy
        Tile newTile = currentMap.getTileAt(targetX, targetY);
        if (newTile instanceof AngryTeethTile){
            getChip().takeDmg(1);
            if (!getChip().isAlive()){
                gameState = GameState.GAME_OVER;
            }
        }
        
        // Move enemies after player moves
        updateEnemies();

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
    
    /*
    * Updates enemy behavior and movement for the enemy in lvl 3
    */
    public void updateEnemies(){
        if(currentLevel == 3){
            for (int y = 0; y < getMapHeight(); y++){
                for (int x = 0; x < getMapWidth(); x++){
                    Tile tile = getTileAt(x, y);
                    if (tile instanceof AngryTeethTile){
                        ((AngryTeethTile) tile).enemyMovement(currentMap);

                        Position enemyPos = tile.getPosition();
                        if (enemyPos.equals(getChip().getPosition())){
                        getChip().takeDmg(1);
                        if (!getChip().isAlive()){
                            gameState = GameState.GAME_OVER;
                            }
                        }
                    }
                }
            }
        }
    }

    /*
    * Checks if the game is over (when Chip's health gets to 0)
    * @return
    */
    public boolean isGameOver(){
        return gameState == GameState.GAME_OVER;
    }

    /*
     * Continue to next level after completion 
     */
    public void continueToNextLevel() {
        if (currentLevel < TOTAL_LEVELS) {
            currentLevel++;
            loadLevel(currentLevel);
        } else {
            gameState = GameState.GAME_COMPLETE;
            // gameRunning = false;
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

}

