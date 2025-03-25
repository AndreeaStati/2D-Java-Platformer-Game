package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;

public class GameContainer extends GameObject {

    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    private void createHitbox() {
        if (objType == BOX) {
            initHitbox(33, 28);

            xDrawOffset = (int) (15 * Game.SCALE);
            yDrawOffset = (int) (32 * Game.SCALE);

        } else {
            initHitbox(33, 28);
            xDrawOffset = (int) (15 * Game.SCALE);
            yDrawOffset = (int) (32 * Game.SCALE);
        }

        hitbox.y += 1;
        hitbox.x += 0;
    }

    public void update() {
        if (doAnimation)
            updateAnimationTick();
    }
}
