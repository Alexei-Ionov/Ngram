package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private int cnt;
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] array;
    private Comparator<T> comp;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        cnt = 0;
        comp = c;
    }
    public T max() {
        if (this.array == null) {
            return null;
        }
        if (isEmpty()) {
            return null;
        }
        int nextIndex = getNextFirst(nextFirst);
        T currMax = array[nextIndex];
        while (cnt < size) {
            if (comp.compare(currMax, array[nextIndex]) < 1) {
                currMax = array[nextIndex];
            }
            nextIndex = getNextFirst(nextIndex);
            cnt += 1;
        }
        return currMax;

    }
    public T max(Comparator<T> c) {
        if (this.array == null) {
            return null;
        }
        if (isEmpty()) {
            return null;
        }
        int nextIndex = getNextFirst(nextFirst);
        T currMax = array[nextIndex];
        while (cnt < size) {
            if (c.compare(currMax, array[nextIndex]) < 1) {
                currMax = array[nextIndex];
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
