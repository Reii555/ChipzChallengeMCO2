public class FireBootsItem extends ItemTile {
    public FireBootsItem(Position position){
        super(position, "Fire_boots", "FireBootsItem", "fireBoots");
    }

    @Override
    public void onEnter(Map map, Chip chip){
        chip.getInventory().equipFireBoots();
        map.removeTile(this.getPosition());
    }
}