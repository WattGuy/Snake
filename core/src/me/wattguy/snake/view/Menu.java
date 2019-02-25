package me.wattguy.snake.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import me.wattguy.snake.Info;
import me.wattguy.snake.Main;
import me.wattguy.snake.utils.buttons.MButtons;
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.utils.ui.MUI;

public class Menu implements Screen, InputProcessor {

    public Vector3 temp;
    public static OrthographicCamera camera;

    public static SpriteBatch batch;
    public static RoundedShapeRenderer srender;
    public static MUI ui;

    private static Menu instance;

    private float time = 0f;
    public static Boolean GAME_STARTED = false;
    public static Boolean SETTINGS = false;

    @Override
    public void show() {
        instance = this;

        time = 0f;
        GAME_STARTED = false;
        SETTINGS = false;

        Info.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        srender = new RoundedShapeRenderer();

        MButtons.initialize();
        ui = new MUI();

        temp = new Vector3();

        camera = new OrthographicCamera(480, 854);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(this);

    }

    public static Menu getInstance(){
        return instance;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (GAME_STARTED || SETTINGS){
            time += delta;

            if (time >= Info.NEED && GAME_STARTED){

                if (Main.game == null){

                    Main.game = new Game();

                }

                Main.buttonSound();

                Main.getInstance().setScreen(Main.game);

            }else if (time >= Info.NEED && SETTINGS){

                if (Main.settings == null){

                    Main.settings = new Settings();

                }

                Main.buttonSound();

                Main.getInstance().setScreen(Main.settings);

            }

        }

        srender.begin(ShapeRenderer.ShapeType.Filled);
        MButtons.draw();
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
        srender.dispose();
        batch.dispose();
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

        if (MButtons.START.getBoundingRectangle().contains(temp.x, temp.y)){

            time = 0f;
            GAME_STARTED = true;

            return false;
        } else if (MButtons.SETTINGS.getBoundingRectangle().contains(temp.x, temp.y)){

            time = 0f;
            SETTINGS = true;

            return false;
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
