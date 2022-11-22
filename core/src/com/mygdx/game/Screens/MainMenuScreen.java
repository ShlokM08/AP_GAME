package com.mygdx.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.APGAME;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.awt.*;

import static com.badlogic.gdx.Gdx.app;

public class MainMenuScreen implements Screen {
    private APGAME game;
    private Stage stage;
    private TextButton buttonPlay,buttonExit,buttonSavedGames;
    private Image image;
    private Texture texture;

    private Texture exit_button_image,newgame_button_image,loadgame_button_image;
    private Image exit_button,newgame_button,loadgame_button;
    private OrthographicCamera gamecam;
    private  Skin skin;



    public MainMenuScreen(APGAME game){
        this.game= game;
        gamecam = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(1280, 720, gamecam));
        Gdx.input.setInputProcessor(stage);
        texture=new Texture("LoadSCREEN.jpg");//LoadSCREEN.jpg
        image = new Image(texture);
        initButtons();



        //show();


    }
    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);


        newgame_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("dei");
                game.setScreen(new TankSelection(game));

            }
        });

        loadgame_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();//for now load game exits the game
            }
        });


        exit_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        /*this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("flat-earth-ui.atlas")));
        this.skin.add("default-font",new BitmapFont());
        this.skin.load(Gdx.files.internal("flat-earth-ui.json"));
*/

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
        //game.batch.begin();

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


private void initButtons(){
    /*buttonPlay = new TextButton("Play",skin,"default");
    buttonPlay.setSize(200,60);
    buttonPlay.setPosition(50,70);
    stage.addActor(buttonPlay);*/

    newgame_button_image=new Texture("newgame_sticker.png");
    newgame_button = new Image(newgame_button_image);
    newgame_button.setSize(220,80);
    newgame_button.setPosition(30,70);
    stage.addActor(image);
    stage.addActor(newgame_button);


    loadgame_button_image=new Texture("loadgame_sticker.png");
    loadgame_button = new Image(loadgame_button_image);
    loadgame_button.setSize(220,80);
    loadgame_button.setPosition(550,70);
    stage.addActor(image);
    stage.addActor(loadgame_button);


    exit_button_image=new Texture("exit.png");
    exit_button = new Image(exit_button_image);
    exit_button.setSize(220,80);
    exit_button.setPosition(1060,70);
    stage.addActor(image);
    stage.addActor(exit_button);

    /*buttonSavedGames = new TextButton("Saved Games",skin,"default");
    buttonSavedGames.setSize(350,100);
    buttonSavedGames.setPosition(470,50);
    stage.addActor(buttonSavedGames);
    buttonExit = new TextButton("Exit",skin,"default");
    buttonExit.setSize(200,60);
    buttonExit.setPosition(1050,70);
    stage.addActor(buttonExit);




    buttonExit.addListener(new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            Gdx.app.exit();
        }*/

}
private void own_ICON(){

}
}