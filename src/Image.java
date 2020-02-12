import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents all actions an Image can perform.
 */
public class Image implements ImageInterface  {
  /**
   * 3D integer array representing the red, blue, and green color channels respectively.
   */
  private int[][][] rgb;
  /**
   * Represents the width in pixels of an image.
   */
  private int width;
  /**
   * Represents the Height in pixels of an image.
   */
  private int height;

  /**
   * Observer subscribed to the subject Image.
   */
  private ControllerObserver observer;

  /**
   * Default Constructor for an image. Makes a blank Image object.
   */
  public Image() {
    //Empty Default Constructor
  }

  /**
   * A constructor for an Image taking in it's file name and figuring out the integer array RGB. and
   * total pixel width and height.
   *
   * @param filename file to be loaded in and do stuff with.
   */
  public Image(String filename) {
    try {
      this.rgb = ImageUtil.readImage(filename);
      this.width = ImageUtil.getWidth(filename);
      //this.width = rgb.length;
      this.height = ImageUtil.getHeight(filename);
      //this.height = rgb[0].length - 1;
    } catch (IOException ex) {
      System.out.println("Input Output Exception occurred.");
    }
  }

  /**
   * A constructor for an Image taking in it's integer array RGB, and total pixel width and height.
   *
   * @param rgb    3D integer array representing the color values at specific pixels
   * @param width  the total width of this Image
   * @param height the total height of this Image
   */
  public Image(int[][][] rgb, int width, int height) {
    this.rgb = new int[rgb.length][rgb[0].length][rgb[0][0].length];
    this.width = width;
    this.height = height;
  }

  /**
   * Load an Image with a given filename.
   *
   * @param filename filename you are trying to load from
   */
  public void loadImage(String filename) {
    try {
      this.rgb = ImageUtil.readImage(filename);
      this.width = ImageUtil.getWidth(filename);
      this.height = ImageUtil.getHeight(filename);

    } catch (IOException ex) {
      System.out.println("Input Output Exception occurred.");
    }
  }

  /**
   * Add observers to subscribe to this model as a Subject.
   *
   * @param observer will be controller observer
   */
  @Override
  public void addObserver(ControllerObserver observer) {
    this.observer = observer;
  }

  /**
   * Notifies observers there has been a change to the subject.
   */
  @Override
  public void notifyObserver() {
    this.observer.update();
  }

  /**
   * Saves image to a file.
   *
   * @param filename name to be saved as
   */
  public void save(String filename) {
    try {
      ImageUtil.writeImage(this.rgb, this.width, this.height, filename);
    } catch (IOException ex) {
      System.out.println("Input Output Exception occurred.");
    }
  }

  /**
   * Method to create a Buffered Image based on current constructed Image.
   *
   * @return a buffered Image based on the current constructed Image
   */
  @Override
  public BufferedImage getImage() {
    return ImageUtil.getImage(this.rgb, this.width, this.height);
  }

  /**
   * Returns a blurred version of this Image.
   */
  public void blur() {

    final double[][] blurFilter = {
            {.0625, .125, .0625},
            {.125, .25, .125},
            {.0625, .125, .0625}
    };

    int[][][] blurredRGB = applyFilter(blurFilter);
    this.rgb = blurredRGB;
  }

  /**
   * Returns a sharpened version of this Image.
   */
  public void sharpen() {
    final double[][] sharpenFilter = {
            {-.125, -.125, -.125, -.125, -.125},
            {-.125, .25, .25, .25, -.125},
            {-.125, .25, 1, .25, -.125},
            {-.125, .25, .25, .25, -.125},
            {-.125, -.125, -.125, -.125, -.125},
    };

    int[][][] sharpenRGB = applyFilter(sharpenFilter);
    this.rgb = sharpenRGB;
  }

  /**
   * Helper method for blur and sharpen.
   *
   * @return filtered version of this image depending on 2D filter array passed in
   */
  private int[][][] applyFilter(double[][] filter) {

    int[][][] filteredRGB = new int[rgb.length][rgb[0].length][rgb[0][0].length];

    for (int row = 0; row < this.height; row++) { // Go through rows.
      for (int col = 0; col < this.width; col++) { // Go through columns.
        for (int color = 0; color < 3; color++) {
          int colorResult = 0;
          int filterSize = filter.length / 2;

          // iterate over filter
          int kernelXIndex = -1;
          for (int filterRow = filterSize * -1; filterRow <= filterSize; filterRow++) {
            kernelXIndex++;
            int kernelYIndex = -1;
            for (int filterCol = filterSize * -1; filterCol <= filterSize; filterCol++) {
              kernelYIndex++;
              if (row + filterRow < 0 || row + filterRow >= height
                      || col + filterCol < 0 || col + filterCol >= width) {
                colorResult += 0;
              } else {
                colorResult += rgb[row + filterRow][col + filterCol][color]
                        * filter[kernelXIndex][kernelYIndex];
              }
            }
          }

          // Clamping if color result is greater than 255 or less than 0. (Not a valid color value)
          if (colorResult > 255) {
            colorResult = 255;
          } else if (colorResult < 0) {
            colorResult = 0;
          }
          filteredRGB[row][col][color] = colorResult;

        }
      }
    }
    return filteredRGB;
  }

