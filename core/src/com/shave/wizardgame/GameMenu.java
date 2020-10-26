package com.shave.wizardgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.maps.objects.RectangleMapObject;


public class GameMenu {
    // text display
    BitmapFont text = new BitmapFont(Gdx.files.internal("font.fnt"));
    Texture menuBackground = new Texture("menu.png");
    String currentText;
    GameCharacter player;
    GameCharacter enemy;

    //menu buttons
    GameButton topLeft;
    GameButton topRight;
    GameButton bottomLeft;
    GameButton bottomRight;

    // game logic stuff
    boolean playerTurn;
    enum State{
        WELCOME, SELECTING_ACTION, SELECTING_CAST, WAITING
    }
    State state;

    // handles turn based logic
    // interacts with user
    // displays buttons for attacks



    public GameMenu(GameCharacter player, GameCharacter enemy) {
        playerTurn = true;
        state = State.WELCOME;
        this.player = player;
        this.enemy = enemy;

        //buttons
        topLeft = new GameButton("topLeft");
        topRight = new GameButton("topRight");
        bottomLeft = new GameButton("bottomleft");
        bottomRight = new GameButton("bottomRight");




    }

    public void draw(SpriteBatch batch, float world_width, float bk_floor){
        batch.draw(menuBackground,0,0,world_width,bk_floor);
        if(playerTurn) {
            switch (state) {
                case WELCOME:
                    setCurrentText("Welcome " + player.getName() + ". Press space to start battle.");
                    break;
                case SELECTING_ACTION:
                    setCurrentText("What would you like to do?");
                    break;
                case SELECTING_CAST:
                    setCurrentText("Select your move");
                    topLeft.setMessage(player.getCastOne());
                    topLeft.drawInactive(batch, world_width * 0.6f, bk_floor * 0.6f);
                    topRight.setMessage(player.getCastTwo());
                    topRight.drawInactive(batch, world_width * 0.8f, bk_floor * 0.6f);
                    bottomLeft.setMessage("Drink Potion");
                    bottomLeft.drawInactive(batch, world_width * 0.6f, bk_floor * 0.2f);
                    bottomRight.setMessage("Heal");
                    bottomRight.drawInactive(batch, world_width * 0.8f, bk_floor * 0.2f);
                    break;
                case WAITING:
                    break;
            }
        }
        text.draw(batch, currentText, 10, 20);
    }

    public void update(){
        switch (state){
            case WELCOME:
                if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                    setState(State.SELECTING_CAST);
                }
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

}
