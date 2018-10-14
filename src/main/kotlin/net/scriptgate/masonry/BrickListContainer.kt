package net.scriptgate.masonry

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Brick
import net.scriptgate.masonry.api.BrickContainer
import java.util.*

class BrickListContainer<T : Brick>(width: Int, height: Int) : BrickContainer<T> {

    private val size: Point = Point(width, height)
    private val items = ArrayList<T>()

    override val bricks: Collection<T>
        get() = items

    override val height: Int
        get() = size.y

    override val width: Int
        get() = size.x

    fun addBrick(brick: T) {
        this.items.add(brick)
    }

    fun removeBrick(brick: T) {
        this.items.remove(brick)
    }
}
