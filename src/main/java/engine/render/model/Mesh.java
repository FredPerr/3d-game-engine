package engine.render.model;

import engine.util.BuffUtil;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading of geometry data into VAOs. It also keeps track of all
 * the created VAOs and VBOs so that they can all be deleted when the game
 * closes.
 */
public class Mesh {

    /**All the vertex array objects.*/
    public static List<Integer> VAOs = new ArrayList<>();
    /**All the vertex buffer objects.*/
    public static List<Integer> VBOs = new ArrayList<>();

    private int vaoId, vertexAmount;

    /**
     * Creates a VAO and stores the position data of the vertices into attribute
     * 0 of the VAO.
     *
     * @param vertices The 3D positions of each vertex in the geometry. X,Y,Z coordinate system.
     * @param indices The order to use the vertices and to connect them.
     */
    public Mesh(float[] vertices, int[] indices) {
        this.vaoId = createVAO();
        this.vertexAmount = indices.length;
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, vertices);
        unbindVAO();
    }

    /**
     * Creates a new VAO and returns its ID. A VAO holds geometry data that we
     * can render and is physically stored in memory on the GPU, so that it can
     * be accessed very quickly during rendering.
     *
     * Like most objects in OpenGL, the new VAO is created using a "gen" method
     * which returns the ID of the new VAO. In order to use the VAO it needs to
     * be made the active VAO. Only one VAO can be active at a time. To make
     * this VAO the active VAO (so that we can store stuff in it) we have to
     * bind it.
     *
     * @return The ID of the newly created VAO.
     */
    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        VAOs.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    /**
     * Unbinds the VAO after we're finished using it. If we want to edit or use
     * the VAO we would have to bind it again first.
     */
    public void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    /**Binds the VAO to OpenGL and it is ready to be render.
     * Should be unbind after rendering.*/
    public void bindVAO(){
        GL30.glBindVertexArray(getVaoId());
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
     *
     * @param renderMode
     *            - The mode to render the mesh (GL11.xx) -> TRIANGLES, POINTS, LINES, ... See the link for more details:
     *            <a href="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glBegin.xml">Begin modes<a/>
     */
    public void render(int renderMode){
        bindVAO();
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(renderMode, getVertexAmount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        unbindVAO();
    }

    /**
     * @return The ID of the VAO which contains the data about all the geometry
     *         of this model.
     */
    public int getVaoId() {
        return this.vaoId;
    }

    /**
     * @return The number of vertices in the mesh / 3D locations.
     */
    public int getVertexAmount() {
        return this.vertexAmount;
    }

    /**
     * Deletes all the VAOs and VBOs when the game is closed. VAOs and VBOs are
     * located in video memory.
     */
    public static void cleanUp() {
        for (int vao : VAOs)
            GL30.glDeleteVertexArrays(vao);
        for (int vbo : VBOs)
            GL15.glDeleteBuffers(vbo);

    }

    /**
     * Stores the position data of the vertices into attribute 0 of the VAO. To
     * do this the positions must first be stored in a VBO. You can simply think
     * of a VBO as an array of data that is stored in memory on the GPU for easy
     * access during rendering.
     *
     * Just like with the VAO, we create a new VBO using a "gen" method, and
     * make it the active VBO (so that we do stuff to it) by binding it.
     *
     * We then store the positions data in the active VBO by using the
     * glBufferData method. We also indicate using GL_STATIC_DRAW that this data
     * won't need to be changed. If we wanted to edit the positions every frame
     * (perhaps to animate the quad) then we would use GL_DYNAMIC_DRAW instead.
     *
     * We the connect the VBO to the VAO using the glVertexAttribPointer()
     * method. This needs to know the attribute number of the VAO where we want
     * to put the data, the number of floats used for each vertex (3 floats in
     * this case, because each vertex has a 3D position, an x, y, and z value),
     * the type of data (in this case we used floats) and then some other more
     * complicated stuff for storing the data in more fancy ways. Don't worry
     * about the last 3 parameters for now, we don't need them here.
     *
     * Now that we've finished using the VBO we can unbind it. This isn't
     * totally necessary, but I think it's good practice to unbind the VBO when
     * you're done using it.
     *
     * @param attributeNumber
     *            - The number of the attribute of the VAO where the data is to
     *            be stored.
     * @param data
     *            - The geometry data to be stored in the VAO, in this case the
     *            positions of the vertices.
     */
    private static void storeDataInAttributeList(int attributeNumber, float[] data) {
        int vboID = GL15.glGenBuffers();
        VBOs.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = BuffUtil.storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * Creates an index buffer, binds the index buffer to the currently active
     * VAO, and then fills it with our indices.
     *
     * The index buffer is different from other data that we might store in the
     * attributes of the VAO. When we stored the positions we were storing data
     * about each vertex. The positions were "attributes" of each vertex. Data
     * like that is stored in an attribute list of the VAO.
     *
     * The index buffer however does not contain data about each vertex. Instead
     * it tells OpenGL how the vertices should be connected. Each VAO can only
     * have one index buffer associated with it. This is why we don't store the
     * index buffer in a certain attribute of the VAO; each VAO has one special
     * "slot" for an index buffer and simply binding the index buffer binds it
     * to the currently active VAO. When the VAO is rendered it will use the
     * index buffer that is bound to it.
     *
     * This is also why we don't unbind the index buffer, as that would unbind
     * it from the VAO.
     *
     * Note that we tell OpenGL that this is an index buffer by using
     * "GL_ELEMENT_ARRAY_BUFFER" instead of "GL_ARRAY_BUFFER". This is how
     * OpenGL knows to bind it as the index buffer for the current VAO.
     *
     * @param indices
     */
    private void bindIndicesBuffer(int[] indices) {
        int vboId = GL15.glGenBuffers();
        VBOs.add(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
        IntBuffer buffer = BuffUtil.storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }
}
