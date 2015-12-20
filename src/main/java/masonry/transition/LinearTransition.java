package masonry.transition;

import masonry.Point;

public class LinearTransition extends TransitionBase {

    private double distance = 0;

    private final Point pointFrom;
    private final Point pointTo;

    private double direction;

    public LinearTransition(int xFrom, int yFrom, int xTo, int yTo) {
        this.pointFrom = new Point(xFrom, yFrom);
        this.pointTo = new Point(xTo, yTo);

        int deltaX = pointTo.x - pointFrom.x;
        int deltaY = pointTo.y - pointFrom.y;

        this.direction = Math.atan2(deltaY, deltaX);

        this.distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    protected Point getLocation() {
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
