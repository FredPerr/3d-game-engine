package engine.render.model;

import engine.util.Image;
import engine.util.Resource;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

//TODO comment here
public class Texture {

    /**All the textures loaded in OpenGL.
     * The textures are in that list when they are created.
     * Unbinding does not remove it and should not.
     * They may not be deleted until the end of the program.*/
    public static List<Integer> textures = new ArrayList<>();

    /**ID given by OpenGL for this texture.*/
    private int id;

    /**Create a texture into OpenGL with parameters.
     * @param filter Filter to use for this texture.
     *               There are plenty of them: LINEAR, NEAREST,...
     *               <a href ="https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glTexParameter.xml">Filters</a>*/
    public Texture(Resource texture, int filter){
        this.id = GL11.glGenTextures();
        Image img = Image.createImage(texture.getFile().getPath());
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.id);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, filter);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, filter);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, img.getWidth(),
                img.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, img.getData());

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    /**@return ID of the texture. This ID is unique and is the bridge
     * for OpenGL.*/
    public int getId(){
        return this.id;
    }
}
