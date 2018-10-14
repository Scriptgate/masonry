package net.scriptgate.masonry.transition.linear

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Transition
import net.scriptgate.masonry.transition.TransitionBase

class LinearTransition extends TransitionBase {

    public static final Closure<Transition> LINEAR = {Point from, Point to  -> new LinearTransition(from, to) }

    protected double distance = 0

    protected final Point pointFrom
    private final Point pointTo

    protected double direction

    private LinearTransition(Point from, Point to) {
        this(from.x, from.y, to.x, to.y)
    }

    protected LinearTransition(int xFrom, int yFrom, int xTo, int yTo) {
        this.pointFrom = new Point(xFrom, yFrom)
        this.pointTo = new Point(xTo, yTo)

        int deltaX = pointTo.x - pointFrom.x
        int deltaY = pointTo.y - pointFrom.y

        this.direction = Math.atan2(deltaY, deltaX)

        this.distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY)
    }

    @Override
    Point getLocation() {
        double percentage = getPercentage() * distance
        int deltaX = (int) (percentage * Math.cos(direction))
        int deltaY = (int) (percentage * Math.sin(direction))

        return new Point(pointFrom.x + deltaX, pointFrom.y + deltaY)
    }

    @Override
    int toX() {
        return pointTo.x
    }

    @Override
    int toY() {
        return pointTo.y
    }
}
