public class FloorTile extends Tile{
    public FloorTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        return true;
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        // nothing, Chip can walk freely on da floor
    }

    // GUI methods
    @Override
    public String getTileType(){
        return "FloorTile";
    }

    @Override
    public String getVisualState(){
        return "floor";
    }
}
