package engine.render.model;

/**A model is a mesh with a texture bind to it.*/
public class TexturedMesh {

    private Mesh mesh;
    private Texture texture;

    /**Create a textured mesh.
     * @param mesh Mesh of the textured mesh.
     * @param texture Texture of the textured mesh.
     *                Set null if the texture is not applied for this model (If the texture coordinates are already null).*/
    public TexturedMesh(Mesh mesh, Texture texture){
        this.mesh = mesh;
        if(!mesh.isUsingTexture() || texture == null)
            this.texture = null;
        else
            this.texture = texture;
    }

    /**@return The mesh of the textured mesh.*/
    public Mesh getMesh(){
        return this.mesh;
    }

    /**@return The texture of the textured mesh.
     * If the texture rendering for this item is unable, it returns null.*/
    public Texture getTexture(){
        return this.texture;
    }
}
