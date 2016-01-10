package masonry.api;

import java.util.Collection;

public interface BrickContainer<T extends Brick> {

    Collection<T> getBricks();

    int getHeight();

    int getWidth();

}
