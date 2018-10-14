package net.scriptgate.masonry.transition

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Transition

import java.util.function.BiFunction

open class LinearTransition(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int) : TransitionBase() {

    protected var distance = 0.0

    private val pointTo: Point = Point(xTo, yTo)
    protected val pointFrom: Point = Point(xFrom, yFrom)

    protected var direction: Double = 0.toDouble()

    constructor(from: Point, to: Point) : this(from.x, from.y, to.x, to.y) {}

    init {

        val deltaX = pointTo.x - pointFrom.x
        val deltaY = pointTo.y - pointFrom.y

        this.direction = Math.atan2(deltaY.toDouble(), deltaX.toDouble())

        this.distance = Math.sqrt((deltaX * deltaX + deltaY * deltaY).toDouble())
    }

    override var location: Point = getLocation()

    internal open fun getLocation(): Point {
        val percentage = percentage * distance
        val deltaX = (percentage * Math.cos(direction)).toInt()
        val deltaY = (percentage * Math.sin(direction)).toInt()

       return Point(deltaX + pointFrom?.x, deltaY  + pointFrom?.y)
    }

    override fun toX(): Int {
        return pointTo.x
    }

    override fun toY(): Int {
        return pointTo.y
    }

    object LINEAR : BiFunction<Point, Point, Transition> {
        override fun apply(from: Point, to: Point): Transition {
            return LinearTransition(from.x, from.y, to.x, to.y)
        }
    }

}
