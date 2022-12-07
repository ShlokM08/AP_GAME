package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.APGAME;
public class  GameScreen  implements Screen {
    private Sprite p1tank1;
    private Sprite p1tank2;
    private Sprite p1tank3;
    private Sprite p2tank1;
    private Sprite p2tank2;
    private Sprite p2tank3;
    private APGAME game;
    private Image image,resume,savegame,exit,close;
    private Texture texture,resume_texture,savegame_texture,exit_texture,close_texture;
    private OrthographicCamera gamecam;
    private Stage stage;
    private Image options,settings;
    private Texture options_texture,settings_texture;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    private BodyDef bodyDefTank1 = new BodyDef();
    private BodyDef bodyDefTank2 = new BodyDef();

    private Body tank1;

    private Body tank2;
    private BodyDef bdef = new BodyDef();



    public GameScreen(APGAME game){
        batch= new SpriteBatch();
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
        if(TankSelection.player1Tank==0){
            p1tank1 = new Sprite(new Texture("TANK1_IMAGE.png"));
            p1tank1.setPosition(100, 100);
            p1tank1.setSize(100, 100);
            //stage.addActor(p1tank1);
        }
        else if (TankSelection.player1Tank==1) {
            p1tank2 = new Sprite(new Texture("TANK2_IMAGE.png"));
            p1tank2.setPosition(100, 100);
            p1tank2.setSize(100, 100);

        } else if (TankSelection.player1Tank==2) {
            p1tank3 = new Sprite(new Texture("TANK3_IMAGE.png"));
            p1tank3.setPosition(100, 100);
            p1tank3.setSize(100, 100);
        }
        if(TankSelection2.player2Tank==0){
            p2tank1 = new Sprite(new Texture("TANK1_IMAGE.png"));
            p2tank1.setPosition(200, 100);
            p2tank1.setSize(100, 100);
            p2tank1.flip(true, false);
            //stage.addActor(p1tank1);
        }
        else if (TankSelection2.player2Tank==1) {
            p2tank2 = new Sprite(new Texture("TANK2_IMAGE.png"));
            p2tank2.setPosition(200, 100);
            p2tank2.setSize(100, 100);
            //reverse image
            p2tank2.flip(true, false);
        } else if (TankSelection2.player2Tank==2) {
            p2tank3 = new Sprite(new Texture("TANK3_IMAGE.png"));
            p2tank3.setPosition(200, 100);
            p2tank3.setSize(100, 100);
            p2tank3.flip(true, false);
        }

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
//GROUND
        //Body Definition
        BodyDef bodyDef = new BodyDef();
        world = new World(new Vector2(0, -9.8f), true);
        gamecam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        /*bodyDef.position.set(0, 0);*/
        bodyDef.position.set(0,0);
        gamecam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //fixture definition
        FixtureDef fixtureDef2 = new FixtureDef();

        bodyDefTank1.type = BodyDef.BodyType.DynamicBody;
        bodyDefTank2.type = BodyDef.BodyType.DynamicBody;
        bodyDefTank1.position.set(-530, -105);
        bodyDefTank2.position.set(500, -105);
        //bdef.position.set(0,0);

        //Box
        PolygonShape tankShape1 = new PolygonShape();
        PolygonShape tankShape2 = new PolygonShape();
        tankShape1.setAsBox(25, 25);
        tankShape2.setAsBox(25, 25);
        fixtureDef2.shape = tankShape1;
        fixtureDef2.density = 15f;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25, 25);
        fixtureDef2.shape = shape;
        fixtureDef2.density = 1.0f;
        fixtureDef2.friction = 0.4f;
        fixtureDef2.restitution = 0.6f;
        // add box to world
        tank1 = world.createBody(bodyDefTank1);
        tank1.createFixture(fixtureDef2);
        tank2 = world.createBody(bodyDefTank2);
        tank2.createFixture(fixtureDef2);
        //world.createBody(bodyDefTank1).createFixture(fixtureDef2);
        System.out.println("tank1");
        //world.createBody(bodyDefTank2).createFixture(fixtureDef2);
        world.createBody(bdef).createFixture(fixtureDef2);


