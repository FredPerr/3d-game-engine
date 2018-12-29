package engine.event;

import java.lang.annotation.*;

/**
 * The event handler annotation is an annotation for listener methods.
 * This annotation goes on the methods that listen an event.
 * It provides a priority system from the
 * {@link engine.event.EventPriority} class.
 *
 * Created by KitK4t on 2018-12-16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

    /**Priority of the execution of the event.
     * Highest to low the order of the execution.*/
    EventPriority priority() default EventPriority.NORMAL;
}
