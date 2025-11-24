public abstract class RedDoorTile extends DoorTile {

    public RedDoorTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        Inventory inv = c.getInventory();
        return inv.hasRedKey(); // Red door tiles are passable only if the player has a red key
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        Inventory inv = chip.getInventory();
        if (inv.hasRedKey()) {
            inv.useRedKey(); // Use a red key when entering the red door tile
        }
    }

}
