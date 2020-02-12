/**
 * This class represents a 2D point on a graph with an x and y coordinate.
 */
public class Point2D {
  /**
   * X point of this 2D point.
   */
  private double x;
  /**
   * Y point of this 2D point.
   */
  private double y;

  /**
   * Constructor that initializes a Point2D.
   *
   * @param x x coordinate of this Point2D
   * @param y y coordinate of this Point2D
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x coordinate of this Point2D.
   *
   * @return the x coordinate of this Point2D
   */
  public double getX() {
    return x;
  }

  /**
   * Returns the y coordinate of this Point2D.
   *
   * @return the y coordinate of this Point2D
   */
  public double getY() {
    return y;
  }

  /**
   * Returns the distance between a Point2D and its center.
   *
   * @param other a Point2D.
   * @return the distance between a Point2D and its center.
   */
  public double calculateDistance(Point2D other) {
    double xDiff = this.getX() - other.getX();
    double yDiff = this.getY() - other.getY();
    return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
  }

  /**
   * Returns a string representation of a Point2D in the form of (x,y).
   *
   * @return a string representation of a Point2D in the form of (x,y).
   */
  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }
}
