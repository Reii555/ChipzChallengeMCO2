public class ExitTile extends Tile {

    public ExitTile(Position position){
        super(position);
    }

    @Override
    public boolean isPassable(Chip c){
        return true; // Exit tiles are always passable
    }

    @Override
    public void onEnter(Map map, Chip chip){
        if (map.isLevelCompleted()) {
            map.setLevelCompleted(true);
        }
    }

    @Override
    public String getTileType(){
        return "EXIT";
    }

     @Override
    public String getVisualState(){
        return "exit";
    }
}
