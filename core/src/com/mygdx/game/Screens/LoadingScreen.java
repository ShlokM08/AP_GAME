package com.mygdx.game.Screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.APGAME;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.awt.*;

public class LoadingScreen implements Screen {
    private APGAME game;
    private Stage stage;
    private Texture texture;
    private Image image;
    private OrthographicCamera gamecam;



    public LoadingScreen(APGAME game){
        this.game= game;
        gamecam = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(1280, 720, gamecam));
        Gdx.input.setInputProcessor(stage);
        texture= new Texture("TankStarsLoadingScreen.jpg");
        image = new Image(texture);
        stage.addActor(image);

    }
    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(gamecam.combined);
        update(delta);
        stage.draw();
        game.batch.begin();
        if (Gdx.input.isTouched()){
            game.setScreen(new MainMenuScreen(game));
        }
        game.batch.end();

    }

    public void update(float delta){
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);


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
        stage.dispose();


    }
}