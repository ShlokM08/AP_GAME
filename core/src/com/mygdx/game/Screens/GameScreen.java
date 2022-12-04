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

public class  GameScreen  implements Screen {

    private APGAME game;
    private Image image,resume,savegame,exit,close,tank1;
    private Texture texture,resume_texture,savegame_texture,exit_texture,close_texture;


    private OrthographicCamera gamecam;
    private Stage stage;
    private Image options,settings;
    private Texture options_texture,settings_texture;

    public GameScreen(APGAME game){
        //super(game);
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
        options.setPosition(0, 616);
        options.setSize(110, 101);
        stage.addActor(options);
        stage.addActor(image);







    }
    @Override
    public void show() {
        //options_menu();

        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                options_menu();

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



public void options_menu() {
    System.out.println("options menu");
    options_texture = new Texture("settingspopup.png");
    options = new Image(options_texture);
    options.setPosition(340, 125);
    options.setSize(550, 550);
    stage.addActor(options);
    stage.addActor(image);


    resume_texture = new Texture("Resume_gaamescreen.png");
    resume = new Image(resume_texture);
    resume.setPosition(530, 445);
    resume.setSize(221, 80);
    stage.addActor(resume);
    stage.addActor(image);


    savegame_texture = new Texture("Save gamescreen.png");
    savegame = new Image(savegame_texture);
    savegame.setPosition(530, 310);
    savegame.setSize(205, 80);
    stage.addActor(savegame);
    stage.addActor(image);


    exit_texture = new Texture("Main Menu_gamescreen.png");
    exit = new Image(exit_texture);
    exit.setPosition(530, 175);
    exit.setSize(205, 80);
    stage.addActor(exit);
    stage.addActor(image);


    close_texture = new Texture("cross.png");
    close = new Image(close_texture);
    close.setPosition(820, 600);
    close.setSize(50, 40);
    stage.addActor(close);
    stage.addActor(image);




    /*tank1 = selectedtanklist.get(0);
    selectedtanklist.get(0).setPosition(500, 650);
    selectedtanklist.get(0).setSize(1000, 1000);
    stage.addActor(selectedtanklist.get(0));
    stage.addActor(image);*/

//ff
    resume.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            game.setScreen(new GameScreen(game));

        }
    });
    savegame.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            game.setScreen(new GameScreen(game));

        }
    });
    exit.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            game.setScreen(new MainMenuScreen(game));

        }
    });
    close.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            game.setScreen(new GameScreen(game));

        }
    });


}}

        /*options = new Image(options_texture);
        options.setPosition(0, 616);
        options.setSize(110, 101);
        stage.addActor(options);
        stage.addActor(image);
        */


    //return null;




