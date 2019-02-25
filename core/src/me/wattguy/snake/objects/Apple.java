package me.wattguy.snake.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

import me.wattguy.snake.Info;
import me.wattguy.snake.utils.Pair;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.utils.Utils;
import me.wattguy.snake.view.Game;

public class Apple {

    private Dot d;
    private Boolean creating = true;
    private float mnoz = 0f;
    private float time = 0f;

    public Apple(){

        random();

    }

    public Dot get() {
        return d;
    }

    public Boolean random(){
        List<Dot> dots = Utils.getWithout();

        if (dots.size() != 0){

            creating = true;
            mnoz = 0f;
            d = dots.get(Utils.randInt(0, dots.size() - 1));
            return true;

        }else return false;

    }

    public void draw(float delta){
        /*time += delta;

        if (creating) {
            if (time >= 0.001f) {

                mnoz += 0.0270f;

            }

            if (mnoz >= 1f) {

                creating = false;

            }
        }

        RoundedShapeRenderer srender = Game.getInstance().srender;

        srender.setColor(Color.RED);

        float sizex = Info.BLOCK_WIDTH * mnoz;
        float sizey = Info.BLOCK_HEIGHT * mnoz;

        //System.out.println("before: " + d.getRealX() + " : " + d.getRealY());
        //Pair p = Utils.getPositionFromCenter(d.getRealX() + ((float) Info.WIDTH_SIZE / 2), d.getRealY()  + ((float) Info.HEIGHT_SIZE / 2), Info.BLOCK_WIDTH, Info.BLOCK_HEIGHT);
        //System.out.println("after: " + p.first() + " : " + p.second());*/
        d.drawApple();

    }

}
