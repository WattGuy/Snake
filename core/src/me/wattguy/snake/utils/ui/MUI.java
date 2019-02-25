package me.wattguy.snake.utils.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import me.wattguy.snake.Config;
import me.wattguy.snake.Info;
import me.wattguy.snake.utils.buttons.MButtons;

public class MUI {
    
    private Stage stage;
    private Skin skin;
    private Skin skinblack;
    private Label main;
    private Label record;
    private Label coins;
    private Label start;
    private Label settings;

    public static final float SNAKE_SIZE = 0.18f;
    public static final float RECORD_SIZE = 0.03f;
    public static final float START_SIZE = 0.06f;
    public static final float EXIT_SIZE = 0.06f;
    
    private ScreenViewport swp;

    public MUI(){

        skin = new Skin(Gdx.files.internal("fonts/white.json"));
        skinblack = new Skin(Gdx.files.internal("fonts/black.json"));

        swp = new ScreenViewport();

        stage = new Stage(swp);

        main = new Label("snake", skin.get("default", Label.LabelStyle.class));
        main.setAlignment(Align.center);
        main.setPosition(Info.WP * 50, Info.HP * 80, Align.center);
        main.setFontScale((float) (Info.HP * SNAKE_SIZE));
        stage.addActor(main);

        record = new Label("рекорд " + Config.RECORD, skin.get("default", Label.LabelStyle.class));
        record.setAlignment(Align.center);
        record.setPosition(Info.WP * 50, Info.HP * 72, Align.center);
        record.setFontScale((float) (Info.HP * RECORD_SIZE));
        stage.addActor(record);

        coins = new Label("монеты " + Config.COINS, skin.get("default", Label.LabelStyle.class));
        coins.setAlignment(Align.center);
        coins.setPosition(Info.WP * 50, Info.HP * 69, Align.center);
        coins.setFontScale((float) (Info.HP * RECORD_SIZE));
        stage.addActor(coins);

        start = new Label("играть", skinblack.get("default", Label.LabelStyle.class));
        start.setAlignment(Align.center);
        start.setPosition(MButtons.start_x + (MButtons.start_width / 2), MButtons.start_y + (MButtons.start_height / 2), Align.center);
        start.setFontScale((float) (Info.HP * START_SIZE));
        stage.addActor(start);

        settings = new Label("настройки", skinblack.get("default", Label.LabelStyle.class));
        settings.setAlignment(Align.center);
        settings.setPosition(MButtons.settings_x + (MButtons.settings_width / 2), MButtons.settings_y + (MButtons.settings_height / 2), Align.center);
        settings.setFontScale((float) (Info.HP * EXIT_SIZE));
        stage.addActor(settings);
    }

    public void update(){

        record.setText("рекорд " + Config.RECORD);
        coins.setText("монеты " + Config.COINS);

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
