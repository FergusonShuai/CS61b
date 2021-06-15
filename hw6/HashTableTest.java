import dict.*;
// import list.*;

public class HashTableTest{
    
    public static void main(String[] argu){
        HashTableChained ht1 = new HashTableChained(11);
        System.out.println("Size of the HashTable1: " + ht1.size());

        ht1.insert(new Integer(1), new String("This is 1"));
        System.out.println("Size of the HashTable1: " + ht1.size());

        ht1.insert(new Integer(2), new String("This is 2"));
        System.out.println("Size of the HashTable1: " + ht1.size());

        ht1.insert(new Integer(4), new String("This is 4"));
        System.out.println("Size of the HashTable1: " + ht1.size());

        // ht1.find(new Integer(10));

        ht1.remove(new Integer(1));
        System.out.println("Size of the HashTable1 after removal: " + ht1.size());

        ht1.insert(new Integer(7), new String("This is 7"));
        System.out.println("Size of the HashTable1: " + ht1.size());

        ht1.makeEmpty();
        System.out.println("Size of the HashTable1 after making the dict empty: " + ht1.size());


    }
    
}