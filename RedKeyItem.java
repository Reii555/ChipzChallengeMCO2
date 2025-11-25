public class RedKeyItem extends ItemTile{

    public RedKeyItem(Position position){
        super(position, "Red_key", "RedKeyItem", "redKey");
    }

    @Override
    public void onEnter(Map map, Chip chip){
        chip.getInventory().addRedKey();

        map.removeTile(this.getPosition());
    }

}
