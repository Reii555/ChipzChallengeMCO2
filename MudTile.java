public class MudTile extends Tile{

    public MudTile(Position position) {
            super(position);
        }

    @Override
    public boolean isPassable(Chip c) {
        return true; // Mud tiles are always passable
    }

    @Override
    public void onEnter(Map map, Chip chip) {

        if (!chip.getInventory().canWalkOnMud()){
        // Movement still happens, but it feels slow because of animation
        chip.takeDmg(0); // No damage, but triggers some visual effect
        }
    }

    // GUI methods
    @Override
    public String getTileType() {
        return "MudTile";
    }

    @Override
    public String getVisualState() {
        return "Mud";
    }
}
