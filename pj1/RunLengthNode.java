/* RunLengthNode.jave */

// package list;

/**
 * RunLenghtNode class is a subclass of DListNode to represent one node used in RunLengthEncoding class
 */

 public class RunLengthNode extends DListNode {

 // protected RunLengthItem item;
 // RunLengthItem runItem;
 // RunLengthNode runPrev;
 // RunLengthNode runNext;

 /* Override super class constructor */
 public RunLengthNode(Object i, DListNode p, DListNode n) {
     
    super(i, p, n); // Specifically call supercalss's 3-parameter constructor
    item = (RunLengthItem)i;  
    prev = (RunLengthNode)p;
    next = (RunLengthNode)n;

 }

 /* Need a new constructor with 6 inputs
 public RunLengthNode(int red, int green, int blue, int length, DListNode p, DListNode n) {
    item = RunLengthItem(red, green, blue, length);  // Calling RunLengthItem 4-parameter constructor.
    prev = (RunLengthNode)p;
    next = (RunLengthNode)n;

 }
 */


 }