/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** Returns the nextNumber in a Collatz sequence. */
    /** this method returns the next number in the sequence that we need */
    public static int nextNumber(int n) {
        if (n % 2 == 0){
            return Math.floorDiv(n, 2);
        }
        else{
            return (3 * n) + 1;

        }

    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");

        // Some starter code to test
        while (n != 1) {          
            n = nextNumber(n);          
            System.out.print(n + " ");
        }
        System.out.println();

    }
}

