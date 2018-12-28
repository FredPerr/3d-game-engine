package test;

import engine.Engine;
import engine.event.EventHandler;
import engine.event.EventListener;
import engine.event.EventSystem;
import engine.event.event.window.EventKey;
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
                1,0
        };

        Mesh mesh = new Mesh(new float[]{
                -0.5f, 0.5f, 0f,//v0
                -0.5f, -0.5f, 0f,//v1
                0.5f, -0.5f, 0f,//v2
                0.5f, 0.5f, 0f,//v3
        }, new int[]{
                0,1,3,//top left triangle (v0, v1, v3)
                3,1,2//bottom right triangle (v3, v1, v2)
        }, textureCoordinates
                , GL11.GL_TRIANGLES);

        TexturedMesh model = new TexturedMesh(
                mesh,
                new Texture(new Resource(ResourceManager.getApplicationFolderPath()+"/assets/textures/test.png"),
                GL11.GL_NEAREST));

        entity = new Entity(model, new Location(0,0,-4), new Rotation(), 1f);
        getDefaultRenderer().addEntity(entity);
    }

    public void end() {}
    public void update() {}

    @EventHandler
    public void test(EventKey e){
        if(e.getKey() == GLFW.GLFW_KEY_SPACE && e.getAction() == GLFW.GLFW_RELEASE)
            entity.getModel().getMesh().setUseTexture(!entity.getModel().getMesh().isUsingTexture());

        if(e.getKey() == GLFW.GLFW_KEY_W && e.getAction() != GLFW.GLFW_RELEASE)
            entity.getLocation().addZ(-0.2f);


        if(e.getKey() == GLFW.GLFW_KEY_S && e.getAction() != GLFW.GLFW_RELEASE)
            entity.getLocation().addZ(0.2f);

        if(e.getKey() == GLFW.GLFW_KEY_A && e.getAction() != GLFW.GLFW_RELEASE)
            entity.getLocation().addX(-0.2f);


        if(e.getKey() == GLFW.GLFW_KEY_D && e.getAction() != GLFW.GLFW_RELEASE)
            entity.getLocation().addX(0.2f);

    }

    public static void main(String[] args){
        TestEngine engine = new TestEngine("Test engine", 720, 480, 60,30);
        EventSystem.addListener(new ListenerTest());
        EventSystem.addListener(engine);
        engine.start();
    }
}
