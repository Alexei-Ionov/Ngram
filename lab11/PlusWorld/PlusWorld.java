package PlusWorld;
import org.junit.Test;
import static org.junit.Assert.*;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of plus shaped regions.
 */
public class PlusWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    public static void addPlus(TETile[][] world, int size, int[] startingPos) {
        int[][] dirr = {{0, size}, {size, 0}, {0, -1 * size}, {-1 * size, 0}};
        addSquare(world, startingPos, size);
        for (int[] i : dirr) {
            int xl = i[0] + startingPos[0];
            int xr = i[1] + startingPos[1];
            int[] newPos = new int[]{xl, xr};
            addSquare(world, newPos, size);
        }
    }



    private static void addSquare(TETile[][] world, int[] startingPos, int size) {
        int sides = Math.floorDiv(size, 2);
        int leftStart = startingPos[0] - sides;
        int rightEnd = startingPos[0] + sides;
        int bot = startingPos[1] - sides;
        int top = startingPos[1] + sides;

        if (size % 2 == 0) {
            leftStart += 1;
            top -= 1;
        }

        for (int x = leftStart; x <= rightEnd; x++) {
            for (int y = bot; y <= top; y++) {
                if (y >= 0 && y < HEIGHT && x >= 0 && x < WIDTH) {
                    world[x][y] = Tileset.TREE;
                }
            }


        }
    }
    private static void FillWorld(TETile[][] world) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }

    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        int size = 3;
        int[] startingPos = new int[]{80, 80};

        FillWorld(world);
        addPlus(world, size, startingPos);
        ter.renderFrame(world);
    }
}
