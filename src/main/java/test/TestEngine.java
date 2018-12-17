package test;

import engine.Engine;
import engine.event.EventSystem;
import engine.loop.Loop;
import engine.window.Window;
import org.lwjgl.glfw.GLFW;

/**
 * Created by KitK4t on 2018-12-15.
 */
public class TestEngine extends Engine{

    public TestEngine(String title, int width, int height, int maxFps, int maxUps) {
        super(title, width, height, maxFps, maxUps);
    }

    public void init() {

    }

    public void update() {

    }

    public void render() {

    }

    public void end() {

    }

    /**Main method to test the engine.*/
    public static void main(String[] args){
        //Create the engine.
        TestEngine engine = new TestEngine("Test engine", 720, 480, 60,30);

        //Add a listener to test the events.
        ListenerTest listenerTest = new ListenerTest(engine.getWindow());
        EventSystem.addListener(listenerTest);

    }
}
