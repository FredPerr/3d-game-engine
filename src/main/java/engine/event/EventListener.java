package engine.event;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * The engine event listener interface is an indicator of a class with listener methods.
 * You have to implement this interface into listener event class.
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
