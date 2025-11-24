public class WallTile extends Tile {

    public WallTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        return false; // Wall tiles are always impassable
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        // No action needed since walls cannot be entered
    }

    // GUI methodz
    @Override
    public String getTileType(){
        return "Wall";
    }

    @Override
    public String getVisualState(){
        return "Solid";
    }

}
