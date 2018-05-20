package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(Vector3f position, Vector3f rotation) {
		this.position = position;
		
				
	}
	
	public void move() {
		//
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			//
			this.position.y += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			//
			this.position.y -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			//
			this.position.x -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			//
			this.position.x += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			//
			this.position.z -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			//
			this.position.z += 0.1f;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			//
			this.yaw -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			//
			this.yaw += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
			//
			this.pitch += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
			//
			this.pitch -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
			//
			this.roll += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
			//
			this.roll -= 0.1f;
		}
		
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	
	
}
