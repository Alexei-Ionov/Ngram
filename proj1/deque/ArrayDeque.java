package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] array;
    private static final int MIN_INT_FOR_RESIZE = 16;
    private static final int BOTVAL = 4;
    private static final int CAPACITY = 8;
    public ArrayDeque() {
        array = (T[]) new Object[CAPACITY];
        size = 0;
        nextFirst = array.length / 2;
        nextLast = nextFirst + 1;
    }
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        int newStartingPos = newSize / BOTVAL;
        int wrappingIndex = getNextFirst(nextFirst);
        for (int j = newStartingPos; j < (size + newStartingPos); j++) {
            newArray[j] = array[wrappingIndex];
            wrappingIndex = getNextFirst(wrappingIndex);
        }
        array = newArray;
        nextFirst = newStartingPos - 1;
        nextLast = size + newStartingPos;
    }
    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        array[nextLast] = item;
        size += 1;
        //allows for clean circular motion of index --> end to front
        nextLast = (nextLast + 1) % (array.length);
    }
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        array[nextFirst] = item;
        size += 1;

        //allows for wrapping around --> front to end
        nextFirst = (nextFirst - 1 + array.length) % array.length;
    }
    //gets me the prev "next first" index
    private int getNextFirst(int i) {
        return (i + 1) % (array.length);
    }

    @Override
    public void printDeque() {
        int wrappingIndex = getNextFirst(nextFirst);
        for (int j = 0; j < size; j++) {
            System.out.print(array[wrappingIndex] + " ");
            //gets me the next first index --> this way i will unfold the array... so to speak
            wrappingIndex = getNextFirst(wrappingIndex);
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((array.length >= MIN_INT_FOR_RESIZE) && (!loadFactorChecker())) {
            resize(array.length / 2);
        }
        int indexToRemove = getNextFirst(nextFirst);
        T val = array[indexToRemove];
        array[indexToRemove] = null;
        size -= 1;
        nextFirst = indexToRemove;
        return val;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((array.length >= MIN_INT_FOR_RESIZE) && (!loadFactorChecker())) {
            resize(array.length / 2);
        }
        int indexToRemove = (nextLast - 1 + array.length) % (array.length);
        T val = array[indexToRemove];
        array[indexToRemove] = null;
        size -= 1;
        nextLast = indexToRemove;
        return val;
    }
    @Override
    public T get(int index) {
        if ((isEmpty()) || (index >= array.length)) {
            return null;
        }
        int wrappingIndex = getNextFirst(nextFirst);
        return array[(wrappingIndex + index) % (array.length)];
    }
    public boolean equals(Object o) {
        if (!(o instanceof Deque<?>)) {
            return false;
        }
        Deque newO = (Deque) o;
        if ((isEmpty()) && ((Deque<?>) o).isEmpty()) {
            return true;
        }
        if ((isEmpty()) && !((Deque<?>) o).isEmpty()) {
            return false;
        }
        if ((((Deque<?>) o).isEmpty()) && !isEmpty()) {
            return false;
        }
        if (size != newO.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(this.get(i).equals(newO.get(i)))) {
                return false;
            }
        }
        return true;
    }
    private boolean isFull() {
        return (size == array.length);
    }
    private boolean loadFactorChecker() {
        return (0.25 <= (float) size / array.length);
    }
    @Override
    public int size() {
        return size;
    }
    private int getLastIndex() {
        return (array.length - 1);
    }
    public Iterator<T> iterator() {
        ArrayIterator iter = new ArrayIterator();
        return iter;
    }
    private class ArrayIterator implements Iterator<T> {
        private int pos;
        private int cnt;

        public ArrayIterator() {
            pos = getNextFirst(nextFirst);
            //lets me track the amount of elements ive already gone thru
            cnt = 0;
        }

        public boolean hasNext() {
            if (cnt >= size()) {
                return false;
            }
            return true;
        }
        public T next() {
            T val = array[pos];
            pos = getNextFirst(pos);
            cnt += 1;
            return val;
        }
    }
}