public abstract class Tile {
    private Position position;

    // constructor
    public Tile(Position position){
        this.position = position;
    }

    // getter + setter
    public Position getPosition() {
        return position;
    }

    protected void setPosition(Position pos) {
        this.position = pos; 
    }

    // abstract methodz
    public abstract boolean isPassable(Chip c);
    public abstract void onEnter(Map map, Chip chip);

    // GUI methodz
    public abstract String getTileType();
    public abstract String getVisualState();
    
}
