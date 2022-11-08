package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class WorldCreator {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private TETile[][] world;

    private TERenderer ter;

    public WorldCreator() {
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][HEIGHT];

        fillWorld();
    }
    public TETile[][] returnWorld() {
        return world;
    }
    private void fillWorld() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }
    private void generateRooms() {
        

    }


}
