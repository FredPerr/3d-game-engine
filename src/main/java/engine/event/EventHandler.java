package engine.event;

import java.lang.annotation.*;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * The event handler annotation is an annotation for listener methods.
 * It provides a priority system.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

    EventPriority priority() default EventPriority.NORMAL;
}
