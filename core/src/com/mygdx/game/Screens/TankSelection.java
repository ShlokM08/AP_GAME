package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.APGAME;

public class TankSelection implements Screen {
    private APGAME game;
    private OrthographicCamera gamecam;
    private Stage stage;
    private TextButton buttonPlay,buttonExit,buttonSavedGames;
    private Image image;
    private Texture texture;

    private Texture right_button_image,left_button_image,back_button_image;
    private Image right_button,left_button,back_button;




    public TankSelection(APGAME game){
        this.game= game;
        gamecam = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(1280, 720, gamecam));
        Gdx.input.setInputProcessor(stage);
        texture=new Texture("LoadSCREEN.jpg");
        image = new Image(texture);
        buttons();



        //show();


    }
    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);


        back_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));

            }
        });
/*
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
*/

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

    private void buttons(){
    //System.out.println("its working");
    left_button_image=new Texture("leftarrow.png");
    left_button = new Image(left_button_image);
    left_button.setSize(200,60);
    left_button.setPosition(50,325);
    stage.addActor(image);
    stage.addActor(left_button);


    back_button_image=new Texture("backarrow.png");
    back_button = new Image(back_button_image);
    back_button.setSize(200,80);
    back_button.setPosition(20,650);
    stage.addActor(image);
    stage.addActor(back_button);


    right_button_image=new Texture("rightarrow.png");
    right_button = new Image(right_button_image);
    right_button.setSize(200,60);
    right_button.setPosition(1050,325);
    stage.addActor(image);
    stage.addActor(right_button);



}
}

