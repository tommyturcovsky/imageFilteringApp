/**
 * Subject Interface for the Image to follow.
 */
public interface Subject {
  /**
   * Notifies observers there has been a change to the subject.
   */
  void notifyObserver();

  /**
   * Add observer.
   *
   * @param observer will be controller observer
   */
  void addObserver(ControllerObserver observer);
}
