package gui;

import helper.GifHelper;
import masonry.Point;
import masonry.transition.ArcedTransition;
import masonry.transition.TransitionTrace;
import net.scriptgate.common.Color3f;
import net.scriptgate.engine.Application;
import net.scriptgate.engine.Engine;
import net.scriptgate.engine.InputComponent;
import net.scriptgate.engine.Renderer;
import net.scriptgate.engine.util.FileUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static masonry.transition.TransitionTrace.trace;
import static net.scriptgate.common.Color3f.RED;
import static net.scriptgate.engine.ApplicationHandlerBuilder.run;
import static net.scriptgate.engine.ApplicationType.OPENGL;

public class ExportTransition implements Application {

    public static void main(String[] args) {
        run(new ExportTransition()).in(OPENGL);
    }

    private TransitionTrace trace;
    private Point pointFrom, pointTo;

    private List<BufferedImage> screenCaptures;
    private boolean hasScreenChanged = true;

    @Override
    public void initializeProperties() {
        Engine.WIDTH = 256;
        Engine.HEIGHT = 256;
    }

    @Override
    public void initialize() {
        pointFrom = new Point(16, 16);
        pointTo = new Point(224, 224);
        trace = trace(new ArcedTransition(pointFrom, pointTo), new Color3f(0, 1, 0));

        screenCaptures = new ArrayList<>();
    }

    @Override
    public void render(Renderer renderer) {
        trace.render(renderer, point -> renderer.fillCircle(point.x, point.y, 2));

        renderer.setColor(RED);
        renderer.fillCircle(pointFrom.x, pointFrom.y, 2);
        renderer.fillCircle(pointTo.x, pointTo.y, 2);

        if (hasScreenChanged) {
            screenCaptures.add(renderer.printScreen());
            hasScreenChanged = false;
        }
    }

    @Override
    public void onTick(InputComponent inputComponent, double elapsedTime) {
        if (trace.isCompleted()) {
            exportScreenCapturesToGif();
        }

        hasScreenChanged = trace.onTick(elapsedTime);
    }

    private void exportScreenCapturesToGif() {

        File gifOutputDirectory = new File("gifs");
        //noinspection ResultOfMethodCallIgnored
        gifOutputDirectory.mkdir();

        File output = FileUtil.getUniqueFileNameWithTimestamp(gifOutputDirectory, "gif");
        try {
            //noinspection ResultOfMethodCallIgnored
            output.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GifHelper.createGif(output, screenCaptures);
        System.exit(0);
    }
}
