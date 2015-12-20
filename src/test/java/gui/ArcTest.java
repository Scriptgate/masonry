package gui;

import masonry.Point;
import masonry.transition.ArcedTransition;
import masonry.transition.LinearTransition;
import masonry.transition.Transition;
import net.scriptgate.engine.Application;
import net.scriptgate.engine.InputComponent;
import net.scriptgate.engine.Renderer;

import java.util.ArrayList;
import java.util.List;

import static net.scriptgate.engine.ApplicationHandlerBuilder.run;
import static net.scriptgate.engine.ApplicationType.OPENGL;

public class ArcTest implements Application {

    public static void main(String[] args) {
        run(new ArcTest()).in(OPENGL);
    }

    private TransitionTrace arc;
    private TransitionTrace linear;

    private Point pointFrom, pointTo;

    @Override
    public void initialize() {
        pointFrom = new Point(300,300);
        pointTo = new Point(500,500);
        arc = new TransitionTrace(new ArcedTransition(pointFrom.x, pointFrom.y, pointTo.x, pointTo.y));
        linear = new TransitionTrace(new LinearTransition(pointFrom.x, pointFrom.y, pointTo.x, pointTo.y));
    }

    @Override
    public void render(Renderer renderer) {
        renderer.setColor(0, 0, 1);
        arc.render(renderer);

        renderer.setColor(0, 1, 0);
        linear.render(renderer);

        renderer.setColor(1, 0, 0);
        renderer.fillCircle(pointFrom.x, pointFrom.y, 2);
        renderer.fillCircle(pointTo.x, pointTo.y, 2);
    }

    @Override
    public void onTick(InputComponent inputComponent, double elapsedTime) {
        arc.onTick(elapsedTime);
        linear.onTick(elapsedTime);
    }

    private static class TransitionTrace {

        private Transition transition;
        private List<Point> trace;

        private TransitionTrace(Transition transition) {
            this.transition = transition;
            this.trace = new ArrayList<>();
        }

        private void onTick(double elapsedTime) {
            trace.add(transition.getLocationAt(elapsedTime));
        }

        public void render(Renderer renderer) {
            trace.forEach(point -> renderer.fillCircle(point.x, point.y, 2));
        }
    }
}
