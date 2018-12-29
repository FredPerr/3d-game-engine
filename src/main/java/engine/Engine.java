package engine;

import engine.event.EventSystem;
import engine.event.listener.ListenerCameraChange;
import engine.event.listener.ListenerWindowResized;
import engine.render.Camera;
import engine.render.DefaultRenderer;
import engine.render.Renderer;
import engine.render.model.Texture;
import engine.render.shader.DefaultShader;
import engine.util.*;
import engine.window.Loop;
import engine.render.model.Mesh;
import engine.window.Window;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

/**
 * The engine class is the main class of the engine. Everything starts from here.
 * The {@link Renderer}s are the objects that render the stuff to the screen via
 * their {@link engine.render.shader.ShaderProgram}. It possesses a {@link Camera}
 * that get the graphics. It possesses a window to manage the display and a loop
 * to refresh the engine.
 *
 * Created by KitK4t on 2018-12-16.
 */
public abstract class Engine implements IEngine {

    private List<Renderer> renderers;
    private Camera camera;
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
        this.camera = new Camera(new Location(), new Rotation(),90, 0.1f, 1000, true, 0.2f,90, -90);
        this.renderers = new ArrayList<>();
        addRenderer(new DefaultRenderer(this, new DefaultShader("defaultshader.vs", "defaultshader.fs")));
        EventSystem.addListener(new ListenerWindowResized(this));
        EventSystem.addListener(new ListenerCameraChange(this));
        this.loop = new Loop(this, maxFps, maxUps);

        //Set the icon to the engine if the resource is valid.
        if(resourceEngineIcon.isValid())
            getWindow().setIcon(Image.createImage(resourceEngineIcon.getFile().getPath()));
    }

    /**Add a renderer to the engine.
     * @param renderer Renderer to add.*/
    public void addRenderer(Renderer renderer){
        for(Renderer r : renderers)
            if(r.getId() == renderer.getId())
                return;
        renderers.add(renderer);
    }

    /**Remove a renderer from the engine.
     * @param renderer Renderer to add.
     *                 The default renderer can't be removed.*/
    public void removeRenderer(Renderer renderer){
        if(renderer.getId() == 0)
            return;
        for(Renderer r : renderers)
            if(r.getId() == renderer.getId())
                renderers.remove(renderer);
    }

    /**Cleans up the shader, the VAOs/VBOs, sounds, textures and everything else that needs to be clean.*/
    public void cleanUp() {
        for(Renderer r : renderers)
            r.getShader().cleanUp();
        for (int vao : Mesh.VAOs)
            GL30.glDeleteVertexArrays(vao);
        for (int vbo : Mesh.VBOs)
            GL15.glDeleteBuffers(vbo);
        for(int texture : Texture.textures)
            GL15.glDeleteTextures(texture);
    }

    /**Render everything in the engine. Starts the shader, render, stop the shader
     * and restart for each renderer.*/
    public void render() {
        for(Renderer r : renderers) {
            r.getShader().start();
            r.render();
            r.getShader().stop();
        }
    }

    /**Starts the engine. It runs the loop in the loop class.
     * This method should be the last in your Engine creation.
     * All the events have to be created before that method.*/
    public void start(){
        getLoop().start();
    }

    /**@return The default renderer of the engine. This renderer can't be removed.*/
    public Renderer getDefaultRenderer(){
        return renderers.get(0);
    }

    /**@return Resource manager of the engine.*/
    public ResourceManager getResourceManager(){
        return this.resourceManager;
    }

    /**@return The window of the engine.*/
    public Window getWindow() {
        return this.window;
    }

    /**@return The loop of the engine.*/
    public Loop getLoop(){
        return this.loop;
    }

    /**@return All the renderers of the engine.*/
    public List<Renderer> getRenderers(){
        return this.renderers;
    }

    /**@return Camera of the renderer.*/
    public Camera getCamera(){
        return this.camera;
    }
}
