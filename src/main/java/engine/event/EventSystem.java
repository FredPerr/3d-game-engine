package engine.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KitK4t on 2018-12-16.
 *
 * The event system class manages the listeners and the calling method to call events.
 */
public class EventSystem {

    private static Map<EventListener, Map<Method, EventHandler>> listenerMethods = new HashMap<>();

    /**Add a listener in the list if it is not already inside.
     * @param listener Listener to add to the list from the list.*/
    public static void addListener(EventListener listener){
        if(!containsListener(listener)){
            Map<Method, EventHandler> methods = new HashMap<>();
            for(Method m : listener.getClass().getDeclaredMethods())
                if(isEventHandlerMethod(m))
                    methods.put(m,getEventHandler(m));
            listenerMethods.put(listener, methods);
        }
    }

    /**Remove a listener from the list if it is inside it.
     * @param listener Listener to remove.*/
    public static void removeListener(EventListener listener){
        if(containsListener(listener))
            listenerMethods.remove(listener);
    }

    /**Search in the listener list to find if a listener is inside it.
     * @param listener Listener to search for.*/
    private static boolean containsListener(EventListener listener){
        return listenerMethods.keySet().contains(listener);
    }

    /**Get the event handler of a method.
     * @param m Method to get the annotation from.
     * @return Event handler annotation. If there is not, it returns null.*/
    private static EventHandler getEventHandler(Method m){
        for(Annotation a : m.getAnnotations())
            if(a.annotationType().getName().equals(EventHandler.class.getName()))
                return (EventHandler) a;
        return null;
    }

    /**@param m Method to return the value from.
     * @return True if the method can be used as an event handler. The method needs to have the EventHandler annotation, to have only one parameter with the type superclass Event, to be not static and be public.*/
    private static boolean isEventHandlerMethod(Method m){
        if(getEventHandler(m) != null
                && !Modifier.isStatic(m.getModifiers())
                && Modifier.isPublic(m.getModifiers())
                && m.getParameterTypes().length == 1
                && m.getParameterTypes()[0].getSuperclass().getName().equals(Event.class.getName()))
            return true;
        return false;
    }

    /**Call all the listeners of an event.
     * @param event Event to call.
     */
    public static void callEvent(Event event){
        for(int i = EventPriority.values().length-1; i > 0; i--)
            for(EventListener l : listenerMethods.keySet())
                for (Method m : listenerMethods.get(l).keySet())
                    if (getEventOfListenerMethod(m).getName().equals(event.getClass().getName())
                            && getEventHandler(m).priority() == EventPriority.getPriority(i))
                        try {
                            m.invoke(l, event);
                        } catch (InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
    }

    /**Get the event class of the method event. If the method is not an event method listener, it returns null.
     * @param m Method listener to get the event type from.
     * @return Get the event type of the method from. If the method is not a listener method, it returns null.*/
    private static Class<? extends Event> getEventOfListenerMethod(Method m){
        if(isEventHandlerMethod(m) && m.getParameterTypes()[0].getSuperclass().getName().equals(Event.class.getName()))
            return ((Class<? extends Event>) m.getParameterTypes()[0]);
        return null;
    }
}
