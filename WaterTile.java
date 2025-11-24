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
        // logic aout if Chip doesnt have the flippers it dies, and can pass if flippers r present
        if(!isPassable(chip)){
            //kill dat ho
            chip.die();
        }else{
            // let dat ho in
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
