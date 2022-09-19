package deque;

public class ArrayDeque<T> {
    private int size;
    private int firstIndex;
    private int lastIndex;
    private T[] array;
    private int leftmostIndex;
    private int rightmostIndex;
    public ArrayDeque(){
        array = (T[]) new Object[8];
        size = 0;
        firstIndex = Math.floorDiv(array.length, 2);
        lastIndex = firstIndex + 1;
        leftmostIndex = firstIndex;
        rightmostIndex = lastIndex;
    }
    public int size(){
        return size;
    }

    private int getLastIndex(){
        return array.length - 1;
    }

    public void resize(int newSize){
        T[] newArray = (T[]) new Object[newSize];
        //since I will be doubling on the resize, then i want half the current lenght of empty space on both the left and right of what we currently have!

        int newStartingPosition = newArray.length/4;
        int j = newStartingPosition;

        for (int i = leftmostIndex; i <= rightmostIndex; i ++){
            newArray[newStartingPosition] = array[i];
            newStartingPosition += 1;
        }
        array = newArray;
        firstIndex = j - 1;
        lastIndex = array.length - j;
        leftmostIndex = j;
        rightmostIndex = newStartingPosition - 1;

    }
    public void addFirst(T item){
        // if array is full, resize!
        if (noRoomLeft()) {
            resize(size * 2);
        }
        //check if firstIndex is still valid, if not: switch it to other side!
        if (firstIndex < 0){
            leftmostIndex = 0;
            firstIndex = getLastIndex();
            rightmostIndex = getLastIndex();
        }
        array[firstIndex] = item;
        size += 1;
        if (firstIndex < leftmostIndex){
            leftmostIndex = firstIndex;
        }
        if (noRoomLeft()){
            firstIndex = leftmostIndex - 1;
            lastIndex = rightmostIndex + 1;

        }
        firstIndex -= 1;

    }
    public void addLast(T item){
        if (noRoomLeft()){
            resize(size * 2);
        }
        if (lastIndex >= array.length){
            rightmostIndex = getLastIndex();
            lastIndex = 0;
            leftmostIndex = 0;
        }
        array[lastIndex] = item;
        size += 1;
        if (lastIndex > rightmostIndex){
            rightmostIndex = lastIndex;
        }

        if (noRoomLeft()){
            lastIndex = rightmostIndex + 1;
            firstIndex = leftmostIndex - 1;
        }
        lastIndex += 1;
    }
    public void printDeque() {
        for (int i = leftmostIndex; i <= rightmostIndex; i ++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    public boolean noRoomLeft(){
        return (size == array.length);
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        if (array.length >= 16 && !loadFactorChecker()){
            resize(array.length/2);
        }
        T val = array[leftmostIndex];
        array[leftmostIndex] = null;
        firstIndex = leftmostIndex;
        leftmostIndex += 1;
        size -= 1;
        return val;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (array.length >= 16 && !loadFactorChecker()) {
            resize(array.length / 2);
        }
        T val = array[rightmostIndex];
        array[rightmostIndex] = null;
        lastIndex = rightmostIndex;
        rightmostIndex -= 1;
        size -= 1;
        return val;
    }
    public T get(int index){
        if (isEmpty()){
            return null;
        }
        return array[index];
    }
    private boolean loadFactorChecker(){
        return (0.25 <= (float)size/array.length);
    }





// notes:

    /**
     * I need to null out unused spots!
     * For arrays of length 16 or more, your usage factor should always be at least 25%. This means that before performing a remove operation that will bring the number of elements
     * in the array under 25% the length of the array, you should resize the size of the array down. For arrays under length 16, your usage factor can be arbitrarily low.
     */



}
