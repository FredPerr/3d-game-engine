package test;

import engine.Engine;
import engine.event.EventSystem;
import engine.render.model.Mesh;
import org.lwjgl.opengl.GL11;

/**
 * Created by KitK4t on 2018-12-15.
 */
public class TestEngine extends Engine{

    private Mesh mesh;

    public TestEngine(String title, int width, int height, int maxFps, int maxUps) {
        super(title, width, height, maxFps, maxUps);
    }

    public void init() {
        getWindow().setClearColor(0.2f,1,0.2f);
        this.mesh = new Mesh(new float[]{
                // Left bottom triangle
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                // Right top triangle
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        });
    }

    public void update() {

    }

    public void render() {
        mesh.render(GL11.GL_TRIANGLES);
    }

    public void end() {

    }

    public static void main(String[] args){
        TestEngine engine = new TestEngine("Test engine", 720, 480, 60,30);
        EventSystem.addListener(new ListenerTest());
        engine.start();
    }
}
