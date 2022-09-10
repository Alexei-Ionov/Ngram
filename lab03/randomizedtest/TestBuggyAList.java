package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;
import org.junit.Assert;


import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {


    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList buggy_lst = new BuggyAList();
        AListNoResizing good_lst = new AListNoResizing();
        int size = 0;
        for (int i = 4; i <= 6; i++) {
            buggy_lst.addLast(i);
            good_lst.addLast(i);
            size += 1;
        }
        assertEquals(buggy_lst.size(), good_lst.size());
        while (size > 0) {

            assertEquals(buggy_lst.removeLast(), good_lst.removeLast());
            size -= 1;

        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList buggy_lst = new BuggyAList();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggy_lst.addLast(randVal);


            }
            else if (operationNumber == 1) {
                // size
                int Lsize = L.size();
                int Buggysize = buggy_lst.size();
                System.out.println("Lsize: " + Lsize);
                System.out.println("Buggysize: " + Buggysize);


            }
            else if (operationNumber == 2) {
                // size
                if (L.size() >0){
                    int L_last_elem = L.getLast();
                    int buggy_last_elem = L.getLast();
                    assertEquals(L_last_elem, buggy_last_elem);
            }
            else{

                if (L.size() >0){
                    assertEquals(buggy_lst.removeLast(), L.removeLast());


                }

            }

        }

    }}}




        // some checks to make during test: sizes are the same, elemnts are the sa}



