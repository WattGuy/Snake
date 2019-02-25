package me.wattguy.snake.objects;

import java.util.List;

import me.wattguy.snake.utils.Utils;

public class Apple {

    private Dot d;
    public Boolean golden = false;

    public Apple(){

        random();

    }

    public Dot get() {
        return d;
    }

    public Boolean random(){
        List<Dot> dots = Utils.getWithout();

        if (dots.size() != 0){

            golden = Utils.randInt(1, 100) >= 95;

            d = dots.get(Utils.randInt(0, dots.size() - 1));
            return true;

        }else return false;

    }

    public void draw(float delta){

        d.drawApple(golden);

    }

}
