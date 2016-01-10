package masonry;

import masonry.api.Brick;
import masonry.api.BrickContainer;
import masonry.api.Masonry;

import static java.util.stream.IntStream.of;
import static masonry.utils.ArrayUtils.indexOf;

public final class BasicMasonry extends Outlayer implements Masonry {

    private int columns;
    private int[] columnsY;
    private int containerWidth;
    private int gutter = 0;

    //TODO: private
    public BasicMasonry(BrickContainer<? extends Brick> element, int columnWidth) {
        super(element, columnWidth);
    }

    @Override
    protected void resetLayout() {
        this.getSize();
        this.measureColumns();

//      reset column Y
        initializeColumnsY();
    }

    private void initializeColumnsY() {
        this.columnsY = new int[this.columns];
    }

    private void measureColumns() {
        this.getContainerWidth();
//      if columnWidth is 0, default to outerWidth of first item
        if (this.columnWidth == 0) {
            Brick firstItem = this.getItems().iterator().next();
//          var firstItemElem = firstItem && firstItem.element;
//          columnWidth fall back to item of first element
            this.columnWidth = firstItem != null ? firstItem.getWidth() :
//                  if first elem has no width, default to size of container
                    this.containerWidth;
        }
//
        int columnWidth = this.columnWidth += this.gutter;

//      calculate columns
        int containerWidth = this.containerWidth + this.gutter;
        float columns = containerWidth / columnWidth * 1.0f;
//      fix rounding errors, typically with gutters
        int excess = columnWidth - containerWidth % columnWidth;
//      if overshoot is less than a pixel, round up, otherwise floor it
        if (excess != 0 && excess < 1) {
            columns = Math.round(columns);
        } else {
            columns = (float) Math.floor(columns);
        }
        this.columns = (int) Math.max(columns, 1);
    }

    private void getContainerWidth() {
        this.containerWidth = this.element.getWidth();
    }

    @Override
    protected LayoutPosition getItemLayoutPosition(Brick item) {
//      how many columns does this brick span
        int remainder = item.getWidth() % this.columnWidth;

//      round if off by 1 pixel, otherwise use ceil
        int columnSpan;
        float colSpanRounded = (item.getWidth() * 1.0f) / this.columnWidth;
        if (remainder != 0 && remainder < 1) {
            columnSpan = Math.round(colSpanRounded);
        } else {
            columnSpan = (int) Math.ceil(colSpanRounded);
        }
        columnSpan = Math.min(columnSpan, this.columns);

        int[] colGroup = this.getColumnGroup(columnSpan);

//      get the minimum Y value from the columns
        int minimumY = of(colGroup).min().getAsInt();
        int shortColIndex = indexOf(colGroup, minimumY);

//      position the brick
        LayoutPosition position = new LayoutPosition(this.columnWidth * shortColIndex, minimumY);

//      apply setHeight to necessary columns
        int setHeight = minimumY + item.getHeight();
        int setSpan = this.columns + 1 - colGroup.length;
        for (int i = 0; i < setSpan; i++) {
            this.columnsY[shortColIndex + i] = setHeight;
        }

        return position;
    }

    private int[] getColumnGroup(int colSpan) {
        if (colSpan <= 1) {
//          if brick spans only one column, use all the column Ys
            return this.columnsY;
        }
//      how many different places could this brick fit horizontally
        int groupCount = this.columns + 1 - colSpan;

        int[] colGroup = new int[groupCount];
//      for each group potential horizontal position
        for (int i = 0; i < groupCount; i++) {
//          make an array of colY values for that one group
//          and get the max value of the array
            int maxValue = of(columnsY).skip(i).limit(colSpan).max().getAsInt();
            colGroup[i] = maxValue;
        }
        return colGroup;
    }

    public static Mason mason() {
        return new Mason();
    }

    public static class Mason {

        private int columnWidth;
        private BrickContainer<? extends Brick> container;

        private Mason() {

        }

        public Mason useColumnWidth(int columnWidth) {
            this.columnWidth = columnWidth;
            return this;
        }

        public Mason on(BrickContainer<? extends Brick> container) {
            this.container = container;
            return this;
        }

        public BasicMasonry build() {
            return new BasicMasonry(container, columnWidth);
        }
    }
}
