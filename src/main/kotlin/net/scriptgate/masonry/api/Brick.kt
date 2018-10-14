package net.scriptgate.masonry.api

interface Brick {

    val height: Int

    val width: Int

    val isLayoutInstant: Boolean

    val x: Int

    val y: Int

    fun goTo(x: Int, y: Int)

    open fun moveTo(x: Int, y: Int) {
        goTo(x, y)
    }
}