        //Body
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, 0.0f);
        shape.dispose();

        //Ground
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[] {
                new Vector2(-320*2, -65*2),
                new Vector2(-245*2, -65*2),
                new Vector2(-178*2,-77*2),
                new Vector2(-173*2,-75*2),
                new Vector2(-150*2,-76*2),
                new Vector2(-130*2,-75*2),
                new Vector2(-95*2,-59*2),
                new Vector2(-55*2,-28*2),
                new Vector2(-40*2,-30*2),
                new Vector2(-20*2,-28*2),
                new Vector2(12*2,-38*2),
                new Vector2(32*2,-55*2),
                new Vector2(91*2,-62*2),
                new Vector2(140*2,-33*2),
                new Vector2(165*2,-34*2),
                new Vector2(181*2,-43*2),
                new Vector2(199*2,-58*2),
                new Vector2(211*2,-62*2),
                new Vector2(230*2,-65*2),
                new Vector2(320*2,-65*2),
                /*new Vector2(-320, -65),
                new Vector2(-245, -65),
                new Vector2(-178,-77),
                new Vector2(-173,-75),
                new Vector2(-150,-76),
                new Vector2(-130,-75),
                new Vector2(-95,-59),
                new Vector2(-55,-28),
                new Vector2(-40,-30),
                new Vector2(-20,-28),
                new Vector2(12,-38),
                new Vector2(32,-55),
                new Vector2(91,-62),
                new Vector2(140,-33),
                new Vector2(165,-34),
                new Vector2(181,-43),
                new Vector2(199,-58),
                new Vector2(211,-62),
                new Vector2(230,-65),



                new Vector2(320,-65),*/

        });

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.025f;
        //fixtureDef.restitution = 0.6f;

        world.createBody(bodyDef).createFixture(fixtureDef);

        groundShape.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.draw();
        game.batch.setProjectionMatrix(gamecam.combined);

        update(delta);
        stage.draw();
        debugRenderer.render(world, gamecam.combined);
        world.step(1/60f, 6, 2);

        /*game.batch.begin();
        //game.batch.draw(game.batch.draw();,"hello",120,120);
//        if (Gdx.input.isTouched()){
//            game.setScreen(new MainMenuScreen(game));
//            dispose();
//        }
        game.batch.end();*/

        batch.begin();
        if(TankSelection.player1Tank==0){
            //add sprite to body
            //p1tank1.setPosition(tank1.getPosition().x, tank1.getPosition().y);
            p1tank1.draw(batch);
            /*p1tank1.setRotation((float) Math.toDegrees(tank1.getAngle()));*/
            p1tank1.setPosition(tank1.getPosition().x+575, tank1.getPosition().y+325);

        }
        //p1tank1.setPosition(tank1.getPosition().x, tank1.getPosition().y);
        //p1tank1.draw(batch);
        //p1tank1.setPosition(tank1.getPosition().x, tank1.getPosition().y);
        //attach sprite to body
        //p1tank1.setPosition();

        else if (TankSelection.player1Tank==1) {
            p1tank1.draw(batch);
            p1tank1.setPosition(tank1.getPosition().x+585, tank2.getPosition().y+325);
            //p1tank2.draw(batch);
            //p1tank2.draw(batch);
        } else if (TankSelection.player1Tank==2) {
            p1tank3.draw(batch);
        }

        if(TankSelection2.player2Tank==0){
            //attach sprite to body
            p2tank1.draw(batch);
            p2tank1.setPosition(tank2.getPosition().x+585, tank2.getPosition().y+325);
        }
        else if (TankSelection2.player2Tank==1) {
            p2tank2.draw(batch);
        } else if (TankSelection2.player2Tank==2) {

            p2tank3.draw(batch);
        }
        batch.end();
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