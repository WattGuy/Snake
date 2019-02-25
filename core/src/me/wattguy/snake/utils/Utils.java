package me.wattguy.snake.utils;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.wattguy.snake.Info;
import me.wattguy.snake.enums.Direction;
import me.wattguy.snake.objects.Dot;
import me.wattguy.snake.view.Game;

public class Utils {

    public static Pair crdsToReal(Integer x, Integer y){
        return new Pair(x * Info.BLOCK_WIDTH + Info.SPACE_LEFT, y * Info.BLOCK_HEIGHT + Info.SPACE_BOTTOM);
    }

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static Pair modify(Pair was, int x, int y){

        return new Pair((Float) was.first() + x, (Float) was.second() + y);

    }

    public static void border(Pair p1, Pair p2, float width, Direction d){
        float wm = width - 1;

        Game.getInstance().srender.rectLine((Float) p1.first() + wm, (Float) p1.second() + wm, (Float) p2.first() + wm, (Float) p2.second() + wm, width);

    }

    public static Direction relativity(Dot d, Dot g){
        int x1 = d.getXCoordinate();
        int y1 = d.getYCoordinate();

        int x2 = g.getXCoordinate();
        int y2 = g.getYCoordinate();

        if (x1 + 1 == x2){

            return Direction.RIGHT;

        }else if (x1 - 1 == x2){

            return Direction.LEFT;

        }else if (y1 + 1 == y2){

            return Direction.UP;

        }else /*if (y1 - 1 == y2)*/{

            return Direction.DOWN;

        }

    }

    public static Pair getPositionFromCenter(float cx, float cy, float sx, float sy){

        return new Pair(cx - (sx / 2), cy - (sy / 2));

    }

    public static Color toRGB(int r, int g, int b) {
        float RED = r / 255.0f;
        float GREEN = g / 255.0f;
        float BLUE = b / 255.0f;
        return new Color(RED, GREEN, BLUE, 1);
    }

    public static List<Dot> getWithout(){
        List<Dot> dots = new ArrayList<>(Game.dots.values());

        if (Game.s != null && Game.s.dots != null){

            for (Dot d : Game.s.dots){

                dots.remove(d);

            }

        }

        if (Game.a != null && Game.a.get() != null){

            dots.remove(Game.a.get());

        }

        if (Game.c != null && Game.c.get() != null){

            dots.remove(Game.c.get());

        }

        if (Game.bricks.size() > 0){

            for (Dot d : Game.bricks) {

                dots.remove(d);

            }

        }

        return dots;

    }

    public static Dot next(Dot d, Direction dir){

        try {
            int x = 0;
            int y = 0;

            if (dir == Direction.UP) {

                y = 1;

            } else if (dir == Direction.DOWN) {

                y = -1;

            } else if (dir == Direction.RIGHT) {

                x = 1;

            } else if (dir == Direction.LEFT) {

                x = -1;

            }

            Dot g = Game.dots.get(Game.get(d.getXCoordinate() + x, d.getYCoordinate() + y));

            if (g != null) {

                return g;

            } else return null;
        }catch(Exception ignored){}

        return null;

    }

}
