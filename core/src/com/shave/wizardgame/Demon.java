package com.shave.wizardgame;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Demon extends GameCharacter{

	//animation
	Animation<TextureRegion> idleAnimation;

	public Demon(String nameIn, float floor, float side, float width, float height,
				 Animation<TextureRegion> idle){
		// mana, health
		super(50, 150, nameIn,"Demon", "Monsters",
				floor, side, width, height, "Spell1", "Spell2");
		idleAnimation = idle;
	}

	public boolean regenerate(){
		if(getMana() <= 10 ){
			return false;
		} else {
			this.useMana(10);
			this.setHealth(this.getHealth() + 25);

			return true;
		}
	}

	// Bite
	public boolean castOne(GameCharacter target, float stateTime){
		System.out.println(getName() + " is trying to bite " + target.getName());
		if(getMana() < 20){
			return false;
		} else {
			target.takeDamage(30);
			useMana(20);
			return true;
		}
	}

	// skyStrike 
	public boolean castTwo(GameCharacter target, float stateTime){
		System.out.println(getName() + " is swooping down on " + target.getName());
		if(getMana() < 25){
			return false;
		} else {
			target.takeDamage(35);
			useMana(25);
			return true;
		}
	}


	public void draw(Batch batch, float stateTime){
		batch.draw(idleAnimation.getKeyFrame(stateTime, true),side, floor, width, height);
	}

	public void update(float delta){

	}

}