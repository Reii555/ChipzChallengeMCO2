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

        // walls
        for(int x = 0; x < width; x++){
            map.setTile(new Position(x, 0), new WallTile(new Position(x, 0))); // top border
            map.setTile(new Position(x, height-1), new WallTile(new Position(x, height-1))); // bottom border
        }

        for(int y = 0; y < height; y++){
            map.setTile(new Position(0, y), new WallTile(new Position(0, y))); // left border
            map.setTile(new Position(width-1, y), new WallTile(new Position(width-1, y))); // right border

        }

        //insert lvl 1 design



        return map;
    }

    // Level 2 design
     public static Map loadLevel2() {
        int width = 10, height = 10;
        Position start = new Position(8, 8);
        int chipsRequired = 3;
        
        Chip chip = new Chip(start);
        Map map = new Map(width, height, chip, chipsRequired);
        
        // Initialize all as floor tiles first
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map.setTile(new Position(x, y), new FloorTile(new Position(x, y)));
            }
        }

        // walls
        for(int x = 0; x < width; x++){
            map.setTile(new Position(x, 0), new WallTile(new Position(x, 0))); // top border
            map.setTile(new Position(x, height-1), new WallTile(new Position(x, height-1))); // bottom border
        }

        for(int y = 0; y < height; y++){
            map.setTile(new Position(0, y), new WallTile(new Position(0, y))); // left border
            map.setTile(new Position(width-1, y), new WallTile(new Position(width-1, y))); // right border

        }

        // interior walls
        map.setTile(new Position(2, 8), new WallTile(new Position(2, 8)));
        
        map.setTile(new Position(2, 7), new WallTile(new Position(2, 7)));
        
        map.setTile(new Position(2, 6), new WallTile(new Position(2, 6)));
        map.setTile(new Position(3, 6), new WallTile(new Position(3, 6)));
        map.setTile(new Position(4, 6), new WallTile(new Position(2, 6)));
        map.setTile(new Position(7, 6), new WallTile(new Position(7, 6)));
        map.setTile(new Position(8, 6), new WallTile(new Position(8, 6)));

        map.setTile(new Position(3, 5), new WallTile(new Position(2, 5)));
        map.setTile(new Position(4, 5), new WallTile(new Position(2, 5)));
        map.setTile(new Position(5, 5), new WallTile(new Position(2, 5)));

        map.setTile(new Position(3, 4), new WallTile(new Position(3, 4)));
        map.setTile(new Position(8, 4), new WallTile(new Position(8, 4)));

        map.setTile(new Position(3, 3), new WallTile(new Position(3, 3)));
        map.setTile(new Position(4, 3), new WallTile(new Position(4, 3)));
        map.setTile(new Position(7, 3), new WallTile(new Position(7, 3)));
        map.setTile(new Position(8, 3), new WallTile(new Position(8, 3)));

        map.setTile(new Position(3, 2), new WallTile(new Position(3, 2)));
        map.setTile(new Position(4, 2), new WallTile(new Position(4, 2)));
        map.setTile(new Position(6, 2), new WallTile(new Position(6, 2)));
        map.setTile(new Position(7, 2), new WallTile(new Position(7, 2)));
        map.setTile(new Position(8, 2), new WallTile(new Position(8, 2)));

        map.setTile(new Position(6, 1), new WallTile(new Position(6, 1)));
        map.setTile(new Position(7, 1), new WallTile(new Position(7, 1)));
        map.setTile(new Position(8, 1), new WallTile(new Position(8, 1)));

        // water tile/s
        map.setTile(new Position(1, 7), new WaterTile(new Position(1, 7)));

        // fire tile/s
        map.setTile(new Position(4, 8), new FireTile(new Position(4, 8)));
        map.setTile(new Position(4, 7), new FireTile(new Position(4, 7)));

        // force tile/s
        map.setTile(new Position(1, 3), new ForceFloor(new Position(1,3), Direction.DOWN));
        map.setTile(new Position(2, 3), new ForceFloor(new Position(2,3), Direction.DOWN));
        map.setTile(new Position(1, 4), new ForceFloor(new Position(1,4), Direction.DOWN));
        map.setTile(new Position(2, 4), new ForceFloor(new Position(2,4), Direction.DOWN));

        // exit tile
        map.setTile(new Position(1, 8), new ExitTile(new Position(1,8)));

        // red door
        map.setTile(new Position(4, 1), new RedDoorTile(new Position(4,1)));

        // blue door
        map.setTile(new Position(1, 6), new BlueDoorTile(new Position(1,6)));

        // items
        // microchips
        map.setTile(new Position(3, 7), new Microchip(new Position(3,7)));
        map.setTile(new Position(2, 5), new Microchip(new Position(2,5)));
        map.setTile(new Position(4, 4), new Microchip(new Position(4,4)));

        // red key
        map.setTile(new Position(6, 3), new RedKeyItem(new Position(6,3)));

        // blue key
        map.setTile(new Position(3, 8), new BlueKeyItem(new Position(3,8)));

        // fire boots
        map.setTile(new Position(8, 5), new FireBootsItem(new Position(8,5)));

        // flipper
        map.setTile(new Position(7, 4), new FireBootsItem(new Position(7,4)));


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

        // Initialize all as floor tiles first
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map.setTile(new Position(x, y), new FloorTile(new Position(x, y)));
            }
        }

        // walls
        for(int x = 0; x < width; x++){
            map.setTile(new Position(x, 0), new WallTile(new Position(x, 0))); // top border
            map.setTile(new Position(x, height-1), new WallTile(new Position(x, height-1))); // bottom border
        }

        for(int y = 0; y < height; y++){
            map.setTile(new Position(0, y), new WallTile(new Position(0, y))); // left border
            map.setTile(new Position(width-1, y), new WallTile(new Position(width-1, y))); // right border

        }

        //ice tiles placing
        map.setTile(new Position(2, 8), new IceTile(new Position(2, 8)));
        map.setTile(new Position(3, 8), new IceTile(new Position(3, 8)));
        map.setTile(new Position(4, 8), new IceTile(new Position(4, 8)));
        map.setTile(new Position(7, 8), new IceTile(new Position(7, 8)));
        map.setTile(new Position(8, 8), new IceTile(new Position(8, 8)));

        map.setTile(new Position(1, 7), new IceTile(new Position(1, 7)));
        map.setTile(new Position(2, 7), new IceTile(new Position(2, 7)));
        map.setTile(new Position(3, 7), new IceTile(new Position(3, 7)));
        map.setTile(new Position(4, 7), new IceTile(new Position(4, 7)));
        map.setTile(new Position(7, 7), new IceTile(new Position(7, 7)));
        map.setTile(new Position(8, 7), new IceTile(new Position(8, 7)));

        map.setTile(new Position(7, 6), new IceTile(new Position(7,6)));
        map.setTile(new Position(8, 6), new IceTile(new Position(8, 6)));

        map.setTile(new Position(7, 5), new IceTile(new Position(7,5)));
        map.setTile(new Position(8,5), new IceTile(new Position(8, 5)));

        map.setTile(new Position(2, 4), new IceTile(new Position(2,4)));
        map.setTile(new Position(3, 4), new IceTile(new Position(3, 4)));

        map.setTile(new Position(1, 3), new IceTile(new Position(1, 3)));
        map.setTile(new Position(2, 3), new IceTile(new Position(2,3)));
        map.setTile(new Position(3,3), new IceTile(new Position(3,3)));

        // interior walls
        map.setTile(new Position(6, 7), new IceTile(new Position(6,7)));

        map.setTile(new Position(1, 6), new IceTile(new Position(1,6)));
        map.setTile(new Position(2, 6), new IceTile(new Position(2,6)));
        map.setTile(new Position(4, 6), new IceTile(new Position(4,6)));
        map.setTile(new Position(6, 6), new IceTile(new Position(6,6)));

        map.setTile(new Position(1, 5), new IceTile(new Position(1,5)));
        map.setTile(new Position(2, 5), new IceTile(new Position(2,5)));
        map.setTile(new Position(3, 5), new IceTile(new Position(3,5)));
        map.setTile(new Position(4, 5), new IceTile(new Position(4,5)));
        map.setTile(new Position(6, 5), new IceTile(new Position(6,5)));

        map.setTile(new Position(4, 4), new IceTile(new Position(4,4)));
        map.setTile(new Position(6, 4), new IceTile(new Position(6,4)));

        map.setTile(new Position(4, 3), new IceTile(new Position(4,3)));
        map.setTile(new Position(6, 3), new IceTile(new Position(6,3)));
        map.setTile(new Position(7, 3), new IceTile(new Position(4,3)));
        map.setTile(new Position(8, 3), new IceTile(new Position(6,3)));

        // red door
        map.setTile(new Position(5, 3), new RedDoorTile(new Position(5,3)));

        //items
        map.setTile(new Position(3, 6), new Microchip(new Position(3,6)));

        return map;
    }
}
