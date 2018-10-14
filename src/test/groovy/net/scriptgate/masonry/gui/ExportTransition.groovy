package net.scriptgate.masonry.gui

import net.scriptgate.common.Color3f
import net.scriptgate.common.Point
import net.scriptgate.engine.Application
import net.scriptgate.engine.Engine
import net.scriptgate.engine.InputComponent
import net.scriptgate.engine.Renderer
import net.scriptgate.engine.lwjgl.OpenGLScreenshotHelper
import net.scriptgate.engine.lwjgl.util.FileUtil
import net.scriptgate.helper.GifHelper
import net.scriptgate.masonry.transition.ArcedTransition
import net.scriptgate.masonry.transition.TransitionTrace

import java.awt.image.BufferedImage

import static net.scriptgate.common.Color3f.RED
import static net.scriptgate.masonry.Launcher.launch
import static net.scriptgate.masonry.transition.TransitionTrace.trace

class ExportTransition implements Application {

    static void main(String[] args) {
        launch(new ExportTransition())
    }

    private TransitionTrace trace
    private Point pointFrom, pointTo

    private List<BufferedImage> screenCaptures
    private boolean hasScreenChanged = true

    @Override
    void initializeProperties() {
        Engine.WIDTH = 256
        Engine.HEIGHT = 256
    }

    @Override
    void initialize() {
        pointFrom = new Point(16, 16)
        pointTo = new Point(224, 224)
        trace = trace(new ArcedTransition(pointFrom, pointTo), new Color3f(0, 1, 0))

        screenCaptures = new ArrayList<>()
    }

    @Override
    void render(Renderer renderer) {
        trace.render(renderer, { point -> renderer.fillCircle(point.x, point.y, 2) })

        renderer.setColor(RED)
        renderer.fillCircle(pointFrom.x, pointFrom.y, 2)
        renderer.fillCircle(pointTo.x, pointTo.y, 2)

        if (hasScreenChanged) {
            screenCaptures.add(OpenGLScreenshotHelper.getScreenshot())
            hasScreenChanged = false
        }
    }

    @Override
    void onTick(InputComponent inputComponent, double elapsedTime) {
        if (trace.isCompleted()) {
            exportScreenCapturesToGif()
        }

        hasScreenChanged = trace.onTick(elapsedTime)
    }

    private void exportScreenCapturesToGif() {

        File gifOutputDirectory = new File("gifs")
        //noinspection ResultOfMethodCallIgnored
        gifOutputDirectory.mkdir()

        File output = FileUtil.getUniqueFileNameWithTimestamp(gifOutputDirectory, "gif")
        try {
            //noinspection ResultOfMethodCallIgnored
            output.createNewFile()
        } catch (IOException e) {
            throw new RuntimeException(e)
        }
        GifHelper.createGif(output, screenCaptures)
        System.exit(0)
    }
}
