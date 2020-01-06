/* TestLockDList.java */
package list;

public class TestLockDList {

    public static void main (String[] args) {
        testLockNode();
    }

    private static void testLockNode(){
        LockDList ldl1 = new LockDList();

        ldl1.insertFront(new Integer(1));
        // System.out.println("Empty ldl1 after first insertFront is: " + ldl1.toString());
        ldl1.insertFront(new Integer(2));
        // System.out.println("ldl1 after second insertFront is: " + ldl1.toString());
        ldl1.insertBack(new Integer(3));
        // System.out.println("ldl1 after second insertBack is: " + ldl1.toString());
        ldl1.insertBack(new Integer(4));
        System.out.println("ldl1 after fourth insertBack is: " + ldl1.toString());

        //System.out.println("Lock state of first element: " + ((LockDListNode)ldl1.front()).getIsLocked());

        DListNode node1 = ldl1.front();
        DListNode node2 = ldl1.back();
        ldl1.lockNode(node1);
        System.out.println("Lock Node front()= "+node1.item);
        System.out.println("Try to remove front node: ");
        ldl1.remove(node1);
        ldl1.remove(node2);
        System.out.println("Lock DList after removal of front and back: " + ldl1.toString());
        node2 = ldl1.back();
        ldl1.lockNode(node2);
        ldl1.remove(node2);
        System.out.println("Lock back node of ldl1 and try to remove it: " + ldl1.toString());


    }
}