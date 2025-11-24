public class LevelManager {
    private int currentLevel = 1;
    private final int TOTAL_LEVELS = 3;
    private boolean gameCompleted = false;

    public Map loadCurrentLevel(){
        switch(currentLevel) {
            case 1: return LevelLoader.loadLevel1();
            case 2: return LevelLoader.loadLevel2();
            case 3: return LevelLoader.loadLevel3();
            default: return null;
        }
    }

    public boolean hasNextLevel(){
        return currentLevel < TOTAL_LEVELS;
    }

    public void advanceToNextLevel(){
        if(hasNextLevel()){
            currentLevel++;
        }else{
            gameCompleted = true;
        }
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public boolean isGameCompleted(){
        return gameCompleted;
    }

    public boolean shouldShowLevelCompleteOptions(){
        return hasNextLevel() || gameCompleted;
    }
}
