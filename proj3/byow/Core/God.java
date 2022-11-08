package byow.Core;

import byow.TileEngine.TETile;

import java.util.HashMap;

public class God {
    private HashMap<String, TETile[][]> seedToWorld;
    private TETile[][] prevWorld;

    private String prevWorldSeed;

    public void God() {
        seedToWorld = new HashMap<>();
        prevWorld = null;
        prevWorldSeed = null;
    }
    public void addWorld(String seed) {
        TETile[][] world = createWorld(seed);
        seedToWorld.put(seed, world);
        prevWorld = world;
        prevWorldSeed = seed;
    }
    public void updateWorld(String seed, TETile[][] newWorld) {
        seedToWorld.put(seed, newWorld);
    }
    public boolean seedExists(String seed) {
        return seedToWorld.get(seed) != null;
    }

    public TETile[][] returnWorld(String seed) {
        return seedToWorld.get(seed);
    }

    private TETile[][] createWorld(String seed) {
        return;
    }

    public TETile[][] returnPrevWorld() {
        return prevWorld;
    }
}

