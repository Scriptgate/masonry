package net.scriptgate.masonry.transition

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Transition

abstract class TransitionBase : Transition {
    private var transition = 0f

    private var transitionDone : Boolean = false

    override var isCompleted: Boolean
        get() = transitionDone
        set(value) {transitionDone = value}


    abstract val location: Point

    override val percentage: Float
        get() = transition / TRANSITION_SPEED

    override fun getLocationAt(elapsedTime: Double): Point {
        if (isCompleted) {
            return Point(toX(), toY())
        }

        if (transition + elapsedTime > TRANSITION_SPEED) {
            isCompleted = true
            return Point(toX(), toY())
        } else {
            transition += elapsedTime.toFloat()
            return location
        }
    }

    companion object {

        var TRANSITION_SPEED = 1000f
    }
}
