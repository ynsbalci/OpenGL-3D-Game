package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;
import terrains.Terrain;

public class Player extends Entity{

	private static final float RUN_SPPED = 20;
	private static final float TURN_SPPED = 160;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;
	
	private static final float TERRAIN_HEIGHT = 0;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	private boolean isInAir = false;
	
	public Player(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale) {
		super(model, position, rotation, scale);
	}
	
	public void move(Terrain terrain) {
		checkInputs();

		super.increaseRotation(new Vector3f(0f, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0f));
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotation().y)));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotation().y)));
		super.increasePosition(new Vector3f(dx, 0f, dz));
		
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(new Vector3f(0f, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0f));
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if (super.getPosition().y<terrainHeight) {
			upwardsSpeed = 0;
			isInAir = false;
			super.getPosition().y = terrainHeight;
		}
	}

	private void jump() {
		if (!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
		

	}	
	
	private void checkInputs() {
		//
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = RUN_SPPED;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPPED;
		}
		else {
			this.currentSpeed = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSpeed = TURN_SPPED;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -TURN_SPPED;
		}else {
			this.currentTurnSpeed = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}
		
	}
}
