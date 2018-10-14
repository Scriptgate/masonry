package net.scriptgate.masonry.api

import net.scriptgate.common.Point

interface Transition {

    val percentage: Float

    val isCompleted: Boolean

    fun toX(): Int

    fun toY(): Int

    fun getLocationAt(elapsedTime: Double): Point

    companion object {

        fun none(x: Int, y: Int): Transition {
            return object : Transition {

                override val percentage: Float
                    get() = 1f

                override val isCompleted: Boolean
                    get() = true

                override fun getLocationAt(elapsedTime: Double): Point {
                    return Point(x, y)
                }

                override fun toX(): Int {
                    return x
                }

                override fun toY(): Int {
                    return y
                }
            }
        }
    }
}
