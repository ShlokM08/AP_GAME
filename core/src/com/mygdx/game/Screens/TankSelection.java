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

public class TankSelection implements Screen {
    private APGAME game;

    private ArrayList<Image> tankList = new ArrayList<Image>();
    private OrthographicCamera gamecam;
    private Stage stage;
    private int tankSelected = 0;

    private Image tank1_name_button,tank2_name_button,tank3_name_button,image;
    private Texture texture,tank1,tank2,tank3,player_2;

    private Texture tank1_name,tank2_name,tank3_name, right_button_image, Left_button_image, back_button_image,select_button_image;
    private Image right_button, left_button, back_button,tank1_button,tank2_button,tank3_button,select_button,player_2_button;
    public TankSelection(APGAME game) {
        System.out.println("dei2");
        this.game = game;
        gamecam = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(1280, 720, gamecam));
        Gdx.input.setInputProcessor(stage);
        texture = new Texture("TankLoadingScreen.jpg");
        image = new Image(texture);

        System.out.println("dei3");

        Left_button_image = new Texture("leftarrow.png");
        left_button = new Image(Left_button_image);
        left_button.setSize(120, 90);
        left_button.setPosition(25, 300);
        /*stage.addActor(image);
        stage.addActor(left_button);*/


        back_button_image = new Texture("backarrow.png");
        back_button = new Image(back_button_image);
        back_button.setSize(120, 90);
        back_button.setPosition(15, 610);
       /* stage.addActor(image);
        stage.addActor(back_button);*/


        right_button_image = new Texture("rightarrow.png");
        right_button = new Image(right_button_image);
        right_button.setSize(120, 90);
        right_button.setPosition(1110, 300);


        tank1_name = new Texture("pinky_tankname.png");
        tank1_name_button = new Image(tank1_name);
        tank1_name_button.setPosition(475, 530);
        tank1_name_button.setSize(385, 100);


        tank2_name = new Texture("frost_tankname.png");
        tank2_name_button = new Image(tank2_name);
        tank2_name_button.setPosition(475, 530);
        tank2_name_button.setSize(355, 90);


        tank3_name = new Texture("tiger_tankname.png");
        tank3_name_button = new Image(tank3_name);
        tank3_name_button.setPosition(475, 530);
        tank3_name_button.setSize(375, 100);



        tank1 = new Texture("TANK1_IMAGE.png");
        tank1_button = new Image(tank1);
        tank1_button.setPosition(300, 175);
        tank1_button.setSize(650, 320);



        tank2 = new Texture("TANK2_IMAGE.png");
        tank2_button = new Image(tank2);
        tank2_button.setPosition(400, 185);
        tank2_button.setSize(480, 245);



        tank3 = new Texture("TANK3_IMAGE.png");
        tank3_button = new Image(tank3);
        tank3_button.setPosition(435, 185);
        tank3_button.setSize(480, 235);





        player_2= new Texture("P1.png");
        player_2_button=new Image(player_2);
        player_2_button.setPosition(1000,640);
        player_2_button.setSize(180,65);


        tankList.add(tank1_button);
        //tankList.add(tank1_name_button);
        tankList.add(tank2_button);
        tankList.add(tank3_button);

        stage.addActor(image);
        stage.addActor(back_button);
        stage.addActor(tank1_name_button);
        stage.addActor(right_button);
        stage.addActor(tankList.get(tankSelected));
        stage.addActor(player_2_button);
        //stage.addActor(select_button);

    }

    @Override
    public void show() {
        stage.addActor(back_button);
        ChooseButton();

        Gdx.input.setInputProcessor(stage);

        back_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));

            }
        });
        left_button.addListener(new ClickListener() {
            @Override

            public void clicked(InputEvent event, float x, float y) {
                System.out.println("L");
                if(tankSelected >=0){
                    tankList.get(tankSelected).remove();
                    if(tankList.get(tankSelected)==tank1_button){
                        tank1_name_button.remove();

                    }
                    if(tankList.get(tankSelected)==tank2_button){
                        stage.addActor(tank2_name_button);
                        tank2_name_button.remove()  ;
                    }
                    if(tankList.get(tankSelected)==tank3_button){
                        tank3_name_button.remove();

                    }
                    tankSelected--;
                    stage.addActor(tankList.get(tankSelected));
                    if(tankList.get(tankSelected)==tank1_button){
                        tank1_button.remove();
                        tank1_name_button.remove();
                        left_button.remove();
                        stage.addActor(tank1_button);
                        stage.addActor(tank1_name_button);
                    }

                    if(tankList.get(tankSelected)==tank2_button){
                        stage.addActor(left_button);
                        stage.addActor(right_button);
                        stage.addActor(tank2_button);
                        stage.addActor(tank2_name_button);
                    }
                    if(tankList.get(tankSelected)==tank3_button){
                        right_button.remove();
                        stage.addActor(tank3_button);
                        stage.addActor(tank3_name_button);
                    }




                }
            }
        });

        right_button.addListener(new ClickListener() {
            @Override

            public void clicked(InputEvent event, float x, float y) {
                System.out.println("R");
                if(tankSelected<=2) {
                    tankList.get(tankSelected).remove();

                    if(tankList.get(tankSelected)==tank1_button){
                        tank1_name_button.remove();

                    }
                    if(tankList.get(tankSelected)==tank2_button){

                        //stage.addActor(tank2_name_button);
                        tank2_name_button.remove()  ;
                    }
                    if(tankList.get(tankSelected)==tank3_button){
                        tank3_name_button.remove();

                    }

                    tankSelected++;
                    stage.addActor(tankList.get(tankSelected));
                    if (tankList.get(tankSelected) == tank1_button) {
                        left_button.remove();
                        stage.addActor(tank1_button);
                        stage.addActor(tank1_name_button);

                        //stage.addActor(tank1_name_button);
                    }
                    if (tankList.get(tankSelected) == tank2_button) {
                        stage.addActor(left_button);
                        stage.addActor(tank2_button);
                        stage.addActor(tank2_name_button);
                    }

                    if (tankList.get(tankSelected) == tank3_button) {
                        right_button.remove();
                        stage.addActor(tank3_button);
                        stage.addActor(tank3_name_button);
                    }





                }



            }




        });


    }



    public void ChooseButton(){
    System.out.println("its here");
    select_button_image = new Texture("CHOOSE_BUTTON.jpg");
    select_button = new Image(select_button_image);
    select_button.setSize(180, 70);
    select_button.setPosition(550, 40);

    select_button.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Image P1=tankList.get(tankSelected);
            game.setScreen(new TankSelection2(game));

            return true;
        }

    });
    stage.addActor(select_button);


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
    }}

