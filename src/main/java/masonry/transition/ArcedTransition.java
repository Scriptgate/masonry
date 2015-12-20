package masonry.transition;

import masonry.Point;

public class ArcedTransition extends LinearTransition {

    public ArcedTransition(Point from, Point to) {
        this(from.x, from.y, to.x, to.y);
    }

    public ArcedTransition(int xFrom, int yFrom, int xTo, int yTo) {
        super(xFrom, yFrom, xTo, yTo);
    }

    @Override
    public Point getLocation() {
        double offset = Math.sin(getPercentage() * Math.PI) * 50;

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
