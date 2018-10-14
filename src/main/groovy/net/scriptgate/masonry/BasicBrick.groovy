package net.scriptgate.masonry

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Brick

class BasicBrick implements Brick {
    private boolean isLayoutInstant = false
    private Point size
    private Point position = new Point(0, 0)

    BasicBrick(int width, int height) {
        size = new Point(width, height)
    }

    @Override
    void goTo(int x, int y) {
        this.position = new Point(x, y)
    }

    @Override
    int getHeight() {
        return size.y
    }

    @Override
    int getWidth() {
        return size.x
    }

    @Override
    boolean isLayoutInstant() {
        return isLayoutInstant
    }

    @Override
    int getX() {
        return position.x
    }

    @Override
    int getY() {
        return position.y
    }
}
