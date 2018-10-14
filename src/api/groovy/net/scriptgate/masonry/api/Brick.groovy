package net.scriptgate.masonry.api

trait Brick {

    abstract int getX()
    abstract int getY()

    abstract void goTo(int x, int y)
    void moveTo(int x, int y) { goTo(x, y) }

    abstract int getWidth()
    abstract int getHeight()

    abstract boolean isLayoutInstant()
}
