package net.scriptgate.masonry

import net.scriptgate.engine.Application
import net.scriptgate.engine.lwjgl.OpenGLApplicationHandler

class Launcher {

    static void launch(Application application) {
        new OpenGLApplicationHandler().start(application)
    }

}
