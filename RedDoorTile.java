public class RedDoorTile extends DoorTile {

    public RedDoorTile(Position position) {
        super(position, "red", "RedDoorTile", "red_door");
    }

    @Override
    public boolean isPassable(Chip c) {
        return c.getInventory().hasRedKey();
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        if (chip != null && chip.getInventory().hasRedKey()) {
            chip.getInventory().useRedKey();
            map.removeTile(this.getPosition()); // Remove door after opening
        }
    }

}
