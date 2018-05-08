package engineTester;
 
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.TextureLoader;
import org.omg.CORBA.OBJ_ADAPTER;

import entities.Camera;
import entities.Entity;
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
        //Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
         
       /* float[] vertices = {			
				-0.5f,0.5f,0,	
				-0.5f,-0.5f,0,	
				0.5f,-0.5f,0,	
				0.5f,0.5f,0,		
				
				-0.5f,0.5f,1,	
				-0.5f,-0.5f,1,	
				0.5f,-0.5f,1,	
				0.5f,0.5f,1,
				
				0.5f,0.5f,0,	
				0.5f,-0.5f,0,	
				0.5f,-0.5f,1,	
				0.5f,0.5f,1,
				
				-0.5f,0.5f,0,	
				-0.5f,-0.5f,0,	
				-0.5f,-0.5f,1,	
				-0.5f,0.5f,1,
				
				-0.5f,0.5f,1,
				-0.5f,0.5f,0,
				0.5f,0.5f,0,
				0.5f,0.5f,1,
				
				-0.5f,-0.5f,1,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1
				
		};*/
		
		/*float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0	
		};*/
		
		/*int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22
		};*/
        //RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        
        
		RawModel model = OBJLoader.loadObjModel("Suzanne", loader);
		
        //ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		ModelTexture texture = new ModelTexture(loader.loadTexture("SuzanneTexture"));
        TexturedModel staticModel = new TexturedModel(model, texture);
        
        Vector3f position = new Vector3f(0, 0, -5);
        Vector3f rotation = new Vector3f(0, 0, 0);
        Vector3f scale = new Vector3f(1, 1, 1);
        
        Entity entity = new Entity(staticModel, position, rotation, scale);
        
        Camera camera = new Camera();
         
        while(!Display.isCloseRequested()){
            //game logic
        	//entity.increasePosition(new Vector3f(0f, 0f, -0.1f));
        	entity.increaseRotation(new Vector3f(0f, 0f, 0f));
        	//entity.increaseScale(new Vector3f(0f, 0f, 0f));
        	camera.move();
            renderer.prepare();
            shader.start();
            //renderer.render(model); //obsolete
            //renderer.render(texturedModel);
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