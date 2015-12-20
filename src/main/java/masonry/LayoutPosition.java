package masonry;

import masonry.api.Brick;

public class LayoutPosition {

    public int x;
    public int y;
    public Brick item;
    public boolean isInstant;

    public LayoutPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
