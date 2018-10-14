package net.scriptgate.masonry

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Brick

internal class BasicBrick(width: Int, height: Int) : Brick {
    override val isLayoutInstant = false
    private val size: Point
    private var position = Point(0, 0)

    override val height: Int
        get() = size.y

    override val width: Int
        get() = size.x

    override val x: Int
        get() = position.x

    override val y: Int
        get() = position.y

    init {
        size = Point(width, height)
    }

    override fun goTo(x: Int, y: Int) {
        this.position = Point(x, y)
    }
}
