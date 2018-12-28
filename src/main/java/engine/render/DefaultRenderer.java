package engine.render;

import engine.Engine;
import engine.render.model.Entity;
import engine.render.shader.ShaderProgram;
import engine.util.MathUtil;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

/**
 * Created by KitK4t on 2018-12-27.
 */
public class DefaultRenderer extends Renderer {

    public DefaultRenderer(Engine engine, ShaderProgram shader) {
        super(engine, shader);
    }

    public void render(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        getShader().loadUniformMatrix(getShader().getUniformLocations().get(3), MathUtil.createViewMatrix(getEngine().getCamera()));

        for(Entity e : getEntities())
            renderEntity(e);
    }

    public void renderEntity(Entity entity){
        entity.getModel().getMesh().bindVAO();
        GL20.glEnableVertexAttribArray(0);
        Matrix4f transformationMatrix = MathUtil.createTransformationMatrix(entity.getLocation(), entity.getRotation(), entity.getScale());
        getShader().loadUniformMatrix(getShader().getUniformLocations().get(1), transformationMatrix);
        if(entity.getModel().getTexture() != null) {
            getShader().loadUniformBoolean(getShader().getUniformLocations().get(0), entity.getModel().getMesh().isUsingTexture());
            GL20.glEnableVertexAttribArray(1);
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL13.glBindTexture(GL11.GL_TEXTURE_2D, entity.getModel().getTexture().getId());
        }
        GL11.glDrawElements(entity.getModel().getMesh().getRenderMode(), entity.getModel().getMesh().getVertexAmount(), GL11.GL_UNSIGNED_INT, 0);
        if(entity.getModel().getTexture() != null)
            GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(0);
        entity.getModel().getMesh().unbindVAO();
    }
}
