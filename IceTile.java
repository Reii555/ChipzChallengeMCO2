public class IceTile extends Tile {

    public IceTile(Position position) {
        super(position);
    }

    @Override
    public boolean isPassable(Chip c) {
        return true; // Ice tiles are always passable
    }

    @Override
    public void onEnter(Map map, Chip chip) {

        Direction lastDirection = chip.getLastMoveDirection();
        
        if(lastDirection != null && lastDirection != Direction.NONE) {
            Position currentPosition = chip.getPosition();
            Position newPosition = currentPosition.move(lastDirection);

             // Continue sliding til Chip hits a non-ice tile or an obstacle
            while (map.inBounds(newPosition.getX(), newPosition.getY()) && 
                   map.getTileAt(newPosition.getX(), newPosition.getY()) instanceof IceTile &&
                   map.getTileAt(newPosition.getX(), newPosition.getY()).isPassable(chip)) {
                
                    chip.setPosition(newPosition);
                    currentPosition = newPosition;
                    newPosition = currentPosition.move(lastDirection);
            }
            
            // Move to the last valid position if it's passable
            if (map.isPositionPassable(newPosition, chip)) {
                chip.setPosition(newPosition);
                map.getTileAt(newPosition.getX(), newPosition.getY()).onEnter(map, chip); // Trigger onEnter for the new tile
            }
        }
    }

    // GUI methods
    @Override
    public String getTileType() {
        return "IceTile";
    }

    @Override
    public String getVisualState() {
        return "Ice";
    }

}
