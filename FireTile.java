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
        // No special action needed when entering a fire tile
        if (!isPassable(chip)) {
            // Handle the case where Chip cannot walk on fire (e.g., lose a life)
            chip.die();
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
