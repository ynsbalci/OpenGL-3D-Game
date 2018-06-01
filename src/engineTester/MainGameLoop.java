package engineTester;
 
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
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
        MasterRenderer renderer = new MasterRenderer(loader);
        
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
        	float x = rand.nextFloat() * 800f;
        	float z = rand.nextFloat() *- 800f;
        	float y = terrain.getHeightOfTerrain(x, z);
			entities.add(new Entity(lowPolyTree, new Vector3f(x, y, z),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
        	entities.add(new Entity(grass, new Vector3f(x, y, z),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
        	entities.add(new Entity(fern, rand.nextInt(4) ,new Vector3f(x, y, z),  new Vector3f(0f, 0f, 0f),  new Vector3f(1f, 1f, 1f)));
		}
        
        
        
     
        
        
        
        TexturedModel person =  new TexturedModel(OBJLoader.loadObjModel("person", loader), new ModelTexture(loader.loadTexture("playerTexture")));
        Player player = new Player(person, new Vector3f(400, 0, -400), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        
        Camera camera = new Camera(player);
        
        List<Light> lights = new ArrayList<>();

        lights.add(new Light(new Vector3f(0, 400, -400), new Vector3f(0.4f, 0.4f, 0.4f)));//sun
        
        lights.add(new Light(new Vector3f(400, 10, -400), new Vector3f(2, 0, 0), new Vector3f(1, 0.01f, 0.002f)));
        lights.add(new Light(new Vector3f(400, 10, -410), new Vector3f(2, 0, 0), new Vector3f(1, 0.01f, 0.002f)));
        
        
        List<GuiTexture> guis = new ArrayList<>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("socuwan"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
        guis.add(gui);
        GuiTexture gui2 = new GuiTexture(loader.loadTexture("thinmatrix"), new Vector2f(0.3f, 0.75f), new Vector2f(0.25f, 0.25f));
        guis.add(gui2);
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        
        while(!Display.isCloseRequested()){
        	
        	
        	camera.move();
        	player.move(terrain);
        	renderer.processEntity(player);
        	
        	renderer.processTerrain(terrain);

        	
        	for (Entity entity : entities) {
        		renderer.processEntity(entity);
			}
        	
            renderer.render(lights, camera);
            guiRenderer.render(guis);
            DisplayManager.updateDisplay();         
        }

        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}