/* Sorts.java */

package sort;

public class Sorts {

  /**
   *  Place any final static fields you would like to have here.
   **/
  private static int[] sortedKeys;

  /**
   *  countingSort() sorts an array of int keys according to the
   *  values of _one_ of the base-16 digits of each key.  "whichDigit"
   *  indicates which digit is the sort key.  A zero means sort on the least
   *  significant (ones) digit; a one means sort on the second least
   *  significant (sixteens) digit; and so on, up to a seven, which means
   *  sort on the most significant digit.
   *  @param key is an array of ints.  Assume no key is negative.
   *  @param whichDigit is a number in 0...7 specifying which base-16 digit
   *    is the sort key.
   *  @return an array of type int, having the same length as "keys"
   *    and containing the same keys sorted according to the chosen digit.
   *
   *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
   **/
  public static int[] countingSort(int[] keys, int whichDigit) {
    // Replace the following line with your solution.
    
    int[] counts = new int[16];  // All counts are default to 0
    sortedKeys = new int[keys.length];
    int i, j;
    
    if (whichDigit < 0 || whichDigit > 7) {
      System.out.println("Digit out of range; respecify whichDigit.");
      return null;
    }

    for (i = 0; i < keys.length; i++) {
      int key = keys[i];
      int shiftedKey = key >> 4 * whichDigit;
      counts[shiftedKey & 15]++;
    }

    // Get cumulative counts array indicating the starting index of each number.
    int total = 0; 
    int c;
    for (j = 0; j < counts.length; j++) {
      c = counts[j];
      counts[j] = total;
      total = total + c;
    }

    // Now we can fill in sortedKey based on counts.
    for (i = 0; i < keys.length; i++) {
      int key = keys[i];
      int shiftedKey = key >> 4 * whichDigit;
      int digitNum = shiftedKey & 15;
      
      sortedKeys[counts[digitNum]] = key;
      counts[digitNum]++;
    }
    
    return sortedKeys;
  }

  /**
   *  radixSort() sorts an array of int keys (using all 32 bits
   *  of each key to determine the ordering).
   *  @param key is an array of ints.  Assume no key is negative.
   *  @return an array of type int, having the same length as "keys"
   *    and containing the same keys in sorted order.
   *
   *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
   **/
  public static int[] radixSort(int[] keys) {
    // Replace the following line with your solution.
    for (int d = 0; d < 8; d++) {
      keys = countingSort(keys, d);
    }
    sortedKeys = keys;
    return sortedKeys;
  }

  /**
   *  yell() prints an array of int keys.  Each key is printed in hexadecimal
   *  (base 16).
   *  @param key is an array of ints.
   **/
  public static void yell(int[] keys) {
    System.out.print("keys are [ ");
    for (int i = 0; i < keys.length; i++) {
      System.out.print(Integer.toString(keys[i], 16) + " ");
    }
    System.out.println("]");
  }

  public static void countsCheck(int[] counts) {
    for (int i = 0; i < counts.length; i++) {
      System.out.format("counts[%d]: %d\n", i, counts[i]);
    }
  }

  //public static void 

  /**
   *  main() creates and sorts a sample array.
   *  We recommend you add more tests of your own.
   *  Your test code will not be graded.
   **/
  public static void main(String[] args) {
    int[] keys = { Integer.parseInt("60013879", 16),
                   Integer.parseInt("11111119", 16),
                   Integer.parseInt("2c735010", 16),
                   Integer.parseInt("2c732010", 16),
                   Integer.parseInt("7fffffff", 16),
                   Integer.parseInt("4001387c", 16),
                   Integer.parseInt("10111119", 16),
                   Integer.parseInt("529a7385", 16),
                   Integer.parseInt("1e635010", 16),
                   Integer.parseInt("28905879", 16),
                   Integer.parseInt("00011119", 16),
                   Integer.parseInt("00000000", 16),
                   Integer.parseInt("7c725010", 16),
                   Integer.parseInt("1e630010", 16),
                   Integer.parseInt("111111e5", 16),
                   Integer.parseInt("61feed0c", 16),
                   Integer.parseInt("3bba7387", 16),
                   Integer.parseInt("52953fdb", 16),
                   Integer.parseInt("40013879", 16),
                   Integer.parseInt("400138ff", 16),
                   // Integer.parseInt("90e13879", 16),
                   Integer.parseInt("700b3879", 16) };

    
    yell(keys);
    keys = radixSort(keys);
    /*
    keys = countingSort(keys, 0);
    yell(keys);
    keys = countingSort(keys, 1);
    yell(keys);
    keys = countingSort(keys, 2);
    */
    yell(keys);
    

    /*
    int[] c = countingSort(keys, 1);
    countsCheck(c);
    */

  }

}
