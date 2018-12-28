package engine.event;

import java.util.ArrayList;
/**
 * The <strong>Event</strong> abstract class is a part of
 * the event system. It stores all the created event which
 * can be created by creating a class extended by the
 * {@link engine.event.Event} class and be instantiated
 * before the loop of the engine starts. The event needs
 * to be in a class otherwise, it will not be stored.
 *
 * Created by KitK4t on 2018-12-16.
 */
public abstract class Event {

    /**List of all the valid events. This variable is
     * private-access because it should not be edited
     * outside this class.*/
    private static ArrayList<Event> events = new ArrayList<>();

    /**Creates an event. This constructor is
     * protected-access because it should be access by
     * extending a class. This constructor check if the
     * class in which the event is is not the
     * {@link engine.event.Event} class.*/
    protected Event(){
        if(!exists() && !this.getClass().getName().equals(Event.class.getName()))
            events.add(this);
    }

    /**Check for all the events stored and try to find
     * this event into it by its class name.
     * @return True if the event is stored (if it exists).
     * Otherwise, it returns false.*/
    private boolean exists(){
        for(Event e : events)
            if(e.getClass().getName().equals(this.getClass().getName()))
                return true;
        return false;
    }
}

