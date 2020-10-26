package com.shave.wizardgame;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FireWizard extends GameCharacter{

	//animation
	Animation<TextureRegion> idleAnimation;
	Animation<TextureRegion> healAnimation;
	float aniTime;

	// move names


	public FireWizard(String nameIn, float floor, float side, float width, float height,
					  Animation<TextureRegion> idle, Animation<TextureRegion> heal){
		super(100, 100, nameIn,"FireWizard", "Wizard",
				floor, side, width, height, "FireBolt", "FlameThrow");
		idleAnimation = idle;
		healAnimation = heal;
		state = State.IDLE;
		castTime = 0;
	}

	public boolean castHeal(GameCharacter target){
		if(getMana() <= 10 ){
			return false;
		} else {
			state = State.CASTINGHEAL;
			this.useMana(10);
			target.setHealth(target.getHealth() + 25);
			return true;
		}
	}

	public boolean drinkPotion(){
		if(getMana() == getMaxMana()){
			return false;
		} else {
			this.setMana(getMaxMana());
			return true;
		}
	}

	// FireBolt
	public boolean castOne(GameCharacter target, float stateTime){
		System.out.println(getName() + " is casting fireBolt on " + target.getName());
		if(getMana() < 30){
			return false;
		} else {
			state = State.CASTINGONE;
			target.takeDamage(30);
			useMana(30);
			return true;
		}
	}

	// FlameThrow 
	public boolean castTwo(GameCharacter target, float stateTime){
		System.out.println(getName() + " is casting flameThrow on " + target.getName());
		if(getMana() < 45){
			return false;
		} else {
			state = State.CASTINGTWO;
			target.takeDamage(50);
			useMana(45);
			return true;
		}
	}


	public void draw(Batch batch, float stateTime){
		if(state == State.CASTINGHEAL) {
			batch.draw(healAnimation.getKeyFrame(aniTime, true), side,floor, width, height);
			if(stateTime - castTime > healAnimation.getAnimationDuration()){
				state = State.IDLE;
			}
		}else{
			batch.draw(idleAnimation.getKeyFrame(stateTime, true), side, floor, width, height);
		}
	}

	public void update(float delta){
		if(state == State.CASTINGHEAL){
			aniTime = aniTime + delta;
		}
	}


}