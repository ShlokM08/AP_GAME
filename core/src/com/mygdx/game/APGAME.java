package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Screens.LoadingScreen;
//import com.mygdx.game.Screens.LoadingScreen;
//import com.sun.org.apache.bcel.internal.generic.LoadInstruction;

public class APGAME extends Game {
	public BitmapFont font;
	public SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		font = new BitmapFont();
		batch = new SpriteBatch();
		//img = new Texture("TankStarsLoadingScreen.jpg");
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose(){
		batch.dispose();
		font.dispose();
		this.getScreen().dispose();
	}

}
























