public class HikingBootsItem extends ItemTile{
    public HikingBootsItem(Position position) {
        super(position, "Hiking_Boots", "HikingBootsItem", "hiking_boots");
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        chip.getInventory().equipHikingBoots();
        map.removeTile(this.getPosition());
    }
}

