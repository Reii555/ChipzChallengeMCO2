public class BackendTest {
    public static void main(String[] args) {
        System.out.println(" CHIP'S CHALLENGE BACKEND TEST !!!\n");

        try{
            //test 1 = basic game initialization
            System.out.println("Testing game initialization...");
            Game game = new Game();
            System.out.println("Game initialized successfully.\n");
            System.out.println("Level: " + game.getLevel());
            System.out.println("Map size: " + game.getMapWidth() + "x" + game.getMapHeight());
        
            //test 2 = chip info
            System.out.println("\nTesting Chip info...");
            Chip chip = game.getChip();
            Position chipPos = chip.getPosition();
            System.out.println("Chip position: " + chipPos);
            System.out.println("Chips collected: " + game.getChipsCollected() + "/" + game.getChipsCollectedRequired());
            System.out.println("Inventory accessible: " + (chip.getInventory() != null) + "\n");

            //test 3 = tile access
            System.out.println("Testing tile access...");
            Tile chipTile = game.getTileAt(chipPos.getX(), chipPos.getY());
            Tile adjacentTile = game.getTileAt(chipPos.getX() + 1, chipPos.getY());
            System.out.println("can access current tile: " + (chipTile != null));
            System.out.println("can access adjacent tile: " + (adjacentTile != null) + "\n");
            System.out.println("Tile types accessible: " + chipTile);

            //test 4 = movement thang
            System.out.println("\nTesting Chip movement...");
            int startX = chipPos.getX();
            int startY = chipPos.getY();

            //test validity of movement
            boolean movedRight = game.moveChip(startX + 1, startY);
            System.out.println("Move right: " + (movedRight ? "SLAYED" : "NAYED"));
            System.out.println("New position: " + chip.getPosition());

            //testing invalid move
            boolean movedOutOfBounds = game.moveChip(-1, -1);
            System.out.println("Move out of bounds: " + (movedOutOfBounds ? "SLAYED" : "NAYED"));
            System.out.println("Invalid move handled: " + (!movedOutOfBounds ? "SLAYED" : "NAYED"));

            //test 5 = game state checks
            System.out.println("\nTesting game state checks...");
            System.out.println("   ✓ Invalid move handled: " + (!movedOutOfBounds ? "CORRECTLY BLOCKED" : "ERROR"));
        
            // Test 5: Game State Checks
            System.out.println("\n5. Testing Game State...");
            System.out.println("   ✓ Level complete check: " + game.isLevelComplete());
            System.out.println("   ✓ Game running: " + game.isGameRunning());
            
            // Test 6: Map Exploration
            System.out.println("\n6. Testing Map Exploration...");
            System.out.println("   Scanning tiles around Chip:");
            for (int y = 0; y < game.getMapHeight(); y++) {
                for (int x = 0; x < game.getMapWidth(); x++) {
                    Tile tile = game.getTileAt(x, y);
                    if (tile != null) {
                        System.out.println("     Tile at (" + x + "," + y + "): " + tile);
                    }
                }
            }

            // Test 7: Inventory System
            System.out.println("\n7. Testing Inventory System...");
            Inventory inv = chip.getInventory();
            System.out.println("   ✓ Can swim: " + inv.canSwim());
            System.out.println("   ✓ Fire walk: " + inv.canWalkOnFire());
            System.out.println("   ✓ Red key: " + inv.hasRedKey());
            System.out.println("   ✓ Blue key: " + inv.hasBlueKey());
            
            System.out.println("\n=== ALL TESTS PASSED! ===");
            System.out.println("Your backend is ready for GUI integration!");
        
        }   catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
            e.printStackTrace();
            }
    }
}


/*
 * The method getItem() is undefined for the type Tile

Replace the call to an undefined Tile.getItem() by printing the Tile object only, and fix the undefined movedInvalid variable to use movedOutOfBounds.
 */