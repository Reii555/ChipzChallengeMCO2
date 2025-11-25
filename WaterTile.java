public class WaterTile extends Tile {

    public WaterTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        Inventory inv = c.getInventory();
        return inv.canSwim(); // Water tiles are passable only if the player can swim
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        if(!isPassable(chip)){
            chip.die();         // Chip dies without the Flippers in the water
        }
    }

    // GUI methods
    @Override
    public String getTileType(){
        return "WaterTile";
    }

    @Override
    public String getVisualState(){
        return "water";
    }
}
