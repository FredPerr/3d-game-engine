package test;

import engine.ELoader;
import engine.util.EImage;
import engine.window.EWindow;
import org.lwjgl.glfw.GLFW;

/**
 * Created by KitK4t on 2018-12-15.
 */
public class TestEngine {

    /**test.TestEngine method to test the engine.*/
    public static void main(String[] args){
        System.out.println("Testing the engine...");
        EWindow window = new EWindow("Test engine", 720, 480);

        while(!window.isCloseRequest()){
            GLFW.glfwPollEvents();
            GLFW.glfwSwapBuffers(window.getHandle());
        }
        window.destroy();
        GLFW.glfwTerminate();
    }
}
