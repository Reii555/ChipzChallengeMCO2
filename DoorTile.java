public abstract class DoorTile extends Tile {
    private String doorColor;
    private String tileType;
    private String visualState;

   public DoorTile(Position position, String doorColor, String tileType, String visualState) {
        super(position);
        this.doorColor = doorColor;
        this.tileType = tileType;
        this.visualState = visualState;
    }

   public abstract boolean isPassable(Chip c);
   public abstract void onEnter(Map map, Chip chip);

    public String getDoorState(){
        return doorColor + "_door";
    }

    @Override
    public String getTileType() {
        return tileType;
    }

    @Override
    public String getVisualState() {
        return visualState;
    }

}
