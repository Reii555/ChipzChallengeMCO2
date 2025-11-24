public class LevelLoader {

    // Level 1 design
    public static LevelData loadLevel1(){
        int width = 10, height = 10;
        Tile[][] tiles = new Tile[height][width];
        Position start = new Position(1, 8);

        //initializing floor tiles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = new FloorTile(new Position(x, y));
            }
        }

        // insert level 1 lojik

        return new LevelData(tiles, start, 3);
    }

    // Level 2 design
    public static LevelData loadLevel2(){
        int width = 10, height = 10;
        Tile[][] tiles = new Tile[height][width];
        Position start = new Position(1, 8);

        //initializing floor tiles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = new FloorTile(new Position(x, y));
            }
        }

        // insert level 2 lojik

        return new LevelData(tiles, start, 3);
    }

    // Level 3 design
    public static LevelData loadLevel3(){
        int width = 10, height = 10;
        Tile[][] tiles = new Tile[height][width];
        Position start = new Position(1, 8);

        //initializing floor tiles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = new FloorTile(new Position(x, y));
            }
        }

        // insert level 3 lojik

        return new LevelData(tiles, start, 3);
    }
}
