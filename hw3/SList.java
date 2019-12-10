/* SList.java */

/**
 *  The SList class is a singly-linked implementation of the linked list
 *  abstraction.  SLists are mutable data structures, which can grow at either
 *  end.
 *
 *  @author Kathy Yelick and Jonathan Shewchuk
 **/

public class SList {

  private SListNode head;
  private int size;

  /**
   *  SList() constructs an empty list.
   **/

  public SList() {
    size = 0;
    head = null;
  }

  /**
   *  isEmpty() indicates whether the list is empty.
   *  @return true if the list is empty, false otherwise.
   **/

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   *  length() returns the length of this list.
   *  @return the length of this list.
   **/

  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts item "obj" at the beginning of this list.
   *  @param obj the item to be inserted.
   **/

  public void insertFront(Object obj) {
    head = new SListNode(obj, head);
    size++;
  }

  /**
   *  insertEnd() inserts item "obj" at the end of this list.
   *  @param obj the item to be inserted.
   **/

  public void insertEnd(Object obj) {
    if (head == null) {       // test if Slist is empty;
      head = new SListNode(obj);
    } else {
      SListNode node = head;
      while (node.next != null) {    //Looping through SListNode to get the tail node.
        node = node.next;
      }
      node.next = new SListNode(obj);
    }
    size++;
  }

  /**
   *  nth() returns the item at the specified position.  If position < 1 or
   *  position > this.length(), null is returned.  Otherwise, the item at
   *  position "position" is returned.  The list does not change.
   *  @param position the desired position, from 1 to length(), in the list.
   *  @return the item at the given position in the list.
   **/

  public Object nth(int position) {
    SListNode currentNode;

    if ((position < 1) || (head == null)) {
      return null;
    } else {
      currentNode = head;
      while (position > 1) {
        currentNode = currentNode.next;
        if (currentNode == null) {
          return null;
        }
        position--;
      }
      return currentNode.item;
    }
  }

  /**
   *  squish() takes this list and, wherever two or more consecutive items are
   *  equals(), it removes duplicate nodes so that only one consecutive copy
   *  remains.  Hence, no two consecutive items in this list are equals() upon
   *  completion of the procedure.
   *
   *  After squish() executes, the list may well be shorter than when squish()
   *  began.  No extra items are added to make up for those removed.
   *
   *  For example, if the input list is [ 0 0 0 0 1 1 0 0 0 3 3 3 1 1 0 ], the
   *  output list is [ 0 1 0 3 1 0 ].
   *
   *  IMPORTANT:  Be sure you use the equals() method, and not the "=="
   *  operator, to compare items.
   **/

  public void squish() {
    // Fill in your solution here.  (Ours is eleven lines long.)
    // SListNode currentNode = head; 

    //int originalSize = size;

    // still doesn't work
    /*
    while(currentNode != null && currentNode.next != null) {
      Object currentValue = currentNode.item;
      SListNode next = currentNode.next;
      Object nextValue = next.item;
      // System.out.println("currentNode.next is not null");
        if (currentValue.equals(nextValue)){
          currentNode.next = next.next;
          next.next = null;
          currentNode = currentNode.next;
          size--;
        } else{
          currentNode = currentNode.next;
          currentValue = currentNode.item;
        }
        // System.out.println("called");
    }
    */

    // solution found online: this is a more clever algorithm, using two pointers (currentNode & cursorNode).
    // currentNode remain relatively steady, only move forward with item changes; while cursorNode go through 
    // input list, acting like a ranger to see if value changes.
    
    // check if null
    if (head == null || head.next == null){
      return;     // for empty SList and SList with only one item, no need to do anything;
    }

    SListNode currentNode = head;       // Pointer used to indicate(store) output list
    SListNode cursorNode = head.next;   // Pointer used to loop through input list;
    do {
      if(!currentNode.item.equals(cursorNode.item)){
      currentNode.next = cursorNode;
      currentNode = cursorNode;
      }
      cursorNode = cursorNode.next;
    } while ( cursorNode != null );
    
    currentNode.next = null;
  }

  /**
   *  twin() takes this list and doubles its length by replacing each node
   *  with two consecutive nodes referencing the same item.
   *
   *  For example, if the input list is [ 3 7 4 2 2 ], the
   *  output list is [ 3 3 7 7 4 4 2 2 2 2 ].
   *
   *  IMPORTANT:  Do not try to make new copies of the items themselves.
   *  Make new SListNodes, but just copy the references to the items.
   **/

  public void twin() {
    // Fill in your solution here.  (Ours is seven lines long.)
    // if (head == null){    // no need to check null separately; already done in while loop.
    //   return;
    // }

    SListNode currentNode = head;
    while(currentNode != null){
      SListNode twinNode = new SListNode(currentNode.item, currentNode.next); 
      // twinNode.next = currentNode.next;
      currentNode.next = twinNode;
      currentNode = twinNode.next;
      size++;
    }
  }

  /**
   *  toString() converts the list to a String.
   *  @return a String representation of the list.
   **/

  public String toString() {
    int i;
    Object obj;
    String result = "[  ";

    SListNode cur = head;

    while (cur != null) {
      obj = cur.item;
      result = result + obj.toString() + "  ";
      cur = cur.next;
    }
    result = result + "]";
    return result;
  }


  /**
   *  main() runs test cases on the SList class.  Prints summary
   *  information on basic operations and halts with an error (and a stack
   *  trace) if any of the tests fail.
   **/

  public static void main (String[] args) {
    // testEmpty();
    // testAfterInsertFront();
    // testAfterInsertEnd();
    // testSquish();
    testTwin();
  }

