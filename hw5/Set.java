/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  List l;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    l = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    // Return number of elements in this Set.
    return l.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    
    try {
      ListNode currentElement = l.front();
      // ListNode lastElement = l.back();

      // Scenario 1: insert at the front.
      if (l.length() == 0 || ((Comparable)l.front().item()).compareTo(c) > 0) {
        // System.out.println("Empty set, inserting first element.");
        l.insertFront(c);
        return;
      }

      // Scenario 2: insert in the middle.
      while (currentElement != l.back()){
        if (((Comparable)currentElement.item()).compareTo(c) > 0) {
          currentElement.insertBefore(c);
          
        } 
        else if (((Comparable)currentElement.item()).compareTo(c) == 0) {
          System.out.format("Trying to insert duplicated items %d, insert failed.%n", c);
          return;
        } 
        currentElement = currentElement.next();
        // System.out.println("currentElement: " + currentElement.item());
      }

      // Scenario 3: insert at the end.
      if (((Comparable)currentElement.item()).compareTo(c) < 0 ) {
        // System.out.println("currentElement: " + currentElement.item());
        l.back().insertAfter(c);
        return;
      }
    } catch (InvalidNodeException e1) {
      System.out.println("insert failed.");
    }
    
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.

    try {
      ListNode p1 = l.front();    //Pointer of set "this"
      ListNode p2 = s.l.front();  //Pointer of set s

      while (((DListNode)p2).isValidNode() && ((DListNode)p1).isValidNode()) {
        if (((Comparable)p2.item()).compareTo(p1.item()) == 0) {
          // System.out.format("Trying to insert duplicates %d, move to next element.%n", p1.item());
          p2 = p2.next();
          p1 = p1.next();
        } else if (((Comparable)p2.item()).compareTo(p1.item()) < 0) {
          p1.insertBefore((Comparable)p2.item());
          p2 = p2.next();
        } else {
          p1 = p1.next();
        }
      }

      // When set "this" has been looped thorough but set "s" hasn't.
      while (((DListNode)p2).isValidNode()) {
        l.insertBack(p2.item());
        p2 = p2.next();
      }

    } catch (InvalidNodeException e1) {
      System.out.println("InvalidNodeException, union() function failed.");
    }

  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
    try {
      ListNode p1 = l.front();
      ListNode p2 = s.l.front();

      while (p1.isValidNode() && p2.isValidNode()) {
        if (((Comparable)p1.item()).compareTo(p2.item()) == 0) {
          p1 = p1.next();
          p2 = p2.next();
        } else if (((Comparable)p1.item()).compareTo(p2.item()) < 0) {
          ListNode toBeDeletedNode = p1;
          p1 = p1.next();
          // p1.prev().remove();
          toBeDeletedNode.remove();
        } else {
          p2 = p2.next();
        }
      }

      while (p1.isValidNode()) {
        ListNode toBeDeletedNode = p1;
        p1 = p1.next();
        toBeDeletedNode.remove();
      }

    } catch (InvalidNodeException e1) {
      System.out.println("InvalidNodeException, intersect() function failed.");
    }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    String str = "{ ";
    try {
      ListNode current = l.front();
      while (current.isValidNode()) {
      str = str + " " + current.item() + " ";
      current = current.next();
      }
    } catch (InvalidNodeException e1) {
      System.out.println("toString() function failed.");
    }
    
    str = str + " }";
    return str;
  }

  public static void main(String[] argv) {
    
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(2));
    // s.insert(new Integer(4));
    // s.insert(new Integer(4));
    // s.insert(new Integer(7));
    // s.insert(new Integer(5));
    // s.insert(new Integer(4));
    s.insert(new Integer(5));
    System.out.println("Set s = " + s);
    // System.out.println("Length of s: " + s.l.length());

    
    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    s3.insert(new Integer(4));
    System.out.println("Set s3 = " + s3);
    

    Set s4 = new Set();
    s4.insert(new Integer(5));
    s4.insert(new Integer(6));
    s4.insert(new Integer(4));
    System.out.println("s4: " + s4);
    
    /*
    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);
    */
    
    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);
    
    /*
    s.union(s4);
    System.out.println("After s.union(s4), s = " + s);
    */

    Set s5 = new Set();
    s5.insert(new Integer(8));
    s5.insert(new Integer(5));
    s5.insert(new Integer(3));
    System.out.println("s5: " + s5);

    Set s6 = new Set();
    System.out.println("s6: " + s6);

    /*
    s.union(s5);
    System.out.println("After s.union(s5), s = " + s);
    */

    s3.intersect(s5);
    System.out.println("After s3.intersect(s5), s = " + s3);

    s6.intersect(s5);
    System.out.println("After s6.intersect(s5), s6 = " + s6);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
    
  }
}
