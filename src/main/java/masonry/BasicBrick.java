package masonry;

import masonry.api.Brick;

class BasicBrick implements Brick {
    private boolean isLayoutInstant = false;
    private Size size;
    private Point position = new Point(0, 0);

    public BasicBrick(int width, int height) {
        size = new Size(width, height);
    }

    @Override
    public void goTo(int x, int y) {
        this.position = new Point(x, y);
    }

    @Override
    public int getHeight() {
        return size.height;
    }

    @Override
    public int getWidth() {
        return size.width;
    }

    @Override
    public boolean isLayoutInstant() {
        return isLayoutInstant;
    }

    @Override
    public int getX() {
        return position.x;
    }

    @Override
    public int getY() {
        return position.y;
    }
}