  /**
   * Make an Image greyscale.
   */
  public void greyscale() {
    final double[][] greyscaleFilter = {
            {.2126, .7152, .0722},
            {.2126, .7152, .0722},
            {.2126, .7152, .0722}
    };

    int[][][] greyscaleRGB = applyColorFilter(greyscaleFilter);
    this.rgb = greyscaleRGB;
  }

  /**
   * Make an Image sepia.
   */
  public void sepia() {
    final double[][] sepiaFilter = {
            {.393, .769, .189},
            {.349, .686, .168},
            {.272, .534, .131}
    };


    int[][][] filteredRGB = applyColorFilter(sepiaFilter);
    this.rgb = filteredRGB;
  }

  /**
   * Helper method for greyscale and sepia.
   *
   * @param filter 2D array to act upon to affect color correction of this image
   * @return color filtered version of this image based on filter based through method
   */
  private int[][][] applyColorFilter(double[][] filter) {

    int[][][] filteredRGB = new int[rgb.length][rgb[0].length][rgb[0][0].length];

    for (int row = 0; row < height; row++) { // Go through rows.
      for (int col = 0; col < width; col++) { // Go through columns.

        // iterate over filter
        int filterSize = filter.length;
        for (int filterRow = 0; filterRow < filterSize; filterRow++) {
          double colorResult = 0;
          for (int filterCol = 0; filterCol < filterSize; filterCol++) {
            // Perform calculation.
            double currentColor = this.rgb[row][col][filterCol];
            double filterDouble = filter[filterRow][filterCol];
            colorResult += (filterDouble * currentColor);
          }
          // Clamping if color result is greater than 255 or less than 0. (Not a valid color value)
          colorResult = colorClamp(colorResult);
          int colorFinal = (int) colorResult;

          filteredRGB[row][col][filterRow] = colorFinal;
        }
      }
    }

    return filteredRGB;
  }

  /**
   * Return a dithered version of this Image.
   */
  public void dither() {
    // First convert to greyscale
    this.greyscale();
    int[][][] ditheredRGB = new int[rgb.length][rgb[0].length][rgb[0][0].length];

    // Go through every position in image traversing row wise.
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {

        int oldColor = this.rgb[row][col][0];

        // new color closer to 0 or 255?
        int newColor;
        if (oldColor < 127) {
          newColor = 0;
        } else {
          newColor = 255;
        }

        // figure out error
        int error = oldColor - newColor;

        // set color of pixel (row,column) to (newColor, newColor, newColor)
        ditheredRGB[row][col][0] = newColor;
        ditheredRGB[row][col][1] = newColor;
        ditheredRGB[row][col][2] = newColor;


        // If statements improve dither quality to catch exceptions and reach the edges of Image

        // add (7/16 * error) to pixel on the right (r; c + 1)
        for (int color = 0; color < 3; color++) {
          if (col + 1 < this.width) {
            this.rgb[row][col + 1][color] += (int) (((double) (7 / 16)) * error);
          }
          // add (3/16 * error) to pixel on the next-row-left (r - 1; c + 1)
          if (col > 0 && row + 1 < this.height) {
            this.rgb[row + 1][col - 1][color] += (int) (((double) (3 / 16)) * error);
          }
          // add (5/16 * error) to pixel below in next row (r + 1; c)
          if (row + 1 < this.height) {
            this.rgb[row + 1][col][color] += (int) (((double) (5 / 16)) * error);
          }
          // add (1/16 * error) to pixel on the next-row-right (r + 1; c + 1)
          if (col + 1 < this.width && row + 1 < this.height) {
            this.rgb[row + 1][col + 1][color] += (int) (((double) (1 / 16)) * error);
          }
        }
      }
    }

