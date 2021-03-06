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
import me.wattguy.snake.utils.RoundedShapeRenderer;
import me.wattguy.snake.menus.shop.Buttons;
import me.wattguy.snake.menus.shop.UI;

public class Shop implements Screen, InputProcessor {

    public Vector3 temp;
    public static OrthographicCamera camera;

    public static SpriteBatch batch;
    public static RoundedShapeRenderer shapeRenderer;
    public static UI ui;

    private static Shop instance;

    @Override
    public void show() {
        instance = this;

        Info.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        shapeRenderer = new RoundedShapeRenderer();

        Buttons.initialize();
        ui = new UI();

        temp = new Vector3();

        camera = new OrthographicCamera(480, 854);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(this);

    }

    public static Shop getInstance(){
        return instance;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.begin();
        Buttons.batchdraw();
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Buttons.draw(delta);
        shapeRenderer.end();

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
        shapeRenderer.dispose();
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

        if (Buttons.EXIT.isClicked(temp.x, temp.y)){

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
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
