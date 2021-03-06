package net.scriptgate.masonry.transition;

import net.scriptgate.common.Point;
import net.scriptgate.masonry.api.Transition;

import java.util.function.BiFunction;

public class ArcedTransition extends LinearTransition {

    public static final BiFunction<Point, Point, Transition> ARCED = (from, to) -> new ArcedTransition(from.x, from.y, to.x, to.y);

    public static final int ARC_HEIGHT = 10;

    public ArcedTransition(Point from, Point to) {
        this(from.x, from.y, to.x, to.y);
    }

    public ArcedTransition(int xFrom, int yFrom, int xTo, int yTo) {
        super(xFrom, yFrom, xTo, yTo);
    }

    @Override
    public Point getLocation() {
        double offset = Math.sin(getPercentage() * Math.PI) * ARC_HEIGHT;

        //leans right when direction is down
        //leans left when direction is up
        //leans top when direction is left
        //leans bottom when direction is right
        double offsetDirection = direction - (Math.PI / 2f);

        int deltaX = (int) (offset * Math.cos(offsetDirection));
        int deltaY = (int) (offset * Math.sin(offsetDirection));

        double percentage = getPercentage() * distance;

        deltaX += (int) (percentage * Math.cos(direction));
        deltaY += (int) (percentage * Math.sin(direction));

        return new Point(pointFrom.x + deltaX, pointFrom.y + deltaY);
    }
}
