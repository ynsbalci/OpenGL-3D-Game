package textures;

public class ModelTexture {

	private int textureID;
	
	private float shineDamper = 1;
	private float reflectivty = 0;
	
	
	public ModelTexture (int id) {
		this.textureID = id;
	}
	
	public int getID() {
		return this.textureID;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivty() {
		return reflectivty;
	}

	public void setReflectivty(float reflectivty) {
		this.reflectivty = reflectivty;
	}
	
	
}
