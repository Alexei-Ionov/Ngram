package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void first_element_prime_test() {
        IntList lst2 = IntList.of(7, 12, 13, 14, 15);
        boolean changed = IntListExercises.squarePrimes(lst2);
        assertEquals("49 -> 12 -> 169 -> 14 -> 15", lst2.toString());
        assertTrue(changed);


    }

    @Test
    public void no_primes() {
        IntList lst3 = IntList.of(4, 4, 6, 8, 10, 100);
        boolean changed = IntListExercises.squarePrimes(lst3);
        assertEquals("4 -> 4 -> 6 -> 8 -> 10 -> 100", lst3.toString());
        assertFalse(changed);

    }

    @Test
    public void all_primes() {
        IntList lst4 = IntList.of(1, 2, 3, 5, 7, 13);
        boolean changed = IntListExercises.squarePrimes(lst4);
        assertEquals("1 -> 4 -> 9 -> 25 -> 49 -> 169", lst4.toString());
        assertTrue(changed);
    }

    @Test
    public void one_element() {
        IntList lst5 = IntList.of(1);
        boolean changed = IntListExercises.squarePrimes(lst5);
        assertEquals("1", lst5.toString());
        assertFalse(changed);


    }
}
