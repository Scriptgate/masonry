package net.scriptgate.masonry;

import net.scriptgate.common.Point;
import net.scriptgate.masonry.api.Brick;
import net.scriptgate.masonry.api.BrickContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BrickListContainer<T extends Brick> implements BrickContainer<T> {

    private Point size;
    private List<T> items = new ArrayList<>();

    public BrickListContainer(int width, int height) {
        this.size = new Point(width, height);
    }

    @Override
    public Collection<T> getBricks() {
        return items;
    }

    public void addBrick(T brick) {
        this.items.add(brick);
    }

    public void removeBrick(T brick) {
        this.items.remove(brick);
    }

    @Override
    public int getHeight() {
        return size.y;
    }

    @Override
    public int getWidth() {
        return size.x;
    }
}
