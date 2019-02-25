package me.wattguy.snake.utils.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import me.wattguy.snake.Info;
import me.wattguy.snake.enums.Direction;
import me.wattguy.snake.objects.Dot;
import me.wattguy.snake.objects.Snake;
import me.wattguy.snake.utils.Pair;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.utils.Utils;
import me.wattguy.snake.view.Game;

public class GButtons {

    public static Texture play;

    public static Sprite LEFT;
    public static Sprite RIGHT;
    public static Sprite MENU;

    public static float menu_x;
    public static float menu_y;

    public static float menu_width;
    public static float menu_height;

    public static Sprite PAUSE;
    
    public static float pause_x;
    public static float pause_y;

    public static float pause_width;
    public static float pause_height;

    public static float abyss;

    public static void initialize(){

        play = new Texture(Gdx.files.internal("sprites/play.png"));

        LEFT = new Sprite();
        LEFT.setSize(0, 0);
        LEFT.setPosition(0, 0);

        RIGHT = new Sprite();
        RIGHT.setSize(0, 0);
        RIGHT.setPosition(0, 0);

        MENU = new Sprite();

        Pair fl = Utils.crdsToReal(0, Info.HEIGHT_SIZE - 1);
        float width = (Info.WP * 35) + Info.WFRAME;
        abyss = Gdx.graphics.getWidth() - (width * 2);

        menu_x = ((float) fl.first());
        float minus_y = Info.SPACE_TOP / 8;
        menu_y = ((float) fl.second()) + Info.BLOCK_HEIGHT + minus_y;

        MENU.setPosition(menu_x, menu_y);

        menu_width = width - Info.WFRAME;
        menu_height = Info.SPACE_TOP - (minus_y * 2);

        MENU.setSize(menu_width, menu_height);
        
        PAUSE = new Sprite();

        pause_x = menu_x + menu_width + abyss;
        pause_y = menu_y;

        PAUSE.setPosition(pause_x, pause_y);

        pause_width = width - Info.WFRAME;
        pause_height = menu_height;

        PAUSE.setSize(pause_width, pause_height);

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

    public static void draw(){
        RoundedShapeRenderer srender = Game.getInstance().srender;

        keys();

        srender.setColor(Color.WHITE);
        srender.roundedRect(menu_x, menu_y, menu_width, menu_height, (float) (Info.HP * 1));
        srender.roundedRect(pause_x, pause_y, pause_width, pause_height, (float) (Info.HP * 1));

    }

    public static void dispose(){
        play.dispose();
    }

}
