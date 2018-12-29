package engine.render.shader;

import engine.util.Resource;
import engine.util.ResourceManager;
import org.joml.Matrix4f;

/**
 * Default implementation of the shader program.
 *
 * This shader render a 3D world with.
 * <ul>
 *     <li>Texture</li>
 *     <li>Color</li>
 *     <li>Switch between texture and color mode.</li>
 * </ul>
 *
 * The files path are <br>
 *     Vertex shader file:<i>%Application jar folder%/assets/shaders/defaultshader.vs</i><br/>
 *     Vertex shader file:<i>%Application jar folder%/assets/shaders/defaultshader.fs</i><br/>
 * <br/>
 * Created by KitK4t on 2018-12-16.
 * */
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
