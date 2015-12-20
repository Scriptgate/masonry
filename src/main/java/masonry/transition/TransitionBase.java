package masonry.transition;

import masonry.Point;

public abstract class TransitionBase implements Transition {
    protected static double TRANSITION_SPEED = 1000;
    private double transition = 0;

    public Point getLocationAt(double elapsedTime) {
        if (transition + elapsedTime > TRANSITION_SPEED) {
            return new Point(toX(), toY());
        } else {
            transition += elapsedTime;
            return getLocation();
        }
    }

    protected abstract Point getLocation();

    @Override
    public double getPercentage() {
        return transition / TRANSITION_SPEED;
    }
}
