/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;  // Field inherited from IntDictionary class
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key) {
    // Fill in your solution here.
    Tree234Node node = root;

    // insert first element into an empty tree
    if (node == null) {
      root = new Tree234Node(null, key);
      size = 1;  // remember to update the size of the tree
      return;
    }

    // if root node is leaf node (tree depth == 1), and root is a full node
    if (node.keys == 3 && node.parent == null) {
      root = popupMiddleKey(node, node.key2);
      node = root;
      // System.out.print("Root node is a full node, middle key got popped up." + "\n"); 
    }

    // walk down the tree to find a place to insert
    while (node.child1 != null) {
      
      if (node.keys == 3) {
        // System.out.println("leaf node (" + node.toString() + ") is a full node, middle key got popped up.");
        // System.out.println("Its parent has " + node.parent.keys + " keys: " + node.parent );
        node = popupMiddleKey(node, node.key2);
        // System.out.println("Node: " + node);
        
      }

      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        System.out.print("Trying to insert duplicate key " + key);
        return;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        System.out.print("Trying to insert duplicate key " + key);
        return;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if ((key == node.key3)) {
        System.out.print("Trying to insert duplicate key " + key);
        return;
      } else {
        node = node.child4;
      }
    }

    // Now we are at the leaf level
    // Need to handle full node at leaf situation before the insertion, because it need one more recursion
    if (node.keys == 3) {
      // System.out.println("leaf node (" + node.toString() + ") is a full node, middle key got popped up.");
      // System.out.println("Its parent has " + node.parent.keys + " keys: " + node.parent );
      node = popupMiddleKey(node, node.key2);
      // System.out.println("Node: " + node);
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        System.out.print("Trying to insert duplicate key " + key);
        return;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        System.out.print("Trying to insert duplicate key " + key);
        return;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if ((key == node.key3)) {
        System.out.print("Trying to insert duplicate key " + key);
        return;
      } else {
        node = node.child4;
      }
    }
    
    
    if (node.keys == 1) {
      if (key < node.key1) {
        node.keys = 2;
        node.key2 = node.key1;
        node.key1 = key;
        size++;
        return;
      } else if (key == node.key1) {
        System.out.print("Trying to insert duplicate key " + key);
        return;
      } else if (key > node.key1) {
        node.keys = 2;
        node.key2 = key;
        size++;
        return;
      } 
    } else if (node.keys == 2) {
      if (key < node.key1) {
        node.keys = 3;
        node.key3 = node.key2;
        node.key2 = node.key1;
        node.key1 = key;
        size++;
        return;
      } else if (key > node.key1 && key < node.key2) {
        node.keys = 3;
        node.key3 = node.key2;
        node.key2 = key;
        size++;
        return;
      } else if (key > node.key2) {
        node.keys = 3;
        node.key3 = key;
        size++;
        return;
      } else {
        System.out.print("Trying to insert duplicate key " + key);
        return;
      }
    }
  }

  /**
   *  popupMiddleKey() pop up the middle key of a 3-key node to its parent
   * @param p is the node with 3 keys; middleKey is the middle key to be popped up.
   */
  public Tree234Node popupMiddleKey(Tree234Node p, int middleKey) {
    if (p.keys != 3) {
      System.out.println("This node doesn't have 3 children, can't pop the middle one.");
      return p;
    } 

    Tree234Node parentNode = p.parent;
    // System.out.print("parentNode: " + parentNode + "\n");


    if (parentNode == null) { // The full node (with 3 keys) is root node 
      Tree234Node newRoot = new Tree234Node(null, middleKey);
      
        newRoot.child1 = new Tree234Node(newRoot, p.key1);
        newRoot.child2 = new Tree234Node(newRoot, p.key3);
      
        if (p.child1 != null) {
          newRoot.child1.child1 = p.child1;
          p.child1.parent = newRoot.child1;
        }
        
        if (p.child2 != null) {
          newRoot.child1.child2 = p.child2;
          p.child2.parent = newRoot.child1;
        }

        if (p.child3 != null) {
          newRoot.child2.child1 = p.child3;
          p.child3.parent = newRoot.child2;
        }

        if (p.child4 != null) {
          newRoot.child2.child2 = p.child4;
          p.child4.parent = newRoot.child2;
        }
        
      // System.out.print("newRoot: " + newRoot + "\n");
      return newRoot;
    } else { // The full node is non-root node
      if (parentNode.keys == 1) {
        // System.out.print("Parent Node only has one key, good." + "\n");
        if (parentNode.key1 < middleKey) {
          parentNode.keys = 2;
          parentNode.key2 = middleKey;
          parentNode.child2 = new Tree234Node(parentNode, p.key1);
          parentNode.child3 = new Tree234Node(parentNode, p.key3);
          parentNode.child2.child1 = p.child1;
          parentNode.child2.child2 = p.child2;
          parentNode.child3.child1 = p.child3;
          parentNode.child3.child2 = p.child4; 

          if (p.child1 != null) {
            p.child1.parent = parentNode.child2;
          }

          if (p.child2 != null) {
            p.child2.parent = parentNode.child2;
          }

          if (p.child3 != null) {
            p.child3.parent = parentNode.child3;
          }

          if (p.child4 != null) {
            p.child4.parent = parentNode.child3;
          }

          // System.out.format("Successfully pop up the middle key %d to parent node, good.", middleKey);   
        } else if (parentNode.key1 > middleKey) {
          // System.out.format("Trying to popup middle key %d \n" , middleKey);
          parentNode.keys = 2;
          parentNode.key2 = parentNode.key1;
          parentNode.key1 = middleKey;
          parentNode.child3 = parentNode.child2;
          parentNode.child1 = new Tree234Node(parentNode, p.key1);
          parentNode.child2 = new Tree234Node(parentNode, p.key3);
          
          parentNode.child1.child1 = p.child1;
          parentNode.child1.child2 = p.child2;
          parentNode.child2.child1 = p.child3;
          parentNode.child2.child2 = p.child4;

          if (p.child1 != null) {
            p.child1.parent = parentNode.child1;
          }

          if (p.child2 != null) {
            p.child2.parent = parentNode.child1;
          }

          if (p.child3 != null) {
            p.child3.parent = parentNode.child2;
          }

          if (p.child4 != null) {
            p.child4.parent = parentNode.child2;
          }
          
        }
      } else if (parentNode.keys == 2 ) {
        if (middleKey < parentNode.key1) {
          parentNode.keys = 3;
          parentNode.key3 = parentNode.key2;
          parentNode.key2 = parentNode.key1;
          parentNode.key1 = middleKey;
          parentNode.child4 = parentNode.child3;
          parentNode.child3 = parentNode.child2;
          parentNode.child2 = new Tree234Node(parentNode, p.key3);
          parentNode.child1 = new Tree234Node(parentNode, p.key1);
          parentNode.child1.child1 = p.child1;
          parentNode.child1.child2 = p.child2;
          parentNode.child2.child1 = p.child3;
          parentNode.child2.child2 = p.child4;

          if (p.child1 != null) {
            p.child1.parent = parentNode.child1;
          }

          if (p.child2 != null) {
            p.child2.parent = parentNode.child1;
          }

          if (p.child3 != null) {
            p.child3.parent = parentNode.child2;
          }

          if (p.child4 != null) {
            p.child4.parent = parentNode.child2;
          }

        } else if (parentNode.key1 < middleKey && middleKey < parentNode.key2) {
          parentNode.keys = 3;
          parentNode.key3 = parentNode.key2;
          parentNode.key2 = middleKey;
          parentNode.child4 = parentNode.child3;
          parentNode.child3 = new Tree234Node(parentNode, p.key3);
          parentNode.child2 = new Tree234Node(parentNode, p.key1);
          parentNode.child3.child1 = p.child3;
          parentNode.child3.child2 = p.child4;
          parentNode.child2.child1 = p.child1;
          parentNode.child2.child2 = p.child2;

          if (p.child1 != null) {
            p.child1.parent = parentNode.child2;
          }

          if (p.child2 != null) {
            p.child2.parent = parentNode.child2;
          }

          if (p.child3 != null) {
            p.child3.parent = parentNode.child3;
          }

          if (p.child4 != null) {
            p.child4.parent = parentNode.child3;
          }
        } else if (middleKey > parentNode.key2) {
          parentNode.keys = 3;
          parentNode.key3 = middleKey;
          parentNode.child3 = new Tree234Node(parentNode, p.key1);
          parentNode.child4 = new Tree234Node(parentNode, p.key3);
          parentNode.child3.child1 = p.child1;
          parentNode.child3.child2 = p.child2;
          parentNode.child4.child1 = p.child3;
          parentNode.child4.child2 = p.child4;

          if (p.child1 != null) {
            p.child1.parent = parentNode.child3;
          }

          if (p.child2 != null) {
            p.child2.parent = parentNode.child3;
          }

          if (p.child3 != null) {
            p.child3.parent = parentNode.child4;
          }

          if (p.child4 != null) {
            p.child4.parent = parentNode.child4;
          }
        }
      } 
      return parentNode;
    }
    
  }


  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    t.insert(50);

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");
    t.insert(23);

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    
    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    
    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nInserting 101.");
    t.insert(101);

    System.out.println("\nInserting 102.");
    t.insert(102);

    System.out.println("\nInserting 110.");
    t.insert(110);

    System.out.println("\nInserting 120.");
    t.insert(120);

    System.out.println("\nInserting 109.");
    t.insert(109);

    System.out.println("\nInserting 108.");
    t.insert(108);

    System.out.println("\nInserting 8.");
    t.insert(8);

    System.out.println("\nInserting 9.");
    t.insert(9);

    
    System.out.println("\nFinal tree:");
    t.printTree();
  }

}
