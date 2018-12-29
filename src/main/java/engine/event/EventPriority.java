package engine.event;

/**
 * The event priority enum class provides a ranking for the
 * order of the execution of the events when they are called.
 * It is the highest to the lowest order.
 *
 * Created by KitK4t on 2018-12-16.
 */
public enum EventPriority {

    LOWEST(0), LOW(1), NORMAL(2), HIGH(3), HIGHEST(4);

    private int priority;

    EventPriority(int priority){
        this.priority = priority;
    }

    /**@return The priority number of the priority.
     * The higher it is, the more important the priority is.*/
    public int getPriority(){
        return this.priority;
    }

    /**@return A priority by its number.
     * @param number Number of the priority.*/
    public static EventPriority getPriority(int number){
        for(EventPriority p : EventPriority.values())
            if(p.getPriority() == number)
                return p;
        return EventPriority.NORMAL;
    }
}
