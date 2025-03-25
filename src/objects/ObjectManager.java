package objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Enemy;
import entities.Player;
import gamestates.Playing;
import levels.Level;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.ObjectConstants.*;
import static utilz.HelpMethods.CanCannonSeePlayer;
import static utilz.HelpMethods.IsProjectileHittingLevel;
import static utilz.Constants.Projectiles.*;

public class ObjectManager {

    private Playing playing;

    private BufferedImage[][] fruitImgs, containerImgs;
    private BufferedImage[] cannonImgs;
    private BufferedImage[] sawImg;
    private BufferedImage cannonBallImg;

    private ArrayList<Fruit> fruits;
    private ArrayList<GameContainer> containers;
    private ArrayList<Saw> saws;
    private ArrayList<Cannon> cannons;

    private ArrayList<Projectile> projectiles = new ArrayList<>();

    private Level currentLevel;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
    }

    public void checkSpikesTouched(Player p) {
        for (Saw s : saws)
            if (s.getHitbox().intersects(p.getHitbox()))
                p.kill();
    }

    public void checkSawTouched(Enemy e) {
        for (Saw s : currentLevel.getSaws())
            if (s.getHitbox().intersects(e.getHitbox()))
                e.hurt(200);
    }

    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for (Fruit f : fruits)
            if (f.isActive()) {
                if (hitbox.intersects(f.getHitbox())) {
                    f.setActive(false);
                    applyEffectToPlayer(f);
                }
            }
    }

    public void applyEffectToPlayer(Fruit p) {
        if (p.getObjType() == BANANA)
            playing.getPlayer().changeHealth(BANANA_VALUE);
        else
            playing.getPlayer().changePower(ANANAS_VALUE);
    }

    public void checkObjectHit(Rectangle2D.Float attackbox) {
        for (GameContainer gc : containers)
            if (gc.isActive() && !gc.doAnimation) {
                if (gc.getHitbox().intersects(attackbox)) {
                    gc.setAnimation(true);
                    int type = 0;
                    if (gc.getObjType() == BARREL)
                        type = 1;
                    fruits.add(new Fruit((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y - gc.getHitbox().height / 2), type));
                    return;
                }
            }
    }

    public void loadObjects(Level newLevel) {
        fruits = new ArrayList<>(newLevel.getPotions());
        containers = new ArrayList<>(newLevel.getContainers());
        saws = newLevel.getSaws();
        cannons = newLevel.getCannons();
        projectiles.clear();
    }

    private void loadImgs() {
        BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.FRUIT_ATLAS);
        fruitImgs = new BufferedImage[2][8];

        for (int j = 0; j < fruitImgs.length; j++)
            for (int i = 0; i < fruitImgs[j].length; i++)
                fruitImgs[j][i] = potionSprite.getSubimage(32 * i, 32 * j, 32, 32);

        BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][7];

        for (int j = 0; j < containerImgs.length; j++)
            for (int i = 0; i < containerImgs[j].length; i++)
                containerImgs[j][i] = containerSprite.getSubimage(64 * i, 64 * j, 64, 64);


        BufferedImage sawSprite = LoadSave.GetSpriteAtlas(LoadSave.TRAP_ATLAS);
        sawImg = new BufferedImage[8];
        for (int i = 0; i < sawImg.length; i++)
            sawImg[i] = sawSprite.getSubimage(i * 38, 0, 38, 38);

        cannonImgs = new BufferedImage[7];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CANNON_ATLAS);

        for (int i = 0; i < cannonImgs.length; i++)
            cannonImgs[i] = temp.getSubimage(i * 40, 0, 40, 26);

        cannonBallImg = LoadSave.GetSpriteAtlas(LoadSave.CANNON_BALL);

    }

    public void update(int[][] lvlData, Player player) {
        for (Fruit f : fruits)
            if (f.isActive())
                f.update();

        for (GameContainer gc : containers)
            if (gc.isActive())
                gc.update();

        for (Saw s: saws)
            if (s.isActive())
                s.update();

        updateCannons(lvlData, player);
        updateProjectiles(lvlData, player);
    }

    private void updateProjectiles(int[][] lvlData, Player player) {
        for (Projectile p : projectiles)
            if (p.isActive()) {
                p.updatePos();
                if (p.getHitbox().intersects(player.getHitbox())) {
                    player.changeHealth(-15);
                    p.setActive(false);
                } else if (IsProjectileHittingLevel(p, lvlData))
                    p.setActive(false);
            }
    }

    private boolean isPlayerInRange(Cannon c, Player player) {
        int absValue = (int) Math.abs(player.getHitbox().x - c.getHitbox().x);
        return absValue <= Game.TILES_SIZE * 5;
    }

    private boolean isPlayerInfrontOfCannon(Cannon c, Player player) {
        if (c.getObjType() == CANNON_LEFT) {
            if (c.getHitbox().x > player.getHitbox().x)
                return true;

        } else if (c.getHitbox().x < player.getHitbox().x)
            return true;
        return false;
    }

    private void updateCannons(int[][] lvlData, Player player) {
        for (Cannon c : cannons) {
            if (!c.doAnimation)
                if (c.getTileY() == player.getTileY())
                    if (isPlayerInRange(c, player))
                        if (isPlayerInfrontOfCannon(c, player))
                            if (CanCannonSeePlayer(lvlData, player.getHitbox(), c.getHitbox(), c.getTileY()))
                                c.setAnimation(true);

            c.update();
            if (c.getAniIndex() == 4 && c.getAniTick() == 0)
                shootCannon(c);
        }
    }

    private void shootCannon(Cannon c) {
        int dir = 1;
        if (c.getObjType() == CANNON_LEFT)
            dir = -1;

        projectiles.add(new Projectile((int) c.getHitbox().x, (int) c.getHitbox().y, dir));

    }

    public void draw(Graphics g, int xLvlOffset) {
        drawFruits(g, xLvlOffset);
        drawContainers(g, xLvlOffset);
        drawTraps(g, xLvlOffset);
        drawCannons(g, xLvlOffset);
        drawProjectiles(g, xLvlOffset);
    }

    private void drawProjectiles(Graphics g, int xLvlOffset) {
        for (Projectile p : projectiles)
            if (p.isActive())
                g.drawImage(cannonBallImg, (int) (p.getHitbox().x - xLvlOffset), (int) (p.getHitbox().y), CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT, null);

    }

    private void drawCannons(Graphics g, int xLvlOffset) {
        for (Cannon c : cannons) {
            int x = (int) (c.getHitbox().x - xLvlOffset);
            int width = CANNON_WIDTH;

            if (c.getObjType() == CANNON_RIGHT) {
                x += width;
                width *= -1;
            }

            g.drawImage(cannonImgs[c.getAniIndex()],
                    x,
                    (int) (c.getHitbox().y),
                    width, CANNON_HEIGHT,
                    null);
        }

    }

    private void drawTraps(Graphics g, int xLvlOffset) {
        for (Saw s : saws) {
            g.drawImage(sawImg[s.getAniIndex()],
                    (int) (s.getHitbox().x - xLvlOffset),
                    (int) (s.getHitbox().y - s.getyDrawOffset()),
                    SAW_WIDTH,
                    SAW_HEIGHT,
                    null);
            s.drawHitbox(g,xLvlOffset);
        }


    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for (GameContainer gc : containers)
            if (gc.isActive()) {
                int type = 0;
                if (gc.getObjType() == BARREL)
                    type = 1;
                g.drawImage(containerImgs[type][gc.getAniIndex()],
                        (int) (gc.getHitbox().x - gc.getxDrawOffset() - xLvlOffset),
                        (int) (gc.getHitbox().y - gc.getyDrawOffset()) ,
                        CONTAINER_WIDTH,
                        CONTAINER_HEIGHT,
                        null);
                gc.drawHitbox(g,xLvlOffset);
            }
    }

    private void drawFruits(Graphics g, int xLvlOffset) {
        for (Fruit f : fruits)
            if (f.isActive()) {
                int type = 0;
                if (f.getObjType() == ANANAS)
                    type = 1;
                g.drawImage(fruitImgs[type][f.getAniIndex()],
                        (int) (f.getHitbox().x - f.getxDrawOffset() - xLvlOffset),
                        (int) (f.getHitbox().y - f.getyDrawOffset()),
                        FRUIT_WIDTH, FRUIT_HEIGHT,
                        null);
            }
    }

    public void resetAllObjects() {
        loadObjects(playing.getLevelManager().getCurrentLevel());
        for (Fruit f : fruits)
            f.reset();
        for (GameContainer gc : containers)
            gc.reset();
        for (Cannon c : cannons)
            c.reset();
    }
}
