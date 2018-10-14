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

    abstract Point getLocation()

    Point getLocationAt(double elapsedTime) {
        if (transition + elapsedTime > TRANSITION_SPEED) {completeTransition()}
        if (isCompleted()) {return arriveAt(toX(), toY())}

        transition += elapsedTime
        return getLocation()
    }

    @Override float getPercentage() { transition / TRANSITION_SPEED }

    @SuppressWarnings("GrMethodMayBeStatic")
    private Point arriveAt(int x, int y) { return new Point(x, y) }
    private void completeTransition() { isCompleted = true }

    @Override boolean isCompleted() { return isCompleted }
}
