public class ArrayDeque2<T>{
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] array;
    public ArrayDeque2(){
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = array.length/2;
        nextFirst = nextFirst + 1;
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
            array[nextFirst % array.length + 1] = item;
        } else {
            array[nextFirst] = item;
        }
        size += 1;
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

    public boolean isEmpty(){
        return (size ==0);
    }
    private boolean isFull(){
        return (size == array.length);

    }





    //notes:
    //for modulo operator: next first will always be decrmeneted and when doing array stuff will ne nextfirst % array.length +1. for next last, it will always be incrementing by 1 and netxLast & array.length for array








    public int size(){
        return size;
    }
    private int getLastIndex(){
        return array.length - 1;
    }

}
