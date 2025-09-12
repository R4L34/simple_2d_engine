package com.r4l.simple_2d_engine.event;

import java.lang.reflect.Method;
import java.util.Objects;

public class ListenerMethod {
    private final Object target;
    private final Method method;

    public ListenerMethod(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListenerMethod)) return false;
        ListenerMethod that = (ListenerMethod) o;
        return Objects.equals(target, that.target) && Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, method);
    }

    @Override
    public String toString() {
        return "ListenerMethod{" +
               "target=" + target +
               ", method=" + method +
               '}';
    }
}
