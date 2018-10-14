package net.scriptgate.masonry.transition;

import net.scriptgate.common.Point;
import net.scriptgate.masonry.api.Transition;

import java.util.function.BiFunction;

public class LinearTransition extends TransitionBase {

    public static final BiFunction<Point, Point, Transition> LINEAR = (from, to) -> new LinearTransition(from.x, from.y, to.x, to.y);

    protected double distance = 0;

    protected final Point pointFrom;
    private final Point pointTo;

    protected double direction;

    public LinearTransition(Point from, Point to) {
        this(from.x, from.y, to.x, to.y);
    }

    public LinearTransition(int xFrom, int yFrom, int xTo, int yTo) {
        this.pointFrom = new Point(xFrom, yFrom);
        this.pointTo = new Point(xTo, yTo);

        int deltaX = pointTo.x - pointFrom.x;
        int deltaY = pointTo.y - pointFrom.y;

        this.direction = Math.atan2(deltaY, deltaX);

        this.distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public Point getLocation() {
        double percentage = getPercentage() * distance;
        int deltaX = (int) (percentage * Math.cos(direction));
        int deltaY = (int) (percentage * Math.sin(direction));

        return new Point(pointFrom.x + deltaX, pointFrom.y + deltaY);
    }

    @Override
    public int toX() {
        return pointTo.x;
    }

    @Override
    public int toY() {
        return pointTo.y;
    }
}
