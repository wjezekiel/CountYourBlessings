/**
 * BST.java
 * @author Ezekiel Chow
 * CIS 22C Finals project
 */

import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    /***CONSTRUCTORS***/

    /**
     * Default constructor for BST
     * sets root to null
     */
    public BST() {
        root = null;

    }

    /**
     * Copy constructor for BST
     * @param bst the BST to make
     * a copy of
     */
    public BST(BST<T> bst) {
        if (bst != null) {
            copyHelper(bst.root);
        }
    }


    /**
     * Helper method for copy constructor
     * @param node the node containing
     * data to copy
     */
    private void copyHelper(Node node) {
        if (node == null){
            return;
        }
        if (node != null) {
            insert(node.data);
            copyHelper(node.left);
            copyHelper(node.right);
        }
    }

    /***ACCESSORS***/

    /**
     * Returns the data stored in the root
     * @precondition !isEmpty()
     * @return the data stored in the root
     * @throws NoSuchElementException when
     * preconditon is violated
     */
    public T getRoot() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("getRoot(): Tree is empty.");
        }
        return root.data;
    }

    /**
     * Determines whether the tree is empty
     * @return whether the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the current size of the
     * tree (number of nodes)
     * @return the size of the tree
     */
    public int getSize() {
        return getSize(root);

    }

    /**
     * Helper method for the getSize method
     * @param node the current node to count
     * @return the size of the tree
     */
    private int getSize(Node node) {
        if (node == null){
            return 0;
        }
        else {
            return getSize(node.left) + getSize(node.right) + 1;
        }

    }

    /**
     * Returns the height of tree by
     * counting edges.
     * @return the height of the tree
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * Helper method for getHeight method
     * @param node the current
     * node whose height to count
     * @return the height of the tree
     */
    private int getHeight(Node node) {
        int rightHeight = 0;
        int leftHeight = 0;
        if (node == null) {
            return -1;
        }
        else {
            rightHeight = getHeight(node.right);
            leftHeight = getHeight(node.left);

            if (rightHeight < leftHeight) {
                return leftHeight +1;
            }
            else {
                return rightHeight + 1;
            }
        }
    }

    /**
     * Returns the smallest value in the tree
     * @precondition !isEmpty()
     * @return the smallest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T findMin() throws NoSuchElementException{
        if(root == null){
            throw new NoSuchElementException("findMin: Tree is Empty");
        }
        return findMin(root);
    }

    /**
     * Helper method to findMin method
     * @param node the current node to check
     * if it is the smallest
     * @return the smallest value in the tree
     */
    private T findMin(Node node) {
        if(node.left != null){
            return findMin(node.left);
        }
        return node.data;
    }

    /**
     * Returns the largest value in the tree
     * @precondition !isEmpty()
     * @return the largest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T findMax() throws NoSuchElementException{
        if(root == null){
            throw new NoSuchElementException("findMax(): Tree is Empty" );
        }
        else {
            return findMax(root);
        }
    }

    /**
     * Helper method to findMax method
     * @param node the current node to check
     * if it is the largest
     * @return the largest value in the tree
     */
    private T findMax(Node node) {
        if(node.right != null){
            return findMax(node.right);
        }
        return node.data;
    }

    /**
     * Searches for a specified value
     * in the tree
     * @param data the value to search for
     * @return whether the value is stored
     * in the tree
     */
    public boolean search(T data) {
        if (root == null) {
            return false;
        }
        else {
            return search(data, root);
        }
    }

    /**
     * Helper method for the search method
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     * in the tree
     */
    private boolean search(T data, Node node) {
        if (data.compareTo(node.data) == 0){
            return true;
        }
        else if(data.compareTo(node.data) < 0){
            if (node.left == null){
                return false;
            }
            else {
                return search(data, node.left);
            }
        }
        else {
            if (node.right == null){
                return false;
            }
            else{
                return search(data, node.right);
            }
        }
    }


    /**
     * Determines whether two trees store
     * identical data in the same structural
     * position in the tree
     * @param o another Object
     * @return whether the two trees are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object o) {
        if(o == this){
            return true;
        }
        else if (!(o instanceof BST)){
            return false;
        }
        else {
            BST<T> b = (BST<T>) o;
            return equals(root,b.root);
        }
    }

    /**
     * Helper method for the equals method
     * @param node1 the node of the first bst
     * @param node2 the node of the second bst
     * @return whether the two trees contain
     * identical data stored in the same structural
     * position inside the trees
     */

    private boolean equals(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (!node1.data.equals(node2.data)) {
            return false;
        }
        return equals(node1.right, node2.right) && equals(node1.left, node2.left);
    }

    /***MUTATORS***/

    /**
     * Inserts a new node in the tree
     * @param data the data to insert
     */
    public void insert(T data) {
        if (root == null) {
            root = new Node(data);
        } else {
            insert(data,root);
        }
    }

    /**
     * Helper method to insert
     * Inserts a new value in the tree
     * @param data the data to insert
     * @param node the current node in the
     * search for the correct location
     * in which to insert
     */
    private void insert(T data, Node node) {
        if (data.compareTo(node.data) <= 0) {
            if(node.left == null) {
                node.left = new Node(data);
            } else {
                insert(data, node.left);
            }
        } else {
            if(node.right == null){
                node.right = new Node(data);
            } else {
                insert(data, node.right);
            }
        }
    }


    /**
     * Removes a value from the BST
     * @param data the value to remove
     * @precondition !isEmpty()
     * @precondition the data is located in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public void remove(T data) throws NoSuchElementException{
        if (root == null){
            throw new NoSuchElementException("remove(): Tree is Empty");
        }
        else if (!search(data)){
            throw new NoSuchElementException("remove(): The Value is Not In the BST");
        }
        else {
            remove(data,root);
        }
    }

    /**
     * Helper method to the remove method
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    private Node remove(T data, Node node) {
        if(node == null){
            return null;
        }
        else if(data.compareTo(node.data) < 0){
            node.left = remove(data, node.left);
        }
        else if(data.compareTo(node.data) > 0){
            node.right = remove(data, node.right);
        }
        else {
            if(node.left == null && node.right == null){
                node = null;
            }
            else if(node.left != null && node.right == null){
                node = node.left;
            }
            else if(node.right != null && node.left == null){
                node = node.right;
            }
            else {
                T temp = findMin(node.right);
                node.data = temp;
                node.right = remove(temp, node.right);
            }
        }
        return node;
    }



    /***ADDITIONAL OPERATIONS***/

    /**
     * Prints the data in pre order
     * to the console
     */
    public void preOrderPrint() {
        preOrderPrint(root);
    }

    /**
     * Helper method to preOrderPrint method
     * Prints the data in pre order
     * to the console
     */
    private void preOrderPrint(Node node) {
        if (node == null){
            return;
        }
        else {
            System.out.print(node.data);
            System.out.println();
            preOrderPrint(node.left);
            preOrderPrint(node.right);
        }

    }

    /**
     * Prints the data in sorted order
     * to the console
     */
    public void inOrderPrint() {
        inOrderPrint(root);
    }

    /**
     * Helper method to inOrderPrint method
     * Prints the data in sorted order
     * to the console
     */
    private void inOrderPrint(Node node) {
        if (node == null) {
            return;
        }
        else {
            inOrderPrint(node.left);
            System.out.print(node.data);
            System.out.println();
            inOrderPrint(node.right);
        }
    }

    /**
     * Prints the data in post order
     * to the console
     */
    public void postOrderPrint() {
        postOrderPrint(root);
    }

    /**
     * Helper method to postOrderPrint method
     * Prints the data in post order
     * to the console
     */
    private void postOrderPrint(Node node) {
        if (node == null) {
            return;
        }
        else {
            postOrderPrint(node.left);
            postOrderPrint(node.right);
            System.out.print(node.data);
            System.out.println();
        }
    }
}