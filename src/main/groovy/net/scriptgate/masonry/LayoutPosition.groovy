package net.scriptgate.masonry

import net.scriptgate.masonry.api.Brick

class LayoutPosition {

    public int x
    public int y
    public Brick item
    public boolean isInstant

    LayoutPosition(int x, int y) {
        this.x = x
        this.y = y
    }
}
