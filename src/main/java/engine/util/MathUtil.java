package engine.util;

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
}
