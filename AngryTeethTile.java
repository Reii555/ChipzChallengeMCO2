public class AngryTeethTile extends Tile{
    private Direction moveDirection = Direction.RIGHT;

    public AngryTeethTile(Position position){
        super(position);
    }

    @Override
    public boolean isPassable(Chip c){
        return false;
    }

    @Override
    public void onEnter(Map map, Chip chip){
        chip.takeDmg(1);
    }

    public void enemyMovement(Map map) {
        Position currentPos = getPosition();
        Position nextPos = currentPos.move(moveDirection);

        // Check if next position is valid and passable (floor or Chip)
        if (map.isValidPosition(nextPos)) {
            Tile nextTile = map.getTileAt(nextPos.getX(), nextPos.getY());
            
            // Allow moving onto floor tiles or where Chip is
            if (nextTile instanceof FloorTile) {
                // Move the enemy
                map.setTile(currentPos, new FloorTile(currentPos));
                map.setTile(nextPos, this);
            } else {
                // Hit wall or obstacle - reverse direction
                reverseDirection();
            }
        } else {
            // Out of bounds - reverse direction  
            reverseDirection();
        }
    }

    private void reverseDirection() {
        moveDirection = (moveDirection == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT;
    }

    @Override
    public String getTileType() {
        return "AngryTeeth";
    }

    @Override
    public String getVisualState() {
        return "Enemy";
    }


}
