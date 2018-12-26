package engine.render.shader;

import engine.util.Resource;
import engine.util.ResourceManager;

public class DefaultShader extends ShaderProgram {

    /**Create the default shader for the engine.
     * @param fragmentFileName Name of the fragment shader file in the assets/shaders/ folder.
     * @param vertexFileName Name of the vertex shader file in the assets/shaders/ folder.*/
    public DefaultShader(String vertexFileName, String fragmentFileName) {
        super(new Resource(ResourceManager.getApplicationFolderPath()+"/assets/shaders/"+vertexFileName),
              new Resource(ResourceManager.getApplicationFolderPath()+"/assets/shaders/"+fragmentFileName));
    }

    /**Define the attribute passed in parameters to the vertex shader input stream.*/
    protected void bindAttributes() {
        bindAttribute(0, "location");
    }
}
