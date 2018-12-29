package engine.event;

/**
 * The event listener interface is an indicator of a class with listener methods.
 * You have to implement a class with this interface to make is "listen" events.
 *
 * Created by KitK4t on 2018-12-16.
 */
public interface EventListener {

    /*
    Example of the usage of a listener:

    public class TestListener implement Listener{

        @EventHandler(priority = EventPriority.LOWEST)
        public void onTestEvent(TestEvent event){
            System.out.println(e.getMessage());
        }
    }

    */
}
