package me.wattguy.snake.menus.settings;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.wattguy.snake.Info;
import me.wattguy.snake.Main;
import me.wattguy.snake.utils.Button;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.view.Menu;
import me.wattguy.snake.view.Settings;

public class Buttons {

    public static Button EXIT;

    public static Sprite CM;

    public static float cm_x;
    public static float cm_y;

    public static float cm_width;
    public static float cm_height;

    public static Sprite CB;

    public static float cb_x;
    public static float cb_y;

    public static float cb_width;
    public static float cb_height;

    public static Sprite SM;

    public static float sm_x;
    public static float sm_y;

    public static float sm_width;
    public static float sm_height;

    public static Sprite SB;

    public static float sb_x;
    public static float sb_y;

    public static float sb_width;
    public static float sb_height;


    public static void initialize(){

        EXIT = new Button(Info.WP * 20, Info.HP * 20, Info.WP * 60, Info.HP * 10);
        EXIT.setAction(new Runnable() {
            @Override
            public void run() {
                if (Main.menu == null) {

                    Main.menu = new Menu();

                }

                Main.buttonSound();

                Main.getInstance().setScreen(Main.menu);
            }
        });
        
        CM = new Sprite();

        cm_x = Info.WP * 3f;
        cm_y = Info.HP * 54.5f;

        CM.setPosition(cm_x, cm_y);

        cm_width = Info.WP * 15;
        cm_height = Info.HP * 15;

        CM.setSize(cm_width, cm_height);

        CB = new Sprite();

        cb_x = Info.WP * 82f;
        cb_y = Info.HP * 54.5f;

        CB.setPosition(cb_x, cb_y);

        cb_width = Info.WP * 15;
        cb_height = Info.HP * 15;

        CB.setSize(cb_width, cb_height);

        SM = new Sprite();

        sm_x = Info.WP * 3f;
        sm_y = Info.HP * 34.5f;

        SM.setPosition(sm_x, sm_y);

        sm_width = Info.WP * 15;
        sm_height = Info.HP * 15;

        SM.setSize(sm_width, sm_height);

        SB = new Sprite();

        sb_x = Info.WP * 82f;
        sb_y = Info.HP * 34.5f;

        SB.setPosition(sb_x, sb_y);

        sb_width = Info.WP * 15;
        sb_height = Info.HP * 15;

        SB.setSize(sb_width, sb_height);

    }

    public static void batchdraw(){
        SpriteBatch batch = Settings.batch;

    }

    public static void draw(float delta){
        RoundedShapeRenderer srender = Settings.srender;

        EXIT.draw(delta, srender);

    }

    public static void dispose(){

    }

}
