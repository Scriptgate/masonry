package net.scriptgate.masonry

import net.scriptgate.common.Point
import net.scriptgate.masonry.api.Brick
import net.scriptgate.masonry.api.BrickContainer

class BrickListContainer<T extends Brick> implements BrickContainer<T> {

    private Point size
    private List<T> items = new ArrayList<>()

    BrickListContainer(int width, int height) {
        this.size = new Point(width, height)
    }

    @Override
    Collection<T> getBricks() {
        return items
    }

    void addBrick(T brick) {
        this.items.add(brick)
    }

    void removeBrick(T brick) {
        this.items.remove(brick)
    }

    @Override
    int getHeight() {
        return size.y
    }

    @Override
    int getWidth() {
        return size.x
    }
}
