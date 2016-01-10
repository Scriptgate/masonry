package masonry;

import masonry.api.Brick;
import net.scriptgate.common.Point;

class BasicBrick implements Brick {
    private boolean isLayoutInstant = false;
    private Point size;
    private Point position = new Point(0, 0);

    public BasicBrick(int width, int height) {
        size = new Point(width, height);
    }

    @Override
    public void goTo(int x, int y) {
        this.position = new Point(x, y);
    }

    @Override
    public int getHeight() {
        return size.y;
    }

    @Override
    public int getWidth() {
        return size.x;
    }

    @Override
    public boolean isLayoutInstant() {
        return isLayoutInstant;
    }

    @Override
    public int getX() {
        return position.x;
    }

    @Override
    public int getY() {
        return position.y;
    }
}
