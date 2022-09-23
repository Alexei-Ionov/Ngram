package deque;
import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private int size;
    private T[] array;
    private Comparator<T> comp;
    private int nextFirst;
    private int nextLast;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T currMax = get(0);
        for (int cnt = 1; cnt < size(); cnt++) {
            T nextVal = get(cnt);
            if ((comp.compare(currMax, nextVal) < 1)) {
                currMax = nextVal;
            }
        }
        return currMax;
    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T currMax = get(0);
        for (int cnt = 1; cnt < size(); cnt++) {
            T nextVal = get(cnt);
            if ((c.compare(currMax, nextVal) < 1)) {
                currMax = nextVal;
            }
        }
        return currMax;
    }
}
