package net.scriptgate.masonry.demo

import net.scriptgate.common.Rectangle
import net.scriptgate.engine.Application
import net.scriptgate.engine.Engine.HEIGHT
import net.scriptgate.engine.Engine.WIDTH
import net.scriptgate.engine.InputComponent
import net.scriptgate.engine.Key
import net.scriptgate.engine.Renderer
import net.scriptgate.masonry.BasicMasonry
import net.scriptgate.masonry.BrickListContainer
import net.scriptgate.masonry.Launcher
import java.util.*

class MasonryGui : Application {

    private var count = 0

    private lateinit var masonry: BasicMasonry
    private lateinit var container: BrickListContainer<ColorBrick>


    override fun initialize() {
        container = BrickListContainer(WIDTH, HEIGHT)
        masonry = BasicMasonry(container, 40)
    }

    override fun onKeyDown(key: Key?) {

    }

    override fun onUpdate(ticks: Int, frames: Int) {
        println("ticks=$ticks frames=$frames count=${count++}")
        container.bricks.forEach { brick -> brick.update(ticks.toDouble()) }
    }

    override fun onTick(inputComponent: InputComponent, elapsedTime: Double) {
        inputComponent.pressedKeys.forEach { key ->
            val width = (Math.floor(2 * Math.random() + 1) * 40).toInt()
            val height = (Math.floor(3 * Math.random() + 1) * 40).toInt()
            val brick = ColorBrick(width, height)
            if (key.keyName != null) {

                when (key.keyName) {
                    "q" -> {

                        container.addBrick(brick)
                        masonry.layout()
                    }
                    "w" -> {
                        container.addBrick(brick)
                        val elementsToSkip = Random().nextInt(container.bricks.size)
                        container.bricks.stream()
                                .skip(elementsToSkip.toLong())
                                .findFirst()
                                .ifPresent { b ->
                                    container.bricks.minus(b)
                                    masonry.layout()
                                }
                    }
                }
            }
        }
    }

    override fun render(renderer: Renderer) {
        renderer.setOpacity(0.5f)
        container.bricks.forEach { brick: ColorBrick -> brick.render(renderer) }
        renderer.drawText(100,100, "Count: $count")
    }

    override fun onClick(x: Int, y: Int) {

        container.bricks
                .stream()
                .filter { brick ->
                    val bounds = Rectangle(brick.x, brick.y, brick.width, brick.height)
                    bounds.contains(x, y)
                }
                .findFirst().ifPresent { brick ->
                    container.removeBrick(brick)
                    masonry.layout()
                }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Launcher.launch(MasonryGui())
        }
    }
}
