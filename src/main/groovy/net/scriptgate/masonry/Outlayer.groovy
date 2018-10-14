package net.scriptgate.masonry

import net.scriptgate.masonry.api.Brick
import net.scriptgate.masonry.api.BrickContainer

abstract class Outlayer {

    public static boolean IS_LAYOUT_INSTANT = false
    public static boolean IS_INIT_LAYOUT = true


    protected BrickContainer<? extends Brick> element
    private boolean isLayoutInited = false

    public int columnWidth = 50

    Outlayer(BrickContainer<? extends Brick> element, int columnWidth) {
        this.columnWidth = columnWidth

        //TODO: validate element
        this.element = element

        if (IS_INIT_LAYOUT) {
            this.layout()
        }
    }

    void layout() {
        this.resetLayout()

        // don't animate first layout
        boolean isInstant = IS_LAYOUT_INSTANT || !this.isLayoutInited
        this.layoutItems(isInstant)

        // flag for initalized
        this.isLayoutInited = true
    }

    private void layoutItems(boolean isInstant) {
        this._layoutItems(element.getBricks(), isInstant)

        this.postLayout()
    }

    private void _layoutItems(Collection<? extends Brick> items, boolean isInstant) {
        if (items == null || items.isEmpty()) {
            // no items, emit event with empty array
            return
        }

        List<LayoutPosition> queue = new ArrayList<>()

        for (Brick item : items) {
            // get x/y object from method
            LayoutPosition position = this.getItemLayoutPosition(item)
            // enqueue
            position.item = item
            position.isInstant = isInstant || item.isLayoutInstant()
            queue.add(position)
        }

        this.processLayoutQueue(queue)
    }

    private void processLayoutQueue(List<LayoutPosition> queue) {
        for (LayoutPosition obj : queue) {
            this.positionItem(obj.item, obj.x, obj.y, obj.isInstant)
        }
    }

    private void positionItem(Brick item, int x, int y, boolean isInstant) {
        if (isInstant) {
            // if not transition, just set CSS
            item.goTo(x, y)
        } else {
            item.moveTo(x, y)
        }
    }

    protected abstract LayoutPosition getItemLayoutPosition(Brick item)


    private void postLayout() {
        this.resizeContainer()
    }

    private void resizeContainer() {
        //TODO: implement?
    }

    protected void resetLayout() {
        getSize()
    }

    protected void getSize() {
//        this.size = GetSize.getSize(this.element);
    }

    Collection<? extends Brick> getItems() {
        return element.getBricks()
    }
}