    this.rgb = ditheredRGB;
  }

  /**
   * Returns a Mosaic version of this Image.
   *
   * @param seeds set of random pixels that clusters colors around it
   */
  public void mosaic(int seeds) {

    int[][][] mosaicRGB = new int[rgb.length][rgb[0].length][rgb[0][0].length];

    int width = this.width;
    int height = this.height;
    Random rand = new Random();

    ArrayList<Point2D> seedList = new ArrayList<>();

    int minIndex = 0;

    for (int i = 0; i < seeds; i++) {
      int randomX = rand.nextInt(width);
      int randomY = rand.nextInt(height);
      Point2D spot = new Point2D(randomX,randomY);

      seedList.add(spot);

    }
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        double smallestDistance = 99999;
        for (int i = 0; i < seedList.size(); i++) {
          Point2D difference = new Point2D(seedList.get(i).getX(),seedList.get(i).getY());

          double totalDifference = difference.calculateDistance(new Point2D(col, row));
          if (totalDifference < smallestDistance) {
            smallestDistance = totalDifference;
            minIndex = i;
          }

        }
        // color current pixel the same as what is at minIndex
        int xOfSeed = (int) seedList.get(minIndex).getX();
        int yOfSeed = (int) seedList.get(minIndex).getY();
        for (int i = 0; i < 3; i++) {
          mosaicRGB[row][col][i] = this.rgb[yOfSeed][xOfSeed][i];
        }
      }
    }
    this.rgb = mosaicRGB;
  }

  /**
   * Generates a Rainbow Image based on width and height passed in.
   *
   * @param width the width of the Image
   */
  public void rainbow(int width) {
    //Initialize Image to be returned
    this.width = width;
    this.height = width;
    System.out.println(height);
    int[][][] rainbowRGB = new int[width][height][3];

    //Initialize Colors
    int stripeHeight = height / 7;
    int[] red = {255, 0 ,0};
    int[] orange = {255, 140, 0};
    int[] yellow = {255, 233, 0};
    int[] green = {101, 255, 0};
    int[] blue = {0, 191, 255};
    int[] indigo = {89, 0, 255};
    int[] violet = {161, 0, 255};

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (i <= stripeHeight) {
          for (int color = 0; color < 3; color++) {
            rainbowRGB[j][i][color] = red[color];
          }
        } else if (i > stripeHeight && i <= stripeHeight * 2) {
          for (int color = 0; color < 3; color++) {
            rainbowRGB[j][i][color] = orange[color];
          }
        } else if (i > stripeHeight * 2 && i <= stripeHeight * 3) {
          for (int color = 0; color < 3; color++) {
            rainbowRGB[j][i][color] = yellow[color];
          }
        } else if (i > stripeHeight * 3 && i <= stripeHeight * 4) {
          for (int color = 0; color < 3; color++) {
            rainbowRGB[j][i][color] = green[color];
          }
        } else if (i > stripeHeight * 4 && i <= stripeHeight * 5) {
          for (int color = 0; color < 3; color++) {
            rainbowRGB[j][i][color] = blue[color];
          }
        } else if (i > stripeHeight * 5 && i <= stripeHeight * 6) {
          for (int color = 0; color < 3; color++) {
            rainbowRGB[j][i][color] = indigo[color];
          }
        } else if (i > stripeHeight * 6 && i <= stripeHeight * 7) {
          for (int color = 0; color < 3; color++) {
            rainbowRGB[j][i][color] = violet[color];
          }
        }
      }
    }

    this.rgb = rainbowRGB;
  }

  /**
   * Generates a Checkerboard Image based on the checker square area.
   *
   * @param checkerSquareArea square area of the individual squares in the checkerboard
   */
  public void checkerboard(int checkerSquareArea) {
    // Established Colors
    int[] purple = {161, 0, 255};
    int[] blue = {0, 191, 255};

    int squareWidth = (int) Math.sqrt(checkerSquareArea);
    int squareHeight = (int) Math.sqrt(checkerSquareArea);

    this.height = squareHeight * 8;
    this.width = squareWidth * 8;

    int[][][] checkerboardRGB = new int[width][height][3];

    // Goes down vertically
    for (int heightCount = 1; heightCount <= 8; heightCount++) {
      // Goes across horizontally for row.
      for (int widthCount = 1; widthCount <= 8; widthCount++) {
        for (int i = (squareWidth * widthCount) - squareWidth; i < squareWidth * widthCount; i++) {
          for (int j = (squareHeight * heightCount) - squareHeight; j < squareHeight * heightCount;
               j++) {
            if (widthCount % 2 == 1 && heightCount % 2 == 1) {
              for (int color = 0; color < 3; color++) {
                checkerboardRGB[j][i][color] = purple[color];
              }
            } else if (widthCount % 2 == 0 && heightCount % 2 == 1) {
              for (int color = 0; color < 3; color++) {
                checkerboardRGB[j][i][color] = blue[color];
              }
            } else if (widthCount % 2 == 1 && heightCount % 2 == 0) {
              for (int color = 0; color < 3; color++) {
                checkerboardRGB[j][i][color] = blue[color];
              }
            } else {
              for (int color = 0; color < 3; color++) {
                checkerboardRGB[j][i][color] = purple[color];
              }
            }
          }
        }
      }
    }

    this.rgb = checkerboardRGB;
  }

  /**
   * Return the rgb 3D array of this image.
   *
   * @return the rgb 3D array of this image.
   */
  public int[][][] getRgb() {
    return rgb;
  }

  /**
   * Return the width of this image.
   *
   * @return the width of this image.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Return the height of this image.
   *
   * @return the height of this image
   */
  public int getHeight() {
    return height;
  }

  /**
   * Helper method. Clamps color numerical value if color is less than 0 or greater than 255.
   *
   * @param colorResult numerical color value to be evaluated
   * @return a clamped numerical color value
   */

  public static double colorClamp(double colorResult) {
    if (colorResult > 255) {
      colorResult = 255;
    } else if (colorResult < 0) {
      colorResult = 0;
    }
    return colorResult;
  }

  /**
   * Private helper method to copy an instance of an Image.
   *
   * @return an exact copy of an image in a new instance.
   */
  private Image copyConstructor() {
    return new Image(this.rgb, this.width, this.height);
  }
}
