package engine.util;

import java.nio.ByteBuffer;

/**
 * Created by KitK4t on 2018-12-15.
 * This is an STB loaded image.
 * Store image data byte buffer and other parameters of the image.
 */
public class EImage {

    private ByteBuffer data;
    private int width, height;

    /**Store image data.
     * @param data Byte buffer data of the image.
     * @param width Width of the image in pixels.
     * @param height Height of the image in pixels.*/
    public EImage(ByteBuffer data, int width, int height){
        this.data = data;
        this.width = width;
        this.height = height;
    }

    /**@return Byte buffer of the data of the image.*/
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
}
