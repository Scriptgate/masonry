package net.scriptgate.masonry.transition

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Transition

import java.util.function.BiFunction

class ArcedTransition(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int) : LinearTransition(xFrom, yFrom, xTo, yTo) {
    override fun getLocation(): Point {

        val offset = Math.sin(super.percentage * Math.PI) * ARC_HEIGHT

        //leans right when direction is down
        //leans left when direction is up
        //leans top when direction is left
        //leans bottom when direction is right
        val offsetDirection = direction - Math.PI / 2f

        var deltaX = (offset * Math.cos(offsetDirection)).toInt()
        var deltaY = (offset * Math.sin(offsetDirection)).toInt()

        val percentage = super.percentage * distance

        deltaX += (percentage * Math.cos(direction)).toInt()
        deltaY += (percentage * Math.sin(direction)).toInt()

        return Point(pointFrom.x + deltaX, pointFrom.y + deltaY)
    }


    constructor(from: Point, to: Point) : this(from.x, from.y, to.x, to.y)


    object ARCED : BiFunction<Point, Point, Transition> {
        override fun apply(from: Point, to: Point): Transition {
            return ArcedTransition(from.x, from.y, to.x, to.y)
        }
    }

    val ARC_HEIGHT = 10

}
