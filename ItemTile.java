public abstract class ItemTile extends Tile {
    private String itemState;
    private String tileType;
    private String visualState;

    public ItemTile(Position position, String itemState, String tileType, String visualState){
        super(position);
        this.itemState = itemState;
        this.tileType = tileType;
        this.visualState = visualState;
    }

    @Override
    public boolean isPassable(Chip c){
        return true;
    }

    @Override
    public void onEnter(Map map, Chip chip){
        // collects
    }

    public String getItemState(){ 
        return itemState; 
    }
    public String getTileType(){
        return tileType;
    }
    public String getVisualState(){ 
        return visualState;
    }
}
