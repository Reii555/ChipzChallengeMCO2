public class AngryTeethTile extends Tile{
    private Direction moveDirection = Direction.LEFT;

    public AngryTeethTile(Position position){
        super(position);
    }

    @Override
    public boolean isPassable(Chip c){
        return false;
    }

    @Override
    public void onEnter(Map map, Chip chip){
         // If Chip steps on enemy = game over
        // You'll need to handle this in Game.java
    }

    public void enemyMovement(Map map) {
        //insert enemy's movement sa map
    }

    @Override
    public String getTileType() {
        return "AngryTeeth";
    }

    @Override
    public String getVisualState() {
        return "Enemy";
    }


}
