package engine.render;

import engine.render.model.Mesh;
import engine.render.model.TexturedMesh;
import engine.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;

/**A renderer is a class that can render entities to the screen with a certain shader.*/
public class Renderer {

    /**Id of the next renderer to be created.*/
    private static int nextId = 0;

    //TODO list of entities here.
    private ArrayList<TexturedMesh> entities;

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
        for(TexturedMesh e : entities)
            renderEntity(e);
    }

    /**
     * Renders a model to the screen.
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
    public void renderEntity(TexturedMesh entity){
        entity.getMesh().bindVAO();
        GL20.glEnableVertexAttribArray(0);
        if(entity.getTexture() != null) {
            getShader().loadUniformBoolean(getShader().getUniformLocations().get(0), entity.getMesh().isUsingTexture());
            GL20.glEnableVertexAttribArray(1);
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL13.glBindTexture(GL11.GL_TEXTURE_2D, entity.getTexture().getId());
        }
        GL11.glDrawElements(entity.getMesh().getRenderMode(), entity.getMesh().getVertexAmount(), GL11.GL_UNSIGNED_INT, 0);
        if(entity.getTexture() != null)
            GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(0);
        entity.getMesh().unbindVAO();
    }

    /**Set the shader of the renderer.
     * @param shader Shader to set.*/
    public void setShader(ShaderProgram shader){
        this.shader = shader;
    }

    /**Add an entity to the renderer.
     * @param entity Entity to add.*/
    public void addEntity(TexturedMesh entity){
        this.entities.add(entity);
    }

    /**Add an entity to the renderer.
     * @param entity Entity to remove.*/
    public void removeEntity(TexturedMesh entity){
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
