package engine.util;

/**The location class is a 3D position (x , y and z).*/
public class Location {

    public float x,y,z;

    /**Default constructor of the location class.
     * Creates a location with 0 values.*/
    public Location(){
        set(0,0,0);
    }

    /**Main constructor of the location class.
     * @param x Value of the x coordinate.
     * @param y Value of the x coordinate.
     * @param z Value of the x coordinate.*/
    public Location(float x, float y, float z){
        set(x,y,z);
    }

    /**Set the values of the location.
     * @param x Value of the x coordinate.
     * @param y Value of the x coordinate.
     * @param z Value of the x coordinate.*/
    public void set(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**Set the x value of the location.
     * @param x Value of the x coordinate.*/
    public void setX(float x){
        this.x = x;
    }

    /**Set the y value of the location.
     * @param y Value of the x coordinate.*/
    public void setY(float y){
        this.y = y;
    }

    /**Set the z value of the location.
     * @param z Value of the x coordinate.*/
    public void setZ(float z){
        this.z = z;
    }

    /**Add the x value of the location.
     * @param x Value of the x coordinate to add.*/
    public void addX(float x){
        this.x += x;
    }

    /**Add the y value of the location.
     * @param y Value of the x coordinate to add.*/
    public void addY(float y){
        this.y += y;
    }

    /**Add the z value of the location.
     * @param z Value of the x coordinate to add.*/
    public void addZ(float z){
        this.z += z;
    }

    /**Add the values for the 2 axis.
     * @param x Amount to add in X.
     * @param y Amount to add in Y.
     * @param z Amount to add in Z.*/
    public void add(float x, float y, float z){
        addX(x);
        addY(y);
        addZ(z);
    }

    /**@return X value of the location.*/
    public float getX(){
        return this.x;
    }

    /**@return Y value of the location.*/
    public float getY(){
        return this.y;
    }

    /**@return Z value of the location.*/
    public float getZ(){
        return this.z;
    }

    /**Get the distance between 2 3D locations.
     * @param location Location to get the distance from.
     * @return Distance between the two locations.*/
    public double distance(Location location){
        return Math.sqrt((location.getX()-getX())*(location.getX()-getX())+
                (location.getY()-getY())*(location.getY()-getY())+
                (location.getZ()-getZ())*(location.getZ()-getZ()));
    }
}
