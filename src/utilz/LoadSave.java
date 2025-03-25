package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadSave {

    //player
    public static final String PLAYER_ATLAS = "player_sprites.png";

    //atlas
   // public static final String LEVEL_ATLAS = "outside_sprites.png";

    //backgrounds
    public static final String MENU_BACKGROUND_IMG = "ui.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String OPTIONS_MENU = "options_background.png";
    //public static final String BACKGROUND_LEVEL1 = "playing_bg_img.png";
    public static final String BACKGROUND_LEVEL1 = "bg_lvl1.png";


    //enemies
    public static final String FLOWER_SPRITE = "flower_enemy_sprite.png";
    public static final String TURTLE_SPRITE = "turtle_enemy.png";
    public static final String BOSS_SPRITE = "mumie.png";


    //buttons
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";

    //health energy bar
    public static final String STATUS_BAR = "health_power_bar.png";

    //level status
    public static final String COMPLETED_IMG = "completed_sprite.png";
    public static final String DEATH_SCREEN = "death_screen.png";

    //objects
    public static final String FRUIT_ATLAS = "fructe.png";
    public static final String CONTAINER_ATLAS = "cufar24.png";
    public static final String TRAP_ATLAS = "saw.png";
    public static final String CANNON_ATLAS = "cannon_atlas.png";
    public static final String CANNON_BALL = "ball.png";


    //incarca imaginea cu tiluri-le specifice nivelului 1 din fisier si converteste datele din imagine intr-o matrice
    public static BufferedImage GetSpriteAtlas(String filename) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + filename);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static BufferedImage[] GetAllLevelsData() {
        URL url = LoadSave.class.getResource("/lvls/data");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] sortedFiles = new File[files.length];

//        System.out.println("??????????????");
//        System.out.println(files);
//        System.out.println("??????????????");

        for (int i = 0; i <= files.length - 1; i++) {
            for (int j = 0; j <= files.length - 1; j++) {
                if (files[j].getName().equals((i + 1) + ".png")) {
                    sortedFiles[i] = files[j];
                }
            }
        }
        BufferedImage []imgs=new BufferedImage[sortedFiles.length];
        for(int i=0;i<imgs.length;i++){
            try{
                imgs[i]=ImageIO.read(sortedFiles[i]);
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        return imgs;
    }


    public static BufferedImage[] GetAllLevelsBackground() {
        URL url = LoadSave.class.getResource("/lvls/bg");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] sortedFiles = new File[files.length];

//            System.out.println("??????????????");
//            System.out.println(files);
//            System.out.println("??????????????");

        for (int i = 0; i <= files.length - 1; i++) {
            for (int j = 0; j <= files.length - 1; j++) {
                if (files[j].getName().equals((i + 1) + ".png")) {
                    sortedFiles[i] = files[j];
                }
            }
        }BufferedImage []imgs=new BufferedImage[sortedFiles.length];
        for(int i=0;i<imgs.length;i++){
            try{
                imgs[i]=ImageIO.read(sortedFiles[i]);
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        return imgs;}
    public static BufferedImage[] GetAllLevelsAtlas () {
        URL url = LoadSave.class.getResource("/lvls/atlas");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] sortedFiles = new File[files.length];

//                System.out.println("??????????????");
//                System.out.println(files);
//                System.out.println("??????????????");

        for (int i = 0; i <= files.length - 1; i++) {
            for (int j = 0; j <= files.length - 1; j++) {
                if (files[j].getName().equals((i + 1) + ".png")) {
                    sortedFiles[i] = files[j];
                }
            }
        }

        BufferedImage[] images = new BufferedImage[files.length];

        for (int i = 0; i <= files.length - 1; i++) {
            try {
                images[i] = ImageIO.read(files[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images;
    }



}