package engine.render;

import engine.render.model.Entity;
import engine.render.shader.ShaderProgram;
import engine.util.MathUtil;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

/**A renderer is a class that can render entities to the screen with a certain shader.*/
public abstract class Renderer {

    /**Id of the next renderer to be created.*/
    private static int nextId = 0;

    private ArrayList<Entity> entities;

    /**Shader of the renderer.*/
    private ShaderProgram shader;

    /**ID of the renderer.*/
    private int id;

    /**Create a renderer for the engine.
     * @param shader Shader of the renderer.*/
    public Renderer(ShaderProgram shader){
        this.shader = shader;
        this.id = nextId++;
        this.entities = new ArrayList<>();
    }

    /**Renders everything with a given shader.*/
    public abstract void render();

    /**
     * Renders an entity to the screen.
     *
     * Before we can render a VAO it needs to be made active, and we can do this
     * by binding it. We also need to enable the relevant attributes of the VAO,
     * which in this case is just attribute 0 where we stored the position data.
     *
     * The VAO can then be rendered to the screen using glDrawArrays(). We tell
     * it what type of shapes to render and the number of vertices that it needs
     * to render.
     *
     * After rendering we unbind the VAO and disable the attribute.
     */
    public abstract void renderEntity(Entity entity);

    /**Set the shader of the renderer.
     * @param shader Shader to set.*/
    public void setShader(ShaderProgram shader){
        this.shader = shader;
    }

    /**Add an entity to the renderer.
     * @param entity Entity to add.*/
    public void addEntity(Entity entity){
        this.entities.add(entity);
    }

    /**Add an entity to the renderer.
     * @param entity Entity to remove.*/
    public void removeEntity(Entity entity){
        this.entities.remove(entity);
    }

    /**@return Shader of the renderer.*/
    public ShaderProgram getShader(){
        return this.shader;
    }

    /**@return ID of the renderer.*/
    public int getId(){
        return this.id;
    }

    /**@return List of all the entities of the renderer.*/
    public List<Entity> getEntities(){
        return this.entities;
    }
}
