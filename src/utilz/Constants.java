package utilz;

import main.Game;

public class Constants {

    public static final float GRAVITY = 0.04f * Game.SCALE;
    public static final int ANI_SPEED = 25;

    public static class Projectiles {
        public static final int CANNON_BALL_DEFAULT_WIDTH = 15;
        public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;

        public static final int CANNON_BALL_WIDTH = (int) (Game.SCALE * CANNON_BALL_DEFAULT_WIDTH);
        public static final int CANNON_BALL_HEIGHT = (int) (Game.SCALE * CANNON_BALL_DEFAULT_HEIGHT);
        public static final float SPEED = 0.75f * Game.SCALE;
    }

    public static class ObjectConstants {

        public static final int BANANA = 0;
        public static final int ANANAS = 1;

        public static final int BARREL = 2;
        public static final int BOX = 3;

        public static final int SAW = 4;

        public static final int CANNON_LEFT = 5;
        public static final int CANNON_RIGHT = 6;

        public static final int BANANA_VALUE = 15;
        public static final int ANANAS_VALUE = 25;

        public static final int CONTAINER_WIDTH_DEFAULT = 64;
        public static final int CONTAINER_HEIGHT_DEFAULT = 64;
        public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
        public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

        public static final int FRUIT_WIDTH_DEFAULT = 32;
        public static final int FRUIT_HEIGHT_DEFAULT = 32;
        public static final int FRUIT_WIDTH = (int) (1.75f * FRUIT_WIDTH_DEFAULT);
        public static final int FRUIT_HEIGHT = (int) (1.75f * FRUIT_HEIGHT_DEFAULT);

        public static final int SAW_WIDTH_DEFAULT = 38;
        public static final int SAW_HEIGHT_DEFAULT = 38;
        public static final int SAW_WIDTH = (int) (1.25f * SAW_WIDTH_DEFAULT);
        public static final int SAW_HEIGHT = (int) (1.25f * SAW_HEIGHT_DEFAULT);

        public static final int CANNON_WIDTH_DEFAULT = 40;
        public static final int CANNON_HEIGHT_DEFAULT = 26;
        public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
        public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);

        public static int GetSpriteAmount(int object_type) {
            switch (object_type) {
                case BANANA, ANANAS, SAW:
                    return 8;
                case BARREL, BOX:
                    return 7;
                case CANNON_LEFT, CANNON_RIGHT:
                    return 7;
            }
            return 1;
        }
    }

    public static class EnemyConstants {

        //enemies
        public static final int FLOWER = 0;
        public static final int TURTLE = 1;
        public static final int BOSS = 2;


        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;


        //flower
        public static final int FLOWER_WIDTH_DEFAULT = 38; //228/6
        public static final int FLOWER_HEIGHT_DEFAULT = 41; //205/5
        public static final int FLOWER_WIDTH = (int) (FLOWER_WIDTH_DEFAULT * Game.SCALE);
        public static final int FLOWER_HEIGHT = (int) (FLOWER_HEIGHT_DEFAULT * Game.SCALE);
        public static final int FLOWER_DRAW_OFFSET_X = (int) (12 * Game.SCALE);   //distnata de la cadrul tile ului pana la cadrul hitbox ului
        public static final int FLOWER_DRAW_OFFSET_Y = (int) (15 * Game.SCALE);

        //turtle
        public static final int TURTLE_WIDTH_DEFAULT = 48;
        public static final int TURTLE_HEIGHT_DEFAULT = 48;
        public static final int TURTLE_WIDTH = (int) ( TURTLE_WIDTH_DEFAULT  * 2);
        public static final int TURTLE_HEIGHT = (int) ( TURTLE_HEIGHT_DEFAULT  * 2);
        public static final int TURTLE_DRAW_OFFSET_X = (int) (11 * Game.SCALE);   //distnata de la cadrul tile ului pana la cadrul hitbox ului
        public static final int TURTLE_DRAW_OFFSET_Y = (int) (17 * Game.SCALE);

        //BOSS
        public static final int BOSS_WIDTH_DEFAULT = 48;
        public static final int BOSS_HEIGHT_DEFAULT = 48;
        public static final int BOSS_WIDTH = (int) ( BOSS_WIDTH_DEFAULT  * Game.SCALE);
        public static final int BOSS_HEIGHT = (int) ( BOSS_HEIGHT_DEFAULT  * Game.SCALE);
        public static final int BOSS_DRAW_OFFSET_X = (int) (26 * Game.SCALE);   //distnata de la cadrul tile ului pana la cadrul hitbox ului
        public static final int BOSS_DRAW_OFFSET_Y = (int) (15 * Game.SCALE);


        public static int GetSpriteAmount(int enemy_type, int enemy_state){

            switch (enemy_state){
                case IDLE:
                    return 4; //rand 4
                case RUNNING: {
                    if (enemy_type == FLOWER || enemy_type == BOSS)
                        return 4;
                    else if (enemy_type == TURTLE)
                        return 6;
                }
                case ATTACK:
                    return 6;

                case HIT: {
                    if (enemy_type == FLOWER)
                        return 4;
                    else if (enemy_type == TURTLE || enemy_type == BOSS)
                        return 2;
                    return 4;
                }

                case DEAD:
                    return 6;
            }

            return 0;

        }

        public static int GetMaxHealth( int enemy_type){
            switch (enemy_type){
                case FLOWER:
                    return 10;
                case TURTLE:
                    return 20;
                case BOSS:
                    return 60;
                default:
                    return 1;
            }
        }


        public static int GetEnemyDamage(int enemy_type){
            switch (enemy_type){
                case FLOWER:
                    return 15;
                case TURTLE:
                    return 20;
                case BOSS:
                    return 10;
                default:
                    return 0;
            }
        }
        // if the enemy's attack box overlaps the plyer's hit box
        // the enemy calls get enemy damage and this value is sent to the plyers change health

    }


    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }

        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

        }

        public static class VolumeButtons {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int ATTACK = 4;
        public static final int HIT = 5;
        public static final int DEAD = 6;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case DEAD:
                    return 8;
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case JUMP:
                case ATTACK:
                    return 3;
                case FALLING:
                default:
                    return 1;
            }
        }
    }

}