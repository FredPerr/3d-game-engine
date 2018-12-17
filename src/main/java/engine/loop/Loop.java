package engine.loop;

import engine.IEngine;
import org.lwjgl.glfw.GLFW;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * The loop class is a class that manage the refreshment of the engine.
 */
public class Loop {

    private IEngine engine;
    private double timeUps, timeFps;
    private int fps, ups, maxFps, maxUps;
    private boolean running;

    public Loop(int maxFps, int maxUps){

        setMaximumFps(maxFps);
        if(getMaximumFps() < 1)
            setMaximumFps(60);

        setMaximumUps(maxUps);
        if(getMaximumUps() < 1)
            setMaximumUps(60);
    }

    /**Set the frames per second amount.
     * This value is stored in a local variable.
     * @param fps Frames per second amount.*/
    public void setFps(int fps){
        this.fps = fps;
    }

    /**@return Frames per second amount.
     * This is stored in a local variable.*/
    public int getFps(){
        return this.fps;
    }

    /**Set the updates per second amount.
     * This is stored in a local variable.
     * @param ups Amount of updates per second to set.*/
    public void setUps(int ups){
        this.ups = ups;
    }

    /**@return Updates per second amount.
     * This is stored in a local variable.*/
    public int getUps(){
        return this.ups;
    }

    /**Set the maximum of frames per second amount.
     * @param maximumFps Maximum of frames per second amount.*/
    public void setMaximumFps(int maximumFps){
        this.maxFps = maximumFps;
        this.timeFps = 1000000000 / getMaximumFps();
    }

    /**@return Maximum of frames per second amount.
     * This is stored in a local variable.*/
    public int getMaximumFps(){
        return this.maxFps;
    }

    /**Set the updates per second amount.
     * This is stored in a local variable.
     * @param maximumUps Maximum amount.*/
    public void setMaximumUps(int maximumUps){
        this.maxUps = maximumUps;
        this.timeUps = 1000000000 / getMaximumUps();
    }

    /**@return Maximum of updates per second amount.
     * This is stored in a local variable.*/
    public int getMaximumUps(){
        return this.maxUps;
    }

    /**Start the engine. Should store a running variable and set it.
     * Start the loop.
     * @param engine Engine to link the loop with.*/
    public void start(IEngine engine){
        this.engine = engine;
        if(!isRunning()) {
            this.running = true;
            long initialTime = System.nanoTime();
            this.timeUps = 1000000000 / getMaximumUps();
            this.timeFps = 1000000000 / getMaximumFps();
            double deltaU = 0, deltaF = 0;
            setFps(0);
            setUps(0);
            long timer = System.currentTimeMillis();
            engine.init();
            while (running) {
                GLFW.glfwPollEvents();
                long currentTime = System.nanoTime();
                deltaU += (currentTime - initialTime) / timeUps;
                deltaF += (currentTime - initialTime) / timeFps;
                initialTime = currentTime;

                if (deltaU >= 1) {

                    engine.update();
                    setUps(getUps()+1);
                    deltaU--;
                }

                if (deltaF >= 1) {
                    engine.render();
                    GLFW.glfwSwapBuffers(getEngine().getWindow().getHandle());
                    setFps(getFps()+1);
                    deltaF--;
                }

                if (System.currentTimeMillis() - timer > 1000) {
                    System.out.println(String.format("UPS: %s, FPS: %s", getUps(), getFps()));
                    setFps(0);
                    setUps(0);
                    timer += 1000;
                }
            }
            engine.end();
            engine.cleanUp();
            engine.getWindow().destroy();
            GLFW.glfwTerminate();
            GLFW.glfwSetErrorCallback(null).free();
            System.exit(0);
        }
    }

    /**Stop the engine. Should store a running variable and set it.
     * Close the engine.*/
    public void stop(){
        this.running = false;
    }

    /**@return True if the loop is running and false if not.
     * The value should be stored in a local variable.*/
    public boolean isRunning(){
        return this.running;
    }

    /**@return Engine interface.*/
    public IEngine getEngine(){
        return this.engine;
    }
}
