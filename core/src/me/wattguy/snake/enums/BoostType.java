package me.wattguy.snake.enums;

import com.badlogic.gdx.graphics.Texture;

import me.wattguy.snake.Main;

public enum BoostType {
    FLASH;

    public Texture get(){
        BoostType type = BoostType.values()[this.ordinal()];

        if (type == FLASH){

            return Main.flash;

        }

        return null;
    }

}
