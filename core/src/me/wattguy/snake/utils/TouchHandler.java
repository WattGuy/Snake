package me.wattguy.snake.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

import me.wattguy.snake.menus.game.Buttons;

public class TouchHandler extends GestureDetector {

    public interface DirectionListener {
        void onSwipeLeft();

        void onSwipeRight();

        void onSwipeUp();

        void onSwipeDown();

        Boolean onTouchButton(float x, float y);

        void onTouchLeft();

        void onTouchRight();

        Boolean onTouch();
    }

    public TouchHandler(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureAdapter {

        private OrthographicCamera camera;
        private Vector3 temp;
        private DirectionListener directionListener;

        public DirectionGestureListener(DirectionListener directionListener){
            this.temp = new Vector3();

            this.camera = new OrthographicCamera();
            camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
            camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            this.directionListener = directionListener;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {

            try {

                temp.set(x, y, 0);
                camera.unproject(temp);

                if (directionListener.onTouchButton(temp.x, temp.y)) {
                    return true;
                }

                if (directionListener.onTouch()) {

                    return true;

                }

                if (Buttons.LEFT.getBoundingRectangle().contains(temp.x, temp.y)) {
                    directionListener.onTouchLeft();
                } else if (Buttons.RIGHT.getBoundingRectangle().contains(temp.x, temp.y))
                    directionListener.onTouchRight();
            }catch(Exception ignored){}

            return true;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {

            try {

                if (Math.abs(velocityX) > Math.abs(velocityY)) {
                    if (velocityX > 0) {
                        directionListener.onSwipeRight();
                    } else {
                        directionListener.onSwipeLeft();
                    }
                } else {
                    if (velocityY > 0) {
                        directionListener.onSwipeDown();
                    } else {
                        directionListener.onSwipeUp();
                    }
                }
            }catch(Exception ignored){}

            return super.fling(velocityX, velocityY, button);
        }

    }

}