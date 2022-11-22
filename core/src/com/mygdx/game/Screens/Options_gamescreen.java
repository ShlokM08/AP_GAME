package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.APGAME;

public class Options_gamescreen implements Screen {


    private APGAME game;

    private OrthographicCamera gamecam;
    private Stage stage;
    private Image image,resume,savegame,exit,close;
    private Texture texture,resume_texture,savegame_texture,exit_texture,close_texture;

    public Options_gamescreen(APGAME game){
        System.out.println("gamescreen");
        this.game = game;
        gamecam = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(1280, 720, gamecam));
        Gdx.input.setInputProcessor(stage);
        texture = new Texture("SETTINGS_optionGameScreen.png");
        image = new Image(texture);
        System.out.println("s");
        stage.addActor(image);





        resume_texture = new Texture("Resume_gaamescreen.png");
        resume = new Image(resume_texture);
        resume.setPosition(5, 628);
        resume.setSize(105, 75);
        stage.addActor(resume);
        stage.addActor(image);


        savegame_texture = new Texture("Save gamescreen.png");
        savegame = new Image(savegame_texture);
        savegame.setPosition(5, 628);
        savegame.setSize(105, 75);
        stage.addActor(savegame);
        stage.addActor(image);



        exit_texture = new Texture("Main Menu_gamescreen.png");
        exit = new Image(exit_texture);
        exit.setPosition(5, 628);
        exit.setSize(105, 75);
        stage.addActor(exit);
        stage.addActor(image);




        close_texture = new Texture("cross.png");
        close = new Image(close_texture);
        close.setPosition(200, 590);
        close.setSize(105, 75);
        stage.addActor(close);
        stage.addActor(image);



    }

    @Override
    public void show() {

        resume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));

            }});
        savegame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));

            }});
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));

            }});
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));

            }});

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(gamecam.combined);

        update(delta);
        stage.draw();

        game.batch.begin();

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
