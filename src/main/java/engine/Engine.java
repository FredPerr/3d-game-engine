package engine;

import engine.loop.Loop;
import engine.window.Window;

/**
 * Created by KitK4t on 2018-12-16.
 */
public abstract class Engine implements IEngine {

    private Window window;
    private Loop loop;

    public Engine(String title, int width, int height, int maxFps, int maxUps){
        this.window = new Window(this, title, width, height);
        this.loop = new Loop(maxFps, maxUps);
        getLoop().start(this);
    }

    public void cleanUp() {
        //clear shader here.
        //clear loader here. (VAOs, VBOs, textures, sounds)
    }

    public Window getWindow() {
        return this.window;
    }

    public Loop getLoop(){
        return this.loop;
    }
}
