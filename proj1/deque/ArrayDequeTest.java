package deque;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ArrayDequeTest {

    @Test
    //works!!
    public void testAdd(){
        ArrayDeque testArray = new ArrayDeque();
        boolean val = false;
        for (int i =0; i < 32 ; i ++){
            if (val){

                testArray.addFirst(i);
            } else {

                testArray.addLast(i);
            }

        }
        testArray.printDeque();

    }

    @Test
    public void testRemoveFirst(){
        ArrayDeque testArray = new ArrayDeque();
        for (int i = 0; i < 8; i ++){
            testArray.addFirst(i);
        }
        testArray.printDeque();
        System.out.println(testArray.removeFirst());
        System.out.println(testArray.removeFirst());
        System.out.println(testArray.removeFirst());
        testArray.printDeque();
        testArray.addFirst(5);
        testArray.addFirst(5);
        testArray.addFirst(5);
        testArray.addFirst(11);
        testArray.addFirst(11);
        testArray.printDeque();

    }
    @Test
    public void testLoadFactorResize(){
        ArrayDeque testArray = new ArrayDeque();
        for (int i = 0; i <= 8; i ++){
            testArray.addFirst(i);
        }
        for (int j = 0; j <= 6; j ++){
            testArray.removeLast();
            testArray.printDeque();
        }


    }


}
