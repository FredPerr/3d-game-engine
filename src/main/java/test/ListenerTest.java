package test;

import engine.event.EventHandler;
import engine.event.EventListener;
import engine.event.EventPriority;

/**
 * Created by KitK4t on 2018-12-16.
 */
public class ListenerTest implements EventListener {

    @EventHandler
    public void onTestEvent(EventTest test){
        System.out.println(test.getReason());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTestEvent2(EventTest test){
        System.out.println(test.getReason() + " priority");
    }
}
