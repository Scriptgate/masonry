package net.scriptgate.masonry.transition;

import net.scriptgate.common.Point;
import net.scriptgate.masonry.api.Transition;

import java.util.function.BiFunction;

public class TransitionComponent {

    private int x = 0;
    private int y = 0;

    private boolean layoutInstant = false;
    private boolean initialLayoutDone = false;

    private Transition transition = Transition.none(0, 0);

    private BiFunction<Point, Point, Transition> TransitionSupplier;

    public TransitionComponent(BiFunction<Point, Point, Transition> TransitionSupplier) {
        this.TransitionSupplier = TransitionSupplier;
    }

    public void goTo(int x, int y) {
        if (isNullTransition(x, y)) {
            return;
        }
        this.x = x;
        this.y = y;
        transition = Transition.none(x, y);
        this.initialLayoutDone = true;
    }

    private boolean isNullTransition(int x, int y) {
        if (x == this.x && y == this.y) {
            return true;
        }
        if (x == transition.toX() && y == transition.toY()) {
            return true;
        }
        return false;
    }

    public void moveTo(int x, int y) {
        if (isNullTransition(x, y)) {
            return;
        }
        transition = TransitionSupplier.apply(new Point(this.x, this.y), new Point(x, y));
    }

    public boolean isLayoutInstant() {
        return layoutInstant || !initialLayoutDone;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getPercentage() {
        return transition.getPercentage();
    }

    public void update(double elapsedTime) {
        Point position = transition.getLocationAt(elapsedTime);
        if (this.x != position.x || this.y != position.y) {
            this.x = position.x;
            this.y = position.y;
        }
    }

    public void setLayoutInstant(boolean layoutInstant) {
        this.layoutInstant = layoutInstant;
    }

    public boolean isCompleted() {
        return transition.isCompleted();
    }
}
