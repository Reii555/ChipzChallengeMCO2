/**
 * Represents a colored door (blue/red) which can be opened with the corresponding key.
 * Doors block passage until unlocked with the correct key from Chip's inventory.
 */
public class Door {
    private DoorColor color;
    private boolean isOpen;

    /**
     * (Constructor) creates a door of specified color, initially closed
     */
    public Door(DoorColor color) {
        this.color = color;
        this.isOpen = false;
    }

    /**
     * Returns the color of this door (RED or BLUE)
     */
    public DoorColor getColor() {
        return color;
    }

    /**
     * Returns whether the door is currently open or not
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Checks if the door can be opened with the given inventory.
     * Returns true if the player has the corresponding key.
     */
    public boolean canOpen(Inventory inventory) {
        if (color == DoorColor.RED) {
            return inventory.hasRedKey();
        } else if (color == DoorColor.BLUE) {
            return inventory.hasBlueKey();
        }
        return false;
    }

    /**
     * Attempts to open the door using keys from Chip's inventory.
     * Consumes the key if successful.
     * Returns true if door was opened, false if not.
     */
    public boolean openWith(Inventory inventory) {
        if (canOpen(inventory)) {
            if (color == DoorColor.RED) {
                inventory.useRedKey();
            } else if (color == DoorColor.BLUE) {
                inventory.useBlueKey();
            }
            isOpen = true;
            System.out.println(color + " door opened!");
            return true;
        } else {
            System.out.println("You don't have the correct key for the " + color + " door!");
            return false;
        }
    }

    /**
     * Handles what happens when Chip tries to enter this door.
     * Attempts to open the door if closed, provides feedback if already open.
     */
    public void onEnter(Map map, Chip chip) {
        if (!isOpen) {
            openWith(chip.getInventory());
        } else {
            System.out.println("The " + color + " door is already open.");
        }
    }
}