  /**
   *  Test twin() function.
   *  For example, [ 3 7 8 ] becomes [ 3 3 7 7 8 8 ] after twin() runs on the input list.
   */

   private static void testTwin(){
     
    SList lst02 = new SList();

    lst02.insertFront(new Integer(3));
    lst02.insertFront(new Integer(3));
    lst02.insertFront(new Integer(7));
    lst02.insertFront(new Integer(7));

    SList lst03 = new SList();

    lst03.insertFront(new Integer(55));
    lst03.insertFront(new Integer(76));

    System.out.println("List " + lst02.toString() + " after twin is: " );
    lst02.twin();
    System.out.println(lst02.toString());

    System.out.println("List " + lst03.toString() + " after twin is: " );
    lst03.twin();
    System.out.println(lst03.toString());
    
   }
  



  /**
   *  Test squish() function.
   *  After squish() executes, the list may well be shorter than when squish()
   *  began.  No extra items are added to make up for those removed.
   *
   *  For example, if the input list is [ 0 0 0 0 1 1 0 0 0 3 3 3 1 1 0 ], the
   *  output list is [ 0 1 0 3 1 0 ].
   */
  
  
   private static void testSquish(){
    
    // SlistNode ln01 = new SlistNode(0, new SlistNode(0, new SListNode(1, new SlistNode(1,null))));

    SList lst01 = new SList();
    lst01.insertFront(new Integer(1));
    lst01.insertFront(new Integer(1));
    lst01.insertFront(new Integer(1));
    lst01.insertFront(new Integer(1));
    lst01.insertFront(new Integer(0));
    lst01.insertFront(new Integer(0));
    lst01.insertFront(new Integer(0));
    lst01.insertFront(new Integer(0));
    lst01.insertFront(new Integer(2));
    lst01.insertFront(new Integer(3));
    lst01.insertFront(new Integer(3));

    
    System.out.println("List " + lst01.toString() + " after squish is: " );
    lst01.squish();
    System.out.println(lst01.toString());
    
    
    /*
    System.out.println("lst01.head.next.next.next is " + lst01.head.next.next.next.item);
    System.out.println("lst01.head.next.next.next.next is " + lst01.head.next.next.next.next.item);
    System.out.println("lst01.head.next.next.next.next.next is " + lst01.head.next.next.next.next.next);
    */
  }
    
  /**
   *  testEmpty() tests toString(), isEmpty(), length(), insertFront(), and
   *  insertEnd() on an empty list.  Prints summary information of the tests
   *  and halts the program if errors are detected.
   **/

  private static void testEmpty() {
    SList lst1 = new SList();
    SList lst2 = new SList();
    System.out.println();
    System.out.println("Here is a list after construction: "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  ]"),
		      "toString on newly constructed list failed");

    System.out.println("isEmpty() should be true. It is: " +
		       lst1.isEmpty());
    TestHelper.verify(lst1.isEmpty() == true,
		      "isEmpty() on newly constructed list failed");    

    System.out.println("length() should be 0. It is: " +
		       lst1.length());
    TestHelper.verify(lst1.length() == 0, 
		      "length on newly constructed list failed");    
    lst1.insertFront(new Integer(3));
    System.out.println("Here is a list after insertFront(3) to an empty list: "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  3  ]"),
		      "InsertFront on empty list failed");
    lst2.insertEnd(new Integer(5));
    System.out.println("Here is a list after insertEnd(5) on an empty list: "
		       + lst2.toString());
    TestHelper.verify(lst2.toString().equals("[  5  ]"),
		      "insertEnd on empty list failed");
  }

  /**
   *  testAfterInsertFront() tests toString(), isEmpty(), length(),
   *  insertFront(), and insertEnd() after insertFront().  Prints summary
   *  information of the tests and halts the program if errors are detected.
   **/

  private static void testAfterInsertFront() {
    SList lst1 = new SList();
    lst1.insertFront(new Integer(3));
    lst1.insertFront(new Integer(2));
    lst1.insertFront(new Integer(1));
    System.out.println();
    System.out.println("Here is a list after insertFront 3, 2, 1: "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  1  2  3  ]"),
		      "InsertFronts on non-empty list failed");
    System.out.println("isEmpty() should be false. It is: " +
		       lst1.isEmpty());
    TestHelper.verify(lst1.isEmpty() == false,
		      "isEmpty() after insertFront failed");    
    System.out.println("length() should be 3. It is: " +
		       lst1.length());
    TestHelper.verify(lst1.length() == 3, 
		      "length() after insertFront failed");
    lst1.insertEnd(new Integer(4));
    System.out.println("Here is the same list after insertEnd(4): "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  1  2  3  4  ]"),
		      "insertEnd on non-empty list failed");
  }
    
  /**
   *  testAfterInsertEnd() tests toString(), isEmpty(), length(),
   *  insertFront(), and insertEnd() after insertEnd().  Prints summary
   *  information of the tests and halts the program if errors are detected.
   **/

  private static void testAfterInsertEnd() {
    SList lst1 = new SList();
    lst1.insertEnd(new Integer(6));
    lst1.insertEnd(new Integer(7));
    System.out.println();
    System.out.println("Here is a list after insertEnd 6, 7: "
		       + lst1.toString());
    System.out.println("isEmpty() should be false. It is: " +
		       lst1.isEmpty());
    TestHelper.verify(lst1.isEmpty() == false,
		      "isEmpty() after insertEnd failed");    
    System.out.println("length() should be 2. It is: " +
		       lst1.length());
    TestHelper.verify(lst1.length() == 2, 
		      "length() after insertEndfailed");
    lst1.insertFront(new Integer(5));
    System.out.println("Here is the same list after insertFront(5): "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  5  6  7  ]"),
		      "insertFront after insertEnd failed");
  }
}
