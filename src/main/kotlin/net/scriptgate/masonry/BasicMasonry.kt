package net.scriptgate.masonry

import net.scriptgate.masonry.api.Brick
import net.scriptgate.masonry.api.BrickContainer
import net.scriptgate.masonry.api.Masonry
import net.scriptgate.masonry.utils.ArrayUtils

import java.util.stream.IntStream.of

class BasicMasonry//TODO: private
(element: BrickContainer<out Brick>, columnWidth: Int) : Outlayer(element, columnWidth), Masonry {

    private var columns: Int = 0
    private var columnsY: IntArray? = null
    private var containerWidth: Int = 0
    private val gutter = 0

    override fun resetLayout() {
        this.getSize()
        this.measureColumns()

        //      reset column Y
        initializeColumnsY()
    }

    private fun initializeColumnsY() {
        this.columnsY = IntArray(this.columns)
    }

    private fun measureColumns() {
        this.getContainerWidth()
        //      if columnWidth is 0, default to outerWidth of first item
        if (this.columnWidth == 0) {
            val firstItem = this.items.iterator().next()
            //          var firstItemElem = firstItem && firstItem.element;
            //          columnWidth fall back to item of first element
            this.columnWidth = firstItem?.width ?: this.containerWidth
        }
        //
        this.columnWidth += this.gutter
        val columnWidth = this.columnWidth

        //      calculate columns
        val containerWidth = this.containerWidth + this.gutter
        var columns = containerWidth / columnWidth * 1.0f
        //      fix rounding errors, typically with gutters
        val excess = columnWidth - containerWidth % columnWidth
        //      if overshoot is less than a pixel, round up, otherwise floor it
        if (excess != 0 && excess < 1) {
            columns = Math.round(columns).toFloat()
        } else {
            columns = Math.floor(columns.toDouble()).toFloat()
        }
        this.columns = Math.max(columns, 1f).toInt()
    }

    private fun getContainerWidth() {
        this.containerWidth = this.element.width
    }

    override fun getItemLayoutPosition(item: Brick): LayoutPosition {
        //      how many columns does this brick span
        val remainder = item.width % this.columnWidth

        //      round if off by 1 pixel, otherwise use ceil
        var columnSpan: Int
        val colSpanRounded = item.width * 1.0f / this.columnWidth
        if (remainder != 0 && remainder < 1) {
            columnSpan = Math.round(colSpanRounded)
        } else {
            columnSpan = Math.ceil(colSpanRounded.toDouble()).toInt()
        }
        columnSpan = Math.min(columnSpan, this.columns)

        val colGroup = this.getColumnGroup(columnSpan)

        //      get the minimum Y value from the columns
        val minimumY = of(*colGroup!!).min().asInt
        val shortColIndex = ArrayUtils.indexOf(colGroup, minimumY)

        //      position the brick
        val position = LayoutPosition(this.columnWidth * shortColIndex, minimumY)

        //      apply setHeight to necessary columns
        val setHeight = minimumY + item.height
        val setSpan = this.columns + 1 - colGroup.size
        for (i in 0 until setSpan) {
            this.columnsY?.set(shortColIndex + i, setHeight)
        }

        return position
    }

    private fun getColumnGroup(colSpan: Int): IntArray? {
        if (colSpan <= 1) {
            //          if brick spans only one column, use all the column Ys
            return this.columnsY
        }
        //      how many different places could this brick fit horizontally
        val groupCount = this.columns + 1 - colSpan

        val colGroup = IntArray(groupCount)
        //      for each group potential horizontal position
        for (i in 0 until groupCount) {
            //          make an array of colY values for that one group
            //          and get the max value of the array
            val maxValue = of(*columnsY!!).skip(i.toLong()).limit(colSpan.toLong()).max().asInt
            colGroup[i] = maxValue
        }
        return colGroup
    }

    class Mason {

        private var columnWidth: Int = 0
        private lateinit var container: BrickContainer<out Brick>

        fun useColumnWidth(columnWidth: Int): Mason {
            this.columnWidth = columnWidth
            return this
        }

        fun on(container: BrickContainer<out Brick>): Mason {
            this.container = container
            return this
        }

        fun build(): BasicMasonry {
            return BasicMasonry(container, columnWidth)
        }
    }
}
