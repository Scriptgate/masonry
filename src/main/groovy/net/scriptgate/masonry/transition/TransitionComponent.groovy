package net.scriptgate.masonry.transition

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Brick
import net.scriptgate.masonry.api.Transition
import sun.reflect.generics.reflectiveObjects.NotImplementedException

import java.util.function.BiFunction

import static net.scriptgate.masonry.transition.TransitionBase.stayAt

class TransitionComponent implements Brick {

    private static boolean isZeroTransition(TransitionComponent component, int x, int y) {
        if (component.isAt(x, y)) {return true}
        if (component.transition.isAtEnd(x, y)) {return true}
        return false
    }

    private int x = 0
    private int y = 0

    private layout = [
            instant : false,
            initialDone: false
    ]

    private Transition transition = stayAt(0, 0)
    private BiFunction<Point, Point, Transition> TransitionSupplier

    TransitionComponent(BiFunction<Point, Point, Transition> TransitionSupplier) {
        this.TransitionSupplier = TransitionSupplier
    }

    @Override
    void goTo(int x, int y) {
        if (isZeroTransition(this, x, y)) {
            return
        }
        this.x = x
        this.y = y
        transition = stayAt(x, y)
        this.layout.initialDone = true
    }

    private boolean shouldMove(int x, int y) { !isZeroTransition(this, x, y) }

    private boolean isAt(int x, int y) { x == this.x && y == this.y }

    @Override
    void moveTo(int x, int y) {
        if (shouldMove(x, y)) {
            transition = TransitionSupplier.apply(new Point(this.x, this.y), new Point(x, y))
        }
    }

    @Override boolean isLayoutInstant() { return layout.instant || !layout.initialDone }

    @Override int getX() { return x }
    @Override int getY() { return y }

    double getPercentage() { return transition.getPercentage() }

    void update(double elapsedTime) {
        Point position = transition.getLocationAt(elapsedTime)
        if (this.x != position.x || this.y != position.y) {
            this.x = position.x
            this.y = position.y
        }
    }

    void setLayoutInstant(boolean layoutInstant) { this.layout.instant = layoutInstant }
    boolean isCompleted() { return transition.isCompleted() }
    int getDestinationX() { return transition.toX() }
    int getDestinationY() { return transition.toY() }

    @Override
    int getWidth() {
        //TODO:

        throw new NotImplementedException()
    }

    @Override
    int getHeight() {
        //TODO:
        throw new NotImplementedException()
    }

}
