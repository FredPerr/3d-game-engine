package engine.util;

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

    private static boolean printed = false;

    /**Creates a tranformation 4x4 matrix.
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
}
