package engine;

import engine.loop.Loop;
import engine.resource.ResourceManager;
import engine.window.Window;

/**
 * Created by KitK4t on 2018-12-16.
 */
public abstract class Engine implements IEngine {

    private ResourceManager resourceManager;
    private Window window;
    private Loop loop;

    public Engine(String title, int width, int height, int maxFps, int maxUps){
        this.resourceManager = new ResourceManager();
        this.window = new Window(this, title, width, height);
        this.loop = new Loop(this, maxFps, maxUps);
    }

    /**Starts the engine. It runs the loop in the loop class.
     * This method should be the last in your Engine creation.
     * Every events should be created before it.*/
    public void start(){
        getLoop().start();
    }

    public void cleanUp() {
        //clear shader here.
        //clear loader here. (VAOs, VBOs, textures, sounds)
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
