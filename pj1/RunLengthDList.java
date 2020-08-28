/* RunLengthDList.jave */

// package list;

public class RunLengthDList extends DList {
  /**
   *  Override newNode() function. 
   *  newNode() calls the RunLengthNode constructor.
   *  In RunLengthDList class we'll use RunLengthNode instead of DListNode. 
   * */

   public RunLengthNode newNode(Object item, DListNode prev, DListNode next) {
    return new RunLengthNode(item, prev, next);
   }

   public RunLengthDList() {
     super();
     head = newNode(null, null, null);
     head.next = head;
     head.prev = head;
     // System.out.println(super.head.getClass());
     
   }


  /* Overriding toString() method for debugging. */
  public String toString() {
    String result = "[\n";
    DListNode current = head.next;
    // System.out.println(current.item.getClass());

    while (current != head) {
      result = result + "R: " + ((RunLengthItem)current.item).getPixel().getRed() 
                      + " G: " + ((RunLengthItem)current.item).getPixel().getGreen() 
                      + " B: " + ((RunLengthItem)current.item).getPixel().getBlue() 
                      + " Length: " + ((RunLengthItem)current.item).getRunLength()
                      + "\n";
      current = current.next;
    }
    return result + "]"; 
  }
 
  
}