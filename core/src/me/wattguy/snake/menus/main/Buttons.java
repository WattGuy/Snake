package me.wattguy.snake.menus.main;

import me.wattguy.snake.Info;
import me.wattguy.snake.Main;
import me.wattguy.snake.utils.Button;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.view.Game;
import me.wattguy.snake.view.Menu;
import me.wattguy.snake.view.Settings;
import me.wattguy.snake.view.Shop;

public class Buttons {

    public static Button START;
    public static Button SETTINGS;
    public static Button SHOP;

    public static void initialize(){

        START = new Button(Info.WP * 20, Info.HP * 44, Info.WP * 60, Info.HP * 10);
        START.setAction(new Runnable() {

            @Override
            public void run() {
                if (Main.game == null){

                    Main.game = new Game();

                }

                Main.buttonSound();

                Main.getInstance().setScreen(Main.game);
            }

        });

        SETTINGS = new Button(Info.WP * 20, Info.HP * 31, Info.WP * 60, Info.HP * 10);
        SETTINGS.setAction(new Runnable() {

            @Override
            public void run() {
                if (Main.settings == null){

                    Main.settings = new Settings();

                }

                Main.buttonSound();

                Main.getInstance().setScreen(Main.settings);
            }

        });

        SHOP = new Button(Info.WP * 20, Info.HP * 18, Info.WP * 60, Info.HP * 10);
        SHOP.setAction(new Runnable() {

            @Override
            public void run() {
                if (Main.shop == null){

                    Main.shop = new Shop();

                }

                Main.buttonSound();

                Main.getInstance().setScreen(Main.shop);
            }

        });

    }

    public static void draw(float delta){
        RoundedShapeRenderer srender = Menu.shapeRenderer;

        START.draw(delta, srender);
        SETTINGS.draw(delta, srender);
        SHOP.draw(delta, srender);

    }

    public static void dispose(){

    }

}
