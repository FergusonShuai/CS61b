/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */
  private int width, height;
  private Pixel[][] pixelMatrix;
  private PixImage greyscalePixelImage;

  /** X[], Y[] are used as index for sliding windows*/
  private int X[] = {-1, 0, 1};
  private int Y[] = {-1, 0, 1};

  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    // Your solution here.
    this.width = width;
    this.height = height;
    this.pixelMatrix = new Pixel[width][height];
    
    for (int w = 0; w < width; w++){
      for (int h = 0; h < height; h++){
        pixelMatrix[w][h] = new Pixel();
      }
    }

  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return this.width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return this.height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
    return pixelMatrix[x][y].getRed();
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return pixelMatrix[x][y].getGreen();
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return pixelMatrix[x][y].getBlue();
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here.
    if (x > width || y > height){
      System.out.println("Coordinates of pixel out of range! Please retry.");
      return;
    }
    if((red < 0 || red > 255) || (green < 0 || green > 255) || (blue < 0 || blue > 255)){
      System.out.println("Intensities of RGB out of range, please sepcify intensities between 0 and 255.");
      return;
    }

    pixelMatrix[x][y].setRed(red);
    pixelMatrix[x][y].setGreen(green);
    pixelMatrix[x][y].setBlue(blue);
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
    String imgStr = "";

    for (int h = 0; h < height; h++){
      for (int w = 0; w < width; w++){
        imgStr = imgStr + "| " + Short.toString(pixelMatrix[w][h].getRed()) + "," + Short.toString(pixelMatrix[w][h].getGreen()) + "," + Short.toString(pixelMatrix[w][h].getBlue()) + " |";
      }
      imgStr = imgStr + "\n";
      /*System.out.print(imgStr);*/

    }

    return imgStr;
  }

  /**
   * toStringGradient() returns a String representation of this PixImage's gradient matrix.
   * @return
   */
  public String toStringGradient(){
    String gradientStr = "";

    for (int h = 0; h < height; h++){
      for (int w = 0; w < width; w++){
        gradientStr = gradientStr + "| R: (" + Integer.toString(pixelMatrix[w][h].getRedGradient_gx()) + "," + Integer.toString(pixelMatrix[w][h].getRedGradient_gy()) + ")" 
                        + " G: (" + Integer.toString(pixelMatrix[w][h].getGreenGradient_gx()) + "," + Integer.toString(pixelMatrix[w][h].getGreenGradient_gy()) + ")"
                        + " B: (" + Integer.toString(pixelMatrix[w][h].getBlueGradient_gx()) + "," + Integer.toString(pixelMatrix[w][h].getBlueGradient_gy()) + ")"
                        + " |";
      }
      gradientStr = gradientStr + "\n";
      /*System.out.print(gradientStr);*/
    }
    return gradientStr;
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    // Replace the following line with your solution.
    PixImage blurredImg = null;

    PixImage oldImg = this;
    if (numIterations <= 0) {
      return this;
    } 

    for (int i = 1; i <= numIterations; i++) {
      /*
      System.out.println("Iteration " + i + ", oldImg: ");
      System.out.print(oldImg.toString() + "\n");
      */
      
      blurredImg = new PixImage(width,height);

      oldImg.averageImage(blurredImg,oldImg);

      /*
      System.out.println("Iteration " + i + ", blurredImg: ");
      System.out.print(blurredImg.toString() + "\n");
      */

      oldImg = blurredImg;

    }

    return blurredImg;
    
  }

  /** Perhapes another function to call averagePixel() method */
  
  public void averageImage(PixImage newImage, PixImage oldImage){
    
    for (int h = 0; h < height; h++){
      for (int w = 0; w < width; w++){
        if ( (h == 0 && w == 0) || (h == 0 && w == width-1) || (h == height-1 && w == 0) || (h == height-1 && w == width-1) ){
          oldImage.averagePixel(newImage, w, h, 4);
        } else if ( h >= 1 && h <= height-2 && w >= 1 && w <= width-2){
          oldImage.averagePixel(newImage, w, h, 9);
        } else {
          oldImage.averagePixel(newImage, w, h, 6);
        }
      }
    }
  }
  

  /** Method used to get the average pixel values (red, green, blue) at coordinate [x,y] in image oldImage.
   * This is actually a very clever method to determine sliding window boundries.
   * @param newImage
   * @param oldImage
   * @param x
   * @param y
   * @param pixelCount is the denominator when averaging the pixel; 4 for pixels in four corners, 6 for pixels on edge and 9 for pixels inside.
   */
  public void averagePixel(PixImage newImage, int x, int y, int pixelCount){
    int redSum = 0, greenSum = 0, blueSum = 0;

    for (int j = 0; j < 3; j++){
      for (int i = 0; i < 3; i++){
        if (x + X[i] >= 0 && x + X[i] < width && y + Y[j] >= 0 && y + Y[j] < height){
          redSum += this.pixelMatrix[x+X[i]][y+Y[j]].getRed();
          greenSum += this.pixelMatrix[x+X[i]][y+Y[j]].getGreen();
          blueSum += this.pixelMatrix[x+X[i]][y+Y[j]].getBlue();
        }
      }
    }
    /*
    System.out.println("redSum: " + redSum + "; greenSum: " + greenSum + "; blueSum: " + blueSum);
    */

    newImage.pixelMatrix[x][y].setRed((short)(redSum/pixelCount));
    newImage.pixelMatrix[x][y].setGreen((short)(greenSum/pixelCount));
    newImage.pixelMatrix[x][y].setBlue((short)(blueSum/pixelCount));

  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */

  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**Define helper function augmentPixImage() to augment/reflect pixels on 4 edges.
   * 
   */
  private PixImage augmentPixImage(){
    
    int newWidth = width + 2;
    int newHeight = height + 2;

    PixImage augmentedPixImage = new PixImage(newWidth, newHeight);

    /* 4 corners */
    augmentedPixImage.pixelMatrix[0][0] = this.pixelMatrix[0][0];
    augmentedPixImage.pixelMatrix[newWidth-1][0] = this.pixelMatrix[width-1][0];
    augmentedPixImage.pixelMatrix[0][newHeight-1] = this.pixelMatrix[0][height-1];
    augmentedPixImage.pixelMatrix[newWidth-1][newHeight-1] = this.pixelMatrix[width-1][height-1];

    /* Things in the middle */
    for (int h = 0; h < height; h++){
      for (int w = 0; w < width; w++){
         augmentedPixImage.pixelMatrix[w+1][h+1] = this.pixelMatrix[w][h];
      }
    }

    /* Top and bottom edges */
    for(int i = 0; i < width; i++){
      augmentedPixImage.pixelMatrix[i+1][0] = this.pixelMatrix[i][0];
      augmentedPixImage.pixelMatrix[i+1][height+1] = this.pixelMatrix[i][height-1];
    }

    /* Left and right edges */
    for(int i = 0; i < height; i++){
      augmentedPixImage.pixelMatrix[0][i+1] = this.pixelMatrix[0][i];
      augmentedPixImage.pixelMatrix[width+1][i+1] = this.pixelMatrix[width-1][i];
    }

    return augmentedPixImage;
  }

  /* Another helper function updateGradient() to update the gradient of PixImage */
  private void updateGradient(){
    int[][] Gx = {{1,0,-1},{2,0,-2},{1,0,-1}};;
    int[][] Gy = {{1,2,1}, {0,0,0},{-1,-2,-1}};

    int redGradient_gx = 0;
    int redGradient_gy = 0;
    int greenGradient_gx = 0;
    int greenGradient_gy = 0;
    int blueGradient_gx = 0;
    int blueGradient_gy = 0;

    PixImage augmentedPixImage = this.augmentPixImage();

    for (int h = 1; h <= height; h++){
      for (int w = 1; w <= width; w++){
        
        /* Calculate red gradient for pixel matrix [w][h] */
        redGradient_gx = Gx[0][0]*augmentedPixImage.pixelMatrix[w-1][h-1].getRed() 
                      +  Gx[0][1]*augmentedPixImage.pixelMatrix[w][h-1].getRed()
                      +  Gx[0][2]*augmentedPixImage.pixelMatrix[w+1][h-1].getRed()
                      +  Gx[1][0]*augmentedPixImage.pixelMatrix[w-1][h].getRed()
                      +  Gx[1][1]*augmentedPixImage.pixelMatrix[w][h].getRed()
                      +  Gx[1][2]*augmentedPixImage.pixelMatrix[w+1][h].getRed()
                      +  Gx[2][0]*augmentedPixImage.pixelMatrix[w-1][h+1].getRed()
                      +  Gx[2][1]*augmentedPixImage.pixelMatrix[w][h+1].getRed()
                      +  Gx[2][2]*augmentedPixImage.pixelMatrix[w+1][h+1].getRed();

        redGradient_gy = Gy[0][0]*augmentedPixImage.pixelMatrix[w-1][h-1].getRed() 
                      +  Gy[0][1]*augmentedPixImage.pixelMatrix[w][h-1].getRed()
                      +  Gy[0][2]*augmentedPixImage.pixelMatrix[w+1][h-1].getRed()
                      +  Gy[1][0]*augmentedPixImage.pixelMatrix[w-1][h].getRed()
                      +  Gy[1][1]*augmentedPixImage.pixelMatrix[w][h].getRed()
                      +  Gy[1][2]*augmentedPixImage.pixelMatrix[w+1][h].getRed()
                      +  Gy[2][0]*augmentedPixImage.pixelMatrix[w-1][h+1].getRed()
                      +  Gy[2][1]*augmentedPixImage.pixelMatrix[w][h+1].getRed()
                      +  Gy[2][2]*augmentedPixImage.pixelMatrix[w+1][h+1].getRed();

        this.pixelMatrix[w-1][h-1].setRedGradient(redGradient_gx, redGradient_gy);

        /* Calculate green gradient for pixel matrix [w][h] */
        greenGradient_gx = Gx[0][0]*augmentedPixImage.pixelMatrix[w-1][h-1].getGreen() 
                        +  Gx[0][1]*augmentedPixImage.pixelMatrix[w][h-1].getGreen()
                        +  Gx[0][2]*augmentedPixImage.pixelMatrix[w+1][h-1].getGreen()
                        +  Gx[1][0]*augmentedPixImage.pixelMatrix[w-1][h].getGreen()
                        +  Gx[1][1]*augmentedPixImage.pixelMatrix[w][h].getGreen()
                        +  Gx[1][2]*augmentedPixImage.pixelMatrix[w+1][h].getGreen()
                        +  Gx[2][0]*augmentedPixImage.pixelMatrix[w-1][h+1].getGreen()
                        +  Gx[2][1]*augmentedPixImage.pixelMatrix[w][h+1].getGreen()
                        +  Gx[2][2]*augmentedPixImage.pixelMatrix[w+1][h+1].getGreen();

        greenGradient_gy = Gy[0][0]*augmentedPixImage.pixelMatrix[w-1][h-1].getGreen() 
                        +  Gy[0][1]*augmentedPixImage.pixelMatrix[w][h-1].getGreen()
                        +  Gy[0][2]*augmentedPixImage.pixelMatrix[w+1][h-1].getGreen()
                        +  Gy[1][0]*augmentedPixImage.pixelMatrix[w-1][h].getGreen()
                        +  Gy[1][1]*augmentedPixImage.pixelMatrix[w][h].getGreen()
                        +  Gy[1][2]*augmentedPixImage.pixelMatrix[w+1][h].getGreen()
                        +  Gy[2][0]*augmentedPixImage.pixelMatrix[w-1][h+1].getGreen()
                        +  Gy[2][1]*augmentedPixImage.pixelMatrix[w][h+1].getGreen()
                        +  Gy[2][2]*augmentedPixImage.pixelMatrix[w+1][h+1].getGreen();

        this.pixelMatrix[w-1][h-1].setGreenGradient(greenGradient_gx, greenGradient_gy);

        /* Calculate blue gradient for pixel matrix [w][h] */
        blueGradient_gx = Gx[0][0]*augmentedPixImage.pixelMatrix[w-1][h-1].getBlue() 
                       +  Gx[0][1]*augmentedPixImage.pixelMatrix[w][h-1].getBlue()
                       +  Gx[0][2]*augmentedPixImage.pixelMatrix[w+1][h-1].getBlue()
                       +  Gx[1][0]*augmentedPixImage.pixelMatrix[w-1][h].getBlue()
                       +  Gx[1][1]*augmentedPixImage.pixelMatrix[w][h].getBlue()
                       +  Gx[1][2]*augmentedPixImage.pixelMatrix[w+1][h].getBlue()
                       +  Gx[2][0]*augmentedPixImage.pixelMatrix[w-1][h+1].getBlue()
                       +  Gx[2][1]*augmentedPixImage.pixelMatrix[w][h+1].getBlue()
                       +  Gx[2][2]*augmentedPixImage.pixelMatrix[w+1][h+1].getBlue();

        blueGradient_gy = Gy[0][0]*augmentedPixImage.pixelMatrix[w-1][h-1].getBlue() 
                       +  Gy[0][1]*augmentedPixImage.pixelMatrix[w][h-1].getBlue()
                       +  Gy[0][2]*augmentedPixImage.pixelMatrix[w+1][h-1].getBlue()
                       +  Gy[1][0]*augmentedPixImage.pixelMatrix[w-1][h].getBlue()
                       +  Gy[1][1]*augmentedPixImage.pixelMatrix[w][h].getBlue()
                       +  Gy[1][2]*augmentedPixImage.pixelMatrix[w+1][h].getBlue()
                       +  Gy[2][0]*augmentedPixImage.pixelMatrix[w-1][h+1].getBlue()
                       +  Gy[2][1]*augmentedPixImage.pixelMatrix[w][h+1].getBlue()
                       +  Gy[2][2]*augmentedPixImage.pixelMatrix[w+1][h+1].getBlue();

        this.pixelMatrix[w-1][h-1].setBlueGradient(blueGradient_gx, blueGradient_gy);

      }
    }
  }

  /* Helper function energy() to calculate energy of the pixel. Returns a matrix with size (height-1)*(width-1) */
  public void updateEnergy(){
    for (int h = 0; h < height; h++){
      for (int w = 0; w < width; w++){
        this.pixelMatrix[w][h].setEnergy();
      }
    }
  }


  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
    long energy = 0;
    short intensity = 0;

    greyscalePixelImage = new PixImage(width,height);
    this.updateGradient();
    
    for (int w = 0; w < width; w++){
      for (int h = 0; h < height; h++){
        this.pixelMatrix[w][h].setEnergy();
        energy = this.pixelMatrix[w][h].getEnergy();
        intensity = mag2gray(energy);
        greyscalePixelImage.pixelMatrix[w][h].setRed(intensity);
        greyscalePixelImage.pixelMatrix[w][h].setGreen(intensity);
        greyscalePixelImage.pixelMatrix[w][h].setBlue(intensity);
      }
    }

    return greyscalePixelImage;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  
  /* Testing get width and height method. */
  /*
  System.out.println("Testing PixImage constructor");

  PixImage image1 = new PixImage(4,3);
  System.out.println("image1's width is "+image1.getWidth());
  System.out.println("image1's height is "+image1.getHeight());
  */

  /* Testing setPixel method */
  /*
  image1.setPixel(0,0,(short)0,(short)0,(short)0);
  image1.setPixel(1,0,(short)1,(short)1,(short)1);
  image1.setPixel(2,0,(short)2,(short)2,(short)2);
  image1.setPixel(3,0,(short)3,(short)3,(short)3);
  image1.setPixel(0,1,(short)4,(short)4,(short)4);
  image1.setPixel(1,1,(short)5,(short)5,(short)5);
  image1.setPixel(2,1,(short)5,(short)5,(short)5);
  image1.setPixel(3,1,(short)6,(short)6,(short)6);
  image1.setPixel(0,2,(short)7,(short)7,(short)7);
  image1.setPixel(1,2,(short)8,(short)8,(short)8);
  image1.setPixel(2,2,(short)9,(short)9,(short)9);
  image1.setPixel(3,2,(short)10,(short)10,(short)10);
  */

  /*
  System.out.println("The red intensity at [0,0] is "+image1.getRed(0,0));
  System.out.println("The green intensity at [0,0] is "+image1.getGreen(0,0));
  System.out.println("The blue intensity at [0,0] is "+image1.getBlue(0,0));
  
  
  System.out.print(image1.toString());
  System.out.println();
  */

  /** Test averagePixel() function. */
  /*
  System.out.println("Test averagePixel() function.");
  PixImage image2 = new PixImage(4,3);
  image1.averagePixel(image2,0,0,4);
  System.out.print(image2.toString());
  System.out.println();

  image1.averagePixel(image2,1,0,6);
  System.out.print(image2.toString());
  System.out.println();

  image1.averagePixel(image2,1,1,9);
  System.out.print(image2.toString());
  System.out.println();

  image1.averagePixel(image2,0,2,4);
  System.out.print(image2.toString());
  System.out.println();
  */

  /** Test averageImage() function */
  /*
  System.out.println("Test averageImage() function.");
  PixImage image3 = new PixImage(4,3);

  image1.averageImage(image3, image1);
  System.out.print(image3.toString());
  System.out.println();
  */

  /** Test boxBlur() function */
  /*
  System.out.print("Test boxBlur(1) function.");
  PixImage imageBlurred1 = image1.boxBlur(1);
  System.out.print(imageBlurred1.toString());
  System.out.println();
  */

  /*
  System.out.print("Test boxBlur(2) function.");
  PixImage imageBlurred2 = image1.boxBlur(2);
  System.out.print("imageBlurred2: " + imageBlurred2.toString());
  System.out.println();

  PixImage image11 = new PixImage(4,3);

  image11.setPixel(0,0,(short)3,(short)3,(short)3);
  image11.setPixel(1,0,(short)3,(short)3,(short)3);
  image11.setPixel(2,0,(short)4,(short)4,(short)4);
  image11.setPixel(3,0,(short)4,(short)4,(short)4);
  image11.setPixel(0,1,(short)4,(short)4,(short)4);
  image11.setPixel(1,1,(short)4,(short)4,(short)4);
  image11.setPixel(2,1,(short)5,(short)5,(short)5);
  image11.setPixel(3,1,(short)5,(short)5,(short)5);
  image11.setPixel(0,2,(short)6,(short)6,(short)6);
  image11.setPixel(1,2,(short)6,(short)6,(short)6);
  image11.setPixel(2,2,(short)7,(short)7,(short)7);
  image11.setPixel(3,2,(short)7,(short)7,(short)7);
  */

  /*

  System.out.println("Test averageImage() function.");
  PixImage image33 = new PixImage(4,3);

  image33 = image11;

  /**This part is very error-prone. 
   * image33 and image11 are actually pointing to the same 2d array.
   * So when we pass image33 and image11 into the averageImage() function, 
   * we are actually changing the original array itself as we sliding the window,
   * therefore causing some wired errors. Be cautious!!!
  
  image11.averageImage(image33, image11);
  System.out.print(image33.toString());
  System.out.println();

  */

  /*
  System.out.println("Test augmentPixImage() function.");
  PixImage image11 = new PixImage(4,3);

  image11.setPixel(0,0,(short)3,(short)3,(short)3);
  image11.setPixel(1,0,(short)3,(short)3,(short)3);
  image11.setPixel(2,0,(short)4,(short)4,(short)4);
  image11.setPixel(3,0,(short)4,(short)4,(short)4);
  image11.setPixel(0,1,(short)4,(short)4,(short)4);
  image11.setPixel(1,1,(short)4,(short)4,(short)4);
  image11.setPixel(2,1,(short)5,(short)5,(short)5);
  image11.setPixel(3,1,(short)5,(short)5,(short)5);
  image11.setPixel(0,2,(short)6,(short)6,(short)6);
  image11.setPixel(1,2,(short)6,(short)6,(short)6);
  image11.setPixel(2,2,(short)7,(short)7,(short)7);
  image11.setPixel(3,2,(short)7,(short)7,(short)7);

  */

  /*
  System.out.println("Original image: " + "\n" + image1.toString());
  PixImage newImage = image1.augmentPixImage();
  System.out.println("New image: " + "\n" + newImage.toString());

  System.out.println("Original image gradients: " + "\n" + image1.toStringGradient());

  image1.updateGradient();

  System.out.println("Updated image gradients: " + "\n" + image1.toStringGradient());

  System.out.println("Grayscale intensities after sobelEdge function : " + "\n" + image1.sobelEdges());
  */

  System.out.println("Test sobelEdges() function.");
  PixImage imageSobelEdges = new PixImage(4,3);

  imageSobelEdges.setPixel(0,0,(short)3,(short)3,(short)3);
  imageSobelEdges.setPixel(1,0,(short)255,(short)255,(short)255);
  imageSobelEdges.setPixel(2,0,(short)4,(short)4,(short)4);
  imageSobelEdges.setPixel(3,0,(short)4,(short)4,(short)4);
  imageSobelEdges.setPixel(0,1,(short)220,(short)220,(short)220);
  imageSobelEdges.setPixel(1,1,(short)4,(short)4,(short)4);
  imageSobelEdges.setPixel(2,1,(short)5,(short)5,(short)5);
  imageSobelEdges.setPixel(3,1,(short)100,(short)100,(short)100);
  imageSobelEdges.setPixel(0,2,(short)6,(short)6,(short)6);
  imageSobelEdges.setPixel(1,2,(short)6,(short)6,(short)6);
  imageSobelEdges.setPixel(2,2,(short)50,(short)50,(short)50);
  imageSobelEdges.setPixel(3,2,(short)7,(short)7,(short)7);

  System.out.println("Grayscale intensities after sobelEdge function : " + "\n" + imageSobelEdges.sobelEdges());


  
  }
}
