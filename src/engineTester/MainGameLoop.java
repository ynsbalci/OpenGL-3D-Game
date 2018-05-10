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
        
        
		RawModel model = OBJLoader.loadObjModel("tree", loader);
		
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("treeTexture")));
        ModelTexture texture = staticModel.getTexture();
        
        Entity entity = new Entity(staticModel, new Vector3f(0f, 0f, -25f),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f));
        
        Terrain terrain1 = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("grass")));
        
        
        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 0));
        
        Camera camera = new Camera();
        
        MasterRenderer renderer = new MasterRenderer();
        
        while(!Display.isCloseRequested()){
        	camera.move();

        	renderer.processTerrain(terrain1);
        	renderer.processTerrain(terrain2);
        	
        	renderer.processEntity(entity);
            renderer.render(light, camera);
            DisplayManager.updateDisplay();         
        }
 
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}