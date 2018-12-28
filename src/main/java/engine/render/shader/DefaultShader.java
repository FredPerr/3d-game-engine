package engine.render.shader;

import engine.util.Resource;
import engine.util.ResourceManager;
import org.joml.Matrix4f;

public class DefaultShader extends ShaderProgram {

    /**Create the default shader for the engine.
     * @param fragmentFileName Name of the fragment shader file in the assets/shaders/ folder.
     * @param vertexFileName Name of the vertex shader file in the assets/shaders/ folder.*/
    public DefaultShader(String vertexFileName, String fragmentFileName) {
        super(new Resource(ResourceManager.getApplicationFolderPath()+"/assets/shaders/"+vertexFileName),
              new Resource(ResourceManager.getApplicationFolderPath()+"/assets/shaders/"+fragmentFileName));
    }

    protected void bindAttributes() {
        bindAttribute(0, "location");
        bindAttribute(1, "textureCoordinates");
    }

    protected void linkUniforms() {
        addUniformLocation("usesTexture");//0
        addUniformLocation("matrixTransformation");//1
        addUniformLocation("matrixProjection");//2
        addUniformLocation("matrixView");//3
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        loadUniformMatrix(getUniformLocations().get(2), matrix);
    }
}
