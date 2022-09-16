package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTests {

    @Test
    public void test_Flick_true(){
        boolean expected = true;
        int x = 128;
        int y = 128;
        boolean actual = Flik.isSameNumber(x, y);
        assertEquals(expected, actual);
    }

    @Test
    public void test_Flick_false(){
        boolean expected = false;
        int x = 7;
        int y = 5;
        boolean actual = Flik.isSameNumber(x, y);
        assertEquals(expected, actual);
    }

    @Test
    public void test_Horrible_Steve(){
        String[] input = new String[]{"hi", "bye"};


    }


}
