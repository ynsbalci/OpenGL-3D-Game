package engineTester;
 
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.TextureLoader;
import org.omg.CORBA.OBJ_ADAPTER;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		
		ModelTexture texture = new ModelTexture(loader.loadTexture("DragonTexture"));
        TexturedModel staticModel = new TexturedModel(model, texture);
        
        Vector3f position = new Vector3f(0, 0, -25);
        Vector3f rotation = new Vector3f(0, 0, 0);
        Vector3f scale = new Vector3f(1, 1, 1);
        
        Entity entity = new Entity(staticModel, position, rotation, scale);
        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 0));
        
        Camera camera = new Camera();
         
        while(!Display.isCloseRequested()){
            //game logic
        	//entity.increasePosition(new Vector3f(0f, 0f, -0.1f));
        	//entity.increaseRotation(new Vector3f(0f, 0f, 0f));
        	//entity.increaseScale(new Vector3f(0f, 0f, 0f));
        	camera.move();
            renderer.prepare();
            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();         
        }
 
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}