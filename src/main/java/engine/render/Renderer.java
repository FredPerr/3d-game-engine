package engine.render;

import engine.render.model.Mesh;
import engine.render.shader.ShaderProgram;

import java.util.ArrayList;

/**TODO comment here*/
public class Renderer {

    /**Id of the next renderer to be created.*/
    private static int nextId = 0;

    //TODO list of entities here.
    private ArrayList<Mesh> entities;

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
    public void render(){
        for(Mesh e : entities)
            e.render();
    }

    /**Set the shader of the renderer.
     * @param shader Shader to set.*/
    public void setShader(ShaderProgram shader){
        this.shader = shader;
    }

    /**Add an entity to the renderer.
     * @param entity Entity to add.*/
    public void addEntity(Mesh entity){
        this.entities.add(entity);
    }

    /**Add an entity to the renderer.
     * @param entity Entity to remove.*/
    public void removeEntity(Mesh entity){
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
}
