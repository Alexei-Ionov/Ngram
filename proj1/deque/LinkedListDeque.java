package deque;
import java.util.Iterator;
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T val;
        private Node next;
        private Node prev;

        private Node(T i, Node p, Node n) {
            val = i;
            next = n;
            prev = p;
        }
    }
    private Node sentinel;
    private int size;

    // constructor in the case that input is null; i.e, just create a lone sentinel node!
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }

    private void addToEmptyList(T val) {
        sentinel.next = new Node(val, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    @Override
    public void addFirst(T val) {
        if (isEmpty()) {
            addToEmptyList(val);
        } else {
            Node temp = sentinel.next;
            sentinel.next = new Node(val, sentinel, temp);
            temp.prev = sentinel.next;
            size += 1;
        }
    }
    @Override
    public void addLast(T val) {
        if (isEmpty()) {
            addToEmptyList(val);
        } else {
            Node last = new Node(val, sentinel.prev, sentinel);
            sentinel.prev.next = last;
            sentinel.prev = last;
            size += 1;
        }
    }
    @Override
    public void printDeque() {
        Node currNode = sentinel.next;
        while (currNode != null) {
            System.out.print(currNode.val + " ");
            if (currNode.next == sentinel) {
                break;
            }
            currNode = currNode.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {

            return null;
        } else {
            T res = sentinel.next.val;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return res;
        }
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T res = sentinel.prev.val;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return res;
        }
    }
    @Override
    public T get(int index) {
        int currIndex = 0;
        Node head = sentinel.next;
        while (head != null) {
            if (currIndex == index) {
                return head.val;
            }
            currIndex += 1;
            head = head.next;
        }

        return null;
    }
    private T getRecursionHelperFn(int index, Node head) {
        if (head == sentinel) {
            return null;
        }
        if (index == 0) {
            return head.val;
        }
        return getRecursionHelperFn(index - 1, head.next);
    }

    public T getRecursive(int index) {
        Node head = sentinel.next;
        return getRecursionHelperFn(index, head);
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
    public Iterator<T> iterator() {
        LinkedListIterator iter = new LinkedListIterator();
        return iter;
    }
    private class LinkedListIterator implements Iterator<T> {
        private Node iterNode;
        public LinkedListIterator() {
            iterNode = sentinel.next;
        }
        public boolean hasNext() {
            if (isEmpty()) {
                return false;
            } else if (iterNode == sentinel) {
                return false;
            }
            return true;
        }
        public T next() {
            T val = iterNode.val;
            iterNode = iterNode.next;
            return val;
        }
    }
}