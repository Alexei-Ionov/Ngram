package deque;


public class LinkedListDeque<Item> {
    private class Node{
        public Item val;
        public Node next;
        public Node prev;

        public Node(Item i, Node p, Node n){
            val = i;
            next = n;
            prev = p;
        }
    }
    private Node sentinel;
    private int size;

    // constructor in the case that input is null; i.e, just create a lone sentinel node!
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        size = 0;

    }
    // constructor in the case that input is not null!
    public LinkedListDeque(Item x){
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public int size(){
        return size;
    }

    public void add_to_empty_lst(Item val){
        sentinel.next = new Node(val, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;

    }

    public void addFirst(Item val){
        if (isEmpty()) {
            add_to_empty_lst(val);
        }
        else {
            Node temp = sentinel.next;
            sentinel.next = new Node(val, sentinel, temp);
            temp.prev = sentinel.next;
            size +=1;
        }

    }
    public void addLast(Item val){
        if (isEmpty()){
            add_to_empty_lst(val);
        }
        else{
            Node last = new Node(val, sentinel.prev, sentinel);
            sentinel.prev.next = last;
            sentinel.prev = last;
            size +=1;
        }

    }
    public boolean isEmpty(){
        if (size ==0){
            return true;

        }
        return false;

    }

    public void printDeque(){
        Node curr_node = sentinel.next;
        while (curr_node != null){
            System.out.print(curr_node.val + " ");
            if (curr_node.next == sentinel){
                break;
            }

            curr_node = curr_node.next;
        }
        System.out.println();
    }
    public Item removeFirst(){
        if (isEmpty()){

            return null;
        }
        else{
            Item res = sentinel.next.val;

            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -=1;

            return res;

        }

    }
    public Item removeLast() {
        if (isEmpty()) {

            return null;
        }
        else {
            Item res = sentinel.prev.val;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return res;
        }
    }
    public Item get(int index){
        int curr_index = 0;
        Node head = sentinel.next;
        while (head != null){
            if (curr_index == index){
                return head.val;
            }
            curr_index +=1;
            head = head.next;
        }
        return null;


    }

    /** public boolean compare_DLL(LinkedListDeque o, Node head){
        Node compared_head = o.sentinel.next;
        while((compared_head != null) && (head != null)){
            if (compared_head.val != head.val){
                return false;
            }
            compared_head = compared_head.next;
            head = head.next;
        }
        if ((head == null && compared_head != null) || (compared_head == null && head != null)){
            return false;
        }
        return true;
    }

     */


    //NOT NEEDED FOR PROJ1a, comes later for part C!
    /**
    public boolean equals(Object o){
        if (o instanceof LinkedListDeque){
            return o.equals(sentinel.next);
        }
        return false;
    }

     */





    public Item getRecursion_helper_fn(int index, Node head){
        if (head == sentinel){
            return null;
        }
        if (index == 0){
            return head.val;
        }
        return getRecursion_helper_fn(index -1, head.next);
    }

    public Item getRecursive(int index){
        Node head = sentinel.next;
        return getRecursion_helper_fn(index, head);
    }




    public static void main(String[] args){
        /// should call her input-ed LInked list deque. however, this means that temp if input is null is also null so will get eror

        LinkedListDeque attempt= new LinkedListDeque(3);
        attempt.addFirst(5);
        attempt.addFirst(10);

        attempt.addFirst(15);

        attempt.addFirst(20);
        attempt.removeFirst();
        attempt.printDeque();
        attempt.removeFirst();
        attempt.printDeque();
        attempt.removeLast();
        attempt.printDeque();







    }


}
