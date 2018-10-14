package net.scriptgate.masonry.gui

import net.scriptgate.masonry.demo.ColorBrick
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test


class ColorBrickTest {

    @Test
    @Ignore
    @Throws(Exception::class)
    fun update_evenElapsedTime() {
        //        ColorBrick.TRANSITION_SPEED = 100;
        val brick = ColorBrick(100, 100)
        brick.moveTo(100, 100)

        brick.update(10.0)

        assertThat(brick.x).isEqualTo(10)
        assertThat(brick.y).isEqualTo(10)

        brick.update(10.0)

        assertThat(brick.x).isEqualTo(20)
        assertThat(brick.y).isEqualTo(20)

        brick.update(30.0)

        assertThat(brick.x).isEqualTo(50)
        assertThat(brick.y).isEqualTo(50)

        brick.update(50.0)

        assertThat(brick.x).isEqualTo(100)
        assertThat(brick.y).isEqualTo(100)
    }

    @Test
    @Ignore
    @Throws(Exception::class)
    fun update_unevenElapsedTime() {
        //        ColorBrick.TRANSITION_SPEED = 100;
        val brick = ColorBrick(100, 100)
        brick.moveTo(100, 100)

        brick.update((100 / 3).toDouble())

        assertThat(brick.x).isEqualTo(33)
        assertThat(brick.y).isEqualTo(33)

        brick.update((100 / 3).toDouble())

        assertThat(brick.x).isEqualTo(66)
        assertThat(brick.y).isEqualTo(66)

        brick.update((100 / 3).toDouble())

        assertThat(brick.x).isEqualTo(99)
        assertThat(brick.y).isEqualTo(99)

        brick.update((100 / 3).toDouble())

        assertThat(brick.x).isEqualTo(100)
        assertThat(brick.y).isEqualTo(100)
    }
}