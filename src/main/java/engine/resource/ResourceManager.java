package engine.resource;

import engine.util.Image;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by KitK4t on 2018-12-16.
 * The Engine loader class provides many methods to load anything inside the engine.
 */
public class ResourceManager {

    /**Load a LWJGL image with STB.
     * @param path Path of the image from the computer root.
     * @return Image created.*/
    public Image loadSTBImage(String path) {
        ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer comp = stack.mallocInt(1);
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            image = org.lwjgl.stb.STBImage.stbi_load(path, w, h, comp, 4);
            if (image == null)
                System.err.println("Could not load the image: " + path);
            width = w.get();
            height = h.get();
        }
        return new Image(image, width, height);
    }

    /**Load a Buffered image from a path outside or inside the application.
     * First get the out and if the path is null it search for the inside file.
     * @param path Path of the file. (C:/... or src/main/...)
     * @return Buffered image.*/
    public BufferedImage loadBufferedImage(String path) {
        try {
            File f = new File(path);
            if (f.exists())
                return ImageIO.read(f);
             else
                return ImageIO.read(ResourceManager.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
