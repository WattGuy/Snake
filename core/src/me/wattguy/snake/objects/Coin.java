package me.wattguy.snake.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import me.wattguy.snake.Info;
import me.wattguy.snake.utils.Pair;
import me.wattguy.snake.utils.Utils;
import me.wattguy.snake.view.Game;

public class Coin {

    private Dot d;
    private Integer score;
    private float mnoz = 0f;
    private float time = 1f;
    private Boolean creating = true;
    private Boolean was = false;

    public Coin(){

        set();

    }

    public Dot get() {
        return d;
    }

    public void set(){
        d = null;
        score = Game.s.dots.size() + Utils.randInt(2, 5);
    }

    public void check(){

        if (Game.s.dots.size() == score){

            random();

        }

    }

    public void random(){
        List<Dot> dots = Utils.getWithout();

        if (dots.size() != 0){

            creating = true;
            mnoz = 0f;
            d = dots.get(Utils.randInt(0, dots.size() - 1));

        }

    }

    public void draw(float delta){

        if (d != null){
            time += delta;

            SpriteBatch batch = Game.getInstance().batch;

            if (creating){

                if (time >= 0.001f){

                    mnoz += 0.0225f;

                }

                if (mnoz >= 1f){

                    creating = false;

                }

                Sprite s = new Sprite(Game.coin);

                s.setSize(Info.BLOCK_WIDTH * mnoz,  Info.BLOCK_HEIGHT * mnoz);

                Pair p = Utils.getPositionFromCenter(d.getRealX() + (Info.BLOCK_WIDTH / 2), d.getRealY() + (Info.BLOCK_HEIGHT / 2), s.getWidth(), s.getHeight());

                s.setPosition((Float) p.first(), (Float) p.second());

                s.draw(batch);

                return;
            }

            Sprite s = new Sprite(Game.coin);

            s.setSize(Info.BLOCK_WIDTH * mnoz,  Info.BLOCK_HEIGHT * mnoz);

            Pair p = Utils.getPositionFromCenter(d.getRealX() + (Info.BLOCK_WIDTH / 2), d.getRealY() + (Info.BLOCK_HEIGHT / 2), s.getWidth(), s.getHeight());

            s.setPosition((Float) p.first(), (Float) p.second());

            s.draw(batch);

            if (time < 0.05f) return;
            time = 0f;
            if (!was) mnoz += 0.01f;
            else mnoz -= 0.01f;

            if (mnoz >= 1.1f){

                was = true;

            }else if (mnoz <= 1f && was){

                was = false;

            }

        }else{
            mnoz = 1f;
            time = 0f;
        }

    }

}
