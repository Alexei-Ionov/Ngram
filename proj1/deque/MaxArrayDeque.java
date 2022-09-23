package  deque;
import deque.ArrayDeque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private T[] maxArray;
    private int cnt;
    private Comparator<T> comp;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        maxArray = (T[]) new Object[8];
        cnt = 0;
        comp = c;
    }
    public T max() {
        if (isEmpty()) {
            return null;
        }
        int nextIndex = getNextFirst(nextFirst);
        T currMax = maxArray[nextIndex];
        while (cnt <= size){
            if (comp.compare(currMax, maxArray[nextIndex]) < 1) {
                currMax = maxArray[nextIndex];
            }
            nextIndex = getNextFirst(nextIndex);
            cnt += 1;
        }
        return currMax;

    }
    public T max(Comparator<T> c){
        if (isEmpty()){
            return null;
        }
        int nextIndex = getNextFirst(nextFirst);
        T currMax = maxArray[nextIndex];
        while (cnt <= size){
            if (c.compare(currMax, maxArray[nextIndex]) < 1) {
                currMax = maxArray[nextIndex];
            }
            nextIndex = getNextFirst(nextIndex);
            cnt += 1;
        }
        return currMax;


    }
    private int getNextFirst(int i) {
        return (i + 1) % (array.length);
    }

}
