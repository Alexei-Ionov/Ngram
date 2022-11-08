package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, running both of these:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input, God god) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        // If the first char of input string is 'n', THEN that must mean we are generating a NEW world
        //if it is some other letter then that must mean we are logging into a pre-existing world
        String inputL = input.toLowerCase();
        int index = 0;
        if (Character.toString(inputL.charAt(0)).equals("n")) {
            StringBuilder seed = new StringBuilder();
            String end = "s";
            index += 1;
            while (!Character.toString(inputL.charAt(index)).equals(end)) {
                seed.append(inputL.charAt(index));
                index += 1;
            }

            if (!god.seedExists(seed.toString())) {
                god.addWorld(seed.toString());
            }
        }
        TETile[][] world = god.returnPrevWorld();
        // else, world is already created and I need to update it by moving!
        String colon = ":";
        for (int i = index; i < inputL.length() - 1; i++) {
            if (Character.toString(inputL.charAt(0)).equals(colon)) {
                //if next val is q, then: god.updateworld(world) [AKA saves the curr work on world]

            }
            //if val is l, then we want to load the prevWorld
            //else update world normally

        }




        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
