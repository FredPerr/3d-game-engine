package engine.render.shader;

import engine.util.Location;
import engine.util.Resource;
import engine.util.ResourceManager;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * A shader program is an OpenGL Shader Language way to pass begin with vertex and to end with
 * pixels by performing many actions to have lights, color and every other
 * 3D graphic effects.
 *
 * Created by KitK4t on 2018-12-16.
 * */
public abstract class ShaderProgram {

    /**Float buffer used to load matrices into the shader.*/
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    /**Location of all the uniform locations.*/
    private List<Integer> uniformLocations;
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
        uniformLocations = new ArrayList<>();
        linkUniforms();
    }

    /**Define the attribute passed in parameters to the vertex shader input stream.*/
    protected abstract void bindAttributes();

    /**Loads and define the locations all the uniforms of the shader.*/
    protected abstract void linkUniforms();

    /**Load the projection matrix.*/
    public abstract void loadProjectionMatrix(Matrix4f matrix);

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

    /**Get the location of an uniform variable in the shader.
     * @param uniformName Name of the uniform.
     * @return Location id of the uniform.*/
    protected void addUniformLocation(String uniformName){
        uniformLocations.add(GL20.glGetUniformLocation(programShaderId, uniformName));
    }

    /**@return Locations of all the uniform.*/
    public List<Integer> getUniformLocations(){
        return this.uniformLocations;
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

    /**Loads a value true or false to the shader into an uniform.
     * It is really important to set the uniform value in the shader as
     * a float because it is not possible to load boolean from the code.
     * @param location Location id of the uniform.
     * @param value Value to set to the uniform.*/
    public void loadUniformBoolean(int location, boolean value){
        float floatValue = 0;
        if(value)
            floatValue = 1;
        GL20.glUniform1f(location, floatValue);
    }

    /**Loads a float value to the shader into an uniform.
     * @param location Location id of the uniform.
     * @param value Value of the uniform.*/
    public void loadUniformFloat(int location, float value){
        GL20.glUniform1f(location, value);
    }

    /**Loads a integer value to the shader into an uniform.
     * @param location Location id of the uniform.
     * @param value Value of the uniform.*/
    public void loadUniformInt(int location, int value){
        GL20.glUniform1i(location, value);
    }

    /**Loads a vector3f (location) value to the shader into an uniform.
     * @param location Location id of the uniform.
     * @param value Value of the uniform.*/
    public void loadUniformLocation(int location, Location value){
        GL20.glUniform3f(location, value.getX(), value.getY(), value.getZ());
    }

    /**Loads a matrix 4f value to the shader into an uniform.
     * @param location Location id of the uniform.
     * @param value Value of the uniform.*/
    public void loadUniformMatrix(int location, Matrix4f value){
        value.get(matrixBuffer);
        GL20.glUniformMatrix4fv(location, false, matrixBuffer);
    }

    /**ID of the shader program.*/
    public int getProgramShaderId(){
        return this.programShaderId;
    }
}
