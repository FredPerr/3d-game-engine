package engine.util;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * This is an STB loaded image. Store image assets byte
 * buffer and other parameters of the image.
 *
 * Created by KitK4t on 2018-12-15.
 */
public class Image {

    private ByteBuffer data;
    private int width, height;

    /**Store image data.
     * @param data Byte buffer assets of the image.
     * @param width Width of the image in pixels.
     * @param height Height of the image in pixels.*/
    private Image(ByteBuffer data, int width, int height){
        this.data = data;
        this.width = width;
        this.height = height;
    }

    /**@return Byte buffer of the assets of the image.*/
    public ByteBuffer getData(){
        return this.data;
    }

    /**@return Width of the image in pixels.*/
    public int getWidth(){
        return this.width;
    }

    /**@return Height of the image in pixels.*/
    public int getHeight(){
        return this.height;
    }

    /**
     * Load a LWJGL image with STB library. Every image should be created from this method.
     * @param path Path of the image from the computer root (C:/...).
     * @return Image created with STB. If the image could not be reached,
     * it returns null after a print out message with the wrong path used.
     */
    public static Image createImage(String path) {
        ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer comp = stack.mallocInt(1),
                    w = stack.mallocInt(1),
                    h = stack.mallocInt(1);
            image = org.lwjgl.stb.STBImage.stbi_load(path, w, h, comp, 4);
            if (image == null) {
                System.err.println("Could not load the image: " + path);
                return null;
            }
            width = w.get();
            height = h.get();
        }
        return new Image(image, width, height);
    }
}
