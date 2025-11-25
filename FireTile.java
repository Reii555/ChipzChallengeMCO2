public class FireTile extends Tile {

    public FireTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        Inventory inv = c.getInventory();
        return inv.canWalkOnFire(); // Fire tiles are passable only if the player can walk on fire
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        if (!isPassable(chip)) {
            chip.die();             // Chip dies without the fire boots in the fire
        }
    }

    // for GUI
    @Override 
    public String getTileType(){
        return "FireTile";
    }

    @Override
    public String getVisualState(){
        return "Fire";
    }

}
