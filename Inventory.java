/**
 * Manages the Chip's collected items, keys, and equipment.
 * Tracks inventory state and provides methods for item usage and capability checks.
 */
public class Inventory {
    private int redKeys;
    private int blueKeys;
    private int microchips;
    private boolean hasFireBoots = false;
    private boolean hasFlippers = false;

    /**
     * (Constructor) creates an empty inventory with no items or equipment
     */
    public Inventory() {
        this.redKeys = 0;
        this.blueKeys = 0;
        this.microchips = 0;
        this.hasFireBoots = false;
        this.hasFlippers = false;
    }

    /**
     * Adds one microchip to the inventory.
     */
    public void addMicrochip(){
        microchips++;
    }

    /**
     * Returns the number of microchips collected.
     */
    public int getMicrochips(){
        return microchips;
    }

    /**
     * Adds one red key to the inventory.
     */
    public void addRedKey(){
        redKeys++;
    }

    /**
     * Adds one blue key to the inventory.
     */
    public void addBlueKey(){
        blueKeys++;
    }

    /**
     * Uses one red key if available.
     * Returns true if successful, false if no red keys are available in Chip's inventory.
     */
    public boolean useRedKey(){
        if(redKeys > 0){
            redKeys--;
            return true;
        }
        return false;
    }

    /**
     * Uses one blue key if available.
     * Returns true if successful, false if no blue keys are available in Chip's inventory.
     */
    public boolean useBlueKey(){
        if(blueKeys > 0){
            blueKeys--;
            return true;
        }
        return false;
    }

    /**
     * Equips flippers, allowing Chip to swim through water tiles.
     */
    public void equipFlippers(){
        hasFlippers = true;
    }

    /**
     * Equips fire boots, allowing Chip to walk on fire tiles.
     */
    public void equipFireBoots(){
        hasFireBoots = true;
    }

    /**
     * Checks if Chip can swim through water (has flippers equipped)
     */
    public boolean canSwim(){
        return hasFlippers;
    }

    /**
     * Checks if Chip can walk on fire (has fire boots equipped)
     */
    public boolean canWalkOnFire(){
        return hasFireBoots;
    }

    /**
     * Checks if Chip has at least one red key
     */
    public boolean hasRedKey(){
        return redKeys > 0;
    }

    /**
     * Checks if Chip has at least one blue key
     */
    public boolean hasBlueKey(){
        return blueKeys > 0;
    }
}
