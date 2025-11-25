public class BlueKeyItem extends ItemTile{

    public BlueKeyItem(Position position){
        super(position, "Blue_key", "BlueKeyItem", "blueKey");
    }

    @Override
    public void onEnter(Map map, Chip chip){
        chip.getInventory().addBlueKey();

        map.removeTile(this.getPosition());
    }

}
