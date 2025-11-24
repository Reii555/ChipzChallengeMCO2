public class ForceFloor extends Tile {

    public ForceFloor(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        return true; // Force floors are always passable
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        // Move the chip in the forced direction
        Position currentPosition = chip.getPosition();
        Position newPosition = map.getForcedPosition(currentPosition);
        if (newPosition != null && map.isPositionPassable(newPosition, chip)) {
            chip.setPosition(newPosition);
            map.getTileAt(newPosition).onEnter(map, chip); // Trigger onEnter for the new tile
        }
    }

}
