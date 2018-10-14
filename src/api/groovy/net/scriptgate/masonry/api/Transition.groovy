package net.scriptgate.masonry.api

import net.scriptgate.common.Point

trait Transition {

    abstract int toX()
    abstract int toY()

    abstract Point getLocationAt(double elapsedTime)

    abstract float getPercentage()

    abstract boolean isCompleted()

    boolean isAtEnd(int x, int y) { x == toX() && y == toY() }
}
