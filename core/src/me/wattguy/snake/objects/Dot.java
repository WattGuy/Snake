package me.wattguy.snake.objects;

import com.badlogic.gdx.graphics.Color;

import me.wattguy.snake.Info;
import me.wattguy.snake.enums.Direction;
import me.wattguy.snake.utils.Pair;
import me.wattguy.snake.utils.Utils;
import me.wattguy.snake.view.Game;

public class Dot {

    private Integer crd_x;
    private Integer crd_y;

    private Float x;
    private Float y;

    private Game game = Game.getInstance();

    public Dot(Integer crd_x, Integer crd_y){

        this.crd_x = crd_x;
        this.crd_y = crd_y;

        Pair p = Utils.crdsToReal(crd_x, crd_y);

        this.x = (Float) p.first();
        this.y = (Float) p.second();

    }

    public Integer getXCoordinate(){
        return crd_x;
    }

    public Integer getYCoordinate(){
        return crd_y;
    }

    public Float getRealX(){
        return x;
    }

    public Float getRealY(){
        return y;
    }

    private void draw(Color c){

        game.srender.setColor(c);
        game.srender.roundedRect(x, y, Info.BLOCK_WIDTH, Info.BLOCK_HEIGHT, Info.BLOCK_WIDTH - 3);

    }

    public void drawTail(){
        Dot g = Game.s.dots.get(Game.s.dots.size() - 2);
        Direction r = Utils.relativity(this, g);

        game.srender.setColor(Color.WHITE);

        float ph = Info.BLOCK_HEIGHT / 100;
        float pw = Info.BLOCK_WIDTH / 100;

        float height1 = ph * 50;
        float width1 = pw * 50;

        float height2 = ph * 30;
        float width2 = pw * 30;

        if (r == Direction.RIGHT){

            game.srender.rect(x + (pw * 50), y + (ph * 25), width1, height1);
            game.srender.rect(x + (pw * 20), y + (ph * 35), width2, height2);

        }else if (r == Direction.LEFT){

            game.srender.rect(x, y + (ph * 25), width1, height1);
            game.srender.rect(x + (pw * 50), y + (ph * 35), width2, height2);

        }else if (r == Direction.UP){

            game.srender.rect(x + (pw * 25), y + (ph * 50), width1, height1);
            game.srender.rect(x + (pw * 35), y + (ph * 20), width2, height2);

        }else if (r == Direction.DOWN){

            game.srender.rect(x + (pw * 25), y, width1, height1);
            game.srender.rect(x + (pw * 35), y + (ph * 50), width2, height2);

        }

    }

    public void drawHead(){
        draw(Color.WHITE);
    }

    public void drawBody(int i){
        Direction r1 = Utils.relativity(this, Game.s.dots.get(i - 1));
        Direction r2 = Utils.relativity(this, Game.s.dots.get(i + 1));

        game.srender.setColor(Color.WHITE);

        float percent = Info.BLOCK_HEIGHT / 100;

        // -x-
        if (r1 == Direction.LEFT && r2 == Direction.RIGHT || r1 == Direction.RIGHT && r2 == Direction.LEFT){
            float height = percent * 80;

            game.srender.rect(x, y + (percent * 10), Info.BLOCK_WIDTH, height);

        }
        // |
        // x
        // |
        else if (r1 == Direction.DOWN && r2 == Direction.UP|| r1 == Direction.UP && r2 == Direction.DOWN){

            float width = percent * 80;

            game.srender.rect(x + (percent * 10), y, width, Info.BLOCK_HEIGHT);

        }
        // -x
        //  |
        else if (r1 == Direction.LEFT && r2 == Direction.DOWN || r1 == Direction.DOWN && r2 == Direction.LEFT){

            game.srender.arc(x, y, (Info.BLOCK_WIDTH / 100) * 90, 0, 90);

        }
        //  |
        // -x
        else if (r1 == Direction.UP && r2 == Direction.LEFT || r1 == Direction.LEFT && r2 == Direction.UP){

            game.srender.arc(x, y + Info.BLOCK_HEIGHT, (Info.BLOCK_WIDTH / 100) * 90, 270, 90);

        }
        // x-
        // |
        else if (r1 == Direction.RIGHT && r2 == Direction.DOWN || r1 == Direction.DOWN && r2 == Direction.RIGHT){

            game.srender.arc(x + Info.BLOCK_WIDTH, y, (Info.BLOCK_WIDTH / 100) * 90, 90, 90);

        }
        // |
        // x-
        else if (r1 == Direction.RIGHT && r2 == Direction.UP || r1 == Direction.UP && r2 == Direction.RIGHT){

            game.srender.arc(x + Info.BLOCK_WIDTH, y + Info.BLOCK_HEIGHT, (Info.BLOCK_WIDTH / 100) * 90, 180, 90);

        }

    }

    public void drawApple(){
        draw(Color.RED);
    }

    public void drawBrick(){
        draw(Color.BROWN);
    }

}