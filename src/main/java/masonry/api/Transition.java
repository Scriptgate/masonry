package masonry.api;

import net.scriptgate.common.Point;

public interface Transition {

    int toX();

    int toY();

    float getPercentage();

    Point getLocationAt(double elapsedTime);

    boolean isCompleted();

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
            public float getPercentage() {
                return 1;
            }

            @Override
            public boolean isCompleted() {
                return true;
            }
        };
    }
}
