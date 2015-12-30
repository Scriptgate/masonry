package gui;

import masonry.Point;
import masonry.api.Brick;
import masonry.transition.ArcedTransition;
import masonry.transition.Transition;
import net.scriptgate.common.Color3f;
import net.scriptgate.engine.Renderer;

import static masonry.transition.Transition.none;
import static net.scriptgate.common.Color3f.BLACK;

public class ColorBrick implements Brick {

    private int height;
    private int width;

    private int x = 0;
    private int y = 0;

    private Color3f color;

    private boolean layoutInstant = false;

    private Transition transition = none(0, 0);
    private boolean initialLayoutDone = false;

    public ColorBrick(int width, int height) {
        this.width = width;
        this.height = height;
        this.color = new Color3f(1, 0, 0);
    }

    @Override
    public void goTo(int x, int y) {
        this.x = x;
        this.y = y;
        transition = none(x, y);
        this.initialLayoutDone = true;
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
        return layoutInstant || !initialLayoutDone;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public Color3f getColor() {
        return color;
    }

    @Override
    public void moveTo(int x, int y) {
        if (x == this.x && y == this.y) {
            return;
        }
        if (x == transition.toX() && y == transition.toY()) {
            return;
        }
        transition = new ArcedTransition(this.x, this.y, x, y);
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
