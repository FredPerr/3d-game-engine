package test;

import engine.Engine;
import engine.event.EventSystem;
import engine.render.model.Mesh;
import org.lwjgl.opengl.GL11;

/**
 * Created by KitK4t on 2018-12-15.
 */
public class TestEngine extends Engine{

    public TestEngine(String title, int width, int height, int maxFps, int maxUps) {
        super(title, width, height, maxFps, maxUps);
    }

    public void init() {
        getWindow().setClearColor(0.2f,1,0.2f);
        Mesh mesh = new Mesh(new float[]{
                -0.5f, 0.5f, 0f,//v0
                -0.5f, -0.5f, 0f,//v1
                0.5f, -0.5f, 0f,//v2
                0.5f, 0.5f, 0f,//v3
        }, new int[]{
                0,1,3,//top left triangle (v0, v1, v3)
                3,1,2//bottom right triangle (v3, v1, v2)
        }, GL11.GL_TRIANGLES);
        getDefaultRenderer().addEntity(mesh);
    }

    public void end() {}
    public void update() {}

    public static void main(String[] args){
        TestEngine engine = new TestEngine("Test engine", 720, 480, 60,30);
        EventSystem.addListener(new ListenerTest());
        engine.start();
    }
}
