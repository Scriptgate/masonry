package net.scriptgate.masonry.gui

import net.scriptgate.masonry.demo.ColorBrick
import org.junit.Ignore
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat


class ColorBrickTest {

    @Test
    @Ignore
    void update_evenElapsedTime() throws Exception {
//        ColorBrick.TRANSITION_SPEED = 100;
        ColorBrick brick = new ColorBrick(100, 100)
        brick.moveTo(100, 100)

        brick.update(10)

        assertThat(brick.getX()).isEqualTo(10)
        assertThat(brick.getY()).isEqualTo(10)

        brick.update(10)

        assertThat(brick.getX()).isEqualTo(20)
        assertThat(brick.getY()).isEqualTo(20)

        brick.update(30)

        assertThat(brick.getX()).isEqualTo(50)
        assertThat(brick.getY()).isEqualTo(50)

        brick.update(50)

        assertThat(brick.getX()).isEqualTo(100)
        assertThat(brick.getY()).isEqualTo(100)
    }

    @Test
    @Ignore
    void update_unevenElapsedTime() throws Exception {
//        ColorBrick.TRANSITION_SPEED = 100;
        ColorBrick brick = new ColorBrick(100, 100)
        brick.moveTo(100, 100)

        brick.update(100 / 3)

        assertThat(brick.getX()).isEqualTo(33)
        assertThat(brick.getY()).isEqualTo(33)

        brick.update(100 / 3)

        assertThat(brick.getX()).isEqualTo(66)
        assertThat(brick.getY()).isEqualTo(66)

        brick.update(100 / 3)

        assertThat(brick.getX()).isEqualTo(99)
        assertThat(brick.getY()).isEqualTo(99)

        brick.update(100 / 3)

        assertThat(brick.getX()).isEqualTo(100)
        assertThat(brick.getY()).isEqualTo(100)
    }
}