package net.scriptgate.masonry.transition

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Transition

abstract class TransitionBase implements Transition {

    public static float TRANSITION_SPEED = 1000

    static Transition stayAt(int x, int y) {
        final def location = new Point(x, y)
        return [
                getLocationAt: { double elapsedTime -> location },
                toX          : { x },
                toY          : { y },
                getPercentage: { 1 },
                isCompleted  : { true }
        ] as Transition
    }

    private float transition = 0
    private boolean isCompleted = false

    Point getLocationAt(double elapsedTime) {
        if (isCompleted()) {
            return new Point(toX(), toY())
        }

        if (transition + elapsedTime > TRANSITION_SPEED) {
            isCompleted = true
            return new Point(toX(), toY())
        } else {
            transition += elapsedTime
            return getLocation()
        }
    }

    abstract Point getLocation()

    @Override
    float getPercentage() {
        return transition / TRANSITION_SPEED
    }

    @Override
    boolean isCompleted() {
        return isCompleted
    }
}
