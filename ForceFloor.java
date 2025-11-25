public class ForceFloor extends Tile {
    private Direction forceDirection;

    public ForceFloor(Position position, Direction forceDirection){
        super(position);
        this.forceDirection = forceDirection;
    }

    @Override
    public boolean isPassable(Chip c) {
        return true; // Force floors are always passable
    }

    @Override
    public void onEnter(Map map, Chip chip) {
        int dx = 0, dy = 0;
        switch(forceDirection) {
            case UP: dy = -1; break;
            case DOWN: dy = 1; break;
            case LEFT: dx = -1; break;
            case RIGHT: dx = 1; break;
            // ignoring the NONE here
        }
        map.requestForce(dx, dy);
    }

    // GUI methods
    public Direction getForceDirection() {
        return forceDirection;
    }

    @Override
    public String getTileType(){
        return "ForceTile" + forceDirection.name();
    }

    @Override
    public String getVisualState(){
         switch(forceDirection) {
            case UP: return "up";
            case DOWN: return "down";
            case LEFT: return "left";
            case RIGHT: return "right";
            default: return "none";
        }
    }

}