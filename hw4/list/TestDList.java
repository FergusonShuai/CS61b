package list;

public class TestDList{
    /**
   *  main() runs test cases on the DList class.  
   **/
  public static void main (String[] args) {
    // testInsert();
    testRemove();
  }

  private static void testInsert(){
    DList dList1 = new DList();
    DList dList2 = new DList();

    dList1.insertFront(new Integer(1));
    // System.out.println("Empty dList1 after first insertFront is: " + dList1.toString());
    dList1.insertFront(new Integer(2));
    // System.out.println("dList1 after second insertFront is: " + dList1.toString());
    dList1.insertBack(new Integer(3));
    // System.out.println("dList1 after second insertBack is: " + dList1.toString());
    dList1.insertBack(new Integer(4));
    System.out.println("dList1 after fourth insertBack is: " + dList1.toString());

    dList2.insertBack(new Integer(3));
    
    // System.out.println("dList2 after second insertBack is: " + dList2.toString());
    // System.out.println("dList1 front node: " + dList1.front().item.toString());
    // System.out.println("dList1 back node: " + dList1.back().item.toString());
    // System.out.println("dList1 head's next next node: " + dList1.next(dList1.head.next).item.toString());
    // System.out.println("dList1 head's 2nd last node using prev() function: " + dList1.prev(dList1.head.prev).item.toString());

    dList1.insertAfter(new Integer(5),dList1.back());
    System.out.println("dList1 insert integer 5 after 4: " + dList1.toString());

    dList1.insertAfter(new Integer(6),dList1.front().next);
    System.out.println("dList1 insert integer 6 after 1: " + dList1.toString());

    dList1.insertBefore(new Integer(7),dList1.front().next);
    System.out.println("dList1 insert integer 7 before 1: " + dList1.toString());

  }

  public static void testRemove(){
    DList dList1 = new DList();
    DList dList2 = new DList();

    dList1.insertFront(new Integer(1));
    // System.out.println("Empty dList1 after first insertFront is: " + dList1.toString());
    dList1.insertFront(new Integer(2));
    // System.out.println("dList1 after second insertFront is: " + dList1.toString());
    dList1.insertBack(new Integer(3));
    // System.out.println("dList1 after second insertBack is: " + dList1.toString());
    dList1.insertBack(new Integer(4));
    System.out.println("dList1 after fourth insertBack is: " + dList1.toString());

    dList1.remove(dList1.front().next);
    System.out.println("dList1 after remove second element: " + dList1.toString());

    dList1.remove(dList1.front());
    System.out.println("dList1 after remove first element: " + dList1.toString());

    dList1.remove(dList1.back());
    System.out.println("dList1 after remove last element: " + dList1.toString());
  }
}