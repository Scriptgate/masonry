package net.scriptgate.masonry

import net.scriptgate.masonry.api.Brick

class LayoutPosition(var x: Int, var y: Int) {
    var item: Brick? = null
    var isInstant: Boolean = false
}
