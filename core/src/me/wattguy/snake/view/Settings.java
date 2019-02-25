package me.wattguy.snake.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import me.wattguy.snake.Config;
import me.wattguy.snake.Info;
import me.wattguy.snake.Main;
import me.wattguy.snake.enums.Control;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.utils.buttons.SButtons;
import me.wattguy.snake.utils.ui.SUI;

public class Settings implements Screen, InputProcessor {

    public Vector3 temp;
    public static OrthographicCamera camera;

    public static SpriteBatch batch;
    public static RoundedShapeRenderer srender;
    public static SUI ui;

    private static Settings instance;

    @Override
    public void show() {
        instance = this;

        Info.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        srender = new RoundedShapeRenderer();

        SButtons.initialize();
        ui = new SUI();

        temp = new Vector3();

        camera = new OrthographicCamera(480, 854);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(this);

    }

    public static Settings getInstance(){
        return instance;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.begin();
        SButtons.batchdraw();
        batch.end();

        srender.begin(ShapeRenderer.ShapeType.Filled);
        SButtons.draw();
        srender.end();

        ui.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        srender.dispose();
        ui.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        temp.set(screenX,screenY,0);
        camera.unproject(temp);

        if (SButtons.EXIT.getBoundingRectangle().contains(temp.x, temp.y)){

            if (Main.menu == null){

                Main.menu = new Menu();

            }

            Main.buttonSound();

            Main.getInstance().setScreen(Main.menu);

            return false;
        }else if (SButtons.CM.getBoundingRectangle().contains(temp.x, temp.y) && Config.CONTROL == Control.RELATIVITY){

            Main.buttonSound();

            Config.setControl(Control.SWIPES);

        }else if (SButtons.CB.getBoundingRectangle().contains(temp.x, temp.y) && Config.CONTROL == Control.SWIPES){

            Main.buttonSound();

            Config.setControl(Control.RELATIVITY);

        }else if (SButtons.SM.getBoundingRectangle().contains(temp.x, temp.y) && !Config.SOUNDS){

            Config.setSounds(true);

            Main.buttonSound();

        }else if (SButtons.SB.getBoundingRectangle().contains(temp.x, temp.y) && Config.SOUNDS){

            Config.setSounds(false);

            Main.buttonSound();

        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
