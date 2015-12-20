package masonry;

import masonry.api.Brick;

import java.util.ArrayList;
import java.util.Collection;

public class Container<T extends Brick> {

    private Size size;
    private Collection<T> items = new ArrayList<>();

    public Container(int width, int height) {
        this.size = new Size(width, height);
    }

    public Collection<T> getItems() {
        return items;
    }

    public void addBrick(T brick) {
        this.items.add(brick);
    }

    public void setSize(int width, int height) {
        this.size = new Size(width, height);
    }

    public int getHeight() {
        return size.height;
    }

    public int getWidth() {
        return size.width;
    }

    public void removeBrick(T brick) {
        this.items.remove(brick);
    }
}
