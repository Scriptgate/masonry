package net.scriptgate.masonry.demo

import net.scriptgate.common.Color3f
import net.scriptgate.engine.Renderer
import net.scriptgate.masonry.api.Brick
import net.scriptgate.masonry.transition.TransitionComponent

import static net.scriptgate.common.Color3f.BLACK
import static net.scriptgate.masonry.transition.linear.LinearTransition.LINEAR

class ColorBrick implements Brick {

    private int height
    private int width

    private Color3f color

    private TransitionComponent transitionComponent = new TransitionComponent(LINEAR)


    ColorBrick(int width, int height) {
        this.width = width
        this.height = height
        this.color = new Color3f(1, 0, 0)
    }

    @Override
    void goTo(int x, int y) {
        transitionComponent.goTo(x, y)
    }

    @Override
    int getHeight() {
        return height
    }

    @Override
    int getWidth() {
        return width
    }

    @Override
    boolean isLayoutInstant() {
        return transitionComponent.isLayoutInstant()
    }

    @Override
    int getX() {
        return transitionComponent.getX()
    }

    @Override
    int getY() {
        return transitionComponent.getY()
    }

    Color3f getColor() {
        return color
    }

    @Override
    void moveTo(int x, int y) {
        transitionComponent.moveTo(x, y)
    }

    void update(double elapsedTime) {
        transitionComponent.update(elapsedTime)
    }

    void render(Renderer renderer) {
        int strokeWidth = 2

        renderer.setColor(BLACK)
        renderer.fillRect(getX(), getY(), getWidth(), getHeight())
        renderer.setColor(getColor())
        renderer.fillRect(
                getX() + strokeWidth,
                getY() + strokeWidth,
                getWidth() - 2 * strokeWidth,
                getHeight() - 2 * strokeWidth)
    }
}
