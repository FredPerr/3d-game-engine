package engine;

import engine.window.Loop;
import engine.window.Window;

/**
 * This is a LWJGL version 3.2 developed engine. This is a 3D engine with main features
 * of 3D to create games easily without having to manage a lot of LWJGL libraries.
 *
 * This interface implements a render, update, init,
 * (cleanUp: is redefine in the engine and not by the user) and end methods.
 *
 * Created by KitK4t on 2018-12-15.
 */
public interface IEngine {

    /**This method is called when everything in the engine is loaded:
     * (window created, loop created).
     * It is executed right before the loop starts.
     * You should initialize your game objects and other stuff into it.
     * You should make a local variable to be able to reuse them in the other methods.*/
    void init();

    /**This method is called every UPS (updates per second). This is set in the loop.
     * Everything with the game update should be in that method.
     * The input should be inside that method.*/
    void update();

    /**This method is called every FPS (frames per second). This is set in the loop.
     * Everything with the graphics should be in that method.*/
    void render();

    /**This method is called when the loop ends.
     * Everything with saving the game and ending the game should be in that method.*/
    void end();

    /**Cleans up the shader, the loader and everything else that needs to be clean.*/
    void cleanUp();

    /**@return Window of the engine.*/
    Window getWindow();

    /**@return Loop of the engine.*/
    Loop getLoop();
}
