package masonry.transition;

import masonry.Point;

public abstract class TransitionBase implements Transition {

    protected static double TRANSITION_SPEED = 1000;
    private double transition = 0;
    private boolean isCompleted = false;

    public Point getLocationAt(double elapsedTime) {
        if (isCompleted) {
            return new Point(toX(), toY());
        }

        if (transition + elapsedTime > TRANSITION_SPEED) {
            isCompleted = true;
            return new Point(toX(), toY());
        } else {
            transition += elapsedTime;
            return getLocation();
        }
    }

    public abstract Point getLocation();

    @Override
    public double getPercentage() {
        return transition / TRANSITION_SPEED;
    }

    @Override
    public boolean isCompleted() {
        return isCompleted;
    }
}
