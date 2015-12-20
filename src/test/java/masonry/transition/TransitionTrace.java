package masonry.transition;

import masonry.Point;
import net.scriptgate.common.Color4f;
import net.scriptgate.engine.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TransitionTrace {

    private Transition transition;
    private List<Point> trace;
    private Color4f color;

    private TransitionTrace(Transition transition, Color4f color) {
        this.transition = transition;
        this.trace = new ArrayList<>();
        this.color = color;
    }

    public boolean onTick(double elapsedTime) {
        if (!isCompleted()) {
            return trace.add(transition.getLocationAt(elapsedTime));
        }
        return false;
    }

    public void render(Renderer renderer, Consumer<Point> pointRenderer) {
        renderer.setColor(color.r, color.g, color.b);
        trace.forEach(pointRenderer);
    }

    public static TransitionTrace trace(Transition transition, Color4f color) {
        return new TransitionTrace(transition, color);
    }

    public boolean isCompleted() {
        return transition.isCompleted();
    }
}
