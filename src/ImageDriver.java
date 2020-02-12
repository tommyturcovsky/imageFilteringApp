import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class which drives our calculator program.
 */
public class ImageDriver {
  /**
   * The starting point of our program.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    try {
      Scanner scanner = new Scanner(new File(args[0]));

      // Initialize controller, model and their relationship.
      ImageInterface model = new Image();
      ImageController controller;
      View view = new View();
      view.setVisible(false);
      controller = new ImageController(model, view);

      while (scanner.hasNext()) {
        // Has to start with load
        String command = scanner.next();

        while (scanner.hasNext()) {
          if (command.equals("load")) {
            command = scanner.next();
            controller.loadImage(command);
            command = scanner.next();
          } else {
            while (!command.equals("load") && scanner.hasNext()) {
              switch (command) {
                case "save":
                  command = scanner.next(); // Will be the filename
                  controller.save(command);
                  break;
                case "blur":
                  controller.blur();
                  break;
                case "sharpen":
                  controller.sharpen();
                  break;
                case "greyscale":
                  controller.greyscale();
                  break;
                case "sepia":
                  controller.sepia();
                  break;
                case "dither":
                  controller.dither();
                  break;
                case "mosaic":
                  command = scanner.next();
                  int seeds = Integer.parseInt(command);
                  controller.mosaic(seeds);
                  break;
                case "rainbow":
                  command = scanner.next();
                  int width = Integer.parseInt(command);
                  controller.rainbow(width);
                  break;
                case "checkerboard":
                  command = scanner.next();
                  int checkerSquareArea = Integer.parseInt(command);
                  controller.checkerboard(checkerSquareArea);
                  break;

                default:
                  throw new IOException("Command not supported");
              }
              if (scanner.hasNext()) {
                command = scanner.next();
              }
            }
          }
        }
      }
    } catch (IOException ex) {
      System.out.println("Input Output Exception Occurred");
    }
  }
}
