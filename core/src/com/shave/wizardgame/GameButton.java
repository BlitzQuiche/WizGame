package com.shave.wizardgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameButton {
    private Texture background;
    private BitmapFont text;
    private String message;
    private int width = 80;
    private int height = 10;


    public GameButton(String messageIn){
        background = new Texture("buttonBase.png");
        text = new BitmapFont(Gdx.files.internal("font.fnt"));
        message = messageIn;
    }

    public void drawInactive(SpriteBatch batch, float xpos, float ypos){
        batch.draw(background, xpos, ypos, width, height);
        text.draw(batch, message, xpos, ypos + 8);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
