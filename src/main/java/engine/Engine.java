package engine;

import engine.window.Loop;
import engine.render.model.Mesh;
import engine.util.Resource;
import engine.util.ResourceManager;
import engine.window.Window;

/**
 * Created by KitK4t on 2018-12-16.
 */
public abstract class Engine implements IEngine {

    private ResourceManager resourceManager;
    private Resource resourceEngineIcon;
    private Window window;
    private Loop loop;

    /**Main constructor
     * @param title Title of the engine. This parameter will be editable later.
     * @param height Height of the window in pixels. This parameter will be editable later.
     * @param width Width of the window in pixels. This parameter will be editable later.
     * @param maxFps Maximum of frames per second (renders amount per second). This parameter will be editable later.
     * @param maxUps Maximum of updates per second (updates amount per second). This parameter will be editable later.*/
    public Engine(String title, int width, int height, int maxFps, int maxUps){
        this.resourceManager = new ResourceManager();
        this.resourceEngineIcon = new Resource(ResourceManager.getApplicationFolderPath()+"/assets/textures/LWJGLEngine.png");
        this.window = new Window(this, title, width, height);
        this.loop = new Loop(this, maxFps, maxUps);

        //Set the icon to the engine if the resource is valid.
        if(resourceEngineIcon.isValid())
            getWindow().setIcon(getResourceManager().loadImage(resourceEngineIcon.getFile().getPath()));
    }

    public void cleanUp() {
        //TODO clear shader here.
        //TODO clear loader here. (VAOs, VBOs, textures, sounds)
        Mesh.cleanUp();
    }

    /**Starts the engine. It runs the loop in the loop class.
     * This method should be the last in your Engine creation.
     * All the events have to be created before that method.*/
    public void start(){
        getLoop().start();
    }

    /**@return Resource manager of the engine.*/
    public ResourceManager getResourceManager(){
        return this.resourceManager;
    }

    public Window getWindow() {
        return this.window;
    }

    public Loop getLoop(){
        return this.loop;
    }
}
