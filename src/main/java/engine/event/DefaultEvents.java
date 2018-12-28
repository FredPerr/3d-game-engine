package engine.event;

import engine.event.event.render.EventFovChange;
import engine.event.event.render.EventRenderDistanceFar;
import engine.event.event.render.EventRenderDistanceNear;

/**
 * Created by KitK4t on 2018-12-27.
 *
 * The default events class stores all the events of the engine ny default (not window events).
 */
public class DefaultEvents {

    public static EventFovChange fovChange = new EventFovChange();
    public static EventRenderDistanceFar renderDistanceFar = new EventRenderDistanceFar();
    public static EventRenderDistanceNear renderDistanceNear = new EventRenderDistanceNear();
}
