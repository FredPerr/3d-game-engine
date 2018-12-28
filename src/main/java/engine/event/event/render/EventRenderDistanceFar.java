package engine.event.event.render;

import engine.event.Event;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * This event is called when the distance in far plan of a camera changes.
 */
public class EventRenderDistanceFar extends Event {

    private float oldDistance, newDistance;

    public void setValues(float oldDistance, float newDistance){
        this.newDistance = newDistance;
        this.oldDistance = oldDistance;
    }

    /**@return Old distance of the render in far plan.*/
    public float getOldDistance(){
        return this.oldDistance;
    }

    /**@return New distance of the render in far plan.*/
    public float getNewDistance(){
        return this.newDistance;
    }
}
