package net.scriptgate.masonry.transition

import net.scriptgate.common.Color3f
import net.scriptgate.common.Point
import net.scriptgate.engine.Renderer
import net.scriptgate.masonry.api.Transition
import java.util.*

class TransitionTrace private constructor(private val transition: Transition, private val color: Color3f) {
    private val trace: MutableList<Point>

    val isCompleted: Boolean
        get() = transition.isCompleted

    init {
        this.trace = ArrayList()
    }

    fun onTick(elapsedTime: Double): Boolean {
        return if (!isCompleted) {
            trace.add(transition.getLocationAt(elapsedTime))
        } else false
    }

    fun render(renderer: Renderer, pointRenderer: (Point) -> Unit) {
        renderer.setColor(color)
        trace.forEach(pointRenderer)
    }

    companion object {

        fun trace(transition: Transition, color: Color3f): TransitionTrace {
            return TransitionTrace(transition, color)
        }
    }
}
