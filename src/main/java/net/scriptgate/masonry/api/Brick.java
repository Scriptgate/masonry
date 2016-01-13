package net.scriptgate.masonry.api;

public interface Brick {

    void goTo(int x, int y);

    default void moveTo(int x, int y) {
        goTo(x, y);
    }

    int getHeight();

    int getWidth();

    boolean isLayoutInstant();

    int getX();

    int getY();
}
