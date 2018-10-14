package net.scriptgate.masonry.demo;

import net.scriptgate.common.Color3f;
import net.scriptgate.engine.Renderer;
import net.scriptgate.masonry.api.Brick;
import net.scriptgate.masonry.transition.TransitionComponent;

import static net.scriptgate.common.Color3f.BLACK;
import static net.scriptgate.masonry.transition.LinearTransition.LINEAR;

public class ColorBrick implements Brick {

    private int height;
    private int width;

    private Color3f color;

    private TransitionComponent transitionComponent = new TransitionComponent(LINEAR);


    public ColorBrick(int width, int height) {
        this.width = width;
        this.height = height;
        this.color = new Color3f(1, 0, 0);
    }

    @Override
    public void goTo(int x, int y) {
        transitionComponent.goTo(x, y);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public boolean isLayoutInstant() {
        return transitionComponent.isLayoutInstant();
    }

    @Override
    public int getX() {
        return transitionComponent.getX();
    }

    @Override
    public int getY() {
        return transitionComponent.getY();
    }

    public Color3f getColor() {
        return color;
    }

    @Override
    public void moveTo(int x, int y) {
        transitionComponent.moveTo(x, y);
    }

    public void update(double elapsedTime) {
        transitionComponent.update(elapsedTime);
    }

    public void render(Renderer renderer) {
        int strokeWidth = 2;

        renderer.setColor(BLACK);
        renderer.fillRect(getX(), getY(), getWidth(), getHeight());
        renderer.setColor(getColor());
        renderer.fillRect(
                getX() + strokeWidth,
                getY() + strokeWidth,
                getWidth() - 2 * strokeWidth,
                getHeight() - 2 * strokeWidth);
    }
}
