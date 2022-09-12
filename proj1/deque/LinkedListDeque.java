package deque;
public class LinkedListDeque<T> {
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

    // constructor in the case that input is not null!
    /**
    public LinkedListDeque(T x) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
     */

    public int size() {
        return size;
    }

    private void Add_To_Empty_List(T val) {
        sentinel.next = new Node(val, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T val) {
        if (isEmpty()) {
            Add_To_Empty_List(val);
        }
        else {
            Node temp = sentinel.next;
            sentinel.next = new Node(val, sentinel, temp);
            temp.prev = sentinel.next;
            size += 1;
        }
    }
    public void addLast(T val) {
        if (isEmpty()) {
            Add_To_Empty_List(val);
        }
        else {
            Node last = new Node(val, sentinel.prev, sentinel);
            sentinel.prev.next = last;
            sentinel.prev = last;
            size += 1;
        }
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    public void printDeque() {
        Node Curr_Node = sentinel.next;
        while (Curr_Node != null) {
            System.out.print(Curr_Node.val + " ");
            if (Curr_Node.next == sentinel) {
                break;
            }

            Curr_Node = Curr_Node.next;
        }
        System.out.println();
    }
    public T removeFirst() {
        if (isEmpty()) {

            return null;
        }
        else {
            T res = sentinel.next.val;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return res;
        }
    }
    public T removeLast() {
        if (isEmpty()) {

            return null;
        }
        else {
            T res = sentinel.prev.val;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return res;
        }
    }
    public T get(int index) {
        int Curr_Index = 0;
        Node head = sentinel.next;
        while (head != null) {
            if (Curr_Index == index) {
                return head.val;
            }
            Curr_Index += 1;
            head = head.next;
        }
        return null;
    }
    private T Get_Recursion_Helper_Fn(int index, Node head) {
        if (head == sentinel) {
            return null;
        }
        if (index == 0) {
            return head.val;
        }
        return Get_Recursion_Helper_Fn(index - 1, head.next);
    }

    public T getRecursive(int index) {
        Node head = sentinel.next;
        return Get_Recursion_Helper_Fn(index, head);
    }
}
