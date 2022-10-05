package bstmap;
import java.util.Set;
import java.util.Iterator;
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {
    private class Node {
        private Node left;
        private Node right;
        private int size;
        private K key;
        private V val;
        private Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = null;
            right = null;
            size = 1;
        }
    }
    private Node root;
    public BSTMap() {
        root = null;
    }
    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size;
    }
    private boolean isEmpty() {
        return root.size == 0;
    }
    @Override
    public void clear() {
        root = null;
    }
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }
    private boolean containsKeyHelper(Node root, K key) {
        if (getNode(root, key) != null) {
            return true;
        }
        return false;
    }
    @Override
    public V get(K key) {
        Node nodeOfInterest = getNode(root, key);
        if (nodeOfInterest != null) {
            return nodeOfInterest.val;
        }
        return null;
    }
    private Node getNode(Node root, K key) {
        if (root == null) {
            return null;
        }
        int comparatorVal = key.compareTo(root.key);
        if (comparatorVal < 0) {
            //check left
            return getNode(root.left, key);
        } else if (comparatorVal > 0) {
            // check right
            return getNode(root.right, key);
        } else {
            return root;
        }
    }
    @Override
    public void put(K key, V value) {
        Node nodeOfInterest = getNode(root, key);
        //node with key already exists in the map
        if (nodeOfInterest != null) {
            nodeOfInterest.val = value;
        }
        else {
            putHelper(root, key, value);
        }
    }
    private Node putHelper(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }
        int comparatorVal = key.compareTo(root.key);
        if (comparatorVal < 0) {
            root.left = putHelper(root.left, key, value);
        } else if (comparatorVal > 0) {
            root.right = putHelper(root.left, key, value);
        }
        return root;
    }
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    public K iterator() {
        throw new UnsupportedOperationException();
    }
}
