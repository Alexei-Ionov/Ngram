package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    public int size;
    public int nextFirst;
    public int nextLast;

    public T[] array;
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = array.length / 2;
        nextLast = nextFirst + 1;
        //the beauty of this: if nextFirst is never called, we know the first element will actually be at its position since
        //it will be the last position for nextLast to reach!!!!
    }
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        //double the array length. put the first "array" (which will be sorted) into the first half, then what is left will be what will be left to fill of the second half.
        //for my first nextFirst, it will be off by one, so I need to bump it up.
        int newStartingPos = newSize / 4;
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
        if ((array.length >= 16) && (!loadFactorChecker())) {
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
        if ((array.length >= 16) && (!loadFactorChecker())) {
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
        arrayIterator iter = new arrayIterator();
        return iter;
    }
    private class arrayIterator implements Iterator<T> {
        private int pos;
        private int cnt;

        public arrayIterator() {
            pos = getNextFirst(nextFirst);
            //lets me track the amount of elements ive already gone thru
            cnt = 0;
        }

        public boolean hasNext() {
            if (cnt > size()) {
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

