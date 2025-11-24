public abstract class BlueDoorTile extends DoorTile {

    public BlueDoorTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        Inventory inv = c.getInventory();
        return inv.hasBlueKey(); // Blue door tiles are passable only if the player has a blue key
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        Inventory inv = chip.getInventory();
        if (inv.hasBlueKey()) {
            inv.useBlueKey(); // Use a blue key when entering the blue door tile
        }
    }

}
