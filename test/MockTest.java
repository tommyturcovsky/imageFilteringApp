import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Mock Test to make sure the MVC Design pattern works.
 */
public class MockTest {
  /**
   * Controller.
   */
  private Features controller;
  /**
   * Tells what the model performed.
   */
  private StringBuilder modelLog;
  /**
   * Tells what the view has performed.
   */
  private StringBuilder viewLog;

  /**
   * Set up variables for tests.
   */
  @Before
  public void setUp() {

    viewLog = new StringBuilder();

    ViewInterface mockView = new MockView(viewLog);

    modelLog = new StringBuilder();

    ImageInterface mockModel = new MockModel(modelLog);

    controller = new ImageController(mockModel, mockView);
  }

  /**
   * Tests load method.
   */
  @Test
  public void testLoad() {
    controller.loadImage("dog.jpg");
    assertEquals("addObserver Called\nModel Load: dog.jpg", modelLog.toString());

  }

  /**
   * Test save method.
   */
  @Test
  public void testSave() {
    controller.save("dog.jpg");
    assertEquals("addObserver Called\nModel Save: dog.jpg", modelLog.toString());
  }

  /**
   * Test blur method.
   */
  @Test
  public void testBlur() {
    controller.blur();
    assertEquals("addObserver Called\nModel Blur Called", modelLog.toString());
  }

  /**
   * Test sharpen method.
   */
  @Test
  public void testSharpen() {
    controller.sharpen();
    assertEquals("addObserver Called\nModel Sharpen Called", modelLog.toString());
  }

  /**
   * Test greyscale method.
   */
  @Test
  public void testGreyscale() {
    controller.greyscale();
    assertEquals("addObserver Called\nModel GreyScale Called", modelLog.toString());
  }

  /**
   * Test mosaic method.
   */
  @Test
  public void testMosaic() {
    controller.mosaic(10000);
    assertEquals("addObserver Called\nModel Mosaic Called Seeds: 10000", modelLog.toString());
  }

  /**
   * Test dither method.
   */
  @Test
  public void testDither() {
    controller.dither();
    assertEquals("addObserver Called\nModel Dither Called", modelLog.toString());
  }

  /**
   * Test rainbow method.
   */
  @Test
  public void testRainbow() {
    controller.rainbow(100);
    assertEquals("addObserver Called\nModel Rainbow Called Width: 100", modelLog.toString());
  }

  /**
   * Test checkerboard method.
   */
  @Test
  public void testCheckerboard() {
    controller.checkerboard(100);
    assertEquals("addObserver Called\nModel Checkerboard Called Area: 100", modelLog.toString());
  }

  /**
   * Test update method.
   */
  @Test
  public void testUpdate() {
    controller.update();
    assertEquals("addObserver Called\nModel Got Image", modelLog.toString());
    assertEquals("Set Commands Called: ImageController@15327b79UpdateView Callednull",
            viewLog.toString());
  }

}
