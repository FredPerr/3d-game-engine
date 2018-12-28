package test;

import engine.Engine;
import engine.event.EventHandler;
import engine.event.EventListener;
import engine.event.EventSystem;
import engine.event.event.window.EventKey;
import engine.render.Camera;
import engine.render.model.Entity;
import engine.render.model.Mesh;
import engine.render.model.Texture;
import engine.render.model.TexturedMesh;
import engine.util.Location;
import engine.util.Resource;
import engine.util.ResourceManager;
import engine.util.Rotation;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

/**
 * Created by KitK4t on 2018-12-15.
 */
public class TestEngine extends Engine implements EventListener {

    Entity entity;

    public TestEngine(String title, int width, int height, int maxFps, int maxUps) {
        super(title, width, height, maxFps, maxUps);
    }

    public void init() {
        getWindow().setClearColor(0.2f,1,0.2f);

        float[] textureCoordinates = new float[]{
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0
        };

        Mesh mesh = new Mesh(new float[]{
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f
        }, new int[]{
                0,1,3,
                3,1,2,
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22
        }, textureCoordinates
                , GL11.GL_TRIANGLES);

        TexturedMesh model = new TexturedMesh(
                mesh,
                new Texture(new Resource(ResourceManager.getApplicationFolderPath()+"/assets/textures/LWJGLEngine.png"),
                GL11.GL_NEAREST));

        entity = new Entity(model, new Location(0,0,-4), new Rotation(), 1f);
        getDefaultRenderer().addEntity(entity);
    }

    public void end() {}
    public void update() {}

    @EventHandler
    public void onKeyTest(EventKey e){

        if(!getWindow().isMouseGrabbed()) return;

        if(e.getKey() == GLFW.GLFW_KEY_W && e.getAction() != GLFW.GLFW_RELEASE)
            getCamera().goForward(0.2f, false);


        if(e.getKey() == GLFW.GLFW_KEY_S && e.getAction() != GLFW.GLFW_RELEASE)
            getCamera().goBackward(0.2f, false);

        if(e.getKey() == GLFW.GLFW_KEY_A && e.getAction() != GLFW.GLFW_RELEASE)
            getCamera().goLeftward(0.2f);


        if(e.getKey() == GLFW.GLFW_KEY_D && e.getAction() != GLFW.GLFW_RELEASE)
            getCamera().goRightward(0.2f);

        if(e.getKey() == GLFW.GLFW_KEY_LEFT_SHIFT && e.getAction() != GLFW.GLFW_RELEASE)
            getCamera().goDownward(0.2f);

        if(e.getKey() == GLFW.GLFW_KEY_SPACE && e.getAction() != GLFW.GLFW_RELEASE)
            getCamera().goUpward(0.2f);
    }

    public static void main(String[] args){
        TestEngine engine = new TestEngine("Test engine", 720, 480, 60,30);
        EventSystem.addListener(new ListenerTest(engine));
        EventSystem.addListener(engine);
        engine.start();
    }
}
