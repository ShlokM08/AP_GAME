package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.APGAME;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private APGAME game;

    private OrthographicCamera gamecam;
    private Stage stage;
    private Image image,options,settings;
    private Texture texture,options_texture,settings_texture;

    public GameScreen(APGAME game){
        System.out.println("gamescreen");
        this.game = game;
        gamecam = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(1280, 720, gamecam));
        Gdx.input.setInputProcessor(stage);
        texture = new Texture("GAMESCREEN.png");
        image = new Image(texture);
        System.out.println("s");
        stage.addActor(image);










        options_texture = new Texture("GameScreen_optionsbutton.png");
        options = new Image(options_texture);
        options.setPosition(5, 628);
        options.setSize(105, 75);
        stage.addActor(options);
        stage.addActor(image);

    }
    @Override
    public void show() {

        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Options_gamescreen(game));

    }});

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(gamecam.combined);

        update(delta);
        stage.draw();

        game.batch.begin();
        //game.batch.draw(game.batch.draw();,"hello",120,120);
//        if (Gdx.input.isTouched()){
//            game.setScreen(new MainMenuScreen(game));
//            dispose();
//        }
        game.batch.end();

    }

    public void update(float delta) {
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
