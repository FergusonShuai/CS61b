/* Pixel.java */
/** Create a class to represent a pixel in an image. */

public class Pixel {
    private short red;
    private short green;
    private short blue;

    private int redGradient_gx;
    private int greenGradient_gx;
    private int blueGradient_gx;
    private int redGradient_gy;
    private int greenGradient_gy;
    private int blueGradient_gy;

    private long energy;

    Pixel() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;

        this.redGradient_gx = 0;
        this.greenGradient_gx = 0;
        this.blueGradient_gx = 0;
        this.redGradient_gy = 0;
        this.greenGradient_gy = 0;
        this.blueGradient_gy = 0;

        this.energy = 0;
    }

    Pixel(int red, int green, int blue) {
        this.red = (short)red;
        this.green = (short)green;
        this.blue = (short)blue;
    }

    public short getRed() {
        return red;
    }

    public short getGreen() {
        return green;
    }

    public short getBlue(){
        return blue;
    }

    public int getRedGradient_gx(){
        return redGradient_gx;
    }

    public int getGreenGradient_gx(){
        return greenGradient_gx;
    }

    public int getBlueGradient_gx(){
        return blueGradient_gx;
    }

    public int getRedGradient_gy(){
        return redGradient_gy;
    }

    public int getGreenGradient_gy(){
        return greenGradient_gy;
    }

    public int getBlueGradient_gy(){
        return blueGradient_gy;
    }

    public long getEnergy(){
        return energy;
    }

    public void setRed(short red) {
        this.red = red;
    }

    public void setGreen(short green) {
        this.green = green;
    }

    public void setBlue(short blue) {
        this.blue = blue;
    }

    public void setRedGradient(int redGradient_gx, int redGradient_gy){
        this.redGradient_gx = redGradient_gx;
        this.redGradient_gy = redGradient_gy;
    }

    public void setGreenGradient(int greenGradient_gx, int greenGradient_gy){
        this.greenGradient_gx = greenGradient_gx;
        this.greenGradient_gy = greenGradient_gy;
    }

    public void setBlueGradient(int blueGradient_gx, int blueGradient_gy){
        this.blueGradient_gx = blueGradient_gx;
        this.blueGradient_gy = blueGradient_gy;
    }

    public void setEnergy(){
        this.energy = redGradient_gx * redGradient_gx + redGradient_gy * redGradient_gy
                    + greenGradient_gx * greenGradient_gx + greenGradient_gy * greenGradient_gy
                    + blueGradient_gx * blueGradient_gx + blueGradient_gy * blueGradient_gy;              
    }

}