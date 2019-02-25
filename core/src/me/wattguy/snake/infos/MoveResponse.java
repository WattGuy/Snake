package me.wattguy.snake.infos;

import me.wattguy.snake.enums.Reason;

public class MoveResponse {

    private Boolean moved;
    private Reason r;

    public MoveResponse(Boolean moved, Reason r){

        this.moved = moved;
        this.r = r;

    }

    public Boolean isMoved() {
        return moved;
    }

    public Reason getReason(){
        return r;
    }

}
