public class BlueDoorTile extends DoorTile {

    public BlueDoorTile(Position position) {
        super(position, "blue", "BlueDoorTile", "blue_door");
    }

    @Override
    public boolean isPassable(Chip c) {
        return c.getInventory().hasBlueKey();
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        if (chip.getInventory().hasBlueKey()) {
            chip.getInventory().useBlueKey();
            map.removeTile(this.getPosition()); // Remove door after opening
        }
    }

}
