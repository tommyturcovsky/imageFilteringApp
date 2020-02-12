import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class provides methods to generate different types of images and writes them to a file.
 */
public class GenerateImage {

  /**
   * Generates an image of a rainbow in a given format based on user specified total width and.
   * height.
   *
   * @param width    total width in pixels of the generated image
   * @param height   total height in pixels of the generated image
   * @param filename the full path of where the image must be stored. This should include the name
   *                 and extension of the file
   * @throws IOException if the file cannot be written to the provided path
   */
  public static void rainbow(int width, int height, String filename) throws IOException {
    BufferedImage rainbow = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    int stripeHeight = height / 7;
    int red = (255 << 16);
    int orange = (255 << 16) + (165 << 8) + 0;
    int yellow = (255 << 16) + (255 << 8) + 0;
    int green = (0 << 16) + (255 << 8) + 0;
    int blue = (0 << 16) + (0 << 8) + 255;
    int indigo = (75 << 16) + (0 << 8) + 130;
    int violet = (128 << 16) + (0 << 8) + 128;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (i < stripeHeight) {
          rainbow.setRGB(j, i, red);
        } else if (i > stripeHeight && i <= stripeHeight * 2) {
          rainbow.setRGB(j, i, orange);
        } else if (i > stripeHeight * 2 && i <= stripeHeight * 3) {
          rainbow.setRGB(j, i, yellow);
        } else if (i > stripeHeight * 3 && i <= stripeHeight * 4) {
          rainbow.setRGB(j, i, green);
        } else if (i > stripeHeight * 4 && i <= stripeHeight * 5) {
          rainbow.setRGB(j, i, blue);
        } else if (i > stripeHeight * 5 && i <= stripeHeight * 6) {
          rainbow.setRGB(j, i, indigo);
        } else if (i > stripeHeight * 6 && i <= stripeHeight * 7) {
          rainbow.setRGB(j, i, violet);
        }
      }
    }
    String extension = filename.substring(filename.indexOf(".") + 1);
    ImageIO.write(rainbow, extension, new FileOutputStream(filename));
  }

  /**
   * Generates an image of a checkerboard in a given format based on user specified area of the.
   * individual checker squares.
   *
   * @param checkerSquareArea the area of the individual squares on the checkerboard
   * @param filename          the full path of where the image must be stored. This should include
   *                          the name and extension of the file
   * @throws IOException if the file cannot be written to the provided path
   */
  public static void checkerboard(int checkerSquareArea, String filename) throws IOException {
    int aquaMarine = (175 << 16) + (255 << 8) + 112;
    int red = (255 << 16);

    int squareWidth = (int) Math.sqrt(checkerSquareArea);
    int squareHeight = (int) Math.sqrt(checkerSquareArea);

    int boardHeight = squareHeight * 8;
    int boardWidth = squareWidth * 8;

    BufferedImage checkers = new BufferedImage(boardWidth, boardHeight, BufferedImage.TYPE_INT_RGB);

    // Goes down vertically
    for (int heightCount = 1; heightCount <= 8; heightCount++) {
      // Goes across horizontally for row.
      for (int widthCount = 1; widthCount <= 8; widthCount++) {
        for (int i = (squareWidth * widthCount) - squareWidth; i < squareWidth * widthCount; i++) {
          for (int j = (squareHeight * heightCount) - squareHeight; j < squareHeight * heightCount;
               j++) {
            if (widthCount % 2 == 1 && heightCount % 2 == 1) {
              checkers.setRGB(i, j, red);
            } else if (widthCount % 2 == 0 && heightCount % 2 == 1) {
              checkers.setRGB(i, j, aquaMarine);
            } else if (widthCount % 2 == 1 && heightCount % 2 == 0) {
              checkers.setRGB(i, j, aquaMarine);
            } else {
              checkers.setRGB(i, j, red);
            }
          }
        }
      }
    }

    String extension = filename.substring(filename.indexOf(".") + 1);
    ImageIO.write(checkers, extension, new FileOutputStream(filename));
  }

}
