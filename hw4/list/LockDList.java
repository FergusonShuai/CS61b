/* LockDList.java */

package list;

/**
 * LockDList class extends the DList class
 */

public class LockDList extends DList {

  /**
   *  Override newNode() function. 
   *  newNode() calls the LockDListNode constructor.
   *  In LockDList class we'll use LockDListNode instead of DListNode. 
   * */

  protected LockDListNode head;

  public DListNode newNode(Object item, DListNode prev, DListNode next) {
    /*
    if (prev instanceof LockDListNode && next instanceof LockDListNode) {
        return new LockDListNode(item, (LockDListNode)prev, (LockDListNode)next);
    }
    */
    return new LockDListNode(item, prev, next);
  }

  /** 
   * LockDList constructor
   * Do I really need to call suepr() in this case?
   */
  
  /*public LockDList() {
    super();
    head = newNode(null, null, null);
    head.next = head;
    head.prev = head;
    size = 0;
  }
  */
  
  /* lockNode(node) method will lock node to prevent node from been removed */

  public void lockNode(DListNode node) {
    if (node == null) {
        return;
    }

    DListNode pointer = this.front();

    /*
    if (node instanceof LockDListNode) {
        LockDListNode ln = (LockDListNode)node;
        while (pointer != ln) {
            pointer = pointer.next;
          }
        if (pointer != null && pointer.getIsLocked() == false) {
            pointer.setLock();
        }
    }
    */

    if(node instanceof LockDListNode) {
        LockDListNode lockDListNode = (LockDListNode) node;
        lockDListNode.isLocked = true;
    }

  }

  /* Override remove() method */
  public void remove(DListNode node) {
    if (node instanceof LockDListNode) {
        LockDListNode lockedNode = (LockDListNode)node;
        if (!lockedNode.isLocked) {
            super.remove(node);
        }
        return;
    }
    
  }
  

  /** Override insertFront() method */
  /*
  public void insertFront(Object item) {
      // super.insertFront(item);
      LockDListNode nn = newNode(item, head.prev, head);
      head.prev.next = nn;
      head.prev = nn;
      size++;

  }
  */

  

}