import java.awt.image.BufferedImage;

/**
 * Mock Model class to be used for tests.
 */
public class MockModel implements ImageInterface {
  /**
   * Object that will keep track of all that happens inside the model.
   */
  private StringBuilder log;


  /**
   * Constructor for the mock model that takes information from the test so that we can test whether
   * the controller interacted with the model in the expected way.
   */
  public MockModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * Overrides save to print out helpful method.
   *
   * @param filename name of file to save this image to.
   */
  @Override
  public void save(String filename) {
    log.append("Model Save: " + filename);
  }

  /**
   * Overrides loadImage to print out helpful method.
   *
   * @param filename filename you are trying to load from
   */
  @Override
  public void loadImage(String filename) {
    log.append("Model Load: " + filename);
  }

  /**
   * Overrides getMethod to print out helpful method.
   */
  @Override
  public BufferedImage getImage() {
    log.append("Model Got Image");
    return null;
  }

  /**
   * Overrides blur to print out helpful method.
   */
  @Override
  public void blur() {
    log.append("Model Blur Called");
  }

  /**
   * Overrides sharpen to print out helpful method.
   */
  @Override
  public void sharpen() {
    log.append("Model Sharpen Called");
  }

  /**
   * Overrides greyscale to print out helpful method.
   */
  @Override
  public void greyscale() {
    log.append("Model GreyScale Called");
  }

  /**
   * Overrides sepia to print out helpful method.
   */
  @Override
  public void sepia() {
    log.append("Model Sepia Called");
  }

  /**
   * Overrides dither to print out helpful method.
   */
  @Override
  public void dither() {
    log.append("Model Dither Called");
  }

  /**
   * Overrides mosaic to print out helpful method.
   *
   * @param seed the number of seeds that share the same color
   */
  @Override
  public void mosaic(int seed) {
    log.append("Model Mosaic Called Seeds: " + seed);
  }

  /**
   * Overrides rainbow to print out helpful method.
   *
   * @param width width of the rainbow image
   */
  @Override
  public void rainbow(int width) {
    log.append("Model Rainbow Called Width: " + width);
  }

  /**
   * Overrides checkerboard to print out helpful method.
   *
   * @param checkerSquareArea square area of the individual squares in the checkerboard
   */
  @Override
  public void checkerboard(int checkerSquareArea) {
    log.append("Model Checkerboard Called Area: " + checkerSquareArea);
  }

  /**
   * Overrides notifyObserver to print out helpful method.
   */
  @Override
  public void notifyObserver() {
    log.append("notifyObserver Called");
  }

  /**
   * Overrides addObserver to print out helpful method.
   *
   * @param observer will be controller observer
   */
  @Override
  public void addObserver(ControllerObserver observer) {
    log.append("addObserver Called\n");
  }
}
