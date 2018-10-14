package net.scriptgate.masonry.api

import net.scriptgate.common.Point

interface Transition {

    abstract int toX()

    abstract int toY()

    abstract float getPercentage()

    abstract Point getLocationAt(double elapsedTime)

    abstract boolean isCompleted()

}
