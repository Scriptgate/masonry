package net.scriptgate.masonry.transition.linear.arc

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Transition
import net.scriptgate.masonry.transition.linear.LinearTransition

import static java.lang.Math.cos
import static java.lang.Math.sin

class ArcedTransition extends LinearTransition {

    public static final Closure<Transition> ARCED = { Point from, Point to -> new ArcedTransition(from, to) }

    public static final int ARC_HEIGHT = 10

    private ArcedTransition(Point from, Point to) {
        super(from.x, from.y, to.x, to.y)
    }

    @Override
    Point getLocation() {
        double offset = sin(getPercentage() * Math.PI) * ARC_HEIGHT

        //leans right when direction is down
        //leans left when direction is up
        //leans top when direction is left
        //leans bottom when direction is right
        double offsetDirection = direction - (Math.PI / 2f)

        int deltaX = (int) (offset * cos(offsetDirection))
        int deltaY = (int) (offset * sin(offsetDirection))

        double percentage = getPercentage() * distance

        deltaX += (int) (percentage * cos(direction))
        deltaY += (int) (percentage * sin(direction))

        return new Point(pointFrom.x + deltaX, pointFrom.y + deltaY)
    }
}
