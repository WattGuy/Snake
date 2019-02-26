package me.wattguy.snake;

public class Info {

    public static int WIDTH_SIZE = 15;
    public static int HEIGHT_SIZE = 15;

    public static float WP;
    public static float HP;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static float NEED = 0.025f;

    public static float ROUNDING;

    public static float TOP_SPACING = 0;
    public static float BOTTOM_SPACING = 0;

    public static float HFRAME;
    public static float WFRAME;

    public static float SPACE_TOP;
    public static float SPACE_BOTTOM;
    public static float SPACE_LEFT;
    public static float SPACE_RIGHT;

    public static float BLOCK_WIDTH;
    public static float BLOCK_HEIGHT;

    public static void resize(int width, int height){

        WIDTH_SIZE = 15;
        HEIGHT_SIZE = 15;

        SCREEN_WIDTH = width;
        SCREEN_HEIGHT = height;

        WP = (float) width / 100;
        HP = (float) height / 100;

        ROUNDING = HP * 1f;

        WFRAME = WP * 2;
        HFRAME = WFRAME;

        TOP_SPACING = (float) height / 10;

        SPACE_TOP = (float) (SCREEN_HEIGHT - (SCREEN_WIDTH - HFRAME)) / 2 + HFRAME;
        SPACE_BOTTOM = (float) (SCREEN_HEIGHT - (SCREEN_WIDTH - HFRAME)) / 2 + HFRAME;
        SPACE_LEFT = WFRAME;
        SPACE_RIGHT = WFRAME;

        BLOCK_WIDTH = (((SCREEN_WIDTH - SPACE_LEFT) - SPACE_RIGHT) / WIDTH_SIZE);
        BLOCK_HEIGHT = (((SCREEN_HEIGHT - SPACE_TOP) - SPACE_BOTTOM) / HEIGHT_SIZE);

        while(SPACE_TOP > BLOCK_HEIGHT + TOP_SPACING){

            SPACE_TOP -= BLOCK_HEIGHT;
            HEIGHT_SIZE++;

        }

        while(SPACE_BOTTOM > BLOCK_HEIGHT + BOTTOM_SPACING){

            SPACE_BOTTOM -= BLOCK_HEIGHT;
            HEIGHT_SIZE++;

        }

    }

}
