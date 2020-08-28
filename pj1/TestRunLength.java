/* TestRunLength.java */

public class TestRunLength {

    public static void main (String[] args) {
        // testRunLengthItem();
        // testRunLengthDList();
        // testRunLengthEncoding(25,26);
        /*
         int[] red = {11, 22, 33, 44};
         int[] green = {55, 66, 77, 88};
         int[] blue = {99, 111, 222, 254};
         int[] runLengths = {1,2,3,3};
         testRunLengthEncoding(3,3,red, green, blue, runLengths);
        */
        PixImage image11 = new PixImage(3,3);

        image11.setPixel(0,0,(short)1,(short)1,(short)1);
        image11.setPixel(1,0,(short)1,(short)1,(short)1);
        image11.setPixel(2,0,(short)2,(short)1,(short)1);
        image11.setPixel(0,1,(short)3,(short)1,(short)1);
        image11.setPixel(1,1,(short)3,(short)1,(short)1);
        image11.setPixel(2,1,(short)3,(short)1,(short)1);
        image11.setPixel(0,2,(short)3,(short)1,(short)1);
        image11.setPixel(1,2,(short)4,(short)2,(short)1);
        image11.setPixel(2,2,(short)4,(short)2,(short)3);

        testRunLengthEncodingFromPixImage(image11);

    }

    public static void testRunLengthItem(){
        RunLengthItem i = new RunLengthItem(1, 2, 3, 10);
        System.out.println("The pixel intensity for red is: " + i.getPixel().getRed());
        System.out.println("The pixel intensity for green is: " + i.getPixel().getGreen());
        System.out.println("The run length is: " + i.getRunLength());

    }

    public static void testRunLengthDList() {
        RunLengthItem i1 = new RunLengthItem(1, 2, 3, 10);
        RunLengthItem i2 = new RunLengthItem(4, 5, 6, 20);
        RunLengthDList dl1 = new RunLengthDList();
        dl1.insertFront(i1);
        // dl1.insertBack(i2);
        // System.out.println(dl1.head.next.item.getClass());
        // System.out.println(((RunLengthItem)dl1.head.next.next.item).getPixel().getRed());


        // System.out.println("toString(): " + dl1.toString());

        dl1.insertBack(i2);

        //System.out.println("RunLengthDList after insertBack(): " + dl1.toString());

    }

    
    public static void testRunLengthEncoding(int w, int h) {
        RunLengthEncoding rle = new RunLengthEncoding(w, h);
        System.out.println("The width of the run length encoding: " + rle.getWidth());
        System.out.println("The height of the run length encoding: " + rle.getHeight());

    }

    public static void testRunLengthEncoding(int w, int h, int[] red, int[] green,
    int[] blue, int[] runLengths) {
        RunLengthEncoding rle = new RunLengthEncoding(w, h, red, green, blue, runLengths);
        System.out.println("The width of the run length encoding: " + rle.getWidth());
        System.out.println("The height of the run length encoding: " + rle.getHeight());
        
        System.out.println("Iterator has next item? " + rle.iterator().hasNext());

        /*
        int[] a = new int[4];
        a = rle.iterator().next();
        b = rle.iterator().next();
        
        for (int i = 0; i < 4; i++) {
            System.out.println(a[i]);
        }
        */

        PixImage pImage = rle.toPixImage();

        System.out.println("RunLengthEncoding to pixel image: \n" + pImage.toString());

        System.out.println("RunLengthEncoding print: \n" + rle.toString() + "\n");

        

    }

    public static void testRunLengthEncodingFromPixImage(PixImage pImage) {
        

        RunLengthEncoding rle = new RunLengthEncoding(pImage);

        System.out.println("RunLengthEncoding print: \n" + rle.toString() + "\n");

    }
    
    



}