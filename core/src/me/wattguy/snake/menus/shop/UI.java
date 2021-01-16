package me.wattguy.snake.menus.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import me.wattguy.snake.Info;

public class UI {

    private Stage stage;
    private Skin skin;
    private Skin skinblack;
    private Label main;

    public static final float LABEL_SIZE = 0.10f;

    private ScreenViewport swp;

    public UI(){

        skin = new Skin(Gdx.files.internal("fonts/white.json"));
        skinblack = new Skin(Gdx.files.internal("fonts/black.json"));

        swp = new ScreenViewport();

        stage = new Stage(swp);

        main = new Label("магазин", skin.get("default", Label.LabelStyle.class));
        main.setAlignment(Align.center);
        main.setPosition(Info.WP * 50, Info.HP * 80, Align.center);
        main.setFontScale((float) (Info.HP * LABEL_SIZE));
        stage.addActor(main);


    }

    public void update(){



    }

    public void draw(){
        update();

        stage.getViewport().apply();
        stage.act();
        stage.draw();

    }

    public void dispose(){
        stage.dispose();
    }

}
