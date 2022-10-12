package ngordnet.main;
import edu.princeton.cs.algs4.In;

/**
 */
public class FileReaderDemo {
    public static void main(String[] args) {
        In in = new In("ngordnet/main/example_input_file.txt");

        /* Every time you call a read method from the In class,
         * it reads the next token from the file, assuming it is
         * of the specified type. The In class thinks of the "next"
         * token as whatever follows whitespace. That whitespace
         * may be spaces, tabs, and/or newlines. */

        /* Compare the calls below to the contents of ./ngordnet/main/example_input_file.txt */

        while (in.hasNextLine()) {
            if (in.isEmpty()) {
                break;
            }
            String word = in.readString();
            Integer year = in.readInt();
            Integer count = in.readInt();
            in.readInt(); //useless column
            System.out.println("The line contained " + word + ", " + year + ", " + count);
        }
    }
}

