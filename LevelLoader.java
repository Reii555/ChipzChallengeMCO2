//might delete this
public class LevelLoader {

    // Level 1 design
     public static Map loadLevel1() {
        int width = 10, height = 10;
        Position start = new Position(1, 8);
        int chipsRequired = 3;
        
        Chip chip = new Chip(start);
        Map map = new Map(width, height, chip, chipsRequired);
        
        // Initialize all as floor tiles first
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map.setTile(new Position(x, y), new FloorTile(new Position(x, y)));
            }
        }

        //insert lvl 1 design
        return map;
    }

    // Level 2 design
     public static Map loadLevel2() {
        int width = 10, height = 10;
        Position start = new Position(1, 8);
        int chipsRequired = 3;
        
        Chip chip = new Chip(start);
        Map map = new Map(width, height, chip, chipsRequired);
        
        // Initialize all as floor tiles first
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map.setTile(new Position(x, y), new FloorTile(new Position(x, y)));
            }
        }

        //insert lvl 2 design
        return map;
    }

    // Level 3 design
     public static Map loadLevel3() {
        int width = 10, height = 10;
        Position start = new Position(1, 8);
        int chipsRequired = 3;
        
        Chip chip = new Chip(start);
        Map map = new Map(width, height, chip, chipsRequired);
        
        // Initialize all as floor tiles first
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map.setTile(new Position(x, y), new FloorTile(new Position(x, y)));
            }
        }

        //insert lvl 3 design
        return map;
    }
}
