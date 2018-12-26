package engine.util;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**The buff util class (buffer util class) provides ways to generate and to manage buffers.*/
public class BuffUtil {

    /**
     * First and empty buffer of the correct size is created. You can think of a
     * buffer as basically an array with a pointer. After putting the necessary
     * data into the buffer the pointer will have increased so that it points at
     * the first empty element of the array. This is so that we could add more
     * data to the buffer if we wanted and it wouldn't overwrite the data we've
     * already put in. However, we're done with storing data and we want to make
     * the buffer ready for reading. To do this we need to make the pointer
     * point to the start of the data, so that OpenGL knows where in the buffer
     * to start reading. The "flip()" method does just that, putting the pointer
     * back to the start of the buffer.
     *
     * @param data
     *            - The float data that is going to be stored in the buffer.
     * @return A FloatBuffer containing the data.
     */
    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * First and empty buffer of the correct size is created. You can think of a
     * buffer as basically an array with a pointer. After putting the necessary
     * data into the buffer the pointer will have increased so that it points at
     * the first empty element of the array. This is so that we could add more
     * data to the buffer if we wanted and it wouldn't overwrite the data we've
     * already put in. However, we're done with storing data and we want to make
     * the buffer ready for reading. To do this we need to make the pointer
     * point to the start of the data, so that OpenGL knows where in the buffer
     * to start reading. The "flip()" method does just that, putting the pointer
     * back to the start of the buffer.
     *
     * @param data
     *            - The int data that is going to be stored in the buffer.
     * @return A IntBuffer containing the data.
     */
    public static IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
