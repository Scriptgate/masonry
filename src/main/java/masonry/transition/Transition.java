package masonry.transition;

import masonry.Point;

public interface Transition {
    Point getLocationAt(double elapsedTime);

    int toX();

    int toY();

    double getPercentage();

    static Transition none(int x, int y) {
        return new Transition() {
            @Override
            public Point getLocationAt(double elapsedTime) {
                return new Point(x, y);
            }

            @Override
            public int toX() {
                return x;
            }

            @Override
            public int toY() {
                return y;
            }

            @Override
            public double getPercentage() {
                return 1;
            }
        };
    }

}
