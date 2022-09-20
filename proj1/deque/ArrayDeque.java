package deque;
public class ArrayDeque<T>{
    private int size;
    private int nextFirst;
    private int nextLast;
    private int numOfAddFirstCalls;

    private T[] array;
    public ArrayDeque(){
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = array.length/2;
        nextLast = nextFirst + 1;
        numOfAddFirstCalls = 0;

    }
    private void resize(int newSize){
        T[] newArray = (T[]) new Object[newSize];
        //since I will be doubling on the resize, then i want half the current lenght of empty space on both the left and right of what we currently have!
        int newStartingPos = newArray.length/4;
        System.arraycopy(array, 0, newArray, newStartingPos, size());
        nextFirst = newStartingPos - 1;
        nextLast = newStartingPos + size();
        array = newArray;
    }

    public void addFirst(T item){
        if (isFull()){
            resize(size * 2);
        }
        if (nextFirst < 0){

            array[array.length + nextFirst] = item;
        } else {
            array[nextFirst] = item;
        }
        size += 1;
        numOfAddFirstCalls += 1;
        nextFirst -= 1;
    }
    public void addLast(T item) {

        if (isFull()) {
            resize(size * 2);
        }
        array[nextLast % array.length] = item;
        size += 1;
        nextLast += 1;
    }
    public void printDeque() {
        for (int i = 0; i < array.length;  i ++){
            if (array[i] != null){
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (array.length >= 16 && !loadFactorChecker()) {
            resize(array.length / 2);
        }
        if (nextFirst < 0) {
            T val = array[nextFirst + 1 + array.length];
            array[nextFirst + 1 + array.length] = null;
            size -= 1;
            numOfAddFirstCalls -= 1;
            nextFirst += 1;
            return val;

        } else {
            T val = array[nextFirst + 1];
            array[nextFirst + 1] = null;
            size -= 1;
            nextFirst += 1;
            numOfAddFirstCalls -= 1;
            return val;
        }
    }
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        if (array.length >= 16 && !loadFactorChecker()){
            resize(array.length/2);
        }
        //edge case, last is on the last element of array
        if (nextLast == array.length){
            T val = array[array.length -1];
            array[array.length - 1] = null;
            size -= 1;
            nextLast -= 1;
            return val;

        }
        // formula works for all other cases
        T val = array[nextLast % array.length - 1];
        array[nextLast % array.length - 1] = null;
        size -= 1;
        nextLast -= 1;
        return val;
    }
    public T get(int index){
        if ((isEmpty()) || (index >= array.length)){
            return null;
        }
        if (numOfAddFirstCalls > 0){
            if (nextFirst < 0){
                if (size == array.length){
                    return array[(nextFirst + 1 + index + array.length) % array.length];
                }
                return array[(nextFirst + index + array.length) % array.length];


              } else {
                return array[nextFirst + 1 + index];
             }
         }
          // in all other cases, there at least to have had been one call to addLast
        // we can track the order based on the calls to addLast
        int back  = array.length - index - 1;
        if (size == array.length){
            back += 1;
        }
        if (back > nextFirst){
            int val = (back + nextFirst) % array.length;
            return array[array.length - val];
        }
        return array[(nextLast % array.length) - back];

    }
    public boolean isEmpty(){
        return (size ==0);
    }
    private boolean isFull(){
        return (size == array.length);

    }
    private boolean loadFactorChecker(){
        return (0.25 <= (float)size/array.length);
    }
    public int size(){
        return size;
    }
    private int getLastIndex(){
        return array.length - 1;
    }

}





    //notes:
    //for modulo operator: next first will always be decrmeneted and when doing array stuff will ne nextfirst % array.length +1. for next last, it will always be incrementing by 1 and netxLast & array.length for array




