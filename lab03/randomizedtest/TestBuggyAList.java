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
    public void testThreeAddThreeRemove(AList lst){
        BuggyAList buggy_lst = new BuggyAList();
        AList good_lst = new AList();
        int size = 0;
        for (int i = 4; i <=6; i++){
            buggy_lst.addLast(i);
            good_lst.addLast(i);
            size +=1;
        }

        while (size >=0){
            buggy_lst.removeLast();
            good_lst.removeLast();

        }









    }}



