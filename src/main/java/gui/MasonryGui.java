package gui;

import masonry.BasicMasonry;
import masonry.BrickListContainer;
import net.scriptgate.common.Rectangle;
import net.scriptgate.engine.Application;
import net.scriptgate.engine.InputComponent;
import net.scriptgate.engine.Renderer;

import java.util.Random;

import static net.scriptgate.engine.ApplicationHandlerBuilder.run;
import static net.scriptgate.engine.ApplicationType.OPENGL;
import static net.scriptgate.engine.Engine.HEIGHT;
import static net.scriptgate.engine.Engine.WIDTH;

public class MasonryGui implements Application {

    private boolean intialized = false;

    public static void main(String[] args) {
        run(new MasonryGui()).in(OPENGL);
    }

    private BasicMasonry masonry;
    private BrickListContainer<ColorBrick> container;


    @Override
    public void initialize() {
        container = new BrickListContainer<>(WIDTH, HEIGHT);
        masonry = new BasicMasonry(container, 40);
    }

    @Override
    public void onKeyDown(int key) {

    }

    @Override
    public void onTick(InputComponent inputComponent, double elapsedTime) {
        container.getBricks().stream().forEach(brick -> brick.update(elapsedTime));

        for (Integer key : inputComponent.getPressedKeys()) {
            //TODO: find a way around implementation-dependant keys
            switch (key) {
                case 0x51://Q
                    int width = (int) (Math.floor(2 * Math.random() + 1) * 40);
                    int height = (int) (Math.floor(3 * Math.random() + 1) * 40);
                    ColorBrick brick = new ColorBrick(width, height);
                    container.addBrick(brick);
                    masonry.layout();
                    break;
                case 0x57://W
                    width = (int) (Math.floor(2 * Math.random() + 1) * 40);
                    height = (int) (Math.floor(3 * Math.random() + 1) * 40);
                    brick = new ColorBrick(width, height);
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
        }
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
