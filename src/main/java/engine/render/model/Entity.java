package engine.render.model;

import engine.util.Location;
import engine.util.Rotation;

import java.util.Locale;

/**An entity is a dynamic object in the world.*/
public class Entity {

    private TexturedMesh model;

    private Location location;
    private Rotation rotation;
    private float scale;

    /**Creates an entity.
     * @param model Model of the entity.
     * @param location Location of the entity.
     * @param rotation Rotation of the entity.
     * @param scale Scale of the entity.*/
    public Entity(TexturedMesh model, Location location, Rotation rotation, float scale){
        this.model = model;
        this.location = location;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**Set the location of the entity.
     * @param location Location of the entity.*/
    public void setLocation(Location location){
        this.location = location;
    }

    /**Set the rotation of the entity.
     * @param rotation Rotation of the entity.*/
    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    /**Set the scale of the entity.
     * @param scale Scale of the entity. 1 being the normal size.*/
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**@return Scale of the entity.*/
    public float getScale(){
        return this.scale;
    }

    /**@return Location of the entity.*/
    public Location getLocation(){
        return this.location;
    }

    /**@return Rotation of the entity.*/
    public Rotation getRotation(){
        return this.rotation;
    }

    /**@return Model of the entity (textured mesh).*/
    public TexturedMesh getModel(){
        return this.model;
    }
}
