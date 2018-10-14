package net.scriptgate.masonry.gui

import net.scriptgate.common.Color3f
import net.scriptgate.common.Color3f.RED
import net.scriptgate.common.Point
import net.scriptgate.engine.Application
import net.scriptgate.engine.Engine
import net.scriptgate.engine.InputComponent
import net.scriptgate.engine.Renderer
import net.scriptgate.engine.lwjgl.OpenGLScreenshotHelper
import net.scriptgate.engine.lwjgl.util.FileUtil
import net.scriptgate.helper.GifHelper
import net.scriptgate.masonry.Launcher.launch
import net.scriptgate.masonry.transition.ArcedTransition
import net.scriptgate.masonry.transition.TransitionTrace
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.*

class ExportTransition : Application {

    private var trace: TransitionTrace? = null
    private var pointFrom: Point? = null
    private var pointTo: Point? = null

    private var screenCaptures: List<BufferedImage> = emptyList()
    private var hasScreenChanged = true

    override fun initializeProperties() {
        Engine.WIDTH = 256
        Engine.HEIGHT = 256
    }

    override fun initialize() {
        pointFrom = Point(16, 16)
        pointTo = Point(224, 224)
        trace = TransitionTrace.trace(ArcedTransition(pointFrom!!, pointTo!!), Color3f(0f, 1f, 0f))

        screenCaptures = ArrayList()
    }

    override fun render(renderer: Renderer) {
        trace!!.render(renderer) { point:Point -> renderer.fillCircle(point.x, point.y, 2) }

        renderer!!.setColor(RED)
        renderer.fillCircle(pointFrom!!.x, pointFrom!!.y, 2)
        renderer.fillCircle(pointTo!!.x, pointTo!!.y, 2)

        if (hasScreenChanged) {
            screenCaptures!!.plus(OpenGLScreenshotHelper.getScreenshot())
            hasScreenChanged = false
        }
    }

    override fun onTick(inputComponent: InputComponent?, elapsedTime: Double) {
        if (trace!!.isCompleted) {
            exportScreenCapturesToGif()
        }

        hasScreenChanged = trace!!.onTick(elapsedTime)
    }

    private fun exportScreenCapturesToGif() {

        val gifOutputDirectory = File("gifs")

        gifOutputDirectory.mkdir()

        val output = FileUtil.getUniqueFileNameWithTimestamp(gifOutputDirectory, "gif")
        try {

            output.createNewFile()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

        GifHelper.createGif(output, screenCaptures)
        System.exit(0)
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(ExportTransition())
        }
    }
}
