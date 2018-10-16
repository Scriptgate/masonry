package net.scriptgate.masonry.transition

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Transition

import java.util.function.BiFunction

class TransitionComponent(private val TransitionSupplier: BiFunction<Point, Point, Transition>) {

    var x = 0
        private set
    var y = 0
        private set

    var isLayoutInstant = true
        get() = field || !initialLayoutDone
    private var initialLayoutDone = false

    private var transition = Transition.none(0, 0)

    val percentage: Double
        get() = transition.percentage.toDouble()

    val isCompleted: Boolean
        get() = transition.isCompleted

    val destinationX: Int
        get() = transition.toX()

    val destinationY: Int
        get() = transition.toY()

    fun goTo(x: Int, y: Int) {
        if (isZeroTransition(x, y)) {
            return
        }
        this.x = x
        this.y = y
        transition = Transition.none(x, y)
        this.initialLayoutDone = true
    }

    private fun isZeroTransition(x: Int, y: Int): Boolean {
        if (x == this.x && y == this.y) {
            return true
        }
        return x == transition.toX() && y == transition.toY()
    }

    fun moveTo(x: Int, y: Int) {
        if (!isZeroTransition(x, y)) {
            transition = TransitionSupplier.apply(Point(this.x, this.y), Point(x, y))
        }
    }

    fun update(elapsedTime: Double) {
        val position = transition.getLocationAt(elapsedTime)
        if (this.x != position.x || this.y != position.y) {
        println("moving from: ${this.x} to ${position.x}")
            this.x = position.x
            this.y = position.y
        }
    }
}
