package com.shave.wizardgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sun.rmi.rmic.Main;

public class GameScreen implements com.badlogic.gdx.Screen{
    //Wizardgame
    WizardGame game;
    //screens
    MainMenuScreen menuScreen;


    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private TextureAtlas textureAtlas;

    private TextureRegion background;
    private float backgroundHeight;

    //animation
    private Animation<TextureRegion> wizardHeal;
    private Animation<TextureRegion> wizardIdle;
    private Animation<TextureRegion> demonIdle;


    //timing
    private int backgroundOffset;
    float animationBase = 5f;
    private float stateTime;

    //world parameters
    private final int WORLD_WIDTH = 512;
    private final int WORLD_HEIGHT = 128;
    private final float BACKGROUND_FLOOR = WORLD_HEIGHT*0.25f;
    private final float BACKGROUND_HEIGHT = WORLD_HEIGHT*0.75f;
    private final float SPRITE_FLOOR = WORLD_HEIGHT *0.3f;
    private final float SPRITELEFT = WORLD_WIDTH *0.1f;
    private final float SPRITERIGHT = WORLD_WIDTH *0.6f;

    //GameCharacters
    private FireWizard wiz;
    private Demon dim;

    //game menu
    GameMenu menu;


    GameScreen(WizardGame game, MainMenuScreen menuScreen){
        this.game = game;
        // screens
        this.menuScreen = menuScreen;

        //camera and viewport
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        //spriteBatch
        batch = new SpriteBatch();

        //set up texture atlas
        textureAtlas = new TextureAtlas("images.atlas");

        //set up the background texture region
        background = new TextureRegion(textureAtlas.findRegion("old-dark-castle-interior-background"));
        backgroundOffset = 0;

        //setting up animations
        wizardHeal = new Animation<TextureRegion>(1f/8f, textureAtlas.findRegions("heal"));
        wizardIdle = new Animation<TextureRegion>(1f/8f, textureAtlas.findRegions("idle"));
        demonIdle = new Animation<TextureRegion>(1f/8f, textureAtlas.findRegions("demon"));


        // making wizard
        wiz = new FireWizard("kieran", SPRITE_FLOOR, SPRITELEFT,
                WORLD_WIDTH*0.1f, BACKGROUND_HEIGHT*0.3f,
                wizardIdle, wizardHeal);
        dim = new Demon("james", SPRITE_FLOOR,SPRITERIGHT,
                WORLD_WIDTH*0.4f, BACKGROUND_HEIGHT*0.8f,
                demonIdle);

        //menu
        menu = new GameMenu(wiz, dim);
    }

    @Override
    public void render(float delta) {
        stateTime = stateTime +  delta;
        batch.begin();
        // detecing input
        detectInput(stateTime);
        //updating stuff
        menu.update();
        wiz.update(delta);
        dim.update(delta);
        // drawing
        batch.draw(background, 0, BACKGROUND_FLOOR, WORLD_WIDTH, BACKGROUND_HEIGHT);

        wiz.draw(batch, stateTime);
        dim.draw(batch, stateTime);
        menu.draw(batch, WORLD_WIDTH, BACKGROUND_FLOOR);

        batch.end();
    }

    public void detectInput(float stateTime){
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            wiz.state = GameCharacter.State.CASTINGHEAL;
            wiz.castTime = stateTime;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    public int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }

    public float getBACKGROUND_FLOOR() {
        return BACKGROUND_FLOOR;
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
    public void show() {

    }

    @Override
    public void dispose() {

    }
}
