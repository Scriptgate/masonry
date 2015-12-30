package helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GifHelper {

    private static final Logger log = LoggerFactory.getLogger(GifHelper.class);

    public static void createGif(File outputFile, List<BufferedImage> images) {
        createGif(outputFile, images, 1, true);
    }

    public static void createGif(File outputFile, List<BufferedImage> images, int timeBetweenFramesMS, boolean loopContinuously) {
        int imageType = images.iterator().next().getType();

        try (ImageOutputStream output = new FileImageOutputStream(outputFile);
             GifSequenceWriter writer = new GifSequenceWriter(output, imageType, timeBetweenFramesMS, loopContinuously)) {
            images.forEach(writer::writeToSequence);
        } catch (IOException e) {
            log.error("An error occurred whilst creating gif file '" + outputFile.getName() + "': ", e);
        }
    }
}
