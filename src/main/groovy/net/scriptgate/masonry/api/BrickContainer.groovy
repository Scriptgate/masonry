package net.scriptgate.masonry.api

interface BrickContainer<T extends Brick> {

    Collection<T> getBricks()

    int getHeight()

    int getWidth()

}
