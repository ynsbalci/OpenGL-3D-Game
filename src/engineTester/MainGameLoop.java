package engineTester;
 
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        
        
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("drogonTexture")));
        
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivty(1);
        
        List<Entity> allEntities = new ArrayList<>();
        Random rand = new Random();
        
        for (int i = 0; i < 25; i++) {
			Vector3f p = new Vector3f(rand.nextFloat() * 20 - 10, rand.nextFloat() * 20 - 10,rand.nextFloat() * -50);
			Vector3f r = new Vector3f(rand.nextFloat() * 180, rand.nextFloat() * 180, rand.nextFloat() * 180);
			Vector3f s = new Vector3f(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
			allEntities.add(new Entity(staticModel, p, r, s));
			
		} 
        
        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 0));
        
        Camera camera = new Camera();
        
        MasterRenderer renderer = new MasterRenderer();
        
        while(!Display.isCloseRequested()){
        	camera.move();

            for(Entity e : allEntities) {
            	
            	e.increaseRotation(new Vector3f(0f, 1f, 0f));
            	renderer.processEntity(e);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();         
        }
 
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}