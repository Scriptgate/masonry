package net.scriptgate.masonry.api

interface BrickContainer<T : Brick> {

    val bricks: Collection<T>

    val height: Int

    val width: Int

}
