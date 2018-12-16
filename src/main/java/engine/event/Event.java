package engine.event;

import java.util.ArrayList;
/**
 * Created by KitK4t on 2018-12-16.
 *
 * The engine event class is a class to create event and do everything with it.
 */
public class Event {

    /**List of all the EEvents.*/
    private static ArrayList<Event> events = new ArrayList<>();

    protected Event(){
        if(!exists() && !this.getClass().getName().equals(Event.class.getName()))
            events.add(this);
    }

    /**@return True if the event already exits.*/
    private boolean exists(){
        for(Event e : events)
            if(e.getClass().getName().equals(this.getClass().getName()))
                return true;
        return false;
    }
}

