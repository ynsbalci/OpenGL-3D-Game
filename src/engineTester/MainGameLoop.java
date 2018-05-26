package engineTester;
 
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
        

        TexturedModel lowPolyTree = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader), new ModelTexture(loader.loadTexture("lowPolyTree")));
        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), new ModelTexture(loader.loadTexture("grassTexture")));
        grass.getTexture().setHasTransparancy(true);
        grass.getTexture().setUseFakeLighting(true);

        
        ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
        fernTextureAtlas.setNumberOfRows(2);
        
        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader), fernTextureAtlas);

        
        Terrain terrain = new Terrain(0, -1 , loader, texturePack, blendMap, "heightmap");
        
        
        List<Entity> entities = new ArrayList<Entity>();
        Random rand = new Random();
        for (int i = 0; i < 500; i++) {
        	float x = rand.nextFloat() * 800f - 400;
        	float z = rand.nextFloat() * 800f - 400;
        	float y = terrain.getHeightOfTerrain(x, z);
			entities.add(new Entity(lowPolyTree, new Vector3f(x, y, z),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
        	entities.add(new Entity(grass, new Vector3f(x, y, z),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
        	entities.add(new Entity(fern, rand.nextInt(4) ,new Vector3f(x, y, z),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
		}
        
        
        
        Light light = new Light(new Vector3f(20000f, 20000f, 20000f), new Vector3f(1, 1, 0));
        
        
        
        MasterRenderer renderer = new MasterRenderer();
        
        TexturedModel person =  new TexturedModel(OBJLoader.loadObjModel("person", loader), new ModelTexture(loader.loadTexture("playerTexture")));
        
        Player player = new Player(person, new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        
        Camera camera = new Camera(player);
        
        
        while(!Display.isCloseRequested()){
        	
        	
        	camera.move();
        	player.move(terrain);
        	renderer.processEntity(player);
        	
        	renderer.processTerrain(terrain);

        	
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