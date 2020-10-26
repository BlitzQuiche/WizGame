package com.shave.wizardgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.Text;

import java.awt.event.MouseEvent;

public class MainMenuScreen implements com.badlogic.gdx.Screen {
    WizardGame game;
    GameScreen gameScreen;

    final float height = 1440;
    final float width = 2880;

    //button positioning
    final float menuMiddle = 1200;
    final int buttonXEnd = 1500;
    final int playYStart = 800;
    final int playYBottomBound = 600;
    final int playYTopBound = 500;
    final int playYEnd = 1200;

    //spritebatch and textures
    SpriteBatch batch;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture title;


    public MainMenuScreen(WizardGame game, GameScreen gameScreen) {
        //screens and game
        this.game = game;
        this.gameScreen = gameScreen;
        //spritebatch
        batch = new SpriteBatch();
        //textures
        playButtonActive = new Texture("playActive.png");
        playButtonInactive = new Texture("playInactive.png");
        title = new Texture("title.png");


    }



    @Override
    public void render(float delta) {
        //background stuff
        Gdx.gl.glClearColor(115f / 255, 115f / 255, 115f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //checking if over play button
        if(Gdx.input.getX() > menuMiddle && Gdx.input.getX() < buttonXEnd
                && Gdx.input.getY() < playYBottomBound && Gdx.input.getY() > playYTopBound){
            batch.draw(playButtonActive, menuMiddle, playYStart);
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                game.setScreen(gameScreen);
            }
        } else {
            batch.draw(playButtonInactive, menuMiddle, playYStart);
        }
        //drawing title
        batch.draw(title, menuMiddle-100, height*0.7f);
        batch.end();
    }

    @Override
    public void show() {

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
