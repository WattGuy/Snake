package me.wattguy.snake.menus.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import me.wattguy.snake.Config;
import me.wattguy.snake.Info;
import me.wattguy.snake.enums.Control;

public class UI {

    private Stage stage;
    private Skin skin;
    private Skin skinblack;
    private Label main;
    private Label control;
    private Label sound;
    private Label onoff;
    private Label swipes;
    private Label controlm;
    private Label soundm;
    private Label controlb;
    private Label soundb;
    private Label rel1;
    private Label rel2;
    private Label exit;

    public static final float LABEL_SIZE = 0.10f;
    public static final float CONTROL_SIZE = 0.06f;
    public static final float CONTROLM_SIZE = 0.13f;
    public static final float CONTROLB_SIZE = 0.13f;
    public static final float SWIPES_SIZE = 0.05f;
    public static final float REL1_SIZE = 0.03f;
    public static final float REL2_SIZE = 0.03f;
    public static final float EXIT_SIZE = 0.06f;

    private ScreenViewport swp;

    public UI(){

        skin = new Skin(Gdx.files.internal("fonts/white.json"));
        skinblack = new Skin(Gdx.files.internal("fonts/black.json"));

        swp = new ScreenViewport();

        stage = new Stage(swp);

        main = new Label("настройки", skin.get("default", Label.LabelStyle.class));
        main.setAlignment(Align.center);
        main.setPosition(Info.WP * 50, Info.HP * 80, Align.center);
        main.setFontScale((float) (Info.HP * LABEL_SIZE));
        stage.addActor(main);

        control = new Label("управление", skin.get("default", Label.LabelStyle.class));
        control.setAlignment(Align.center);
        control.setPosition(Info.WP * 50f, Info.HP * 72.5f, Align.center);
        control.setFontScale((float) (Info.HP * CONTROL_SIZE));
        stage.addActor(control);

        swipes = new Label("", skin.get("default", Label.LabelStyle.class));
        swipes.setAlignment(Align.center);
        swipes.setPosition(Info.WP * 50f, Info.HP * 62.5f, Align.center);
        swipes.setFontScale((float) (Info.HP * SWIPES_SIZE));
        stage.addActor(swipes);

        rel1 = new Label("", skin.get("default", Label.LabelStyle.class));
        rel1.setAlignment(Align.center);
        rel1.setPosition(Info.WP * 50f, Info.HP * 64f, Align.center);
        rel1.setFontScale((float) (Info.HP * REL1_SIZE));
        stage.addActor(rel1);

        rel2 = new Label("", skin.get("default", Label.LabelStyle.class));
        rel2.setAlignment(Align.center);
        rel2.setPosition(Info.WP * 50f, Info.HP * 61f, Align.center);
        rel2.setFontScale((float) (Info.HP * REL2_SIZE));
        stage.addActor(rel2);

        controlm = new Label("", skin.get("default", Label.LabelStyle.class));
        controlm.setAlignment(Align.center);
        controlm.setPosition(Info.WP * 10f, Info.HP * 62.5f, Align.center);
        controlm.setFontScale((float) (Info.HP * CONTROLM_SIZE));
        stage.addActor(controlm);

        controlb = new Label("", skin.get("default", Label.LabelStyle.class));
        controlb.setAlignment(Align.center);
        controlb.setPosition(Info.WP * 90f, Info.HP * 62.5f, Align.center);
        controlb.setFontScale((float) (Info.HP * CONTROLB_SIZE));
        stage.addActor(controlb);

        onoff = new Label("выкл", skin.get("default", Label.LabelStyle.class));
        onoff.setAlignment(Align.center);
        onoff.setPosition(Info.WP * 50f, Info.HP * 42.5f, Align.center);
        onoff.setFontScale((float) (Info.HP * SWIPES_SIZE));
        stage.addActor(onoff);

        sound = new Label("звуки", skin.get("default", Label.LabelStyle.class));
        sound.setAlignment(Align.center);
        sound.setPosition(Info.WP * 50f, Info.HP * 52.5f, Align.center);
        sound.setFontScale((float) (Info.HP * CONTROL_SIZE));
        stage.addActor(sound);

        soundm = new Label("<", skin.get("default", Label.LabelStyle.class));
        soundm.setAlignment(Align.center);
        soundm.setPosition(Info.WP * 10f, Info.HP * 42.5f, Align.center);
        soundm.setFontScale((float) (Info.HP * CONTROLM_SIZE));
        stage.addActor(soundm);

        soundb = new Label(">", skin.get("default", Label.LabelStyle.class));
        soundb.setAlignment(Align.center);
        soundb.setPosition(Info.WP * 90f, Info.HP * 42.5f, Align.center);
        soundb.setFontScale((float) (Info.HP * CONTROLB_SIZE));
        stage.addActor(soundb);

        exit = new Label("меню", skinblack.get("default", Label.LabelStyle.class));
        exit.setAlignment(Align.center);
        exit.setPosition(Buttons.EXIT.getX() + (Buttons.EXIT.getWidth() / 2), Buttons.EXIT.getY() + (Buttons.EXIT.getHeight() / 2), Align.center);
        exit.setFontScale((float) (Info.HP * EXIT_SIZE));
        stage.addActor(exit);
    }

    public void update(){

        if (Config.CONTROL == Control.SWIPES){

            rel1.setText("");
            rel2.setText("");

            swipes.setText("свайпы");

            controlm.setText("");
            controlb.setText(">");

        }else{

            swipes.setText("");

            rel1.setText("нажатия");
            rel2.setText("относительно положения");

            controlb.setText("");
            controlm.setText("<");

        }

        if (Config.SOUNDS){

            onoff.setText("вкл");

            soundb.setText(">");
            soundm.setText("");

        }else{

            onoff.setText("выкл");

            soundb.setText("");
            soundm.setText("<");

        }

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
