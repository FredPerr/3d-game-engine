package engine.util;

import org.lwjgl.system.MemoryStack;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by KitK4t on 2018-12-16.
 * The Engine resource manager class provides many methods to load anything inside the engine.
 * Also helps to manager files.
 */
public class ResourceManager {

    /**
     * This method is static and can be access from everywhere in the code (fully independent).
     * However, this method have to be called from a runnable jar compiled. Errors or mistakes could occur.
     *
     * @return File of the runnable jar application.
     * If the a problem is encountered, it returns null.
     */
    public static File getApplicationFile() {
        try {
            return new File(ResourceManager.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**Loads the content of a file as a text. It can be used to load the content of a resource.
     * @param file Path of the file to load the content from.
     * @return Content of the resource file.*/
    public static String getFileSource(String file){
        StringBuilder source = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null)
                source.append(line).append("\n");//TODO might need // before \n
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return source.toString();
    }

    /**
     * This method is static and can be access from everywhere in the code (fully independent).
     *
     * @return Path of the runnable application`s folder.
     * This method simply returns the parent of the jar runnable file.
     */
    public static String getApplicationFolderPath() {
        return getApplicationFile().getParent();
    }

    /**
     * Load a LWJGL image with STB library. Every image should be created from this method.
     * @param path Path of the image from the computer root (C:/...).
     * @return Image created with STB. If the image could not be reached,
     * it returns null after a print out message with the wrong path used.
     */
    public Image loadImage(String path) {
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
        return Image.createImage(image, width, height);
    }
}
