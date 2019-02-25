package me.wattguy.snake.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

import me.wattguy.snake.utils.buttons.GButtons;

public class TouchHandler extends GestureDetector {

    public interface DListener {
        void onSwipeLeft();

        void onSwipeRight();

        void onSwipeUp();

        void onSwipeDown();

        void onTouchMenu();

        void onTouchPause();

        void onTouchLeft();

        void onTouchRight();

        Boolean onTouch();
    }

    public TouchHandler(DListener directionListener) {
        super(new DGListener(directionListener));
    }

    private static class DGListener extends GestureAdapter {

        private OrthographicCamera camera;
        private Vector3 temp;
        private DListener dListener;

        public DGListener(DListener directionListener){
            this.temp = new Vector3();

            this.camera = new OrthographicCamera();
            camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
            camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            this.dListener = directionListener;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {

            try {

                temp.set(x, y, 0);
                camera.unproject(temp);

                if (GButtons.MENU.getBoundingRectangle().contains(temp.x, temp.y)) {
                    dListener.onTouchMenu();
                    return false;
                } else if (GButtons.PAUSE.getBoundingRectangle().contains(temp.x, temp.y)) {
                    dListener.onTouchPause();
                    return false;
                }

                if (dListener.onTouch()) {

                    return true;

                }

                if (GButtons.LEFT.getBoundingRectangle().contains(temp.x, temp.y)) {
                    dListener.onTouchLeft();
                } else if (GButtons.RIGHT.getBoundingRectangle().contains(temp.x, temp.y))
                    dListener.onTouchRight();
            }catch(Exception ignored){}

            return true;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {

            try {

                if (Math.abs(velocityX) > Math.abs(velocityY)) {
                    if (velocityX > 0) {
                        dListener.onSwipeRight();
                    } else {
                        dListener.onSwipeLeft();
                    }
                } else {
                    if (velocityY > 0) {
                        dListener.onSwipeDown();
                    } else {
                        dListener.onSwipeUp();
                    }
                }
            }catch(Exception ignored){}

            return super.fling(velocityX, velocityY, button);
        }

    }

}