public abstract class DoorTile extends Tile {

    public DoorTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        Inventory inv = c.getInventory();
        return inv.hasKey(); // Door tiles are passable only if the player has a key
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        Inventory inv = chip.getInventory();
        if (inv.hasKey()) {
            inv.useKey(); // Use a key when entering the door tile
        }
    }

    public abstract String getDoorState();

}
