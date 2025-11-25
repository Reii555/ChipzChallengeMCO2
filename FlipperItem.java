public class FlipperItem extends ItemTile{
    public FlipperItem(Position position){
        super(position, "Flippers", "FlippersItem", "flippers");
    }

    @Override
    public void onEnter(Map map, Chip chip){
        chip.getInventory().equipFlippers();
        map.removeTile(this.getPosition());
    }
}
