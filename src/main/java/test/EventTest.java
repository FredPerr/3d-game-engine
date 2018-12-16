package test;

import engine.event.Event;
/**
 * Created by KitK4t on 2018-12-16.
 */
public class EventTest extends Event {

    private String reason;

    public EventTest(){
        this.reason = "Default reason";
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public String getReason(){
        return this.reason;
    }
}
