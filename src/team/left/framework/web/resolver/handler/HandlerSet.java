package team.left.framework.web.resolver.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerSet {
    private final Object handlerInstance;
    private final Method handlerMethod;
    
    public HandlerSet(Object handlerInstance, Method handlerMethod) {
        this.handlerInstance = handlerInstance;
        this.handlerMethod = handlerMethod;
        this.handlerMethod.setAccessible(true);
    }

    public Object getHandlerInstance() {
        return handlerInstance;
    }

    public Method getHandlerMethod() {
        return handlerMethod;
    }
    
    public Object handle(Object[] arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return this.handlerMethod.invoke(this.handlerInstance, arguments);
    }

    @Override
    public String toString() {
        return "HandlerSet [handlerInstance=" + handlerInstance + ", handlerMethod=" + handlerMethod + "]";
    }
}
