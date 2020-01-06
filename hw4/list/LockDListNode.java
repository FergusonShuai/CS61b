/* LockDListNode.java */

package list;

/**
 * LockDListNode extend DListNode, but the node been locked can't be removed.
 */

public class LockDListNode extends DListNode {

  protected boolean isLocked;

  public LockDListNode(Object i, DListNode p, DListNode n){
    super(i, p, n);
    prev = (LockDListNode)p;
    next = (LockDListNode)n;
    isLocked = false;
  }

  public boolean getIsLocked() {
    return isLocked;
  }

  public void setLock() {
    if (isLocked == false) {
        isLocked = true;
    }
  }
    
}