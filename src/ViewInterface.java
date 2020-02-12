import java.awt.image.BufferedImage;

/**
 * Interface containing all the things the view can do.
 */
public interface ViewInterface {

  /**
   * Set commands that certain buttons can perform with the features of the MVC model.
   *
   * @param features features that this application can do with an Image
   */
  void setCommands(Features features);

  /**
   * Update the view with a Buffered Image to be shown to user.
   *
   * @param image BufferedImage to be used to show the user
   */
  void updateView(BufferedImage image);

}
