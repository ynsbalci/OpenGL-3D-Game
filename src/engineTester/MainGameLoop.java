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
import terrains.Terrain;
import textures.ModelTexture;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        
        

        TexturedModel lowPolyTree = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader), new ModelTexture(loader.loadTexture("lowPolyTree")));
        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), new ModelTexture(loader.loadTexture("grassTexture")));
        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader), new ModelTexture(loader.loadTexture("fern")));
        grass.getTexture().setHasTransparancy(true);
        fern.getTexture().setHasTransparancy(true);
        grass.getTexture().setUseFakeLighting(true);
        //fern.getTexture().setUseFakeLighting(true);
        
        List<Entity> entities = new ArrayList<Entity>();
        Random rand = new Random();
        for (int i = 0; i < 500; i++) {
			entities.add(new Entity(lowPolyTree, new Vector3f(rand.nextFloat() * 800f - 400, 0f, rand.nextFloat() * 800f - 400f),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
        	entities.add(new Entity(grass, new Vector3f(rand.nextFloat() * 800f - 400, 0f, rand.nextFloat() * 800f - 400f),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
        	entities.add(new Entity(fern, new Vector3f(rand.nextFloat() * 800f - 400, 0f, rand.nextFloat() * 800f - 400f),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
		}
        
        Terrain terrain1 = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(-1, 0, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain3 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain4 = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass")));
        
        
        Light light = new Light(new Vector3f(20000f, 20000f, 20000f), new Vector3f(1, 1, 0));
        
        Camera camera = new Camera(new Vector3f(0f, 5f, 25), new Vector3f(0f, 0f, 0f));
        
        MasterRenderer renderer = new MasterRenderer();
        
        while(!Display.isCloseRequested()){
        	camera.move();

        	renderer.processTerrain(terrain1);
        	renderer.processTerrain(terrain2);
        	renderer.processTerrain(terrain3);
        	renderer.processTerrain(terrain4);
        	for (Entity entity : entities) {
        		renderer.processEntity(entity);
			}
        	
            renderer.render(light, camera);
            DisplayManager.updateDisplay();         
        }
 
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}