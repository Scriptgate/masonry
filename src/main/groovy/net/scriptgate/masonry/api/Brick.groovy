package net.scriptgate.masonry.api

trait Brick {

    abstract void goTo(int x, int y)

    void moveTo(int x, int y) {
        goTo(x, y)
    }

    abstract int getHeight()

    abstract int getWidth()

    abstract boolean isLayoutInstant()

    abstract int getX()

    abstract int getY()
}
