package engine.util;

/**
 * The rotation class is a 3D vector.
 * It posses a pitch, a yaw and a roll
 * which are the 3 possible angle movements.
 *
 * Created by KitK4t on 2018-12-16.
 * */
public class Rotation extends Location {

    /**@return Pitch angle in degrees. The pitch is the rotation in the direction of down to up.*/
    public float getPitch(){
        return getZ();
    }

    /**@return Roll angle in degrees. The roll is the rotation in the direction of side to side on yourself.*/
    public float getRoll(){
        return getX();
    }

    /**@return Yaw angle in degrees. The yaw is the rotation in the direction of left to right.*/
    public float getYaw(){
        return getY();
    }

    /**@return Pitch angle in degrees. The pitch is the rotation in the direction of down to up. Z axis.*/
    public void setPitch(float value){
        this.z =  value;
    }

    /**@return Roll angle in degrees. The roll is the rotation in the direction of side to side on yourself. X axis.*/
    public void setRoll(float value){
        this.x = value;
    }

    /**@return Yaw angle in degrees. The yaw is the rotation in the direction of left to right. Y axis.*/
    public void setYaw(float value){
        this.y = value;
    }
}
