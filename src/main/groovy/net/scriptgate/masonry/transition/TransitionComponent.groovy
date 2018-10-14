package net.scriptgate.masonry.transition

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Transition

import java.util.function.BiFunction

import static net.scriptgate.masonry.transition.TransitionBase.stayAt

class TransitionComponent {

    private int x = 0
    private int y = 0

    private boolean layoutInstant = false
    private boolean initialLayoutDone = false

    private Transition transition = stayAt(0, 0)

    private BiFunction<Point, Point, Transition> TransitionSupplier

    TransitionComponent(BiFunction<Point, Point, Transition> TransitionSupplier) {
        this.TransitionSupplier = TransitionSupplier
    }

    void goTo(int x, int y) {
        if (isNullTransition(x, y)) {
            return
        }
        this.x = x
        this.y = y
        transition = stayAt(x, y)
        this.initialLayoutDone = true
    }

    private boolean isNullTransition(int x, int y) {
        if (x == this.x && y == this.y) {
            return true
        }
        if (x == transition.toX() && y == transition.toY()) {
            return true
        }
        return false
    }

    void moveTo(int x, int y) {
        if (isNullTransition(x, y)) {
            return
        }
        transition = TransitionSupplier.apply(new Point(this.x, this.y), new Point(x, y))
    }

    boolean isLayoutInstant() {
        return layoutInstant || !initialLayoutDone
    }

    int getX() {
        return x
    }

    int getY() {
        return y
    }

    double getPercentage() {
        return transition.getPercentage()
    }

    void update(double elapsedTime) {
        Point position = transition.getLocationAt(elapsedTime)
        if (this.x != position.x || this.y != position.y) {
            this.x = position.x
            this.y = position.y
        }
    }

    void setLayoutInstant(boolean layoutInstant) {
        this.layoutInstant = layoutInstant
    }

    boolean isCompleted() {
        return transition.isCompleted()
    }

    int getDestinationX() {
        return transition.toX()
    }

    int getDestinationY() {
        return transition.toY()
    }
}
