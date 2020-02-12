/**
 * All the features that the controller can call upon.
 */
public interface Features extends ControllerObserver {
  /**
   * Controller calls on the Image class (model) to create an instance of an Image.
   *
   * @param filename name of image file to load
   */
  void loadImage(String filename);

  /**
   * Controller calls on the Image class (model) to save the Image to a file.
   *
   * @param filename name of image file to be saved to
   */
  void save(String filename);

  /**
   * Controller calls on the Image class (model) to blur the Image.
   */
  void blur();

  /**
   * Controller calls on the Image class (model) to sharpen the Image.
   */
  void sharpen();

  /**
   * Controller calls on the Image class (model) make the image greyscale.
   */
  void greyscale();

  /**
   * Controller calls on the Image class (model) make the image sepia.
   */
  void sepia();

  /**
   * Controller calls on the Image class (model) make the image a black/white dither.
   */
  void dither();

  /**
   * Controller calls on the Image class (model) make the image a mosaic based on number of seeds.
   */
  void mosaic(int seeds);

  /**
   * Controller calls on the Image class (model) generate a rainbow image based on width.
   */
  void rainbow(int width);

  /**
   * Controller calls on the Image class (model) generate a checkerboard image.
   * based checkerSquareArea.
   */
  void checkerboard(int checkerSquareArea);
}
