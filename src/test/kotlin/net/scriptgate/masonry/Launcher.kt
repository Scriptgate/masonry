package net.scriptgate.masonry

import net.scriptgate.engine.Application
import net.scriptgate.engine.lwjgl.OpenGLApplicationHandler

object Launcher {

    fun launch(application: Application) {
        OpenGLApplicationHandler().start(application)
    }

}
