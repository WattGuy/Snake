package me.wattguy.snake.utils.shop;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.wattguy.snake.Info;
import me.wattguy.snake.Main;
import me.wattguy.snake.utils.Button;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.view.Menu;
import me.wattguy.snake.view.Shop;

public class Buttons {

    public static Button EXIT;

    public static void initialize(){

        EXIT = new Button(Info.WP * 20, Info.HP * 5, Info.WP * 60, Info.HP * 10);
        EXIT.setAction(new Runnable() {

            @Override
            public void run() {
                if (Main.menu == null){

                    Main.menu = new Menu();

                }

                Main.buttonSound();

                Main.getInstance().setScreen(Main.menu);
            }

        });

    }

    public static void batchdraw(){
        SpriteBatch batch = Shop.batch;

    }

    public static void draw(float delta){
        RoundedShapeRenderer srender = Shop.srender;

        EXIT.draw(delta, srender);

    }

    public static void dispose(){

    }

}
