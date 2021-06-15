/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  // protected Entry e; // Each entry is a ListNode.
  protected SList[] buckets;
  protected int bucketSize;
  protected int entrySize;
  protected static final int defaultBucketSize = 101;
  protected int collisionCount;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    int nearestPrime = sizeEstimate;
    while(!isPrime(nearestPrime)){
      nearestPrime++;
    }

    bucketSize = nearestPrime;
    buckets = new SList[bucketSize];

    for(int i = 0; i < bucketSize; i++){
      buckets[i] = new SList();
    }

    entrySize = 0;

  }

  public boolean isPrime(int n){
    boolean primeFlag = true;
    if(n <= 0){
      primeFlag = false;
    }
    else if(n > 3){
      for(int i=2; i <= n/2; i++){
        if(n % i == 0){
          primeFlag = false;
          break;
        }
      }
    }
    return primeFlag;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    bucketSize = defaultBucketSize;
    buckets = new SList[bucketSize];
    entrySize = 0;

    for(int i = 0; i < bucketSize; i++){
      buckets[i] = new SList();
    }
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    int hashValue = ((127 * code + 101) % 16908799) % bucketSize;
    if (hashValue < 0){
      hashValue = hashValue + 16908799;
    }
    hashValue = hashValue % bucketSize;
    return hashValue;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return entrySize;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    if (entrySize == 0) {
      return true;
    }
    return false;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry e = new Entry();
    e.key = key;
    e.value = value;

    // System.out.println("hashCode: " + key.hashCode());
    int bucketIndex = compFunction(key.hashCode());

    // System.out.println("bucketIndex: " + bucketIndex);
    buckets[bucketIndex].insertBack(e);

    entrySize++;
    
    return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int hashCode = compFunction((Integer)key);
    Entry e = null;

    if (buckets[hashCode].isEmpty()){
      return e;
    }

    ListNode curr = buckets[hashCode].front();

    while (curr.isValidNode()) {
      try {
        Entry currentEntry = (Entry)curr.item();
        if (key.equals(currentEntry.key)) {
          e = currentEntry;
          break;
        } else{
          curr = curr.next();
        }
      } catch(InvalidNodeException e1) {
        System.err.println("Invalid node exception find: " + e1);
      }
    }
    
    if (e != null) {
      System.out.println("Find the entry");
    } else {
      System.out.println("Can not find the entry");
    }
    
    return e;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int hashCode = compFunction((Integer)key);
    Entry e = null;

    if (buckets[hashCode].isEmpty()) {
      return e;
    }

    ListNode curr = buckets[hashCode].front();

    while (curr.isValidNode()) {
      try {
        Entry currentEntry = (Entry)curr.item();
        if (key.equals(currentEntry.key)) {
          e = (Entry)curr.item();
          curr.remove();
          break;
        } else {
          curr = curr.next();
        }
      } catch(InvalidNodeException e1) {
        System.err.println("Error message: " + e1);
      }
    }
    entrySize--;

    return e;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    buckets = new SList[bucketSize];
    
    for (int i = 0; i < bucketSize; i++) {
      buckets[i] = new SList();
    }

    entrySize = 0;
    collisionCount = 0;
  }

  public String collisionPrint() {
    String s = "[ ";
    
    for (int i = 0; i < bucketSize; i++) {
      s = s + buckets[i].length() + ", ";
    }

    s = s + "]";

    return s;
  }

}
