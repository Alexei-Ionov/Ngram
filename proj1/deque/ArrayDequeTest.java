package deque;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void testAdd() {
        ArrayDeque newTest = new ArrayDeque();

        boolean val = true;
        for (int i = 0; i < 30; i++) {
            if (val) {
                val = false;
                newTest.addFirst(i);
            } else {
                val = true;
                newTest.addLast(i);
            }
            newTest.printDeque();

        }


    }

    @Test
    public void testResize() {
        ArrayDeque newTest = new ArrayDeque();
        boolean val = true;
        for (int i = 0; i < 15; i++) {
            if (val) {
                newTest.addFirst(i);
            } else {
                newTest.addLast(i);
            }
            newTest.printDeque();

        }


    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque newTest = new ArrayDeque();

        boolean val = false;
        for (int i = 0; i <8; i++) {
            if (val) {
                newTest.addFirst(i);
            } else {
                newTest.addLast(i);
            }
            newTest.printDeque();

        }

        System.out.println(newTest.removeLast());
        System.out.println(newTest.removeFirst());
        newTest.printDeque();

    }

    @Test
    //works if no resizing is done!
    public void testGet() {
        ArrayDeque newTest = new ArrayDeque();

        boolean val = false;
        for (int i = 0; i < 8; i++) {
            if (val) {
                val = false;
                newTest.addFirst(i);
            } else {

                newTest.addLast(i);
            }
        }
        newTest.printDeque();

        System.out.println(newTest.get(0));
        System.out.println(newTest.get(5));

        System.out.println(newTest.removeFirst());
        newTest.printDeque();
        System.out.println(newTest.get(3));




    }

    @Test
    public void testMod(){
        int val = -3;
        System.out.println(val % 16);
        System.out.println((Math.floorMod(val, 8)));



    }}




