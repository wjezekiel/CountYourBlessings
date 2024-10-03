/**
 * Hash.java
 * @author Ezekiel Chow
 * CIS 22C, Finals Project
 */
import java.util.ArrayList;

public class Hash<T extends Comparable<T>> {

    private int numElements;
    private ArrayList<List<T> > Table;

    /**
     * Constructor for the Hash.java
     * class. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     * @param size the table size
     */
    public Hash(int size) {
        Table = new ArrayList<List<T>>();
        for (int i = 0; i < size; i++) {
            Table.add(new List<T>());
        }
        numElements = 0;
    }

    /**Accessors*/

    /**
     * Returns the hash value in the Table
     * for a given key by taking modulus
     * of the hashCode value for that key
     * and the size of the table
     * @param t the key
     * @return the index in the Table
     */
    private int hash(T t) {
        int code = t.hashCode();
        return code % Table.size();
    }

    /**
     * Counts the number of keys at this index
     * @param index the index in the Table
     * @precondition 0 <= index < Table.length
     * @return the count of keys at this index
     * @throws IndexOutOfBoundsException
     */
    public int countBucket(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= Table.size()){
            throw new IndexOutOfBoundsException("countBucket(): index is outside bounds of the table");

        }
        return Table.get(index).getLength();
    }

    /**
     * Returns total number of keys in the Table
     * @return total number of keys
     */
    public int getNumElements() {
        return numElements;
    }

    /**
     * Searches for a specified key in the Table
     * @param t the key to search for
     * @return the index in the Table
     * at which the element is located
     * or -1 if it is not found
     */
    public int search(T t) {
        int bucket = hash(t);
        Table.get(bucket).placeIterator();
        for (int i = 0; i< Table.get(bucket).getLength(); i++){
            if(Table.get(bucket).getIterator().equals(t)){
                return bucket;
            }
            Table.get(bucket).advanceIterator();
        }
        return -1;
    }


    /**Mutators*/

    /**
     * Inserts a new key in the Table
     * calls the hash method to determine placement
     * @param t the key to insert
     */
    public void insert(T t) {
        if (t != null){
            int index = hash(t);
            Table.get(index).addLast(t);
            numElements++;
        }

    }


    /**
     * Removes the key t from the Table
     * calls the hash method on the key to
     * determine correct placement.
     * Has no effect if t is not in
     * the Table.
     * @param t the key to remove
     */
    public void remove(T t) {
        int bucket = hash(t);
        Table.get(bucket).placeIterator();
        while(!Table.get(bucket).offEnd()) {
            if (Table.get(bucket).getIterator().equals(t)) {
                Table.get(bucket).removeIterator();
                numElements--;
                return;
            }
            Table.get(bucket).advanceIterator();
        }
        numElements--;
    }

    /**Additional Methods*/

    /**
     * Prints all the keys at a specified
     * bucket in the Table. Each key displayed
     * on its own line, with a blank line
     * separating each key
     * Above the keys, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     * @param bucket the index in the Table
     */
    public void printBucket(int bucket) {
        System.out.println("Print bucket #" + bucket + ":\n");
        System.out.print(Table.get(bucket).toString());
    }

    /**
     * Starting at the first bucket, and continuing
     * in order until the last bucket, concatenates
     * all elements at all buckets into one String
     */
    @Override public String toString() {
        String s = "";
        for(int i = 0; i < Table.size(); i++){
            s += Table.get(i).toString();
        }
        return s;
    }
}