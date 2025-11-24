public abstract class Tile {
    private Position position;

    // constructor
    public Tile(Position position){
        this.position = position;
    }

    // getterz
    public Position getPosition() {
        return position;
    }

    // abstract methodz
    public abstract boolean isPassable(Chip c);
    public abstract void onEnter(Map map, Chip chip);

    // GUI methodz
    public abstract String getTileType();
    public abstract String getVisualState();
    
}
