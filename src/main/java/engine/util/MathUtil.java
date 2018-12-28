package engine.util;

import engine.render.Camera;
import engine.render.DefaultRenderer;
import org.joml.Matrix4f;

import java.util.Random;

/**The math util class provides functions to do math operations.*/
public class MathUtil {

    private static Random random = new Random();

    /**@return Random boolean.*/
    public static boolean randomBoolean(){
        return random.nextBoolean();
    }

    /**@return Random float between 0 and 1.*/
    public static float randomFloat(){
        return random.nextFloat();
    }

    /**@return Random float between 0 and 1.
     * @param max Max inclusive.
     * @param min Min inclusive.*/
    public static float randomInt(int min, int max){
        int v = random.nextInt();
        while(v < min || v > max)
            v = random.nextInt();
        return v;
    }

    /**Creates a transformation 4x4 matrix.
     * @param movement Movement of the matrix.
     * @param rotation Rotation of the matrix.
     * @param scale Scale of the matrix.*/
    public static Matrix4f createTransformationMatrix(Location movement, Rotation rotation, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.identity()
        .translate(movement.getX(), movement.getY(), movement.getZ(), matrix)
        .rotate((float)Math.toRadians(rotation.getX()), 1,0,0)
        .rotate((float)Math.toRadians(rotation.getY()), 0,1,0)
        .rotate((float)Math.toRadians(rotation.getZ()), 0,0,1)
        .scale(scale);
        return matrix;
    }

    /**Set a projection matrix 4x4.
     * @param camera Camera to create the matrix from.
     * @param windowHeight Current height of the window in pixels.
     * @param windowWidth Current width of the window in pixels.*/
    public static Matrix4f setProjectionMatrix(int windowWidth, int windowHeight, Camera camera){
        float aspectRatio = (float) windowWidth/(float) windowHeight;
        float yScale = (float) (1f / Math.tan((float)Math.toRadians(camera.getFov()/2f))) * aspectRatio;
        float xScale = yScale / aspectRatio;
        float frustumLength = camera.getRenderDistanceFar()-camera.getRenderDistanceNear();

        return new Matrix4f()
        .m00(xScale)
        .m11(yScale)
        .m22(-((camera.getRenderDistanceFar() + camera.getRenderDistanceNear()) / frustumLength))
        .m23(-1)
        .m32(-((2 * camera.getRenderDistanceNear() * camera.getRenderDistanceFar()) / frustumLength))
        .m33(0);
    }
}
