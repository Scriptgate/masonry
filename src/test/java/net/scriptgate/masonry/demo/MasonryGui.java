package net.scriptgate.masonry.demo;

import net.scriptgate.common.Rectangle;
import net.scriptgate.engine.Application;
import net.scriptgate.engine.InputComponent;
import net.scriptgate.engine.Key;
import net.scriptgate.engine.Renderer;
import net.scriptgate.masonry.BasicMasonry;
import net.scriptgate.masonry.BrickListContainer;

import java.util.Random;

import static net.scriptgate.engine.Engine.HEIGHT;
import static net.scriptgate.engine.Engine.WIDTH;
import static net.scriptgate.masonry.Launcher.launch;

public class MasonryGui implements Application {

    public static void main(String[] args) {
        launch(new MasonryGui());
    }

    private BasicMasonry masonry;
    private BrickListContainer<ColorBrick> container;


    @Override
    public void initialize() {
        container = new BrickListContainer<>(WIDTH, HEIGHT);
        masonry = new BasicMasonry(container, 40);
    }

    @Override
    public void onKeyDown(Key key) {

    }

    @Override
    public void onTick(InputComponent inputComponent, double elapsedTime) {
        container.getBricks().stream().forEach(brick -> brick.update(elapsedTime));

        inputComponent.getPressedKeys().forEach(key -> {
            int width = (int) (Math.floor(2 * Math.random() + 1) * 40);
            int height = (int) (Math.floor(3 * Math.random() + 1) * 40);
            ColorBrick brick = new ColorBrick(width, height);
            switch (key.getKeyName()) {
                case "q":
                    container.addBrick(brick);
                    masonry.layout();
                    break;
                case "w":
                    container.addBrick(brick);
                    if (container.getBricks().isEmpty()) {
                        break;
                    }
                    int elementsToSkip = new Random().nextInt(container.getBricks().size());
                    container.getBricks().stream()
                            .skip(elementsToSkip)
                            .findFirst()
                            .ifPresent(b -> {
                                container.getBricks().remove(b);
                                masonry.layout();
                            });
                    break;
            }
        });
    }

    @Override
    public void onClick(int x, int y) {
        container.getBricks()
                .stream()
                .filter(brick -> {
                    Rectangle bounds = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
                    return bounds.contains(x, y);
                })
                .findFirst().ifPresent(brick -> {
            container.removeBrick(brick);
            masonry.layout();
        });
    }

    @Override
    public void render(Renderer renderer) {
        renderer.setOpacity(0.5f);
        container.getBricks().forEach(brick -> {
            brick.render(renderer);
        });
    }
}
