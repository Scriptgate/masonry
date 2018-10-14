package net.scriptgate.masonry.api

import net.scriptgate.common.Point

interface Transition {

    abstract int toX()
    abstract int toY()

    abstract Point getLocationAt(double elapsedTime)

    abstract float getPercentage()

    abstract boolean isCompleted()

}
