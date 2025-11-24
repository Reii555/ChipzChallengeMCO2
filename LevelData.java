//might delete this
public class LevelData {
    private final Tile[][] tiles;
    private final Position startPosition;
    private final int chipsRequired;

    //public final List<Enemy> enemies; 

public LevelData(Tile[][] tiles, Position startPosition, int chipsRequired /*List<Enemy> enemies*/){
    this.tiles = tiles;
    this.startPosition = startPosition;
    this.chipsRequired = chipsRequired;
    //this.enemies = enemies;
    }

    //getters
    public Tile[][] getTiles(){
        return tiles;
    }
    public Position getStartPosition(){
        return startPosition;
    }
    public int getChipsRequired(){
        return chipsRequired;
    }
    /*public List<Enemy> getEnemies(){
        return enemies;
    }*/


}