package engine.event.event.render;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * This event is called when the distance in near plan of a camera changes.
 */
public class EventRenderDistanceNear extends Event {

    private float oldDistance, newDistance;

    public void setValues(float oldDistance, float newDistance){
        this.newDistance = newDistance;
        this.oldDistance = oldDistance;
    }

    /**@return Old distance of the render in near plan.*/
    public float getOldDistance(){
        return this.oldDistance;
    }

    /**@return New distance of the render in near plan.*/
    public float getNewDistance(){
        return this.newDistance;
    }
}