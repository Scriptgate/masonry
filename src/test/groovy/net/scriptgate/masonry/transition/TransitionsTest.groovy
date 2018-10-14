package net.scriptgate.masonry.transition

import net.scriptgate.common.Color3f
import net.scriptgate.common.Point
import net.scriptgate.engine.Application
import net.scriptgate.engine.Engine
import net.scriptgate.engine.InputComponent
import net.scriptgate.engine.Renderer

import static net.scriptgate.common.Color3f.RED
import static net.scriptgate.masonry.Launcher.launch
import static net.scriptgate.masonry.transition.TransitionTrace.trace

class TransitionsTest implements Application {

    static void main(String[] args) {
        launch(new TransitionsTest())
    }

    private Point pointFrom
    private Point pointTo
    private List<TransitionTrace> traces


    @Override
    void initialize() {
        Engine.WIDTH = 256
        Engine.HEIGHT = 256

        pointFrom = new Point(16, 16)
        pointTo = new Point(224, 224)
        traces = new ArrayList<>()
        traces.add(trace(new ArcedTransition(pointFrom, pointTo), new Color3f(0, 0, 1)))
        traces.add(trace(new LinearTransition(pointFrom, pointTo), new Color3f(0, 1, 0)))
    }

    @Override
    void render(Renderer renderer) {
        traces.forEach { trace ->
            trace.render(renderer, {point -> renderer.fillCircle(point.x, point.y, 2)})
        }

        renderer.setColor(RED)
        renderer.fillCircle(pointFrom.x, pointFrom.y, 2)
        renderer.fillCircle(pointTo.x, pointTo.y, 2)
    }

    @Override
    void onTick(InputComponent inputComponent, double elapsedTime) {
        traces.forEach{trace -> trace.onTick(elapsedTime)}
    }
}
