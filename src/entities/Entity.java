package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Entity {

	private TexturedModel model;
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;
	
	private int textureIndex = 0;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Entity(TexturedModel model, int index, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = model;
		this.textureIndex = index;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public float getTextureXOffset() {
		int column = textureIndex%model.getTexture().getNumberOfRows();
		return (float)column / (float)model.getTexture().getNumberOfRows();
	}
	
	public float getTextureYOffset() {
		int row = textureIndex%model.getTexture().getNumberOfRows();
		return (float)row / (float)model.getTexture().getNumberOfRows();

	}
	
	
	public void increasePosition(Vector3f position) {
		this.position.x += position.x;
		this.position.y += position.y;
		this.position.z += position.z;
	}
	
	public void increaseRotation(Vector3f rotation) {
		this.rotation.x += rotation.x;
		this.rotation.y += rotation.y;
		this.rotation.z += rotation.z;
	}
	public void increaseScale(Vector3f scale) {
		this.scale.x += scale.x;
		this.scale.y += scale.y;
		this.scale.z += scale.z;
	}
	
	
	public TexturedModel getModel() {
		return model;
	}
	public void setModel(TexturedModel model) {
		this.model = model;
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getRotation() {
		return rotation;
	}
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	public Vector3f getScale() {
		return scale;
	}
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	
	
	
	
}
