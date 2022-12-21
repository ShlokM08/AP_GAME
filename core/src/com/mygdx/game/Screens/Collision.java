package com.mygdx.game.Screens;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.APGAME;

public class Collision implements ContactListener,Screen {
    private Stage stage;

    private Image image, options;
    private Texture options_texture;


float health1=6;
float health2=1;


    @Override
    public void beginContact(Contact contact) {
        Fixture shape1 = contact.getFixtureA();
        Fixture shape2 = contact.getFixtureB();

        if(shape1.getUserData()== null || shape2.getUserData()== null){
            return;
        }
        if(shape1.getUserData().equals("tank2")&&shape2.getUserData().equals("bullet")||shape1.getUserData().equals("bullet")&&shape2.getUserData().equals("tank2")){
            health2--;
            System.out.println("tank 1 bullet touches tank 2");
            if(health2==0){
                System.out.println("tank 2 is dead");

                /*options_texture = new Texture("empty_health_Tank2.png");
                System.out.println("1");
                options = new Image(options_texture);
                options.setPosition(35, 150);
                options.setSize(175, 75);
                System.out.println("2");

                stage.addActor(options);
                System.out.println("3");

                stage.addActor(image);*/

                /*options_texture = new Texture("5_fuel_Tank1.png");
                options = new Image(options_texture);
                options.setPosition(35, 150);
                options.setSize(175, 75);
                Stage stage = new Stage();
                stage.addActor(options);
                stage.addActor(image);*/
            }
            if(health2==1){
                System.out.println("tank 2 has 1 health left");
            }
            if(health2==2){
                System.out.println("tank 2 has 2 health left");
            }
            if(health2==3){
                System.out.println("tank 2 has 3 health left");
            }
            if(health2==4){
                System.out.println("tank 2 has 4 health left");
            }
            if(health2==5){
                System.out.println("tank 2 has 5 health left");
            }


        }
        if(shape1.getUserData().equals("tank1")&&shape2.getUserData().equals("bullet2")||shape1.getUserData().equals("bullet2")&&shape2.getUserData().equals("tank1")){
            System.out.println("tank 2 bullet touches tank 1");
            health1--;
            if(health1==0){
                System.out.println("tank 1 is dead");
            }
            if(health1==1){
                System.out.println("tank 1 has 1 health left");
            }
            if(health1==2){
                System.out.println("tank 1 has 2 health left");
            }
            if(health1==3){
                System.out.println("tank 1 has 3 health left");
            }
            if(health1==4){
                System.out.println("tank 1 has 4 health left");
            }
            if(health1==5){
                System.out.println("tank 1 has 5 health left");
            }


        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
