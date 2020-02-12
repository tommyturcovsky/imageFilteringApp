import java.awt.image.BufferedImage;

/**
 * Implementation of the Controller of our Image application.
 */
public class ImageController implements Features {
  /**
   * Image to be done stuff with.
   */
  private ImageInterface image;
  /**
   * View of the MVC pattern.
   */
  private ViewInterface view;

  /**
   * Constructor for the ImageController.
   *
   * @param image the model
   * @param view the view
   */
  public ImageController(ImageInterface image, ViewInterface view) {
    this.image = image;
    this.view = view;
    image.addObserver(this);
    view.setCommands(this);

  }

  /**
   * Update the Image shown in the view.
   */
  @Override
  public void update() {
    BufferedImage buff = image.getImage();
    view.updateView(buff);
  }

  /**
   * Controller calls on the Image class (model) to create an instance of an Image.
   *
   * @param filename name of image file to load
   */
  public void loadImage(String filename) {
    image.loadImage(filename);
    image.notifyObserver();
  }

  /**
   * Controller calls on the Image class (model) to save the Image to a file.
   *
   * @param filename name of image file to be saved to
   */
  public void save(String filename) {
    image.save(filename);
  }

  /**
   * Controller calls on the Image class (model) to blur the Image.
   */
  public void blur() {
    image.blur();
    image.notifyObserver();
  }

  /**
   * Controller calls on the Image class (model) to sharpen the Image.
   */
  public void sharpen() {
    image.sharpen();
    image.notifyObserver();
  }

  /**
   * Controller calls on the Image class (model) make the image greyscale.
   */
  public void greyscale() {
    image.greyscale();
    image.notifyObserver();
  }

  /**
   * Controller calls on the Image class (model) make the image sepia.
   */
  public void sepia() {
    image.sepia();
    image.notifyObserver();
  }

  /**
   * Controller calls on the Image class (model) make the image a black/white dither.
   */
  public void dither() {
    image.dither();
    image.notifyObserver();
  }

  /**
   * Controller calls on the Image class (model) make the image a mosaic based on number of seeds.
   */
  public void mosaic(int seeds) {
    image.mosaic(seeds);
    image.notifyObserver();
  }

  /**
   * Controller calls on the Image class (model) generate a rainbow image based height and width.
   *
   * @param width width of the rainbow
   */
  public void rainbow(int width) {
    image.rainbow(width);
    image.notifyObserver();
  }

  /**
   * Controller calls on the Image class (model) generate a checkerboard image.
   * based checkerSquareArea.
   *
   * @param checkerSquareArea individual checker square area
   */
  public void checkerboard(int checkerSquareArea) {
    image.checkerboard(checkerSquareArea);
    image.notifyObserver();
  }
}