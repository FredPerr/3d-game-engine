package engine.event;

/**
 * Created by KitK4t on 2018-12-16.
 */
public enum EventPriority {

    LOWEST(0), LOW(1), NORMAL(2), HIGH(3), HIGHEST(4);

    private int priority;

    EventPriority(int priority){
        this.priority = priority;
    }

    /**@return The priority number of the priority.*/
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
