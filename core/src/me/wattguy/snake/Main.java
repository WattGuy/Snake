package me.wattguy.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;

import me.wattguy.snake.view.Menu;

public class Main extends com.badlogic.gdx.Game {

	private static Main instance;

	public static Screen game = null;
	public static Screen menu = null;
	public static Screen settings = null;

	public static Sound button;
	public static Sound coin;

	@Override
	public void create () {
		instance = this;

		button = Gdx.audio.newSound(Gdx.files.internal("sounds/button.ogg"));
		coin = Gdx.audio.newSound(Gdx.files.internal("sounds/action.ogg"));

		Config.initialize();

		Info.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		menu = new Menu();
		setScreen(menu);
	}

	public static void buttonSound(){

		if (Config.SOUNDS){

			button.play(.5f);

		}

	}

	public static void actionSound(){

		if (Config.SOUNDS){

			coin.play();

		}

	}

	public static Main getInstance(){
		return instance;
	}

	@Override
	public void dispose() {
		super.dispose();

		if (menu != null){

			menu.dispose();

		}

		if (settings != null){

			settings.dispose();

		}

		if (game != null){

			game.dispose();

		}
	}
}
