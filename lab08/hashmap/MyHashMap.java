package hashmap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Collection;
/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private static int startSize = 16;
    private static double loadFactor = 0.75;

    private int bucketCount;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        size = 0;
        buckets = createTable(startSize);
        bucketCount = startSize;

    }

    public MyHashMap(int initialSize) {
        startSize = initialSize;
        buckets = createTable(startSize);
        bucketCount = startSize;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        startSize = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(startSize);
        bucketCount = startSize;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }
    private void resize(int newSize) {
        bucketCount = newSize;
        // allows me to track all the old stuff
        Collection<Node>[] tempTable = buckets;
        buckets = createTable(bucketCount);
        for (Collection<Node> bucket : tempTable) {
            for (Node currNode : bucket) {
                int hashcode = Math.floorMod(currNode.key.hashCode(), bucketCount);
                Collection head = buckets[hashcode];
                head.add(currNode);
            }
        }
    }

    private boolean oversized() {
        return (double) (size / bucketCount) > loadFactor;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void clear() {
        buckets = createTable(bucketCount);
        size = 0;

    }
    @Override
    public boolean containsKey(K key) {
        int hashcode = Math.floorMod(key.hashCode(), bucketCount);
        if (buckets[hashcode] == null) {
            return false;
        }
        Node nodeOfInterest = nodeGetter(key);
        return nodeOfInterest != null;
    }
    private Node nodeGetter(K key) {
        int hashcode = Math.floorMod(key.hashCode(), bucketCount);
        for (Node currNode : buckets[hashcode]) {
            if (currNode.key.equals(key)) {
                return currNode;
            }
        }
        return null;
    }
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node nodeOfInterest = nodeGetter(key);
        if (nodeOfInterest != null) {
            return nodeOfInterest.value;
        }
        return null;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void put(K key, V value) {
        if (oversized()) {
            resize(bucketCount * 2);
        }
        if (containsKey(key)) {
            Node node = nodeGetter(key);
            node.value = value;
        } else {
            int hashcode = Math.floorMod(key.hashCode(), bucketCount);
            Collection head = buckets[hashcode];
            Node newNode = createNode(key, value);
            head.add(newNode);
            size += 1;
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }













}

