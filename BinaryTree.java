public class BinaryTree<E extends Comparable<E>> {
    private static class Node<E> {
        private E data;
        private Node<E> dad;
        private Node<E> root;
        private Node<E> leftSon;
        private Node<E> rightSon;

        public Node(E data) {
            this.data = data;
            this.dad = null;
            this.leftSon = null;
            this.rightSon = null;
        }
    }

    private Node<E> root;
    private int size;

    public BinaryTree() {
        this.size = 0;
        this.root = null;
    }

    public void insert(E value) {
        root = insertRecursive(root, value);
    }

    public Node<E> insertRecursive(Node<E> node, E value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }

        if (value.compareTo(node.data) < 0) {
            node.leftSon = insertRecursive(node.leftSon, value);
        } else if (value.compareTo(node.data) > 0) {
            node.rightSon = insertRecursive(node.rightSon, value);
        }
        return node;
    }

    private boolean contais(E value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node<E> node, E value) {
        if (node == null) {
            return false;
        }

        int compare = value.compareTo(node.data);

        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            containsRecursive(node.leftSon, value);
        } else {
            containsRecursive(node.rightSon, value);
        }
        return false;
    }

    public boolean isEmpty() {return size == 0;}
}
