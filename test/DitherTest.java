import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests to prove Dither pixels are either black or white.
 */
public class DitherTest {

  @Test
  public void proveDitherWorks() {
    Image inputImage = new Image("bird.jpg");
    inputImage.dither();

    for (int row = 0; row < inputImage.getHeight(); row++) {
      for (int col = 0; col < inputImage.getWidth(); col++) {
        for (int color = 0; color < 3; color++) {
          int [][][] colorArray = inputImage.getRgb();
          int currentColor = colorArray[row][col][color];
          if (currentColor != 0) {
            assertEquals(255, currentColor);
          }
        }
      }
    }
  }
}