package net.scriptgate.helper

import org.slf4j.LoggerFactory
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.function.Consumer
import javax.imageio.stream.FileImageOutputStream

object GifHelper {

    private val log = LoggerFactory.getLogger(GifHelper::class.java)

    @JvmOverloads
    fun createGif(outputFile: File, images: List<BufferedImage>, timeBetweenFramesMS: Int = 1, loopContinuously: Boolean = true) {
        val imageType = images.iterator().next().type

        try {
            FileImageOutputStream(outputFile).use { output -> GifSequenceWriter(output, imageType, timeBetweenFramesMS, loopContinuously).use { writer -> images.forEach(Consumer<BufferedImage> { writer.writeToSequence(it) }) } }
        } catch (e: IOException) {
            log.error("An error occurred whilst creating gif file '" + outputFile.name + "': ", e)
        }

    }
}
