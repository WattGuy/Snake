package me.wattguy.snake.menus.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

import me.wattguy.snake.Info;
import me.wattguy.snake.Main;
import me.wattguy.snake.enums.Direction;
import me.wattguy.snake.objects.Dot;
import me.wattguy.snake.objects.Snake;
import me.wattguy.snake.utils.Button;
import me.wattguy.snake.utils.Pair;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.utils.Utils;
import me.wattguy.snake.view.Game;

public class Buttons {
    
    public static Sprite LEFT;
    public static Sprite RIGHT;
    
    public static Button MENU;
    public static Button PAUSE;

    public static float abyss;

    public static void initialize(){
        
        LEFT = new Sprite();
        LEFT.setPosition(0, 0);
        LEFT.setSize(0, 0);
        
        RIGHT = new Sprite();
        RIGHT.setPosition(0, 0);
        RIGHT.setSize(0, 0);

        Pair fl = Utils.crdsToReal(0, Info.HEIGHT_SIZE - 1);
        float width = (Info.WP * 35) + Info.WFRAME;
        abyss = Gdx.graphics.getWidth() - (width * 2);
        
        float minus_y = Info.SPACE_TOP / 8;
        
        MENU = new Button(((float) fl.first()), ((float) fl.second()) + Info.BLOCK_HEIGHT + minus_y, width - Info.WFRAME, Info.SPACE_TOP - (minus_y * 2));
        MENU.setAction(new Runnable() {

            @Override
            public void run() {
                Main.buttonSound();
                Main.getInstance().setScreen(Main.menu);
            }

        });
        
        PAUSE = new Button(MENU.getX() + MENU.getWidth() + abyss, MENU.getY(), width - Info.WFRAME, MENU.getHeight());
        PAUSE.setAction(new Runnable() {

            @Override
            public void run() {
                Main.buttonSound();
                Game.PAUSED = true;
            }

        });

    }

    public static void rectangleUpdate(Snake s){
        Dot head = s.dots.get(0);

        if (s.d == Direction.RIGHT || s.d == Direction.LEFT){

            float x = 0;
            float firsty = (float) (head.getRealY() + (Info.BLOCK_HEIGHT / 2)) + 1;

            float firsth = Gdx.graphics.getHeight() - firsty;
            
            if (s.d == Direction.RIGHT){

                LEFT.setPosition(x, firsty);
                LEFT.setSize(Gdx.graphics.getWidth(), firsth);

                RIGHT.setPosition(x, 0);
                RIGHT.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - firsth - 1);
                
            }else{

                RIGHT.setPosition(x, firsty);
                RIGHT.setSize(Gdx.graphics.getWidth(), firsth);

                LEFT.setPosition(x, 0);
                LEFT.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - firsth - 1);
                
            }

        }else if (s.d == Direction.UP || s.d == Direction.DOWN){

            float y = 0;
            float secondx = (float) (head.getRealX() + (Info.BLOCK_WIDTH / 2)) + 1;

            float secondw = Gdx.graphics.getWidth() - secondx;

            if (s.d == Direction.UP){

                RIGHT.setPosition(secondx, y);
                RIGHT.setSize(secondw, Gdx.graphics.getHeight());

                LEFT.setPosition(0, y);
                LEFT.setSize(Gdx.graphics.getWidth() - secondw, Gdx.graphics.getHeight());

            }else{

                LEFT.setPosition(secondx, y);
                LEFT.setSize(secondw, Gdx.graphics.getHeight());

                RIGHT.setPosition(0, y);
                RIGHT.setSize(Gdx.graphics.getWidth() - secondw, Gdx.graphics.getHeight());

            }

        }

    }

    private static void keys(){

        try {

            if (!Game.DIED && !Game.PAUSED && !Game.WON) {

                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

                    Game.s.setDirection(Direction.UP);

                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

                    Game.s.setDirection(Direction.DOWN);

                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

                    Game.s.setDirection(Direction.LEFT);

                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

                    Game.s.setDirection(Direction.RIGHT);

                }

            } else if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {

                if (Game.WON || Game.DIED) {

                    Game.WON = false;
                    Game.DIED = false;

                    Game.getInstance().renewGame();

                } else {

                    Game.PAUSED = false;

                }

            }
        }catch(Exception ignored){}

    }

    public static void draw(float delta){
        RoundedShapeRenderer srender = Game.getInstance().shapeRenderer;

        keys();

        MENU.draw(delta, srender);
        PAUSE.draw(delta, srender);

    }

    public static void dispose(){
    }

}
