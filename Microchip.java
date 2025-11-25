public class Microchip extends ItemTile{
    public Microchip(Position position){
        super(position, "microchip", "Microchip", "chip");
    }

    @Override
    public void onEnter(Map map, Chip chip){
        // Specific collection logic for microchips
        chip.getInventory().addMicrochip();
        map.incrementChipsCollected();
        map.removeTile(this.getPosition()); // implement removeItemTile() in Map.java
    }

}

