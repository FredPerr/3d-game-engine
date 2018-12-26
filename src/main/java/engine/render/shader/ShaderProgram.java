package engine.render.shader;

import engine.util.Resource;
import engine.util.ResourceManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**A shader program is a GLSL way to pass begin with vertex and to end with
 * pixels by performing many actions to have lights, color and every other
 * 3D graphic effects.*/
public abstract class ShaderProgram {

    /**Unique ID of the shader. This ID is given by OpenGL.*/
    private int programShaderId;
    /**Unique ID of the vertex part of the shader. This ID is given by OpenGL.*/
    private int vertexShaderId;
    /**Unique ID of the fragment part of the shader. This ID is given by OpenGL.*/
    private int fragmentShaderId;

    /**Create a shader with two resource shader files.
     * It attaches the shader and bind the attributes.
     * @param fragmentFileResource Resource of the fragment file. (File with extension .fs)
     * @param vertexFileResource Resource of the vertex file. (File with extension .vs)*/
    public ShaderProgram(Resource vertexFileResource, Resource fragmentFileResource){
        this.vertexShaderId = loadShader(
                ResourceManager.getFileSource(vertexFileResource.getFile().getPath()),
                GL20.GL_VERTEX_SHADER);
        this.fragmentShaderId = loadShader(
                ResourceManager.getFileSource(fragmentFileResource.getFile().getPath()),
                GL20.GL_FRAGMENT_SHADER);
        this.programShaderId = GL20.glCreateProgram();
        GL20.glAttachShader(programShaderId, vertexShaderId);
        GL20.glAttachShader(programShaderId, fragmentShaderId);
        bindAttributes();
        GL20.glLinkProgram(programShaderId);
        GL20.glValidateProgram(programShaderId);
    }

    /**Define the attribute passed in parameters to the vertex shader input stream.*/
    protected abstract void bindAttributes();

    /**Start using the shader. This method should be called every render.*/
    public void start(){
        GL20.glUseProgram(programShaderId);
    }

    /**Stop using the shader. This method should be called after every render.*/
    public void stop(){
        GL20.glUseProgram(0);
    }

    /**Detach each part of the shader and the shader deletes itself after.
     * It stops the shader before cleaning up.*/
    public void cleanUp(){
        stop();
        GL20.glDetachShader(programShaderId, vertexShaderId);
        GL20.glDetachShader(programShaderId, fragmentShaderId);
        GL20.glDeleteShader(vertexShaderId);
        GL20.glDeleteShader(fragmentShaderId);
        GL20.glDeleteProgram(programShaderId);
    }

    /**Bind an attribute to the shader. This method have to be used in
     * abstract bindAttributes method.
     * @param attribute Attribute number to set the value in.
     * @param variableName Name of the variable use to get it in the shader.*/
    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programShaderId, attribute, variableName);
    }

    /**Load a shader to OpenGL and creates an ID.
     * It compile the shader.
     * @param shaderSource Source text of the shader file.
     * @param type Type of the shader from OpenGL 2.0|3.3 Shader type.
     *             There are fragment (from OpenGL 2.0: GL20.FRAGMENT_SHADER),
     *             vertex (from OpenGL 2.0: GL20.VERTEX_SHADER) and geometry
     *             shader (from OpenGL 3.3: GL33.GEOMETRY_SHADER).*/
    public int loadShader(String shaderSource, int type){
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS ) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }
}
