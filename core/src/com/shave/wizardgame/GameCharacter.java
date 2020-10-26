package com.shave.wizardgame;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public abstract class GameCharacter{
	private int mana;
	private int health;
	private final int maxMana;
	private final int maxHealth;
	private String name;
	private String type;
	private String faction;
	private BufferedReader reader;
	private FileReader file;
	private String castOne;
	private String castTwo;

	//world parameters
	float floor;
	float side;

	//size
	float width;
	float height;

	// state
	enum State{
		CASTINGHEAL, CASTINGONE, CASTINGTWO, IDLE
	}
	State state;
	float castTime;






	public GameCharacter(int maxManaIn, int maxHealthIn, String nameIn, String type, String faction,
						 float floor, float side, float width, float height, String castOneName, String castTwoName){
		mana = maxManaIn;
		maxMana = maxManaIn;
		health = maxHealthIn;
		maxHealth = maxHealthIn;
		name = nameIn;
		this.type = type;
		this.faction = faction;
		this.floor = floor;
		this.side = side;
		this.width = width;
		this.height = height;
		castOne = castOneName;
		castTwo = castTwoName;

	}

	public int getMana(){
		return mana;
	}

	public void useMana(int cost){
		mana = mana - cost;
		if(mana <0){
			mana = 0;
		}
	}

	public void setMana(int num){
		mana = num;
	}

	public int getHealth(){
		return health;
	}

	public boolean takeDamage(int damage){
		health = health-damage;
		if(health <= 0){
			return true;
		} else {
			return false;
		}
	}

	public void setHealth(int num){
		health = num;
	}

	public int getMaxMana(){
		return maxMana;
	}

	public int getMaxHealth(){
		return maxHealth;
	}

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	public void listAbilities(){
		try{
			file = new FileReader("game/characterInfo/"+getType()+".txt");
			reader = new BufferedReader(file);
			String line = reader.readLine();
			while(line != null){
				System.out.println(line);
				line = reader.readLine();
			}	
		} catch(IOException e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public abstract boolean castOne(GameCharacter target, float stateTime);

	public abstract boolean castTwo(GameCharacter target, float stateTime);

	public String getCastOne() {
		return castOne;
	}

	public String getCastTwo() {
		return castTwo;
	}

	public abstract void draw(Batch batch, float stateTime);

	public abstract void update(float stateTime);

	@Override
	public String toString(){
		return "\nName: " + name + "\tClass: " + type 
			  +"\tHealth: " + health+"\tMana: " + mana;
	}

}