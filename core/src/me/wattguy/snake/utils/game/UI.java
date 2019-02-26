package me.wattguy.snake.utils.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import me.wattguy.snake.Info;
import me.wattguy.snake.view.Game;

public class UI {

    private Stage stage;
    private Stage sstage;
    private Skin skin;
    private Skin skinblack;
    private Label main;
    private Label center;
    private Label sub;
    private Label menu;
    private Label pause;
    private Label lscore;
    private Label score;

    public static Boolean COUNTING;

    private float time = 0f;
    private float times = 0f;
    private float howmany = 0;

    public static final float PAUSE_SIZE = 0.06f;
    public static final float MENU_SIZE = 0.06f;
    public static final float LSCORE_SIZE = 0.04f;
    public static final float SCORE_SIZE = 0.04f;

    private FitViewport fwp;
    private ScreenViewport swp;

    public UI(){

        COUNTING = true;

        skin = new Skin(Gdx.files.internal("fonts/white.json"));
        skinblack = new Skin(Gdx.files.internal("fonts/black.json"));

        swp = new ScreenViewport();
        fwp = new FitViewport(800, 600);

        stage = new Stage(fwp);
        sstage = new Stage(swp);

        center = new Label("3", skin.get("default", Label.LabelStyle.class));
        center.setAlignment(Align.center);
        center.setFontScale(3f);
        center.setPosition(400, 300, Align.center);
        stage.addActor(center);

        main = new Label("", skin.get("default", Label.LabelStyle.class));
        main.setAlignment(Align.center);
        main.setFontScale(1f);
        main.setPosition(400, 375, Align.center);
        stage.addActor(main);

        sub = new Label("", skin.get("default", Label.LabelStyle.class));
        sub.setAlignment(Align.center);
        sub.setPosition(400, 290, Align.center);
        sub.setFontScale(0.5f);
        stage.addActor(sub);

        menu = new Label("меню", skinblack.get("default", Label.LabelStyle.class));
        menu.setAlignment(Align.center);
        menu.setPosition(Buttons.MENU.getX() + (Buttons.MENU.getWidth() / 2), Buttons.MENU.getY() + (Buttons.MENU.getHeight() / 2), Align.center);
        menu.setFontScale((float) (Info.HP * MENU_SIZE));
        sstage.addActor(menu);

        pause = new Label("пауза", skinblack.get("default", Label.LabelStyle.class));
        pause.setAlignment(Align.center);
        pause.setPosition(Buttons.PAUSE.getX() + (Buttons.PAUSE.getWidth() / 2), Buttons.PAUSE.getY() + (Buttons.PAUSE.getHeight() / 2), Align.center);
        pause.setFontScale((float) (Info.HP * PAUSE_SIZE));
        sstage.addActor(pause);

        lscore = new Label("очки", skin.get("default", Label.LabelStyle.class));
        lscore.setAlignment(Align.center);
        lscore.setPosition(Buttons.MENU.getX() + Buttons.MENU.getWidth() + (Buttons.abyss / 2), Buttons.MENU.getY() + (Buttons.MENU.getHeight() / 2) + ((Info.SPACE_TOP / 20) * 3), Align.center);
        lscore.setFontScale((float) (Info.HP * LSCORE_SIZE));
        sstage.addActor(lscore);

        score = new Label("3", skin.get("default", Label.LabelStyle.class));
        score.setAlignment(Align.center);
        score.setPosition(Buttons.MENU.getX() + Buttons.MENU.getWidth() + (Buttons.abyss / 2), Buttons.MENU.getY() + (Buttons.MENU.getHeight() / 2) - ((Info.SPACE_TOP / 20) * 3), Align.center);
        score.setFontScale((float) (Info.HP * SCORE_SIZE));
        sstage.addActor(score);

    }

    public void update(float delta){

        if (COUNTING && !Game.PAUSED){
            time += delta;
            times += delta;

            if (times >= 1f){

                howmany++;

                if (howmany == 3){

                    center.setText("");
                    COUNTING = false;

                    Game.getInstance().renewGame();

                }else {

                    center.setText("" + (Integer.parseInt(center.getText().toString()) - 1));
                    center.setFontScale(3.3f);
                    times = 0f;

                }

            }

            if (time >= 0.020){

                center.setFontScale(center.getFontScaleX() * 0.998f);
                time = 0f;

            }

        }

        if (Game.s != null){

            score.setText("" + Game.s.dots.size());

        }

        if (Game.WON){

            main.setText("ты выиграл");
            sub.setText("нажми для новой игры");

        } else if (Game.DIED){

            main.setText("ты мертв");
            sub.setText("нажми для новой игры");

        } else if (Game.PAUSED){

            main.setText("пауза");
            sub.setText("нажми для продолжения");

        }else if (!main.getText().toString().isEmpty()){

            main.setText("");
            sub.setText("");

        }

    }

    public void draw(float delta){
        update(delta);

        sstage.getViewport().apply();
        sstage.act();
        sstage.draw();

        stage.getViewport().apply();
        stage.act();
        stage.draw();

    }

    public void dispose(){
        stage.dispose();
        sstage.dispose();
    }

}
