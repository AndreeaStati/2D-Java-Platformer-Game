package objects;

import main.Game;

//fierastrau
public class Saw extends GameObject{

    public Saw(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;

        initHitbox(24,24);

        xDrawOffset -= (int)( 1* Game.SCALE);
        yDrawOffset += (int)( 3* Game.SCALE );

        hitbox.x += xDrawOffset+2;
        hitbox.y += yDrawOffset;
    }

    public void update() {
        // if (doAnimation)
        updateAnimationTick();
    }


}
