package textures;

public class ModelTexture {

	private int textureID;
	
	private float shineDamper = 1;
	private float reflectivty = 0;
	
	private boolean hasTransparancy = false;
	private boolean useFakeLighting = false;
	
	private int numberOfRows = 1;
	
	
	public ModelTexture (int id) {
		this.textureID = id;
	}
	
	
	
	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public boolean isUseFakeLighting() {
		return useFakeLighting;
	}

	public void setUseFakeLighting(boolean useFakeLighting) {
		this.useFakeLighting = useFakeLighting;
	}


	public boolean isHasTransparancy() {
		return hasTransparancy;
	}

	public void setHasTransparancy(boolean hasTransparancy) {
		this.hasTransparancy = hasTransparancy;
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
