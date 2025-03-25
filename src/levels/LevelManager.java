package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

import static db.DbConnection.insertB;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private BufferedImage background;
    private ArrayList<Level> levels;
    private int lvlIndex = 0;
    private Playing playing;

    public LevelManager(Game game) {
        this.game = game;
        levels = new ArrayList<>();
        buildAllLevels();
    }

    public void loadNextLevel() {
        lvlIndex++;
//        insertB("dataBase", "DataTable", lvlIndex, playing.getPlayer().getCurrentHealth(), playing.getPlayer().getAniTick());
        if (lvlIndex >= levels.size()) {
            lvlIndex = 0;
            Gamestate.state = Gamestate.MENU;
        }

        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    private void buildAllLevels() {
        BufferedImage[] allLevelsData = LoadSave.GetAllLevelsData();
        BufferedImage[] allLevelsBackground = LoadSave.GetAllLevelsBackground();
        BufferedImage[] allLevelsAtlas = LoadSave.GetAllLevelsAtlas();

        for (int i=0; i<3; i++) {
            Level level = new Level(allLevelsData[i], allLevelsBackground[i], allLevelsAtlas[i]);
            levels.add(level);
        }
    }

//    private void importOutsideSprites() {
//        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
//        levelSprite = new BufferedImage[48];
//        for (int j = 0; j < 4; j++)
//            for (int i = 0; i < 12; i++) {
//                int index = j * 12 + i;
//                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
//            }
//    }

    public void draw(Graphics g, int lvlOffset) {

        g.drawImage(levels.get(lvlIndex).getImgBackground(), 0, 0, 1248, 672, null);

        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < levels.get(lvlIndex).getLevelData()[0].length; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levels.get(lvlIndex).getLvlAtlas()[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levels.get(lvlIndex);
    }

    public int getAmountOfLevels() {
        return levels.size();
    }

    public int getLvlIndex(){
        return lvlIndex;
    }

}
