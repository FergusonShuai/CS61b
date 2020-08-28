/* RunLengthItem.java */

// package pj1;

/** RunLengthItem class represents the object used in RunLengthNode class.
 *  Essentially it is a Pixel class + RunLength value
 */

 public class RunLengthItem {
   
    protected Pixel p;
    protected int rl;

    /* Zero-parameter constructor, calling Pixel zero-parameter constructor. */
    public RunLengthItem() {
        p = new Pixel();
        rl = 0;
    }

    /* Four-parameter constructor */
    public RunLengthItem(int red, int green, int blue, int runLength) {
        p = new Pixel(red, green, blue);
        rl = runLength;
    }

    public Pixel getPixel() {
        return p;
    }

    public int getRunLength() {
        return rl;
    }

    public void setRunLength(int runLength) {
        rl = runLength;
    }


 }