package structure;

public class TreeSet<K extends Comparable<K>, V> {

    Node<K, V> root = null;

    public void add(K key, V value) {
        root = addRecursive(root, key, value);
    }

    public void delete(K key) {
        root = deleteRecursive(root, key);
    }

    public V find(K key) {
        Node<K, V> node = findNode(key);
        if (node != null) {
            return node.getValue();
        }
        return null;
    }

    private Node<K, V> findNode(K key) {
        return findTreeNodeRecursive(root, key);
    }

    private Node<K, V> addRecursive(Node<K, V> current, K key, V value) {
        if (current == null) {
            return new Node<>(key, value);
        }

        if (current.compareTo(key) > 0) {
            current.left = addRecursive(current.left, key, value);
        }
        if (current.compareTo(key) < 0) {
            current.right = addRecursive(current.right, key, value);
        }

        return current;
    }

    private Node<K, V> findTreeNodeRecursive(Node<K, V> current, K key) {
        if (current == null || current.key.compareTo(key) == 0) {
            return current;
        }
        if (current.compareTo(key) > 0) {
            return findTreeNodeRecursive(current.left, key);
        } else {
            return findTreeNodeRecursive(current.right, key);
        }
    }

    private Node<K, V> deleteRecursive(Node<K, V> current, K key) {

        if (current == null) {
            return null;
        }

        if (current.key.compareTo(key) == 0) {
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            Node<K, V> minNode = findMinNode(current.right);
            current.key = minNode.key;
            current.value = minNode.value;
            current.right = deleteRecursive(current.right, current.key);

        } else if (current.key.compareTo(key) > 0) {
            current.left = deleteRecursive(current.left, key);
            return current;

        }

        current.right = deleteRecursive(current.right, key);
        return current;

    }

    private Node<K, V> findMinNode(Node<K, V> current) {
        if (current == null)
            return null;
        if (current.left != null) {
            return findMinNode(current.left);
        }
        return current;
    }


    private static class Node<K extends Comparable<K>, V> {

        Node<K, V> left;
        Node<K, V> right;
        K key;
        V value;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            right = null;
            left = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int compareTo(K key) {
            return this.key.compareTo(key);
//            if (key instanceof String) {
//                if (((String) key).compareTo((String) this.key) >= 0) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            } else {
//                if ((Integer) key >= (Integer) this.key) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
        }
    }
}




