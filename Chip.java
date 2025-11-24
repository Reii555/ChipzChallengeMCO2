// hi this is a test

/**
 * Represents the Chip in the game
 * Tracks Chip's position, inventory, and association with the current map
 */
 public class Chip {
    private Position position;
    private Direction lastMoveDirection;
    private Inventory inventory;
    private Map map;

  /**
   * (Constructor) creates Chip with a specified starting position
   */
    public Chip(Position start) {
        this.position = start;
        this.inventory = new Inventory();
    }

  /**
   * (Constructor) creates Chip associated with a specific map
   */
    public Chip(Map map){
        this.map = map;
    }

   /**
    * Returns Chip's current position on the map
    */
    public Position getPosition() {
        return position;
    }

   /**
    * Sets Chip's position to the specified coordinates
    */
    public void setPosition(Position pos) {
        this.position = pos;
    }

    /*
    * Returns the last move direction of Chip
    */
    public Direction getLastMoveDirection() {
    return lastMoveDirection;
    }

    /*
    * Sets the last move direction of Chip
    */
    public void setLastMoveDirection(Direction direction) {
    this.lastMoveDirection = direction;
    }

  /**
   * Returns Chip's inventory containing collected items and equipment
   */
    public Inventory getInventory() {
        return inventory;
    }

  /**
   * Returns the map that Chip is currently navigating
   */
    public Map getMap() {
        return map;
    }
}

