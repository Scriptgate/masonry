package net.scriptgate.masonry.transition

import net.scriptgate.common.Color3f
import net.scriptgate.common.Color3f.RED
import net.scriptgate.common.Point
import net.scriptgate.engine.Application
import net.scriptgate.engine.Engine
import net.scriptgate.engine.InputComponent
import net.scriptgate.engine.Renderer
import net.scriptgate.masonry.Launcher.launch
import java.util.*

class TransitionsTest : Application {

    private var pointFrom: Point? = null
    private var pointTo: Point? = null
    private var traces: MutableList<TransitionTrace>? = null


    override fun initialize() {
        Engine.WIDTH = 256
        Engine.HEIGHT = 256

        pointFrom = Point(16, 16)
        pointTo = Point(224, 224)
        traces = ArrayList()
        traces!!.add(TransitionTrace.trace(ArcedTransition(pointFrom!!, pointTo!!), Color3f(0f, 0f, 1f)))
        traces!!.add(TransitionTrace.trace(LinearTransition(pointFrom!!, pointTo!!), Color3f(0f, 1f, 0f)))
    }

    override fun render(renderer: Renderer) {

        traces!!.forEach { trace -> trace.render(renderer) {
            point:Point -> ({ p: Point -> renderer.fillCircle(p.x, p.y, 2)})(point)
        } }

        renderer!!.setColor(RED)
        renderer.fillCircle(pointFrom!!.x, pointFrom!!.y, 2)
        renderer.fillCircle(pointTo!!.x, pointTo!!.y, 2)
    }

    override fun onTick(inputComponent: InputComponent?, elapsedTime: Double) {
        traces!!.forEach { trace -> trace.onTick(elapsedTime) }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(TransitionsTest())
        }
    }
}
