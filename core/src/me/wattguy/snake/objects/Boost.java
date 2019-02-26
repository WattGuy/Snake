package me.wattguy.snake.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import me.wattguy.snake.Info;
import me.wattguy.snake.Main;
import me.wattguy.snake.enums.BoostType;
import me.wattguy.snake.utils.Pair;
import me.wattguy.snake.utils.Utils;
import me.wattguy.snake.view.Game;

public class Boost {

    private Dot d;
    private Integer score;
    private Integer dscore = null;
    private float mnoz = 0f;
    private float time = 1f;
    public BoostType type;
    private Texture texture;
    private Boolean creating = true;
    private Boolean destroying = false;
    private Boolean was = false;

    public Boost(){

        set();

    }

    public Dot get() {
        return d;
    }

    public void set(){
        d = null;

        type = BoostType.values()[Utils.randInt(0, BoostType.values().length - 1)];
        texture = type.get();
        score = Game.s.dots.size() + Utils.randInt(2, 5);

    }

    public void check(){

        if (Game.s.dots.size() >= score && d == null){

            Utils.async(new Runnable() {

                @Override
                public void run() {
                    random();
                }

            });

        }else if (d != null && dscore != null && Game.s.dots.size() == dscore){

            destroying = true;

        }

    }

    public void random(){
        List<Dot> dots = Utils.getWithout();

        if (dots.size() != 0){

            creating = true;
            dscore = Game.s.dots.size() + Utils.randInt(1, 3);
            mnoz = 0f;
            d = dots.get(Utils.randInt(0, dots.size() - 1));

        }

    }

    public void draw(float delta){

        if (d != null){
            time += delta;

            SpriteBatch batch = Game.getInstance().batch;

            Sprite s = new Sprite(texture);
            s.setSize(Info.BLOCK_WIDTH * mnoz,  Info.BLOCK_HEIGHT * mnoz);
            Pair p = Utils.getPositionFromCenter(d.getRealX() + (Info.BLOCK_WIDTH / 2), d.getRealY() + (Info.BLOCK_HEIGHT / 2), s.getWidth(), s.getHeight());
            s.setPosition((Float) p.first(), (Float) p.second());

            if (creating){

                if (time >= 0.001f){

                    mnoz += 0.0225f;

                }

                if (mnoz >= 0.9f){

                    creating = false;

                }

                s.draw(batch);

                return;
            }else if (destroying){

                if (time >= 0.001f){

                    mnoz -= 0.0225f;

                }

                if (mnoz <= 0f){

                    destroying = false;
                    d = null;
                    set();

                }

                s.draw(batch);

                return;

            }

            s.draw(batch);

            if (time < 0.05f) return;
            time = 0f;
            if (!was) mnoz += 0.01f;
            else mnoz -= 0.01f;

            if (mnoz >= 1.0f){

                was = true;

            }else if (mnoz <= 0.9f && was){

                was = false;

            }

        }else{
            mnoz = 1f;
            time = 0f;
        }

    }

}