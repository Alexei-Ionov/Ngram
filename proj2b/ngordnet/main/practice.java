package ngordnet.main;

import java.util.ArrayList;
import java.util.Random;

public class practice {
    public ArrayList<ArrayList<Integer>> practice() {
        ArrayList<ArrayList<Integer>> doubled = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i =0 ; i < 5; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Random randval = new Random();
                int val = randval.nextInt();
                temp.add(val);

            }
            doubled.add(temp);
        }
        res.addAll(doubled);

        return res;
    }
    public static void main(String[] args) {
        practice temp = new practice();
        System.out.println(temp.practice());

    }
}
