package masonry.transition;

import net.scriptgate.common.Color3f;
import net.scriptgate.common.Point;
import net.scriptgate.engine.Renderer;
import net.scriptgate.masonry.api.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TransitionTrace {

    private Transition transition;
    private List<Point> trace;
    private Color3f color;

    private TransitionTrace(Transition transition, Color3f color) {
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
        renderer.setColor(color);
        trace.forEach(pointRenderer);
    }

    public static TransitionTrace trace(Transition transition, Color3f color) {
        return new TransitionTrace(transition, color);
    }

    public boolean isCompleted() {
        return transition.isCompleted();
    }
}
