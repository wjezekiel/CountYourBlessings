/**
 * Defines a singly-linked list class
 * @author Ezekiel Chow
 * CIS 22C, Finals Project
 */

import java.util.NoSuchElementException;

public class List<T> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /****CONSTRUCTOR****/

    /**
     * Instantiates a new List with default values
     * @postcondition
     */
    public List() {
        first = null;
        last = null;
        length = 0;
        iterator = null;
    }

    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<T> original) {
        if (original == null) {
            return;
        }
        if (original.length == 0) {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        }

        else {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored in the first node
     * @precondition length != 0
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
        }
        return first.data; //why do we return first.data and not first?
    }

    /**
     * Returns the value stored in the last node
     * @precondition length != 0
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("getLast: List is Empty. No data to access!");
        }
        return last.data; //why do we return last.data and not last?
    }

    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /****MUTATORS****/

    /**
     * Creates a new first element
     * @param data the data to insert at the
     * front of the list
     * @postcondition
     */
    public void addFirst(T data) {
        if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            N.next = first;
            first.prev = N;
            first = N;
        }
        length++;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the
     * end of the list
     * @postcondition
     */
    public void addLast(T data) {
        if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            last.next = N;
            N.prev = last;
            last = N;
        }
        length++;
    }

    /**
     * removes the element at the front of the list
     * @precondition length != 0
     * @postcondition
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeFirst() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
        }
        else if (length == 1) {
            first = last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        length--;
    }

    /**
     * removes the element at the end of the list
     * @precondition length != 0
     * @postcondition
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("removeLast: list is empty. Nothing to remove.");
        } else if (length == 1) {
            first = last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        length--;
    }
    /**
     * Returns the value stored in the first node
     * @precondition iterator != null
     * @return the element currently pointed at by the iterator
     * @throws NoSuchElementException when precondition is violated
     */
    public T getIterator() throws NullPointerException{
        if (iterator == null) {
            throw new NullPointerException("getIterator: Iterator is null. There is no such element to access");
        }
        return iterator.data;
    }
    /**
     * moves the iterator to the start of the list
     */
    public void placeIterator() {
        iterator = first;
    }

    /**
     * removes the element currently pointed to by the iterator
     * @precondition Iterator cannot be null
     * @postcondition Iterator then points to null
     */
    public void removeIterator() throws NullPointerException{
        if (iterator == null) {
            throw new NullPointerException("removeIterator: Iterator is null. There is no such element to remove.");
        }
        else if (iterator == first) {
            removeFirst();
        }
        else if (iterator == last) {
            removeLast();
        }
        else {
            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            length--;
        }
        iterator = null;
    }

    /**
     * inserts an element after the node currently pointed to by the iterator
     * @param data the new data to insert
     * @precondition !offEnd()
     * @throws NullPointerException when the precondition is violated
     */
    public void addIterator(T data) throws NullPointerException {
        if (offEnd()) {
            throw new NullPointerException("addIterator: Iterator is off end. Cannot add.");
        }
        else if (iterator == last) {
            addLast(data);
        }
        else {
            Node n = new Node(data);
            n.next = iterator.next;
            n.prev = iterator;
            iterator.next.prev = n;
            iterator.next = n;
            length++;
        }
    }

    /**
     * Advances the iterator by one node in the list
     * @precondition iterator != null
     * @throws NullPointerException when the precondition is violated
     */
    public void advanceIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("advanceIterator: Iterator is null and cannot advance.");
        }
        iterator = iterator.next;
    }

    /**
     * Reverse the iterator by one node in the list
     * @precondition !offEnd()
     * @throws NullPointerException when the precondition is violated
     */
    public void reverseIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("advanceIterator: Iterator is null and cannot reverse.");
        }
        iterator = iterator.prev;
    }

    /**
     * Returns whether or not the iterator is off the end of the list, i.e. null
     * @return whether the iterator is null
     */
    public boolean offEnd() {
        return iterator == null;
    }

    /**
     * Determines whether two Lists have the same data
     * in the same order
     * @param L the List to compare to this List
     * @return whether the two Lists are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if (!(o instanceof List)) {
            return false;
        } else {
            List<T> L = (List<T>) o;
            if (this.length != L.length) {
                return false;
            } else {
                Node temp1 = this.first;
                Node temp2 = L.first;
                while (temp1 != null) { //Lists are same length
                    if (!(temp1.data.equals(temp2.data))) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return true;
            }
        }
    }
    /****ADDITIONAL OPERATIONS****/

    /**
     * List with each value on its own line
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override public String toString() {
        String result = "";
        Node temp = first;
        while(temp != null) {
            result += temp.data + "\n";
            temp = temp.next;
        }
        return result;
    }

    /**
     * prints the contents of the linked list to the screen
     */
    public void printNumberedList() {
        int number = 1;
        placeIterator();
        while (!offEnd()) {
            System.out.println(number + ". " + getIterator());
            advanceIterator();
            number++;
        }
    }


    /**
     * Advances the iterator exactly n times
     * @param n the number of times to advance
     * the iterator
     * @precondition iterator != null
     * @precondition (n + indexIterator) <= length
     * @throws NullPointerException when the
     * iterator is offEnd
     * @throws IndexOutOfBoundsException when
     * (iterator position + n) > length
     */
    public void advanceNTimes(int n) throws NullPointerException, IndexOutOfBoundsException{
        if (offEnd()){
            throw new NullPointerException("advanceNTimes(): Iterator is off end");
        }
        else{
            for( int i = 0; i < n; i++){
                advanceIterator();
            }
        }
    }

    /**
     * Searches the List for a specific element
     * @param data the element to search for
     * @return the location of the element in the
     * List from 1 to length or -1 if not found
     * @postcondition the position of the iterator remains
     * unchanged
     */
    public int linearSearch(T data) {
        placeIterator();
        int index = 0;
        for(int i = 0; i < length; i++){
            if (getIterator().equals(data)){
                return index;
            }
            advanceIterator();
            index++;

        }
        return -1;
    }

}