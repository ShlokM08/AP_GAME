package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.APGAME;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
//latest code as of 18/12/2022
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

    private BodyDef bulletDef = new BodyDef();
    private BodyDef bulletDef2 = new BodyDef();

    private Body bulletBody;
    private Body bulletBody2;
    private FixtureDef fixtureDefTank1 = new FixtureDef();
    private FixtureDef fixtureDefTank2 = new FixtureDef();

    private Body tank1;

    private Body tank2;
    private BodyDef bdef = new BodyDef();

    private Vector2 mov1 = new Vector2();

    private Vector2 mov2 = new Vector2();

    private float speed = 80000;

    private Texture fire_texture,fire_texture2;

    private Image fire;
    InputMultiplexer multiplexer = new InputMultiplexer();



    public GameScreen(APGAME game){
        batch= new SpriteBatch();
        //super(game);
        System.out.println("gamescreen");
        this.game = game;
        gamecam = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(1280, 720, gamecam));
        multiplexer.addProcessor(stage);
        texture = new Texture("GAMESCREEN.png");
        fire_texture = new Texture("FireButton.png");
        fire_texture2 = new Texture("FireButton.png");
        ImageButton FireButton2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(fire_texture2)));
        ImageButton fireButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(fire_texture)));
        fireButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                bulletDef.type = BodyDef.BodyType.DynamicBody;
                bulletDef.position.set(tank1.getPosition().x+100, tank1.getPosition().y+15);

                fixtureDefTank1 = new FixtureDef();
                fixtureDefTank1.density = 5f;
                fixtureDefTank1.friction = 0.4f;
                fixtureDefTank1.restitution = 0.6f;

                CircleShape bullet = new CircleShape();
                bullet.setRadius(10);
                fixtureDefTank1.shape = bullet;

                bulletBody = world.createBody(bulletDef);
                bulletBody.createFixture(fixtureDefTank1);
                bulletBody.applyLinearImpulse(110000,110000, bulletBody.getPosition().x, bulletBody.getPosition().y, true);

                //checking collision
                if (Intersector.overlaps(new Rectangle(bulletBody.getPosition().x, bulletBody.getPosition().y, 10, 10), new Rectangle(tank2.getPosition().x, tank2.getPosition().y, 100, 100))) {
                    System.out.println("collision of tank1 bullet with tank2");
                }


            }
        });
        fireButton.setPosition(200,50);
        fireButton.setSize(160,120);
        /*if(Intersector.overlaps(new Rectangle(tank1.getPosition().x,tank1.getPosition().y,100,100),new Rectangle(bulletBody.getPosition().x,bulletBody.getPosition().y,100,100))){
            System.out.println("Intersected");
        }*/
        //collision detection





        FireButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                bulletDef2.type = BodyDef.BodyType.DynamicBody;
                bulletDef2.position.set(tank2.getPosition().x-45, tank2.getPosition().y+15);

                fixtureDefTank2 = new FixtureDef();
                fixtureDefTank2.density = 5f;
                fixtureDefTank2.friction = 0.4f;
                fixtureDefTank2.restitution = 0.6f;

                CircleShape bullet2 = new CircleShape();
                bullet2.setRadius(10);
                fixtureDefTank2.shape = bullet2;

                bulletBody2 = world.createBody(bulletDef2);
                bulletBody2.createFixture(fixtureDefTank2);
                bulletBody2.applyLinearImpulse(-150000,110000, bulletBody2.getPosition().x, bulletBody2.getPosition().y, true);


                //checking collision
                if (Intersector.overlaps(new Rectangle(bulletBody2.getPosition().x, bulletBody2.getPosition().y, 10, 10), new Rectangle(tank1.getPosition().x, tank1.getPosition().y, 100, 100))) {
                    System.out.println("collision of tank2 bullet with tank1");
                }


            }
        });
        FireButton2.setPosition(875,50);
        FireButton2.setSize(160,120);





        image = new Image(texture);
        System.out.println("s");
        stage.addActor(image);
        if(TankSelection.player1Tank==0){
            p1tank1 = new Sprite(new Texture("TANK1_IMAGE.png"));
            p1tank1.setPosition(100, 100);
            p1tank1.setSize(170, 100);
            //stage.addActor(p1tank1);
        }
        else if (TankSelection.player1Tank==1) {
            p1tank2 = new Sprite(new Texture("TANK2_IMAGE.png"));
            p1tank2.setPosition(100, 100);
            p1tank2.setSize(125, 70);

        } else if (TankSelection.player1Tank==2) {
            p1tank3 = new Sprite(new Texture("TANK3_IMAGE.png"));
            p1tank3.setPosition(100, 100);
            p1tank3.setSize(125, 70);
        }
        if(TankSelection2.player2Tank==0){
            p2tank1 = new Sprite(new Texture("TANK1_IMAGE.png"));
            p2tank1.setPosition(200, 100);
            p2tank1.setSize(170, 100);
            p2tank1.flip(true, false);
            //stage.addActor(p1tank1);
        }
        else if (TankSelection2.player2Tank==1) {
            p2tank2 = new Sprite(new Texture("TANK2_IMAGE.png"));
            p2tank2.setPosition(200, 100);
            p2tank2.setSize(125, 70);
            //reverse image
            p2tank2.flip(true, false);
        } else if (TankSelection2.player2Tank==2) {
            p2tank3 = new Sprite(new Texture("TANK3_IMAGE.png"));
            p2tank3.setPosition(200, 100);
            p2tank3.setSize(125, 70);
            p2tank3.flip(true, false);
        }


        options_texture = new Texture("GameScreen_optionsbutton.png");
        options = new Image(options_texture);
        options.setPosition(0, 616);
        options.setSize(110, 101);
        stage.addActor(options);
        stage.addActor(image);
        stage.addActor(fireButton);
        stage.addActor(FireButton2);





    }
    @Override
    public void show() {
        //options_menu();
        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                options_menu();
            }
        });
        multiplexer.addProcessor(new InputController(){
            float fuel = 6;
            float fuel2 = 6;
            @Override

            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.A:
                        if (fuel > 0) {
                            mov1.x = -speed;

                            if(fuel ==6 ){
                                options_texture = new Texture("full_health_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            fuel -= 1;// decrease fuel level for tank 1



                            if(fuel ==5 ){
                                options_texture = new Texture("5_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel ==4 ){
                                //stage.clear();
                                options_texture = new Texture("4_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel ==3 ){
                                options_texture = new Texture("3_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel ==2 ){
                                options_texture = new Texture("2_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }


                            else if(fuel ==1){
                                //stage.dispose();
                                options_texture = new Texture("empty_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }


                        }
                        break;
                    case Input.Keys.D:
                        if (fuel > 0) {
                            mov1.x = speed;
                            fuel -= 1; // decrease fuel level for tank 1

                            if(fuel ==5 ){
                                options_texture = new Texture("5_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel ==4 ){
                                //stage.clear();
                                options_texture = new Texture("4_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel ==3 ){
                                options_texture = new Texture("3_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel ==2 ){
                                options_texture = new Texture("2_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }


                            else if(fuel ==1){
                                //stage.dispose();
                                options_texture = new Texture("empty_fuel_Tank1.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }


                        }
                        break;
                    case Input.Keys.LEFT:
                        if (fuel2 > 0) {
                            mov2.x = -speed;
                            fuel2 -= 1; // decrease fuel level for tank 2

                            if(fuel2 ==5 ){
                                options_texture = new Texture("5_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel2 ==4 ){
                                //stage.clear();
                                options_texture = new Texture("4_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel2 ==3 ){
                                options_texture = new Texture("3_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel2 ==2 ){
                                options_texture = new Texture("2_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }


                            else if(fuel2 ==1){
                                //stage.dispose();
                                options_texture = new Texture("empty_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }


                        }
                        break;
                    case Input.Keys.RIGHT:
                        if (fuel2 > 0) {
                            mov2.x = speed;
                            fuel2 -= 1; // decrease fuel level for tank 2.
                            if(fuel2 ==5 ){
                                options_texture = new Texture("5_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel2 ==4 ){
                                //stage.clear();
                                options_texture = new Texture("4_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel2 ==3 ){
                                options_texture = new Texture("3_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }
                            else if(fuel2 ==2 ){
                                options_texture = new Texture("2_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }


                            else if(fuel2 ==1){
                                //stage.dispose();
                                options_texture = new Texture("empty_fuel_Tank2.png");
                                options = new Image(options_texture);
                                options.setPosition(200, 75);
                                options.setSize(550, 550);
                                stage.addActor(options);
                                stage.addActor(image);
                            }


                        }
                        break;

                }
                return false;
            }
//IJLLIO
            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.A:
                    case Input.Keys.D:
                        mov1.x = 0;
                        break;
                    case Input.Keys.LEFT:
                    case Input.Keys.RIGHT:
                        mov2.x = 0;
                        break;
                }
                return false;
            }

        });
        Gdx.input.setInputProcessor(multiplexer);
        //shooting a weapon ball







        //GROUND
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        world = new World(new Vector2(0, -9.81f), true);
        gamecam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        debugRenderer = new Box2DDebugRenderer();
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
        });

        //fixture definition for ground

        fixtureDef.shape = groundShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;
        fixtureDef.density = 1f;


        world.createBody(bodyDef).createFixture(fixtureDef);

        //Body defintions(Box for Tanks)
        Vector2[] tankVertices = new Vector2[4];

        bodyDefTank1.type = BodyDef.BodyType.DynamicBody;
        bodyDefTank1.position.set(-575, 0);
        bodyDefTank1.fixedRotation = true;
        tank1 = world.createBody(bodyDefTank1);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25, 25);
        tank1.createFixture(shape, 2);
        shape.dispose();


        bodyDefTank2.type = BodyDef.BodyType.DynamicBody;
        bodyDefTank2.position.set(520, 0);
        bodyDefTank2.fixedRotation = true;
        tank2 = world.createBody(bodyDefTank2);
        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(25, 25);
        tank2.createFixture(shape2, 2);
        shape2.dispose();



        /*if(tank1.getPosition().x>tank2.getPosition().x){
            tank1.applyForceToCenter(-100,0,true);
            tank2.applyForceToCenter(100,0,true);
        }
        else if(tank1.getPosition().x<tank2.getPosition().x){
            tank1.applyForceToCenter(100,0,true);
            tank2.applyForceToCenter(-100,0,true);
        }*/





    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.batch.setProjectionMatrix(gamecam.combined);
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.render(world, gamecam.combined);
        world.step(1/60f, 21, 2);
        tank1.applyForceToCenter(mov1, true);
        tank2.applyForceToCenter(mov2, true);



        batch.begin();
        if(TankSelection.player1Tank==0){
            p1tank1.draw(batch);
            p1tank1.setPosition(tank1.getPosition().x+590, tank1.getPosition().y+325);
            //p1tank1.setRotation((float) Math.toDegrees(tank1.getAngle()));
        }
        else if (TankSelection.player1Tank==1) {
            p1tank2.draw(batch);
            p1tank2.setPosition(tank1.getPosition().x+590, tank1.getPosition().y+325);
            //p1tank2.setRotation((float) Math.toDegrees(tank1.getAngle()));
        } else if (TankSelection.player1Tank==2) {
            p1tank3.draw(batch);
            p1tank3.setPosition(tank1.getPosition().x+590, tank1.getPosition().y+325);
            //p1tank3.setRotation((float) Math.toDegrees(tank1.getAngle()));
        }
        if(TankSelection2.player2Tank==0){
            p2tank1.draw(batch);
            p2tank1.setPosition(tank2.getPosition().x+590, tank2.getPosition().y+325);
            //p2tank1.setRotation((float) Math.toDegrees(tank2.getAngle()));
        }
        else if (TankSelection2.player2Tank==1) {
            p2tank2.draw(batch);
            p2tank2.setPosition(tank2.getPosition().x+590, tank2.getPosition().y+325);
            //p2tank2.setRotation((float) Math.toDegrees(tank2.getAngle()));
        } else if (TankSelection2.player2Tank==2) {
            p2tank3.draw(batch);
            p2tank3.setPosition(tank2.getPosition().x+590, tank2.getPosition().y+325);
            //p2tank3.setRotation((float) Math.toDegrees(tank2.getAngle()));

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

    public void CollisionDetector(){

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