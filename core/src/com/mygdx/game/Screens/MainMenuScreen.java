package com.mygdx.game.Screens;

//package com.mygdx.game.Screens;


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

import java.awt.*;

public class MainMenuScreen implements Screen {
    private APGAME game;
    private Stage stage;
    private Image image;
    privaTexture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Viewport viewPort;



    public MainMenuScreen(APGAME game){
        this.game= game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        texture=new Texture("MainMenuScreen.jpg");
        image = new Image(texture);
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(3000,2400,gamecam);
        viewPort = new StretchViewport(1280, 640, gamecam);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.draw();


        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(texture,-640,-320);
        //game.batch.draw(game.batch.draw();,"hello",120,120);
        if (Gdx.input.isTouched()){
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
        game.batch.end();
        //game.batch.begin();

    }

    public void update(float delta){
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        viewPort.update(width, height);


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


    }
}