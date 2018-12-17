package engine;

import engine.util.EImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * The Engine loader class provides many methods to load anything inside the engine.
 */
public class Loader {

    /**Load a LWJGL image with STB.
     * @param path Path from computer root (c:/...)
     * @return Image created.*/
    public EImage loadImage(String path){
        ByteBuffer image;
        int width, height;
        try(MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer comp = stack.mallocInt(1);
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            image = org.lwjgl.stb.STBImage.stbi_load(path, w, h, comp, 4);
            if(image == null)
                System.err.println("Could not load " + path);
            width = w.get();
            height = h.get();
        }
        return new EImage(image, width, height);
    }
}
