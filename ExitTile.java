public class ExitTile extends Tile {

    public ExitTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        return true; // Exit tiles are always passable
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        map.setLevelCompleted(true); // Mark the level as completed when Chip enters the exit tile
    }

}
