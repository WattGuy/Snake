package me.wattguy.snake.utils.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import me.wattguy.snake.Info;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.view.Menu;

public class MButtons {

    public static Sprite START;

    public static float start_x;
    public static float start_y;

    public static float start_width;
    public static float start_height;

    public static Sprite SETTINGS;

    public static float settings_x;
    public static float settings_y;

    public static float settings_width;
    public static float settings_height;

    public static void initialize(){

        START = new Sprite();

        start_x = Info.WP * 20;
        start_y = Info.HP * 39;

        START.setPosition(start_x, start_y);

        start_width = Info.WP * 60;
        start_height = Info.HP * 10;

        START.setSize(start_width, start_height);

        SETTINGS = new Sprite();

        settings_x = Info.WP * 20;
        settings_y = Info.HP * 26;

        SETTINGS.setPosition(settings_x, settings_y);

        settings_width = Info.WP * 60;
        settings_height = Info.HP * 10;

        SETTINGS.setSize(settings_width, settings_height);

    }

    public static void draw(){
        RoundedShapeRenderer srender = Menu.srender;

        if (Menu.GAME_STARTED) srender.setColor(Color.GRAY);
        else srender.setColor(Color.WHITE);
        srender.roundedRect(start_x, start_y, start_width, start_height, (float) (Info.HP * 1));

        if (Menu.SETTINGS) srender.setColor(Color.GRAY);
        else srender.setColor(Color.WHITE);
        srender.roundedRect(settings_x, settings_y, settings_width, settings_height, (float) (Info.HP * 1));

    }

    public static void dispose(){

    }

}
