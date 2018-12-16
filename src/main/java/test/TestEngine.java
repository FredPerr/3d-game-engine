package test;

import engine.event.Event;
import engine.event.EventSystem;
import engine.window.EWindow;
import org.lwjgl.glfw.GLFW;

/**
 * Created by KitK4t on 2018-12-15.
 */
public class TestEngine {

    /**test.TestEngine method to test the engine.*/
    public static void main(String[] args){

        System.out.println("Testing the engine...");

        //Creating the window.
        EWindow window = new EWindow("Test engine", 720, 480);

        //Add a listener to test the events.
        ListenerTest listenerTest = new ListenerTest(window);
        EventSystem.addListener(listenerTest);

        //Starting a basic loop.
        while(!window.isCloseRequest()){
            GLFW.glfwPollEvents();
            GLFW.glfwSwapBuffers(window.getHandle());
        }

        //Ending the engine.
        window.destroy();
        GLFW.glfwTerminate();
        System.exit(0);

        //TODO test
    }
}
