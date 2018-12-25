package test;

import engine.Engine;
import engine.event.EventSystem;

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

    public static void main(String[] args){
        TestEngine engine = new TestEngine("Test engine", 720, 480, 60,30);
        EventSystem.addListener(new ListenerTest());
        engine.start();
    }
}
