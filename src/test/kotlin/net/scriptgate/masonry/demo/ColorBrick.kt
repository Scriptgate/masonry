package net.scriptgate.masonry.demo

import net.scriptgate.common.Color3f
import net.scriptgate.common.Color3f.BLACK
import net.scriptgate.engine.Renderer
import net.scriptgate.masonry.api.Brick
import net.scriptgate.masonry.transition.LinearTransition
import net.scriptgate.masonry.transition.TransitionComponent

class ColorBrick(override val width: Int, override val height: Int) : Brick {

    val color: Color3f = Color3f(1f, 0f, 0f)

    private val transitionComponent = TransitionComponent(LinearTransition.LINEAR)

    override val isLayoutInstant: Boolean
        get() = transitionComponent.isLayoutInstant

    override val x: Int
        get() = transitionComponent.x

    override val y: Int
        get() = transitionComponent.y


    override fun goTo(x: Int, y: Int) {
        transitionComponent.goTo(x, y)
    }

    override fun moveTo(x: Int, y: Int) {
        transitionComponent.moveTo(x, y)
    }

    fun update(elapsedTime: Double) {
        transitionComponent.update(elapsedTime)
    }

    fun render(renderer: Renderer) {
        val strokeWidth = 2

        renderer.setColor(BLACK)
        renderer.fillRect(x, y, width, height)
        renderer.setColor(color)
        renderer.fillRect(
                x + strokeWidth,
                y + strokeWidth,
                width - 2 * strokeWidth,
                height - 2 * strokeWidth)
    }
}
