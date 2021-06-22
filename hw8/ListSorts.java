/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 2000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    int size = q.size();
    LinkedQueue qOfQ = new LinkedQueue();

    for (int i = 0; i < size; i++) {
      try {
        Object item = q.dequeue();
        LinkedQueue subQ = new LinkedQueue();
        subQ.enqueue(item);
        qOfQ.enqueue(subQ);
      } catch (QueueEmptyException e1) {
        System.out.println("QueueEmptyException raised in makeQueueOfQueues() function.");
      }
    }
    return qOfQ;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue q3 = new LinkedQueue();

    try {
      while (!q1.isEmpty() && !q2.isEmpty()) {
      
        Comparable i1 = ((Comparable)q1.front());
        Comparable i2 = ((Comparable)q2.front());

        if (i1.compareTo(i2) < 0) {
          q3.enqueue(i1);
          q1.dequeue();
        } else if (i1.compareTo(i2) > 0) {
          q3.enqueue(i2);
          q2.dequeue();
        } else {
          q3.enqueue(i1);  // Ensure stable sorting: enqueue i1 first and then i2
          q3.enqueue(i2);
          q1.dequeue();
          q2.dequeue();
        }
      } 
    }catch (QueueEmptyException e1) {
        System.out.println("QueueEmptyException e1 raised in mergeSortedQueues() upper part.");
        e1.printStackTrace();
      }
    
      if (!q2.isEmpty()) {
        // System.out.println("q1 is empty, q2 needs to be appeneded to q3.");
        q3.append(q2);
      } else {
        q3.append(q1);
        
      }
    
    return q3;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    while (!qIn.isEmpty()) {
      
      try {
        Comparable item = (Comparable) qIn.dequeue();
        if (item.compareTo(pivot) < 0) {
          qSmall.enqueue(item);
        } else if (item.compareTo(pivot) == 0) {
          qEquals.enqueue(item);  //  Can't ensure stable sorting.
        } else {
          qLarge.enqueue(item);
        }
      } catch (QueueEmptyException e1) {
        e1.getStackTrace();
      }
      
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    LinkedQueue qOfQ = makeQueueOfQueues(q);
    
    try {
      while (qOfQ.size() > 1) {
        LinkedQueue q1 = ((LinkedQueue)qOfQ.dequeue());  // Ensure stable sorting -  q1 comes before q2
        LinkedQueue q2 = ((LinkedQueue)qOfQ.dequeue());
        LinkedQueue mergedQ = mergeSortedQueues(q1, q2);
        // System.out.println("mergedQ: " + mergedQ.toString());
        qOfQ.enqueue(mergedQ);
        // System.out.println("qOfQ: " + qOfQ.toString());
      }
      if (qOfQ.size() == 1) {
        q.append((LinkedQueue)qOfQ.dequeue());
      }
    } catch (QueueEmptyException e3) {
      System.out.println("QueueEmptyException e3 raised in mergeSort() function.");
      e3.printStackTrace();
    }
    
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.

    // Since our input changes for every recursion, we can use q as stopping criteria
    if (q.size() <= 1) {
      return;
    }

    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    int a = (int)(q.size() * Math.random()) + 1;
    int b = (int)(q.size() * Math.random()) + 1;
    int c = (int)(q.size() * Math.random()) + 1;
    int n = Math.max(Math.min(a,b), Math.min(Math.max(a,b),c));

    Object pivot = q.nth(n);

    partition(q, (Comparable)pivot, qSmall, qEquals, qLarge);
    quickSort(qSmall);
    quickSort(qLarge);

    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);

  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  public static LinkedQueue makeSortedQueue(int a, int b) {
    
    LinkedQueue q = new LinkedQueue();

    if (b <= a) {
      System.out.println("Make sure b is no less than a.");
      return null;
    }

    for (int i = a; i <= b; i++) {
      q.enqueue(new Integer(i));
    }

    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    
    LinkedQueue q = makeRandom(10);
    System.out.println("q: " + q.toString());
    mergeSort(q);
    System.out.println("q: " + q.toString());
    
    q = makeRandom(15);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    q = makeRandom(1);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());



    /*
    // Test code for makeQueueOfQueues()
    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    LinkedQueue qq = makeQueueOfQueues(q);
    System.out.println("qq: " + qq.toString());
    System.out.println("q: " + q.toString());
    */

    /*
    // Test code for mergeSortedQueues()
    LinkedQueue q1 = makeSortedQueue(2,5);
    LinkedQueue q2 = makeSortedQueue(6,10);
    LinkedQueue q3 = new LinkedQueue();

    System.out.println("q1: " + q1.toString());
    System.out.println("q2: " + q2.toString());
    System.out.println("q3: " + q3.toString());

    q3 = mergeSortedQueues(q1, q2);

    System.out.println("After merge: ");
    System.out.println("q1: " + q1.toString());
    System.out.println("q2: " + q2.toString());
    System.out.println("q3: " + q3.toString());
    

    q2 = makeSortedQueue(3,10);
    LinkedQueue q4 = makeSortedQueue(7, 15);
    LinkedQueue q5 = new LinkedQueue();

    System.out.println("q4: " + q4.toString());
    q5 = mergeSortedQueues(q4, q2);
    System.out.println("q5: " + q5.toString());
    */

    /*
    // Test code for partition()
    LinkedQueue q1 = makeRandom(12);
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    Object item = new Integer(8);

    System.out.println("q1: " + q1.toString());
    System.out.println("qSmall: " + qSmall.toString());
    System.out.println("qEquals: " + qEquals.toString());
    System.out.println("qLarge: " + qLarge.toString());
    System.out.println("Pivot: " + item.toString());

    partition(q1, (Comparable)item, qSmall, qEquals, qLarge );

    System.out.println("After partition: ");
    System.out.println("q1: " + q1.toString());
    System.out.println("qSmall: " + qSmall.toString());
    System.out.println("qEquals: " + qEquals.toString());
    System.out.println("qLarge: " + qLarge.toString());
    */



    // Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
  }

}
