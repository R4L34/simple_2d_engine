package com.r4l.simple_2d_engine.event;

import java.lang.reflect.Method;
import java.util.*;

public class EventBus {

    private final Map<Class<?>, List<ListenerMethod>> listeners = new HashMap<>();

    //Registring events
    public void register(Object listener) {
        Class<?> clazz = listener.getClass();
        if (!clazz.isAnnotationPresent(EventHandler.class)) {
            System.err.println("Cannot register: " + clazz.getName() + " missing @EventHandler");
            return;
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubscribeEvent.class)) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length != 1) {
                    System.err.println("Invalid method: " + method.getName() + " in " + clazz.getName() +
                                       " must have exactly one parameter");
                    continue;
                }

                Class<?> eventType = params[0];
                listeners.computeIfAbsent(eventType, k -> new ArrayList<>())
                         .add(new ListenerMethod(listener, method));
            }
        }
    }
    
    //Call Events
    public void post(Object event) {
        Class<?> eventType = event.getClass();
        List<ListenerMethod> methods = listeners.get(eventType);
        if (methods != null) {
            for (ListenerMethod lm : methods) {
                try {
                    lm.getMethod().invoke(lm.getTarget(), event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
