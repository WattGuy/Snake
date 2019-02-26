package me.wattguy.snake.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import me.wattguy.snake.Info;

public class Button {

    private Sprite region;

    private float x;
    private float y;

    private float width;
    private float height;

    private float time = 0f;
    private Boolean clicked = false;

    private Runnable r = null;
    public Button(float x, float y, float width, float height){

        this.region = new Sprite();
        region.setPosition(x, y);
        region.setSize(width, height);

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

    }

    public Boolean isClicked(float x, float y){

        if (region.getBoundingRectangle().contains(x, y)){

            clicked = true;
            return true;

        }else return false;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setAction(Runnable r){
        this.r = r;
    }

    public void draw(float delta, RoundedShapeRenderer srender){

        Color c;
        if (clicked) {
            time += delta;

            if (time >= Info.NEED) {
                time = 0f;
                clicked = false;

                r.run();

            }

            c = Color.GRAY;
        }else c = Color.WHITE;

        srender.setColor(c);
        srender.roundedRect(x, y, width, height, Info.ROUNDING);

    }

}
