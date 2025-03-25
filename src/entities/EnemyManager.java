package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.EnemyConstants.BOSS_DRAW_OFFSET_Y;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] flowerArr;
    private BufferedImage[][] turtleArr;
    private BufferedImage[][] bossArr;

    private ArrayList<Flower> flowers = new ArrayList<>();
    private ArrayList<Turtle> turtles = new ArrayList<>();
    private ArrayList<Boss> bosses = new ArrayList<>();



    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
    }

    public void loadEnemies(Level level) {
        flowers = level.getFlowers();
        turtles = level.getTurtles();
        bosses = level.getBoss();
    }

    public void update(int[][] lvlData, Player player) {
        boolean isAnyActive = false;
        for (Flower f : flowers)
            if (f.isActive()) {
                f.update(lvlData, player);
                isAnyActive = true;
            }

        for (Turtle t : turtles)
            if (t.isActive()) {
                t.update(lvlData, player);
                isAnyActive = true;
            }

        for (Boss b : bosses)
            if (b.isActive()) {
                b.update(lvlData, player);
                isAnyActive = true;
            }

        if (!isAnyActive)
            playing.setLevelCompleted(true);
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawFlowers(g, xLvlOffset);
        drawTurtles(g,xLvlOffset);
        drawBoss(g,xLvlOffset);
    }

    private void drawFlowers(Graphics g, int xLvlOffset) {
        for (Flower f : flowers)
            if (f.isActive()) {

                g.drawImage(flowerArr[f.getState()][f.getAniIndex()],
                        (int) f.getHitbox().x - xLvlOffset - FLOWER_DRAW_OFFSET_X + f.flipX(),
                        (int) f.getHitbox().y - FLOWER_DRAW_OFFSET_Y + 9,
                        FLOWER_WIDTH * f.flipW(),
                        FLOWER_HEIGHT, null);

//				f.drawHitbox(g, xLvlOffset);
//				f.drawAttackBox(g, xLvlOffset);
            }

    }

    private void drawTurtles(Graphics g, int xLvlOffset) {
        for (Turtle f : turtles)
            if (f.isActive()) {

                g.drawImage(turtleArr[f.getState()][f.getAniIndex()],
                        (int) f.getHitbox().x - xLvlOffset - TURTLE_DRAW_OFFSET_X + f.flipX(),
                        (int) f.getHitbox().y - TURTLE_DRAW_OFFSET_Y - 13,
                        TURTLE_WIDTH * f.flipW(),
                        TURTLE_HEIGHT, null);

				//f.drawHitbox(g, xLvlOffset);
				//f.drawAttackBox(g, xLvlOffset);
            }

    }

    private void drawBoss(Graphics g, int xLvlOffset) {
        for (Boss b : bosses)
            if (b.isActive()) {

                g.drawImage(bossArr[b.getState()][b.getAniIndex()],
                        (int) b.getHitbox().x - xLvlOffset - BOSS_DRAW_OFFSET_X + b.flipX(),
                        (int) b.getHitbox().y - BOSS_DRAW_OFFSET_Y  ,
                        BOSS_WIDTH * b.flipW(),
                        BOSS_HEIGHT, null);

                //b.drawHitbox(g, xLvlOffset);
				//b.drawAttackBox(g, xLvlOffset);
            }

    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Flower f : flowers)
            if (f.isActive())
                if (f.getCurrentHealth() > 0)
                    if (attackBox.intersects(f.getHitbox())) {
                        f.hurt(10);
                        return;
                    }

        for (Turtle t : turtles)
            if (t.isActive())
                if (t.getCurrentHealth() > 0)
                    if (attackBox.intersects(t.getHitbox())) {
                        t.hurt(15);
                        return;
                    }

        for (Boss b : bosses)
            if (b.isActive())
                if (b.getCurrentHealth() > 0)
                    if (attackBox.intersects(b.getHitbox())) {
                        b.hurt(5);
                        return;
                    }
    }

    private void loadEnemyImgs() {
        flowerArr = new BufferedImage[5][6];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.FLOWER_SPRITE);
        for (int j = 0; j < flowerArr.length; j++)
            for (int i = 0; i < flowerArr[j].length; i++)
                flowerArr[j][i] = temp.getSubimage(i * FLOWER_WIDTH_DEFAULT,
                        j * FLOWER_HEIGHT_DEFAULT,
                        FLOWER_WIDTH_DEFAULT,
                        FLOWER_HEIGHT_DEFAULT);

        turtleArr = new BufferedImage[5][6];
        BufferedImage turtleAtlas = LoadSave.GetSpriteAtlas(LoadSave.TURTLE_SPRITE);
        for (int j = 0; j < turtleArr.length; j++)
            for (int i = 0; i < turtleArr[j].length; i++)
                turtleArr[j][i] = turtleAtlas.getSubimage(i * TURTLE_WIDTH_DEFAULT,
                        j * TURTLE_HEIGHT_DEFAULT,
                        TURTLE_WIDTH_DEFAULT,
                        TURTLE_HEIGHT_DEFAULT);


        bossArr = new BufferedImage[5][6];
        BufferedImage bossAtlas = LoadSave.GetSpriteAtlas(LoadSave.BOSS_SPRITE);
        for (int j = 0; j < bossArr.length; j++)
            for (int i = 0; i < bossArr[j].length; i++)
                bossArr[j][i] = bossAtlas.getSubimage(i * BOSS_WIDTH_DEFAULT,
                        j * BOSS_HEIGHT_DEFAULT,
                        BOSS_WIDTH_DEFAULT,
                        BOSS_HEIGHT_DEFAULT);

    }


    public void resetAllEnemies() {
        for (Flower f : flowers)
            f.resetEnemy();

        for (Turtle t : turtles)
            t.resetEnemy();

        for (Boss b : bosses)
            b.resetEnemy();
    }

}
