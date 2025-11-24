public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position move(Direction direction) {
    switch (direction) {
        case UP: return new Position(x, y - 1);
        case DOWN: return new Position(x, y + 1);
        case LEFT: return new Position(x - 1, y);
        case RIGHT: return new Position(x + 1, y);
        default: return this;
    }
}

    /*
     * Returns a string representation of the position in the format (x, y)
     * @return the string representation of the position
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
}
