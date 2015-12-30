package gui;

import masonry.BasicMasonry;
import masonry.Container;
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
    private Container<ColorBrick> grid;


    @Override
    public void initialize() {
        grid = new Container<>(WIDTH, HEIGHT);
        masonry = new BasicMasonry(grid, 40);
    }

    @Override
    public void onKeyDown(int key) {

    }

    @Override
    public void onTick(InputComponent inputComponent, double elapsedTime) {
        grid.getItems().stream().forEach(brick -> brick.update(elapsedTime));

        for (Integer key : inputComponent.getPressedKeys()) {
            //TODO: find a way around implementation-dependant keys
            switch (key) {
                case 0x51://Q
                    int width = (int) (Math.floor(2 * Math.random() + 1) * 40);
                    int height = (int) (Math.floor(3 * Math.random() + 1) * 40);
                    ColorBrick brick = new ColorBrick(width, height);
                    grid.addBrick(brick);
                    masonry.layout();
                    break;
                case 0x57://W
                    width = (int) (Math.floor(2 * Math.random() + 1) * 40);
                    height = (int) (Math.floor(3 * Math.random() + 1) * 40);
                    brick = new ColorBrick(width, height);
                    grid.addBrick(brick);
                    if (grid.getItems().isEmpty()) {
                        break;
                    }
                    int elementsToSkip = new Random().nextInt(grid.getItems().size());
                    grid.getItems().stream()
                            .skip(elementsToSkip)
                            .findFirst()
                            .ifPresent(b -> {
                                grid.getItems().remove(b);
                                masonry.layout();
                            });
                    break;
            }
        }
    }

    @Override
    public void onClick(int x, int y) {
        grid.getItems()
                .stream()
                .filter(brick -> {
                    Rectangle bounds = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
                    return bounds.contains(x, y);
                })
                .findFirst().ifPresent(brick -> {
            grid.removeBrick(brick);
            masonry.layout();
        });
    }

    @Override
    public void render(Renderer renderer) {
        renderer.setOpacity(0.5f);
        grid.getItems().forEach(brick -> {
            brick.render(renderer);
        });
    }
}
