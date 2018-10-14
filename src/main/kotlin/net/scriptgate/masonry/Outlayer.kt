package net.scriptgate.masonry

import net.scriptgate.masonry.api.Brick
import net.scriptgate.masonry.api.BrickContainer
import java.util.*

abstract class Outlayer(protected var element: BrickContainer<out Brick>, columnWidth: Int) {
    private var isLayoutInited = false

    var columnWidth = 50

    val items: Collection<Brick>
        get() = element.bricks

    init {
        this.columnWidth = columnWidth

        if (IS_INIT_LAYOUT) {
            this.layout()
        }
    }//TODO: validate element

    fun layout() {
        this.resetLayout()

        // don't animate first layout
        val isInstant = IS_LAYOUT_INSTANT || !this.isLayoutInited
        this.layoutItems(isInstant)

        // flag for initalized
        this.isLayoutInited = true
    }

    private fun layoutItems(isInstant: Boolean) {
        this._layoutItems(element.bricks, isInstant)

        this.postLayout()
    }

    private fun _layoutItems(items: Collection<Brick>?, isInstant: Boolean) {
        if (items == null || items.isEmpty()) {
            // no items, emit event with empty array
            return
        }

        val queue = ArrayList<LayoutPosition>()

        for (item in items) {
            // get x/y object from method
            val position = this.getItemLayoutPosition(item)
            // enqueue
            position.item = item
            position.isInstant = isInstant || item.isLayoutInstant
            queue.add(position)
        }

        this.processLayoutQueue(queue)
    }

    private fun processLayoutQueue(queue: List<LayoutPosition>) {
        for (obj in queue) {
            this.positionItem(obj.item, obj.x, obj.y, obj.isInstant)
        }
    }

    private fun positionItem(item: Brick?, x: Int, y: Int, isInstant: Boolean) {
        if (isInstant) {
            // if not transition, just set CSS
            item!!.goTo(x, y)
        } else {
            item!!.moveTo(x, y)
        }
    }

    protected abstract fun getItemLayoutPosition(item: Brick): LayoutPosition


    private fun postLayout() {
        this.resizeContainer()
    }

    private fun resizeContainer() {
        //TODO: implement?
    }

    protected open fun resetLayout() {
        getSize()
    }

    protected fun getSize() {
        //        this.size = GetSize.getSize(this.element);
    }

    companion object {

        var IS_LAYOUT_INSTANT = false
        var IS_INIT_LAYOUT = true
    }
}
