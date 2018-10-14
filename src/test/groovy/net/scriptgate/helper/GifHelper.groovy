package net.scriptgate.helper

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.imageio.stream.FileImageOutputStream
import javax.imageio.stream.ImageOutputStream
import java.awt.image.BufferedImage

class GifHelper {

    private static final Logger log = LoggerFactory.getLogger(GifHelper.class)

    static void createGif(File outputFile, List<BufferedImage> images) {
        createGif(outputFile, images, 1, true)
    }

    static void createGif(File outputFile, List<BufferedImage> images, int timeBetweenFramesMS, boolean loopContinuously) {
        int imageType = images.iterator().next().getType()

        ImageOutputStream output = null
        try {
            output = new FileImageOutputStream(outputFile)

             GifSequenceWriter writer = new GifSequenceWriter(output, imageType, timeBetweenFramesMS, loopContinuously)

            images.forEach { writer.writeToSequence(it) }
        } catch (IOException e) {
            log.error("An error occurred whilst creating gif file '" + outputFile.getName() + "': ", e)
        } finally {
            output?.close()
        }
    }
}
