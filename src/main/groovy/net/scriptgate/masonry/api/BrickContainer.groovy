package net.scriptgate.masonry.api

interface BrickContainer<T extends Brick> {

    int getWidth()
    int getHeight()

    Collection<T> getBricks()
}
