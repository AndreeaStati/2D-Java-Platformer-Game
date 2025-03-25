package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Boss;
import entities.Flower;
import entities.Turtle;
import main.Game;
import objects.Cannon;
import objects.GameContainer;
import objects.Fruit;
import objects.Saw;
import utilz.HelpMethods;

import static utilz.HelpMethods.*;

public class Level {

    private BufferedImage imgData, imgBackground, imgAtlas;
    private int[][] lvlData;
    private BufferedImage[] lvlAtlas;

    private ArrayList<Flower> flowers;
    private ArrayList<Turtle> turtles;
    private ArrayList<Boss> boss;


    private ArrayList<Fruit> fruits;
    private ArrayList<Saw> saws;
    private ArrayList<GameContainer> containers;
    private ArrayList<Cannon> cannons;

    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    private Point playerSpawn;

    private static int lvlIndex=0;

    public Level(BufferedImage imgData, BufferedImage imgBackground, BufferedImage imgAtlas ) {
        this.imgData = imgData;
        this.imgBackground = imgBackground;
        this.imgAtlas = imgAtlas;

        if (lvlIndex == 0 || lvlIndex ==1 || lvlIndex==2)
            importOutsideSprites7x7();


        createLevelData();

        createEnemies();

        createFruits();
        createContainers();
        createSaws();
        createCannons();

        calcLvlOffsets();
        calcPlayerSpawn();

        lvlIndex++;
    }

    private void importOutsideSprites7x7() {
        lvlAtlas = new BufferedImage[49];
        for (int j = 0; j < 7; j++)
            for (int i = 0; i < 7; i++) {
                int index = j * 7 + i;
                lvlAtlas[index] = imgAtlas.getSubimage(i * 64, j * 64, 64, 64);
            }
    }

    private void createCannons() {
        cannons = HelpMethods.GetCannons(imgData);
    }

    private void createSaws() {
        saws = HelpMethods.GetSaws(imgData);
    }

    private void createContainers() {
        containers = HelpMethods.GetContainers(imgData);
    }

    private void createFruits() {
        fruits = HelpMethods.GetFruits(imgData);
    }

    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(imgData);
    }

    private void calcLvlOffsets() {
        lvlTilesWide = imgData.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
    }

    private void createEnemies() {
        flowers = GetFlowers(imgData);
        turtles = GetTurtles(imgData);
        boss = GetBoss(imgData);
    }

    private void createLevelData() {
        lvlData = GetLevelData(imgData);
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return lvlData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    public ArrayList<Flower> getFlowers() {
        return flowers;
    }

    public ArrayList<Turtle> getTurtles() {
        return turtles;
    }

    public ArrayList<Boss> getBoss(){ return boss; }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public ArrayList<Fruit> getPotions() {
        return fruits;
    }

    public ArrayList<GameContainer> getContainers() {
        return containers;
    }

    public ArrayList<Saw> getSaws() {
        return saws;
    }

    public ArrayList<Cannon> getCannons(){
        return cannons;
    }

    public BufferedImage getImgBackground(){
        return imgBackground;
    }

    public BufferedImage[] getLvlAtlas(){
        return lvlAtlas;
    }
}
